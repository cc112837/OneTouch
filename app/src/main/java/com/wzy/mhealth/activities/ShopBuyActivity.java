package com.wzy.mhealth.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.ShopBuyAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.DefaultAdress;
import com.wzy.mhealth.model.ShopCart;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 商品生成支付页面
*/
public class ShopBuyActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private View headview = null;
    private LinearLayout ll_have;
    private TextView tv_name, tv_total, tv_cal, tv_newadd;
    private TextView tv_tel, tv_decrease, tv_beforetotal;
    private TextView tv_address;
    private ListView lv_shopbuy;
    String addressid, totalprice;
    private List<ShopCart.DataEntity> list = new ArrayList<>();
    private ShopBuyAdapter shopBuyAdapter;
    private List<ShopCart.DataEntity> shop;
    boolean flag = false;
    private double sub;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 281:
                    DefaultAdress defaultAdress = (DefaultAdress) msg.obj;
                    if (defaultAdress.getStatus().equals("1")) {
                        flag = true;
                        ll_have.setVisibility(View.VISIBLE);
                        tv_newadd.setVisibility(View.GONE);
                        addressid = defaultAdress.getAddressId() + "";
                        tv_name.setText(defaultAdress.getName() + "");
                        tv_tel.setText(defaultAdress.getTelephone() + "");
                        tv_address.setText(defaultAdress.getAddress());
                    } else {
                        flag = false;
                        ll_have.setVisibility(View.GONE);
                        tv_newadd.setVisibility(View.VISIBLE);
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_buy);
        sub = getIntent().getDoubleExtra("sub", 0.00);
        shop = (List<ShopCart.DataEntity>) getIntent().getSerializableExtra("shop");
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv_shopbuy = (ListView) findViewById(R.id.lv_shopbuy);
        tv_decrease = (TextView) findViewById(R.id.tv_decrease);
        tv_beforetotal = (TextView) findViewById(R.id.tv_beforetotal);
        shopBuyAdapter = new ShopBuyAdapter(this, shop);
        headview = LayoutInflater.from(ShopBuyActivity.this).inflate(R.layout.shoporder_header, null);
        lv_shopbuy.addHeaderView(headview);
        lv_shopbuy.setAdapter(shopBuyAdapter);
        tv_name = (TextView) headview.findViewById(R.id.tv_name);
        tv_tel = (TextView) headview.findViewById(R.id.tv_tel);
        tv_address = (TextView) headview.findViewById(R.id.tv_address);
        tv_newadd = (TextView) headview.findViewById(R.id.tv_newadd);
        ll_have = (LinearLayout) headview.findViewById(R.id.ll_have);
        String url = Constants.SERVER_URL + "MhealthShopAddressDefaultServlet";
        MyHttpUtils.handData(handler, 281, url, null);
        tv_cal = (TextView) findViewById(R.id.tv_cal);
        tv_total = (TextView) findViewById(R.id.tv_total);
        double value = 0;
        int num = 0;
        for (int i = 0; i < shop.size(); i++) {
            num += shop.get(i).getProductNumber();
            value += shop.get(i).getTotalPrice();
        }
        totalprice = String.format("%.2f", value - sub);
        tv_total.setText("实付款：¥" + totalprice);
        tv_cal.setText("去结算(" + num + ")");
        tv_decrease.setText("优惠：-¥" + String.format("%.2f", sub));
        tv_beforetotal.setText("原价：¥" + String.format("%.2f", value));
        tv_beforetotal.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        lv_shopbuy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent = new Intent(ShopBuyActivity.this, AddressActivity.class);
                    intent.putExtra("flag", "update");
                    startActivity(intent);
                }
            }
        });
        leftBtn.setOnClickListener(this);
        tv_cal.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String url = Constants.SERVER_URL + "MhealthShopAddressDefaultServlet";
        MyHttpUtils.handData(handler, 281, url, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                new AlertDialog.Builder(ShopBuyActivity.this).setTitle("提示")
                        .setMessage("便宜不等人，请三思而行").setPositiveButton("去意已决~", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setNegativeButton("我再想想", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                break;
            case R.id.tv_cal:
                if (flag) {
                    Intent intent = new Intent(ShopBuyActivity.this, CartBuyActivity.class);
                    intent.putExtra("price", totalprice);
                    intent.putExtra("addressId", addressid + "");
                    startActivity(intent);
                    ShopBuyActivity.this.finish();
                } else {
                    ToastUtil.show(ShopBuyActivity.this, "请填写收货地址");
                }
                break;
        }
    }
}
