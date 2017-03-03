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
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 地址选择适配器
*/
public class LocationAdapter extends BaseAdapter {

    private Context context;
    private List<Provice.DataEntity> list;
    private LayoutInflater mInflater;
    private int selectedposition = -1;

    public LocationAdapter(Context context, List<Provice.DataEntity> list) {

        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (list.size() == 0) {
            return 0;
        } else
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
            viewHolder.province = (TextView) convertView.findViewById(R.id.city);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position == selectedposition) {
            convertView.setBackgroundColor(0xffffffff);
        } else {
            convertView.setBackgroundColor(0xfff0f0f0);
        }
        viewHolder.province.setText(list.get(position).getProvice());
        return convertView;
    }

    public void setSelectItem(int selectItem) {
        this.selectedposition = selectItem;
    }

    class ViewHolder {
        public TextView province;
    }

}
