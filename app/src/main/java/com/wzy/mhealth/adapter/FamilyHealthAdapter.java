package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.FamilyHealth;

public 	class FamilyHealthAdapter extends ArrayAdapter<FamilyHealth> {

	private Context context;

	public FamilyHealthAdapter(Context context) {
		super(context, 0);
		this.context = context;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder vH;
		final FamilyHealth item = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.row_familyhealth, null);
			vH = new ViewHolder();
			vH.nameView = (TextView) convertView.findViewById(R.id.nameView);
			vH.ageView = (TextView) convertView.findViewById(R.id.ageView);
			vH.medicalHistoryView = (TextView) convertView.findViewById(R.id.medicalHistory);
			vH.relatedView = (TextView) convertView.findViewById(R.id.related);
			convertView.setTag(vH);
		} else {
			vH = (ViewHolder) convertView.getTag();
		}
		if (item.getMemberName() != null)
			vH.nameView.setText(item.getMemberName());
		if (item.getMemberAge() != null)
			vH.ageView.setText(item.getMemberAge());
		if (item.getMemberMedicalHistory() != null)
			vH.medicalHistoryView.setText(item.getMemberMedicalHistory());
		if (item.getMemberRelated() != null)
			vH.relatedView.setText(item.getMemberRelated());

		return convertView;
	}

	class ViewHolder {
		TextView nameView, ageView, medicalHistoryView, relatedView;
	}



}
