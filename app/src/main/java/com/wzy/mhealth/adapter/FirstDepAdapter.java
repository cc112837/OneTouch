package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.FirstDep;

import java.util.List;

public class FirstDepAdapter extends BaseAdapter {

    private Context context;
    public List<FirstDep.DataEntity> list;
    private LayoutInflater mInflater;

    public FirstDepAdapter(Context context, List<FirstDep.DataEntity> list) {
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
            convertView = mInflater.inflate(R.layout.keshi_item, null);
            viewHolder.tv_keshi = (TextView) convertView.findViewById(R.id.tv_keshi);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_keshi.setText(list.get(position).getFirstDepName());
        return convertView;
    }

    static class ViewHolder {
        public TextView tv_keshi;

    }

}
