package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 购物车页面
 * 创建时间：2016/11/15 15:37
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.snappingstepper.SnappingStepper;
import com.bigkoo.snappingstepper.listener.SnappingStepperValueChangeListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Cart;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private ListView lv_cart;
    private CheckBox cb_radio;
    private CartAdapter cartAdapter;
    private TextView tv_total, tv_cal;
    private List<String> flaglist = new ArrayList<>();
    private List<Cart.DataEntity> list = new ArrayList<>();
    private List<Cart.DataEntity> cartDetail = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 272:
                    final Cart cart = (Cart) msg.obj;
                    list.clear();
                    list.addAll(cart.getData());
                    totalprice(cartDetail);
                    tv_cal.setOnClickListener(CartActivity.this);
                    cartAdapter.notifyDataSetChanged();
                    cb_radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                flaglist.clear();
                                for (int i = 0; i < list.size(); i++) {
                                    flaglist.add(i + "");
                                }
                                cartAdapter.setFlaglist(flaglist);
                            } else {
                                flaglist.clear();
                                cartAdapter.setFlaglist(flaglist);
                                cartDetail.clear();
                                totalprice(cartDetail);
                            }
                            cartAdapter.notifyDataSetChanged();
                        }
                    });

                    break;
            }
        }
    };

    /**
     * 计算总价
     *
     * @param cartDetail
     */
    private void totalprice(List<Cart.DataEntity> cartDetail) {
        double mdoubTotal = 0;
        int count = 0;
        if (cartDetail.size() == 0) {
            tv_total.setText("合计：¥" + 0);
            tv_cal.setText("去结算（" + 0 + ")");
        }
        for (int i = 0; i < cartDetail.size(); i++) {
            double price = cartDetail.get(i)
                    .getProductNumber()
                    * (cartDetail.get(i).getProductNewPrice());
            count += cartDetail.get(i).getProductNumber();
            mdoubTotal += price;
            tv_total.setText("合计：¥" + String.format("%.2f", mdoubTotal));
            tv_cal.setText("去结算（" + count + ")");
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
        String url = Constants.SERVER_URL + "MhealthShoppingCartServlet";
        MyHttpUtils.handData(handler, 272, url, null);
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv_cart = (ListView) findViewById(R.id.lv_cart);
        tv_cal = (TextView) findViewById(R.id.tv_cal);
        tv_total = (TextView) findViewById(R.id.tv_total);
        cb_radio = (CheckBox) findViewById(R.id.cb_radio);
        cartAdapter = new CartAdapter(this, list);
        cartAdapter.setFlaglist(flaglist);
        lv_cart.setAdapter(cartAdapter);
        leftBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_cal:
                intent = new Intent(CartActivity.this, ShopBuyActivity.class);
                startActivity(intent);
                break;
        }
    }

    class CartAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private Context context;
        private List<String> flaglist;

        public void setFlaglist(List<String> flaglist) {
            this.flaglist = flaglist;
        }

        private List<Cart.DataEntity> list;

        public CartAdapter(Context context, List<Cart.DataEntity> list) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
            this.list = list;
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.list_cart_item, null);
                viewHolder.tv_count=(TextView) convertView.findViewById(R.id.tv_count);
                viewHolder.cb_check = (CheckBox) convertView.findViewById(R.id.cb_check);
                viewHolder.tv_shopname = (TextView) convertView.findViewById(R.id.tv_shopname);
                viewHolder.stepperCustom = (SnappingStepper) convertView.findViewById(R.id.stepperCustom);
                viewHolder.iv_shop = (ImageView) convertView.findViewById(R.id.iv_shop);
                viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.stepperCustom.setValue(list.get(position).getProductNumber());
            viewHolder.stepperCustom.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
                @Override
                public void onValueChange(View view, int value) {
                    switch (view.getId()) {
                        case R.id.stepperCustom:
                            cartDetail.remove(list.get(position));
                            list.get(position).setProductNumber(value);
                            cartDetail.add(list.get(position));
                            viewHolder.tv_price.setText("" + String.format("%.2f", list.get(position).getProductNewPrice() * value));
                            cartAdapter.notifyDataSetChanged();
                            totalprice(cartDetail);
                            notifyDataSetChanged();
                            break;
                    }
                }
            });
            viewHolder.tv_price.setText("" + String.format("%.2f",list.get(position).getProductNewPrice() * list.get(position).getProductNumber()));
            viewHolder.cb_check.setTag(R.id.cb_check, position);
            viewHolder.cb_check.setChecked(flaglist.contains(position + ""));
            if(flaglist.size()==list.size()&&list!=null&&flaglist!=null){
                cb_radio.setChecked(true);
            }
            viewHolder.cb_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = (int) buttonView.getTag(R.id.cb_check);
                    if (isChecked) {
                        viewHolder.stepperCustom.setVisibility(View.VISIBLE);
                        viewHolder.tv_count.setVisibility(View.GONE);
                        flaglist.add(position + "");
                        cartDetail.add(list.get(position));
                        cartAdapter.notifyDataSetChanged();
                    } else {
                        viewHolder.tv_count.setText("数量:" + list.get(position).getProductNumber());
                        viewHolder.stepperCustom.setVisibility(View.GONE);
                        viewHolder.tv_count.setVisibility(View.VISIBLE);
                        flaglist.remove(position + "");
                        cartDetail.remove(list.get(position));
                        cartAdapter.notifyDataSetChanged();
                    }
                    totalprice(cartDetail);
                    notifyDataSetChanged();
                }
            });
            viewHolder.tv_shopname.setText("" + list.get(position).getProductName());
            ImageLoader.getInstance().displayImage(list.get(position).getProductImageSmall(), viewHolder.iv_shop);
            return convertView;
        }

        class ViewHolder {
            public TextView tv_shopname;
            public TextView tv_price,tv_count;
            public SnappingStepper stepperCustom;
            private ImageView iv_shop;
            private CheckBox cb_check;
        }

    }
}
