package com.wzy.mhealth.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.DoctorEntity;
import com.wzy.mhealth.model.UserEvaluation;

import java.util.List;

public class TuWenZiXunAdapter extends BaseAdapter {

	private Context context;
	private List<UserEvaluation.DataEntity> list;
	private LayoutInflater mInflater;
	private DoctorEntity doctor;

	private TextView name, tuwenzixunfeiyong;
	private TextView zhicheng;
	private TextView hospital;
	private TextView department, recommend, attitude, level;


	public TuWenZiXunAdapter(Context context, List<UserEvaluation.DataEntity> list,
			DoctorEntity doctor) {
		this.context = context;
		this.list = list;
		this.doctor = doctor;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return (list.size() + 5);
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
	public int getViewTypeCount() {
		return 5;
	}

	@Override
	public int getItemViewType(int position) {
		if (position == 0) {
			return 0;
		} else if (position == 1) {
			return 1;
		} else if (position == 2) {
			return 2;
		} else if (position == 3) {
			return 1;
		} else if (position == 4) {
			return 3;
		} else {
			return 4;
		}

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (getItemViewType(position) == 0) {
				convertView = mInflater.inflate(R.layout.tuwenitem, null);
				tuwenzixunfeiyong = (TextView) convertView
						.findViewById(R.id.tuwenzixunfeiyong);
				tuwenzixunfeiyong.setText(doctor.getPrice_picture()+"元/次");
			} else if (getItemViewType(position) == 1) {
				convertView = mInflater.inflate(R.layout.divideritem, null);

			} else if (getItemViewType(position) == 2) {
				convertView = mInflater.inflate(R.layout.doctoritem, null);
				name = (TextView) convertView.findViewById(R.id.name);
				name.setText(doctor.getName());
				zhicheng = (TextView) convertView.findViewById(R.id.zhicheng);
				zhicheng.setText(doctor.getDoctorTitle());
				hospital = (TextView) convertView.findViewById(R.id.hospital);
				hospital.setText(doctor.getHospitalName());
				hospital.setSingleLine(true);
				department = (TextView) convertView
						.findViewById(R.id.department);
				department.setText(doctor.getFirstdepName());
				recommend = (TextView) convertView.findViewById(R.id.recommend);
				recommend.setText(doctor.getRecommend());
				attitude = (TextView) convertView.findViewById(R.id.attitude);
				attitude.setText(doctor.getAttitude());
				level = (TextView) convertView.findViewById(R.id.level);
				level.setText(doctor.getLevel());

			} else if (getItemViewType(position) == 3) {
				convertView = mInflater.inflate(R.layout.shuomingitem, null);
				TextView wenzi = (TextView) convertView.findViewById(R.id.wenzi);
				if(list.size() == 0)
					wenzi.setText("暂无用户评价");
			} else {
				convertView = mInflater.inflate(R.layout.pingjiaitem, null);
				viewHolder = new ViewHolder();
				viewHolder.user = (TextView) convertView
						.findViewById(R.id.user);

				viewHolder.manyidu = (TextView) convertView
						.findViewById(R.id.manyidu);

				viewHolder.brief = (TextView) convertView
						.findViewById(R.id.brief2);
				String str = list.get(position - 5).getUserName();
				viewHolder.user.setText(str.charAt(0) + "****"+ str.charAt(str.length() - 1));
				viewHolder.manyidu.setText(list.get(position - 5).getSatisfy()+"");
				viewHolder.brief.setText(list.get(position - 5).getEvaluate());
				convertView.setTag(viewHolder);
			}
		} else {
			if (getItemViewType(position) == 4) {

				viewHolder = (ViewHolder) convertView.getTag();
				String str = list.get(position - 5).getUserName();
				viewHolder.user.setText(str.charAt(0) + "****"+ str.charAt(str.length() - 1));
				viewHolder.manyidu.setText(list.get(position - 5).getSatisfy()+"");
				viewHolder.brief.setText(list.get(position - 5).getEvaluate());
			}
		}

		return convertView;
	}

	static class ViewHolder {
		public TextView user;
		public TextView manyidu;
		public TextView brief;
	}

}
