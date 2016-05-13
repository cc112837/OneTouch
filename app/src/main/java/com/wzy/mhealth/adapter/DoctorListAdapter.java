package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.DoctorEntity;

import java.util.List;

public class DoctorListAdapter extends BaseAdapter {

	private Context context;
	private List<DoctorEntity> list;
	private LayoutInflater mInflater;

	public DoctorListAdapter(Context context, List<DoctorEntity> list) {
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
			viewHolder.picturefee = (TextView) convertView
					.findViewById(R.id.picturefee);
			viewHolder.phonefee = (TextView) convertView
					.findViewById(R.id.phonefee);
			viewHolder.score = (TextView) convertView.findViewById(R.id.score);
			// viewHolder.photo.setImageResource(list.get(position).getResId());
			// viewHolder.name.setText(list.get(position).getName());
			// viewHolder.department.setText(list.get(position).getDepartment());
			// viewHolder.zhicheng.setText(list.get(position).getZhicheng());
			// viewHolder.hospital.setText(list.get(position).getHospital());
			// viewHolder.hospital.setSingleLine(true);
			// //viewHolder.hospital.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
			// viewHolder.brief.setText(list.get(position).getBrief());
			// viewHolder.brief.setSingleLine(true);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(list.get(position).getName());
		viewHolder.department.setText(list.get(position).getFirstdepName());
		viewHolder.zhicheng.setText(list.get(position).getDoctorTitle());
		viewHolder.hospital.setText(list.get(position).getHospitalName());
		viewHolder.hospital.setSingleLine(true);
		// viewHolder.hospital.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
		viewHolder.brief.setText(list.get(position).getIntroduction());
		viewHolder.brief.setSingleLine(true);
		viewHolder.score.setText(list.get(position).getRecommend());
		if (list.get(position).getPrice_picture() != null
				&& !list.get(position).getPrice_picture().equals("null")
				&& !list.get(position).getPrice_picture().equals(""))
			viewHolder.picturefee.setText("图文咨询："
					+ list.get(position).getPrice_picture()+"元/次");
		else  viewHolder.picturefee.setText("图文咨询未开通");
		if (list.get(position).getPrice_phone() != null
				&& !list.get(position).getPrice_phone().equals("null")
				&& !list.get(position).getPrice_picture().equals(""))
			viewHolder.phonefee.setText("电话咨询："
					+ list.get(position).getPrice_phone()+"元/分钟");
		else  viewHolder.phonefee.setText("电话咨询未开通");
		return convertView;

	}

	static class ViewHolder {
		public TextView name;
		public ImageView photo;
		public TextView department;
		public TextView zhicheng;
		public TextView hospital;
		public TextView brief, picturefee, phonefee, score;
	}

}
