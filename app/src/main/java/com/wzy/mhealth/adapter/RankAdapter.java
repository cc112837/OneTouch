package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.AllStepRank;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
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
    private List<String> flag= new ArrayList<>();
    private List<AllStepRank.DataEntity> list;
    public RankAdapter(Context context,List<AllStepRank.DataEntity> list) {
        mInflater = LayoutInflater.from(context);
        this.list=list;
        this.context=context;
    }
    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
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
        viewHolder.tv_name.setText(""+list.get(position).getUserName());
        viewHolder.tv_rank.setText(position+1+"");
        viewHolder.tv_dastep.setText(""+list.get(position).getStepNum());
        viewHolder.tv_count.setText(""+"0");
        viewHolder.cb_prasid.setTag(R.id.cb_prasid, position);
        ImageLoader.getInstance().displayImage("", viewHolder.iv_tank, PhotoUtils.avatarImageOptions);
        viewHolder.cb_prasid.setChecked(flag.contains(position + ""));
        viewHolder.cb_prasid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int position = (int) buttonView.getTag(R.id.cb_prasid);
                if (isChecked) {
                    flag.add(position + "");
                } else {
                    flag.remove(position + "");
                }
            }
        });
        return convertView;
    }
    static class ViewHolder {
        public TextView tv_rank;
        public  TextView tv_name;
        public  TextView tv_dastep;
        public  TextView tv_count;
        public CheckBox cb_prasid;
        public ImageView iv_tank;


    }
}
