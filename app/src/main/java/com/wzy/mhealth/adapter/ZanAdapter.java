package com.wzy.mhealth.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Zan;
import com.wzy.mhealth.utils.MyUtils;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：用户点赞
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/9/14 13:48
 * 修改备注：
 */
public class ZanAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private List<Zan.DataEntity> list;
    public ZanAdapter(Context context, List<Zan.DataEntity> list) {
        mInflater = LayoutInflater.from(context);
        this.list=list;
        this.context=context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.zan_item, null);
            viewHolder.iv_head=(ImageView) convertView.findViewById(R.id.iv_head);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_zan);
            viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_time.setText(""+list.get(position).getTime()+"");
        viewHolder.name.setText(list.get(position).getUserName()+"赞了我");
        ImageLoader.getInstance().displayImage(list.get(position).getImage(), viewHolder.iv_head, PhotoUtils.avatarImageOption);
        return convertView;
    }
    static class ViewHolder {
        public TextView name;
        public  TextView tv_time;
        public ImageView iv_head;
    }
}
