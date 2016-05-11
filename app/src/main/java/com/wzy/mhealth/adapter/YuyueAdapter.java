package com.wzy.mhealth.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.ImgConfig;
import com.wzy.mhealth.model.YuyueDoctor;

import java.util.List;


public class YuyueAdapter extends BaseAdapter {
	
	private Context context;
	private List<YuyueDoctor> list;
	
	
	public YuyueAdapter(Context context, List<YuyueDoctor> list) {
		
		this.context = context;
		this.list = list;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		final YuyueDoctor item = (YuyueDoctor) getItem(position);	
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.row_yuyue, null);
			viewHolder = new ViewHolder();
			viewHolder.dateLayout = (RelativeLayout) convertView.findViewById(R.id.dateLayout);
			viewHolder.lineLayout = (LinearLayout) convertView.findViewById(R.id.lineLayout);
			viewHolder.lineLayout2 = (LinearLayout) convertView.findViewById(R.id.lineLayout2);
			viewHolder.nickView = (TextView) convertView.findViewById(R.id.nickView);
			viewHolder.sexView = (TextView)convertView.findViewById(R.id.sexView);
			viewHolder.sexView1 = (TextView)convertView.findViewById(R.id.sexView1);
			viewHolder.ageView = (TextView)convertView.findViewById(R.id.ageView);
			viewHolder.dateView = (TextView) convertView.findViewById(R.id.dateView);
			viewHolder.miaoshuView = (TextView) convertView.findViewById(R.id.miaoshuView);
			viewHolder.headImg = (ImageView) convertView.findViewById(R.id.headImg);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.nickView.setText(item.getDoctorname());
		viewHolder.nickView.setTextColor(Color.BLACK);
		viewHolder.ageView.setText(item.getFirstdepName());
		viewHolder.ageView.setTextColor(Color.BLACK);
		viewHolder.sexView1.setText("  ");
		viewHolder.sexView.setText(item.getDoctorTitle());
		viewHolder.sexView.setTextColor(Color.BLACK);

		viewHolder.miaoshuView.setText(item.getHospitalName());
		viewHolder.miaoshuView.setTextColor(Color.BLACK);
		ImgConfig.showHeadImg(item.getDoctorname(), viewHolder.headImg);
		
		YuyueDoctor lastPatient = null;
		
		if(position!=0)
			lastPatient = (YuyueDoctor)getItem(position-1);
		
		if(lastPatient != null && lastPatient.getDate().equals(item.getDate())){
			viewHolder.dateLayout.setVisibility(View.GONE);
			viewHolder.lineLayout2.setVisibility(View.GONE);
			viewHolder.lineLayout.setVisibility(View.GONE);
		}
		else {
			if(lastPatient == null)
				viewHolder.lineLayout2.setVisibility(View.GONE);
			else
				viewHolder.lineLayout2.setVisibility(View.VISIBLE);
			viewHolder.lineLayout.setVisibility(View.VISIBLE);
			viewHolder.dateLayout.setVisibility(View.VISIBLE);
			viewHolder.dateView.setText(item.getDate());
			viewHolder.dateView.setTextColor(Color.BLACK);
		}
		
		return convertView;
	}
	
	
	class ViewHolder {
		LinearLayout lineLayout,lineLayout2;
		RelativeLayout dateLayout;
		TextView nickView,sexView,sexView1,ageView,dateView,miaoshuView;
		ImageView headImg;
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
}