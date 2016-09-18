package com.wzy.mhealth.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.ZhixingTaocan;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/9/14 13:48
 * 修改备注：
 */
public class OrderAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private List<ZhixingTaocan.DataEntity> list;
    public OrderAdapter(Context context,List<ZhixingTaocan.DataEntity> list) {
        mInflater = LayoutInflater.from(context);
        this.list=list;
        this.context=context;
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
        ViewHolder viewHolder ;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.order_item, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.price.setText("¥"+list.get(position).getOldPrice()+"");
        viewHolder.name.setText(list.get(position).getName()+"");
        return convertView;
    }
    static class ViewHolder {
        public TextView name;
        public  TextView price;

    }
}
