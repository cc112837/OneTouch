package com.wzy.mhealth.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.DecreseActivity;
import com.wzy.mhealth.activities.TicketActivity;
import com.wzy.mhealth.model.ShopCart;
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
public class ShopBuyAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<ShopCart.DataEntity> list;

    public ShopBuyAdapter(Context context, List<ShopCart.DataEntity> list) {
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
            convertView = mInflater.inflate(R.layout.shopbuy_list_item, null);
            viewHolder.iv_disshow=(ImageView) convertView.findViewById(R.id.iv_disshow);
            viewHolder.ll_only = (LinearLayout) convertView.findViewById(R.id.ll_only);
            viewHolder.tv_shopnum = (TextView) convertView.findViewById(R.id.tv_shopnum);
            viewHolder.ll_tiket = (LinearLayout) convertView.findViewById(R.id.ll_tiket);
            viewHolder.ll_decrease = (LinearLayout) convertView.findViewById(R.id.ll_decrease);
            viewHolder.tv_shoptitle = (TextView) convertView.findViewById(R.id.tv_shoptitle);
            viewHolder.tv_value = (TextView) convertView.findViewById(R.id.tv_value);
            viewHolder.tv_use = (TextView) convertView.findViewById(R.id.tv_use);
            viewHolder.tv_crease = (TextView) convertView.findViewById(R.id.tv_crease);
            viewHolder.tv_increase = (TextView) convertView.findViewById(R.id.tv_increase);
            viewHolder.tv_decrease = (TextView) convertView.findViewById(R.id.tv_decrease);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.ll_only.setVisibility(View.VISIBLE);
        viewHolder.ll_tiket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//发票信息
                Intent intent = new Intent(context, TicketActivity.class);
                context.startActivity(intent);
            }
        });
        viewHolder.ll_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//优惠券
                Intent intent = new Intent(context, DecreseActivity.class);
                context.startActivity(intent);
            }
        });
        ImageLoader.getInstance().displayImage(list.get(position).getProductImageSmall(),viewHolder.iv_disshow, com.avoscloud.leanchatlib.utils.PhotoUtils.avatarImageOptions);
        viewHolder.tv_increase.setText("+¥" + 0);
        viewHolder.tv_decrease.setText("-¥" + 0);
        viewHolder.tv_shopnum.setText("数量：" + list.get(position).getProductNumber());
        viewHolder.tv_crease.setText("¥" + String.format("%.2f",list.get(position).getTotalPrice));
        viewHolder.tv_value.setText(0+ "张可用");
        viewHolder.tv_shoptitle.setText(""+list.get(position).getProductName());
        viewHolder.tv_use.setText("未使用");
        return convertView;
    }

    static class ViewHolder {
        public ImageView iv_disshow;
        public TextView tv_shoptitle, tv_shopnum;
        public TextView tv_value, tv_use, tv_crease, tv_increase, tv_decrease;
        private LinearLayout ll_only, ll_tiket, ll_decrease;
    }
}
