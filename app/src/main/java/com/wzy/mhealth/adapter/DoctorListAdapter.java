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
import com.wzy.mhealth.utils.ImageUtil;
import com.wzy.mhealth.utils.MyUtils;

import java.util.List;

public class DoctorListAdapter extends BaseAdapter {

    private Context context;
    private List<Doctor.DataEntity> list;
    private LayoutInflater mInflater;

    public DoctorListAdapter(Context context, List<Doctor.DataEntity> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
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
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_doctorlist, null);
            viewHolder.photo = (ImageView) convertView.findViewById(R.id.photo);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.department = (TextView) convertView
                    .findViewById(R.id.department);
            viewHolder.zhicheng = (TextView) convertView
                    .findViewById(R.id.zhicheng);
            viewHolder.hospital = (TextView) convertView
                    .findViewById(R.id.hospital);
            viewHolder.brief = (TextView) convertView.findViewById(R.id.brief);
            viewHolder.picturefee = (ImageView) convertView
                    .findViewById(R.id.picturefee);
            viewHolder.phonefee = (ImageView) convertView
                    .findViewById(R.id.phonefee);
            viewHolder.score = (TextView) convertView.findViewById(R.id.score);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(list.get(position).getUserName());
        viewHolder.department.setText(list.get(position).getFirstdep());
        viewHolder.zhicheng.setText(list.get(position).getDoctorTilte());
        viewHolder.hospital.setText(list.get(position).getHospital());
        viewHolder.hospital.setSingleLine(true);
        ImageLoader.getInstance().displayImage("",viewHolder.photo, ImageUtil.avatarlistdoctor);
        viewHolder.brief.setText(list.get(position).getSpecialization() + "");
        viewHolder.brief.setSingleLine(true);
        viewHolder.score.setText(list.get(position).getRecommend() + "");
        if (list.get(position).getPricePicture() != 0
                )
            viewHolder.picturefee.setImageResource(R.mipmap.camera_doctor);
        else viewHolder.picturefee.setImageResource(R.mipmap.camera_doctor_grey);
        if (list.get(position).getPricePhone() != null
                && !list.get(position).getPricePhone().equals("null")
                && !list.get(position).getPricePhone().equals(""))
            viewHolder.phonefee.setImageResource(R.mipmap.phone_doctor);
        else viewHolder.phonefee.setImageResource(R.mipmap.phone_doctor_grey);
        return convertView;

    }

    static class ViewHolder {
        public TextView name;
        public ImageView photo, phonefee, picturefee;
        public TextView department;
        public TextView zhicheng;
        public TextView hospital;
        public TextView brief, score;
    }

}
