package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.UserEvaluation;

import java.util.List;

public class EvaluationListAdapter extends BaseAdapter {

	private Context context;
	private List<UserEvaluation> list;
	private LayoutInflater mInflater;

	public EvaluationListAdapter(Context context, List<UserEvaluation> list) {
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
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder vholder = null;
		if (view == null) {
			vholder = new ViewHolder();
			view = mInflater.inflate(R.layout.item_evaluation_list, null);
			vholder.name = (TextView) view.findViewById(R.id.yonghu);
			vholder.degree = (TextView) view.findViewById(R.id.yonghuDegree);
			vholder.pingjia = (TextView) view.findViewById(R.id.yonghupingjia);
 			view.setTag(vholder);
		} else {
			vholder = (ViewHolder) view.getTag();
		}
		String str = list.get(position).getUserid();
		vholder.name.setText(str.charAt(0) + "****"
				+ str.charAt(str.length() - 3));
		vholder.degree.setText(list.get(position).getDegree()+"");
		vholder.pingjia.setText(list.get(position).getComment());
		return view;

	}

	static class ViewHolder {
		public TextView name;
		public TextView degree;
		public TextView pingjia;
	}

}
