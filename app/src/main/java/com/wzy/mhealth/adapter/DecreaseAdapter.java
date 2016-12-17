package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Decrease;
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
public class DecreaseAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<Decrease.DataEntity> list;

    public DecreaseAdapter(Context context, List<Decrease.DataEntity> list) {
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
            convertView = mInflater.inflate(R.layout.list_decrease_item, null);
            viewHolder.tv_cross = (TextView) convertView.findViewById(R.id.tv_cross);
            viewHolder.tv_value = (TextView) convertView.findViewById(R.id.tv_value);
            viewHolder.cb_check = (CheckBox) convertView.findViewById(R.id.cb_check);
            viewHolder.tv_chao = (TextView) convertView.findViewById(R.id.tv_chao);
            viewHolder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_value.setText("有效期:"+list.get(position).getCouponTime());
        viewHolder.tv_cross.setText(""+list.get(position).getCouponPrice());
        viewHolder.tv_chao.setText("满"+list.get(position).getMuchPrice()+"可用");
        viewHolder.tv_money.setText("¥");
        viewHolder.cb_check.setVisibility(View.GONE);
        viewHolder.cb_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                }
            }
        });
        viewHolder.tv_cross.setTextColor(context.getResources().getColor(R.color.red));
        viewHolder.tv_money.setTextColor(context.getResources().getColor(R.color.red));
        return convertView;
    }

    static class ViewHolder {
        public TextView tv_cross, tv_money;
        public TextView tv_value, tv_chao;
        private CheckBox cb_check;
    }
}
