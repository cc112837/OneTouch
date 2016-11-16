package com.wzy.mhealth.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.snappingstepper.SnappingStepper;
import com.bigkoo.snappingstepper.listener.SnappingStepperValueChangeListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Cart;

import java.util.List;

/**
 * 项目名称：mhealth
 * 类描述：购物车的适配器
 * 创建人：吴聪聪
 * 邮箱：cc112837@163.com
 * 创建时间：2016/9/14 13:48
 * 修改人：Administrator
 * 修改时间：2016/11/15 14:38
 * 修改备注：
 */
public class CartAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<Cart.DataEntity> list;

    public CartAdapter(Context context, List<Cart.DataEntity> list) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
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
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.list_cart_item, null);
            viewHolder.cb_check = (CheckBox) convertView.findViewById(R.id.cb_check);
            viewHolder.tv_shopname = (TextView) convertView.findViewById(R.id.tv_shopname);
            viewHolder.stepperCustom = (SnappingStepper) convertView.findViewById(R.id.stepperCustom);
            viewHolder.iv_shop = (ImageView) convertView.findViewById(R.id.iv_shop);
            viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.stepperCustom.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                switch (view.getId()) {
                    case R.id.stepperCustom:

                        break;
                }
            }
        });
        viewHolder.tv_shopname.setText("");
        viewHolder.tv_price.setText("");
        viewHolder.cb_check.setChecked(true);
        ImageLoader.getInstance().displayImage("", viewHolder.iv_shop);
        return convertView;
    }

    static class ViewHolder {
        public TextView tv_shopname;
        public TextView tv_price;
        public SnappingStepper stepperCustom;
        private ImageView iv_shop;
        private CheckBox cb_check;
    }
}
