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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vholder = null;
        if (view == null) {
            vholder = new ViewHolder();
            view = mInflater.inflate(R.layout.item_evaluation_list, null);
            vholder.tv_data = (TextView) view.findViewById(R.id.tv_data);
            vholder.tv_month = (TextView) view.findViewById(R.id.tv_month);
            vholder.name = (TextView) view.findViewById(R.id.yonghu);
            vholder.degree = (TextView) view.findViewById(R.id.yonghuDegree);
            vholder.pingjia = (TextView) view.findViewById(R.id.yonghupingjia);
            view.setTag(vholder);
        } else {
            vholder = (ViewHolder) view.getTag();
        }
        String str = list.get(position).getUserid();
        vholder.name.setText("用户："+str.charAt(0) + "****"
                + str.charAt(str.length() - 3));
        vholder.tv_data.setText("");
        vholder.tv_month.setText("");
        vholder.degree.setText("满意度："+list.get(position).getDegree() + "");
        vholder.pingjia.setText(list.get(position).getComment());
        return view;

    }

    static class ViewHolder {
        public TextView name;
        public TextView degree;
        public TextView pingjia;
        public TextView tv_month;
        public TextView tv_data;
    }

}
