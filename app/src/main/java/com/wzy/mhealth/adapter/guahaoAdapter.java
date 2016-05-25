package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.GuaHao;

import java.util.List;

public class guahaoAdapter extends BaseAdapter {
	
	private List<GuaHao> list;
	Context context;
	public guahaoAdapter(Context context, List<GuaHao> list) {
		
		this.context = context;
		this.list = list;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		final GuaHao item = (GuaHao) getItem(position);	
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.row_guahao_d, null);
			viewHolder = new ViewHolder();
			viewHolder.dateView = (TextView) convertView.findViewById(R.id.dateView);
			viewHolder.haoView = (TextView) convertView.findViewById(R.id.haoView);
			viewHolder.haoView1 = (TextView) convertView.findViewById(R.id.haoView1);
			viewHolder.menzhenView = (TextView) convertView.findViewById(R.id.menzhenView);
			viewHolder.monView = (TextView) convertView.findViewById(R.id.monView);
			viewHolder.weekView = (TextView)convertView.findViewById(R.id.weekView);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.dateView.setText(item.getDate());
		viewHolder.weekView.setText(item.getWeek());
		if(item.getNumber() == 0){
			viewHolder.haoView.setText("约满 ");
			viewHolder.haoView1.setText("");
		}
		else{
			viewHolder.haoView.setText(" ");
			viewHolder.haoView1.setText("有号  ");
		}
		
		return convertView;
	}
	
	
	class ViewHolder {
		TextView dateView,weekView,menzhenView,monView,haoView,haoView1;
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