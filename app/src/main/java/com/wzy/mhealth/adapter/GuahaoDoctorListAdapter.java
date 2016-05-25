package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.GuahaoDoctorEntity;

import java.util.List;

public class GuahaoDoctorListAdapter extends BaseAdapter {

	private Context context;
	private List<GuahaoDoctorEntity> list;
	private LayoutInflater mInflater;

	public GuahaoDoctorListAdapter(Context context, List<GuahaoDoctorEntity> list) {
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
			convertView = mInflater.inflate(R.layout.item_guahaodoctorlist, null);
			viewHolder.photo = (ImageView) convertView.findViewById(R.id.photo);
			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.zhicheng = (TextView) convertView
					.findViewById(R.id.zhicheng);
			viewHolder.score= (TextView) convertView
					.findViewById(R.id.score);
			viewHolder.yuyueliang = (TextView) convertView
					.findViewById(R.id.yuyueliang);
			viewHolder.brief = (TextView) convertView.findViewById(R.id.brief);
			viewHolder.youhao = (TextView) convertView.findViewById(R.id.youhao);
			viewHolder.yueman = (TextView) convertView.findViewById(R.id.yueman);
			
			// viewHolder.photo.setImageResource(list.get(position).getResId());
//			viewHolder.name.setText(list.get(position).getName());
//			viewHolder.department.setText(list.get(position).getDepartment());
//			viewHolder.zhicheng.setText(list.get(position).getZhicheng());
//			viewHolder.hospital.setText(list.get(position).getHospital());
//			viewHolder.hospital.setSingleLine(true);
//			//viewHolder.hospital.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
//			viewHolder.brief.setText(list.get(position).getBrief());
//			viewHolder.brief.setSingleLine(true);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.name.setText(list.get(position).getName());
		viewHolder.score.setText(list.get(position).getPingfen());
		viewHolder.zhicheng.setText(list.get(position).getZhicheng());
		viewHolder.yuyueliang.setText(list.get(position).getJiezhenliang());
	
		//viewHolder.hospital.setEllipsize(TextUtils.TruncateAt.valueOf("END"));
		viewHolder.brief.setText(list.get(position).getBrief());
		if(list.get(position).getYouhao() <= 0){
			viewHolder.yueman.setVisibility(View.VISIBLE);
			viewHolder.youhao.setVisibility(View.GONE);
		}else{
			viewHolder.yueman.setVisibility(View.GONE);
			viewHolder.youhao.setVisibility(View.VISIBLE);
		}
		viewHolder.brief.setSingleLine(true);
		return convertView;

	}

	static class ViewHolder {
		public TextView name;
		public ImageView photo;
		public TextView score;
		public TextView zhicheng;
		public TextView yuyueliang;
		public TextView brief;
		public TextView youhao,yueman;
	}

}
