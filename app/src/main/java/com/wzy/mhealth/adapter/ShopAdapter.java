package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Shop;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：商品首页的适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/11/15 14:38
 * 修改备注：
 */
public class ShopAdapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private List<Shop.DataEntity> list;
    public ShopAdapter(Context context, List<Shop.DataEntity> list) {
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
            convertView = mInflater.inflate(R.layout.shop_list_item, null);
            viewHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tv_oldprice = (TextView) convertView.findViewById(R.id.tv_oldprice);
            viewHolder.iv_home=(ImageView) convertView.findViewById(R.id.iv_home);
            viewHolder.tv_newprice = (TextView) convertView.findViewById(R.id.tv_newprice);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_oldprice.setText("");
        viewHolder.tv_name.setText("");
        viewHolder.tv_newprice.setText("");
        ImageLoader.getInstance().displayImage("",viewHolder.iv_home);
        return convertView;
    }
    static class ViewHolder {
        public TextView tv_name;
        public  TextView tv_oldprice,tv_newprice;
        private ImageView iv_home;
    }
}
