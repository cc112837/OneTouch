package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.SelfHealth;
import com.wzy.mhealth.utils.MyUtils;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：商品首页的适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/11/19 8:38
 * 修改备注：
 */
public class ManageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<SelfHealth.DataEntity> list;

    public ManageAdapter(Context context, List<SelfHealth.DataEntity> list) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return MyUtils.isEmpty(list) ? 0 : list.size();
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
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.order_header, null);
            viewHolder.tv_data = (TextView) convertView.findViewById(R.id.tv_data);
            viewHolder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            viewHolder.tv_hosi=(TextView) convertView.findViewById(R.id.tv_hosi);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_hosi.setText("医院名称是");
        viewHolder.tv_data.setText("2017-09-21");
        viewHolder.tv_num.setText("体检报告");
        return convertView;
    }

    static class ViewHolder {
        public TextView tv_data, tv_num,tv_hosi;
    }
}
