package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.ShopOrder;
import com.wzy.mhealth.utils.MyUtils;

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
public class ShopOrderAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<ShopOrder.DataEntity> list;

    public ShopOrderAdapter(Context context, List<ShopOrder.DataEntity> list) {
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
            convertView = mInflater.inflate(R.layout.list_shoporder_item, null);
            viewHolder.tv_shopname = (TextView) convertView.findViewById(R.id.tv_shopname);
            viewHolder.tv_orderstatus = (TextView) convertView.findViewById(R.id.tv_orderstatus);
            viewHolder.iv_car = (ImageView) convertView.findViewById(R.id.iv_car);
            viewHolder.iv_order = (ImageView) convertView.findViewById(R.id.iv_order);
            viewHolder.tv_time=(TextView) convertView.findViewById(R.id.tv_time);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            viewHolder.tv_number=(TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.iv_delete = (ImageView) convertView.findViewById(R.id.iv_delete);
            viewHolder.tv_click1 = (TextView) convertView.findViewById(R.id.tv_click1);
            viewHolder.tv_click2= (TextView) convertView.findViewById(R.id.tv_click2);
            viewHolder.tv_shoptitle = (TextView) convertView.findViewById(R.id.tv_shoptitle);
            viewHolder.tv_shopnum= (TextView) convertView.findViewById(R.id.tv_shopnum);
            viewHolder.tv_shoping=(TextView) convertView.findViewById(R.id.tv_shoping);
            viewHolder.ll_only=(LinearLayout) convertView.findViewById(R.id.ll_only);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ll_only.setVisibility(View.VISIBLE);
        viewHolder.iv_car.setVisibility(View.VISIBLE);
        viewHolder.tv_orderstatus.setText("已完成");//订单状态
        viewHolder.tv_shopname.setText("店铺名");
        viewHolder.tv_shoping.setText("运输中");
        viewHolder.tv_shoptitle.setText("商品名称");
        viewHolder.tv_shopnum.setText("数量："+3);
        viewHolder.tv_number.setText("共"+2+"件商品");
        viewHolder.tv_price.setText("实付款：¥"+23.3);
        viewHolder.tv_time.setText("2016-12-32 11:00:00");
        viewHolder.tv_click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.tv_click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ImageLoader.getInstance().displayImage("", viewHolder.iv_order, PhotoUtils.avatarImageOption);
        return convertView;
    }

    static class ViewHolder {
        public TextView tv_shopname,tv_shoping,tv_time,tv_price,tv_number;
        public TextView tv_orderstatus, tv_click1,tv_click2,tv_shoptitle,tv_shopnum;
        private ImageView iv_order,iv_delete,iv_car;
        private LinearLayout ll_only;
    }
}
