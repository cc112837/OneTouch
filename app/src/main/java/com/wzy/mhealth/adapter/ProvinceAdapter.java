package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Provice;

import java.util.List;

public class ProvinceAdapter extends BaseAdapter {

    private Context context;
    public List<Provice.DataEntity.CityArrEntity> list;
    private LayoutInflater mInflater;

    public ProvinceAdapter(Context context, List<Provice.DataEntity.CityArrEntity> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
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
            convertView = mInflater.inflate(R.layout.province_item, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.city);
            viewHolder.name.setText(list.get(position).getCity());
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    static class ViewHolder {
        public TextView name;

    }

}
