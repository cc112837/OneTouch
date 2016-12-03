package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 购物车页面
 * 创建时间：2016/11/15 15:37
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bigkoo.snappingstepper.SnappingStepper;
import com.bigkoo.snappingstepper.listener.SnappingStepperValueChangeListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Cart;
import com.wzy.mhealth.model.ShopCart;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.MyUtils;
import com.wzy.mhealth.utils.ToastUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private ListView lv_cart;
    private CheckBox cb_radio;
    private CartAdapter cartAdapter;
    private TextView tv_total, tv_cal;
    private LinearLayout ll_bottom,ll_repalce;
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
                    if(list.size()==0){
                        ll_bottom.setVisibility(View.GONE);
                        ll_repalce.setVisibility(View.VISIBLE);
                    }
                    else {
                        ll_repalce.setVisibility(View.GONE);
                        ll_bottom.setVisibility(View.VISIBLE);
                        cartAdapter.setList(list);
                        totalprice(cartDetail);
                        tv_cal.setOnClickListener(CartActivity.this);
                        cartAdapter.notifyDataSetChanged();
                        cb_radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    for (int i = 0; i < list.size(); i++) {
                                        cartAdapter.selectedMap.put(i, true);
                                    }
                                } else {
                                    if (!cartAdapter.selectedMap.containsValue(false)) {
                                        for (int i = 0; i < list.size(); i++) {
                                            cartAdapter.selectedMap.put(i, false);
                                        }
                                        cartDetail.clear();
                                    }
                                    totalprice(cartDetail);
                                }
                                cartAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    break;
                case 282:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (stepInfo.getStatus().equals("1")) {
                        cartDetail.clear();
                        totalprice(cartDetail);
                        String url = Constants.SERVER_URL + "MhealthShoppingCartServlet";
                        MyHttpUtils.handData(handler, 272, url, null);
                    }
                    break;
                case 284:
                    ShopCart shopCart = (ShopCart) msg.obj;
                    Intent intent = new Intent(CartActivity.this, ShopBuyActivity.class);
                    intent.putExtra("shop", (Serializable) shopCart.getData());
                    startActivity(intent);
                    break;
                case 285:
                    StepInfo stepInfo1 = (StepInfo) msg.obj;

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
        ll_bottom=(LinearLayout)findViewById(R.id.ll_bottom);
        ll_repalce=(LinearLayout)findViewById(R.id.ll_repalce);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv_cart = (ListView) findViewById(R.id.lv_cart);
        tv_cal = (TextView) findViewById(R.id.tv_cal);
        tv_total = (TextView) findViewById(R.id.tv_total);
        cb_radio = (CheckBox) findViewById(R.id.cb_radio);
        cartAdapter = new CartAdapter(this, list);
        lv_cart.setAdapter(cartAdapter);
        cartAdapter.setList(list);
        lv_cart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(CartActivity.this).setTitle("删除提示")
                        .setMessage("您确定要删除这个商品吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String uri = Constants.SERVER_URL + "MhealthShoppingCartDeleteServlet";
                        TiUser user = new TiUser();
                        user.setName(list.get(position).getShopcartId() + "");
                        MyHttpUtils.handData(handler, 282, uri, user);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return true;
            }
        });
        lv_cart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CartActivity.this, ShopDetailActivity.class);
                intent.putExtra("id", list.get(position).getProductId() + "");
                startActivity(intent);
            }
        });
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
                if (cartDetail.size() > 0) {
                    String url = Constants.SERVER_URL + "MhealthShoppingCartBuyServlet";
                    TiUser user = new TiUser();
                    JSONArray jsonArray = new JSONArray();
                    for (int i = 0; i < cartDetail.size(); i++) {
                        try {
                            JSONObject object = new JSONObject();
                            object.put("productId", cartDetail.get(i).getProductId());
                            object.put("shopCartId",cartDetail.get(i).getShopcartId());
                            object.put("productNum", cartDetail.get(i).getProductNumber());
                            jsonArray.put(object);//向json数组里面添加对象
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    user.setName(jsonArray.toString());
                    MyHttpUtils.handData(handler, 284, url, user);
                } else {
                    ToastUtil.show(CartActivity.this, "请选择商品");
                }
                break;
        }
    }



    class CartAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private Context context;
        Map<Integer, Boolean> selectedMap;

        private List<Cart.DataEntity> list;

        public CartAdapter(Context context, List<Cart.DataEntity> list) {
            mInflater = LayoutInflater.from(context);
            this.context = context;
            this.list = list;
            selectedMap = new HashMap<>();
        }

        public void setList(List<Cart.DataEntity> list) {
            for (int i = 0; i < list.size(); i++) {
                selectedMap.put(i, false);
            }
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
                viewHolder.tv_count = (TextView) convertView.findViewById(R.id.tv_count);
                viewHolder.cb_check = (CheckBox) convertView.findViewById(R.id.cb_check);
                viewHolder.tv_shopname = (TextView) convertView.findViewById(R.id.tv_shopname);
                viewHolder.stepperCustom = (SnappingStepper) convertView.findViewById(R.id.stepperCustom);
                viewHolder.iv_shop = (ImageView) convertView.findViewById(R.id.iv_shop);
                viewHolder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv_count.setText("数量:" + list.get(position).getProductNumber());
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
                            String url = Constants.SERVER_URL + "MhealthShoppingCartNumServlet";
                            TiUser user = new TiUser();
                            user.setName(value + "");
                            user.setCardId(list.get(position).getShopcartId() + "");
                            MyHttpUtils.handData(handler, 285, url, user);
                            break;
                    }
                }
            });
            viewHolder.tv_price.setText("" + String.format("%.2f", list.get(position).getProductNewPrice() * list.get(position).getProductNumber()));
            viewHolder.cb_check.setChecked(selectedMap.get(position));
            viewHolder.cb_check.setTag(R.id.cb_check, position);
            viewHolder.cb_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int position = (int) buttonView.getTag(R.id.cb_check);
                    if (isChecked) {
                        viewHolder.stepperCustom.setVisibility(View.VISIBLE);
                        viewHolder.tv_count.setVisibility(View.GONE);
                        selectedMap.put(position, true);
                        cartDetail.add(list.get(position));
                        cartAdapter.notifyDataSetChanged();
                        if (!cartAdapter.selectedMap.containsValue(false)) {
                            cb_radio.setChecked(true);
                        }
                        totalprice(cartDetail);
                    } else {
                        selectedMap.put(position, false);
                        viewHolder.stepperCustom.setVisibility(View.GONE);
                        viewHolder.tv_count.setVisibility(View.VISIBLE);
                        cartDetail.remove(list.get(position));
                        cartAdapter.notifyDataSetChanged();
                        if (cartAdapter.selectedMap.containsValue(false)) {
                            cb_radio.setChecked(false);
                        }
                        totalprice(cartDetail);
                    }
                }
            });
            viewHolder.tv_shopname.setText("" + list.get(position).getProductName());
            ImageLoader.getInstance().displayImage(list.get(position).getProductImageSmall(), viewHolder.iv_shop);
            return convertView;
        }

        class ViewHolder {
            public TextView tv_shopname;
            public TextView tv_price, tv_count;
            public SnappingStepper stepperCustom;
            private ImageView iv_shop;
            private CheckBox cb_check;
        }

    }
}
