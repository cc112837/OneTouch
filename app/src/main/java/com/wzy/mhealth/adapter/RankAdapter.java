package com.wzy.mhealth.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.AllStepRank;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：等级排名适配
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/18 11:34
 * 修改人：Administrator
 * 修改时间：2016/9/18 11:34
 * 修改备注：
 */
public class RankAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<AllStepRank.DataEntity> list;
    private Handler handler;

    public RankAdapter(Context context, List<AllStepRank.DataEntity> list, Handler handler) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.handler = handler;
    }

    public void setList(List<AllStepRank.DataEntity> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return MyUtils.isEmpty(list) ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_tank, null);
            viewHolder.tv_rank = (TextView) convertView.findViewById(R.id.tv_rank);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_dastep = (TextView) convertView.findViewById(R.id.tv_dastep);
            viewHolder.tv_count = (TextView) convertView.findViewById(R.id.tv_count);
            viewHolder.cb_prasid = (CheckBox) convertView.findViewById(R.id.cb_prasid);
            viewHolder.iv_tank = (ImageView) convertView.findViewById(R.id.iv_tank);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText("" + list.get(position).getUserName());
        viewHolder.tv_rank.setText(position + 1 + "");
        viewHolder.tv_dastep.setText("" + list.get(position).getStepNum());
        viewHolder.tv_count.setText("" + list.get(position).getLikeNum());
        viewHolder.cb_prasid.setChecked(list.get(position).isLike());
        ImageLoader.getInstance().displayImage(list.get(position).getImage(), viewHolder.iv_tank, PhotoUtils.avatarImageOption);
        int value = list.get(position).getLikeNum();
        final List<Integer> flag = new ArrayList<>();
        flag.add(value);
        viewHolder.cb_prasid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    int c = 1 + flag.get(0);
                    flag.clear();
                    flag.add(c);
                } else {
                    int c = flag.get(0) - 1;
                    flag.clear();
                    flag.add(c);
                }
                viewHolder.tv_count.setText(flag.get(0) + "");
                String url = Constants.SERVER_URL + "LikeNumServlet";
                TiUser user = new TiUser();
                user.setName(LeanchatUser.getCurrentUser().getUsername() + "");
                user.setCardId(list.get(position).getStepNumId() + "");
                MyHttpUtils.handData(handler, 268, url, user);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        public TextView tv_rank;
        public TextView tv_name;
        public TextView tv_dastep;
        public TextView tv_count;
        public CheckBox cb_prasid;
        public ImageView iv_tank;
    }
}
