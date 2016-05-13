package com.wzy.mhealth.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Bingli2;

import java.util.List;

public class TabBingli2Adapter extends BaseAdapter {
	private List<Bingli2> list;
	private Context con;

	private LayoutInflater mInflater;

	public TabBingli2Adapter(Context context, List<Bingli2> list) {
		// TODO Auto-generated constructor stub
		this.con = context;
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
			convertView = mInflater.inflate(R.layout.tab_bingli2_list_item, null);

			viewHolder.imgArrow = (ImageView) convertView
					.findViewById(R.id.interact_listitem_arrow_icon);

			viewHolder.isCertain = (TextView) convertView
					.findViewById(R.id.bingli_listitem_name);
			viewHolder.detailOfDisease = (TextView) convertView
					.findViewById(R.id.interact_listitem_sex);
			viewHolder.time = (TextView) convertView
					.findViewById(R.id.interact_listitem_year);
			viewHolder.doctorName = (TextView) convertView
					.findViewById(R.id.bingli_listitem_marriage);
			viewHolder.zhicheng = (TextView) convertView
					.findViewById(R.id.interact_listitem_shoushu);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.isCertain.setText(list.get(position).getIsCertain());
		viewHolder.detailOfDisease.setText(list.get(position).getDetailOfDisease());
		viewHolder.time.setText(list.get(position).getTime());
		viewHolder.doctorName.setText(list.get(position).getDoctorName());
		viewHolder.zhicheng.setText(list.get(position).getTitle());
		viewHolder.imgArrow.setImageResource(R.mipmap.arrow_right);

		return convertView;
	}

	static class ViewHolder {
		public ImageView imgArrow;
		public TextView isCertain;
		public TextView detailOfDisease;
		public TextView time;
		public TextView zhicheng, doctorName;
	}
}
