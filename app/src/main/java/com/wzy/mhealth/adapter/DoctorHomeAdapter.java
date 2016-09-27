package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Doctor;

import java.util.List;

public class DoctorHomeAdapter extends BaseAdapter {

    private Context context;
    private List<Doctor.DataEntity> list;
    private LayoutInflater mInflater;

    public DoctorHomeAdapter(Context context, List<Doctor.DataEntity> list) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.doctor_main_item, null);
            viewHolder.iv_doctorhead = (ImageView) convertView.findViewById(R.id.iv_doctorhead);
            viewHolder.tv_pre = (TextView) convertView.findViewById(R.id.tv_pre);
            viewHolder.tv_name = (TextView) convertView
                    .findViewById(R.id.tv_name);
            viewHolder.tv_hosi = (TextView) convertView
                    .findViewById(R.id.tv_hosi);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_name.setText(list.get(position).getUserName());
        ImageLoader.getInstance().displayImage("",viewHolder.iv_doctorhead,com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImageOptions );
        viewHolder.tv_pre.setText(list.get(position).getFirstdep());
        viewHolder.tv_hosi.setText(list.get(position).getDoctorTilte());
        return convertView;

    }

    static class ViewHolder {
        public ImageView iv_doctorhead;
        public TextView tv_name;
        public TextView tv_pre,tv_hosi;
    }

}
