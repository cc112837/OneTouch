package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.HospitalEntity;

import java.util.List;

public class HospitalListAdapter extends BaseAdapter {

	private Context context;
	private List<HospitalEntity> list;
	private LayoutInflater mInflater;

	public HospitalListAdapter(Context context, List<HospitalEntity> list) {
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
			convertView = mInflater.inflate(R.layout.item_hospitallist, null);
			viewHolder.photo = (ImageView) convertView.findViewById(R.id.photo);
			viewHolder.name = (TextView) convertView.findViewById(R.id.hospitalname);//医院名称
			viewHolder.grade = (TextView) convertView
					.findViewById(R.id.hospitalgrade);//医院等级
			viewHolder.yuyue = (TextView) convertView.findViewById(R.id.yueliang);
			viewHolder.pingjia = (TextView) convertView.findViewById(R.id.pingjialiang);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(list.get(position).getName());
		viewHolder.grade.setText(list.get(position).getgrade());
		viewHolder.pingjia.setText(list.get(position).getpingjia());
//		// TODO: 2016/2/17
		//viewHolder.hospital.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
		viewHolder.yuyue.setText(list.get(position).getyuyueliang());

		return convertView;

	}

	static class ViewHolder {
		public ImageView photo;
		public TextView name;
		public TextView grade;
		public TextView yuyue;
		public TextView pingjia;
	}

}
