package com.wzy.mhealth.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Tijian;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/6/23 14:35
 * 修改人：Administrator
 * 修改时间：2016/6/23 14:35
 * 修改备注：
 */
public class TaoCanAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private List<Tijian.DataEntity.TaocanIdEntity> list;

    public TaoCanAdapter(Context context,List<Tijian.DataEntity.TaocanIdEntity> list) {
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

        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_item_fuchan_content, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_sale=(TextView) convertView.findViewById(R.id.tv_sale);
            viewHolder.afterprice = (TextView) convertView.findViewById(R.id.tv_afterprice);
            viewHolder.beforeprice = (TextView) convertView.findViewById(R.id.tv_beforeprice);
            viewHolder.afterprice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.beforeprice.setText("¥"+list.get(position).getNewPrice()+"");
        viewHolder.afterprice.setText("¥"+list.get(position).getOldPrice()+"");
        viewHolder.name.setText(list.get(position).getName()+"");
        viewHolder.tv_sale.setText("已售套餐："+list.get(position).getTaocanNum());
        return convertView;
    }
    static class ViewHolder {
        public TextView name;
        public  TextView afterprice;
        public TextView beforeprice,tv_sale;

    }

}
