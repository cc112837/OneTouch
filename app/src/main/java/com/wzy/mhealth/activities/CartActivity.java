package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 购物车页面
 * 创建时间：2016/11/15 15:37
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.CartAdapter;
import com.wzy.mhealth.model.Cart;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private ListView lv_cart;
    private CheckBox cb_radio;
    private TextView tv_total, tv_cal;
    private List<Cart.DataEntity> list = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 272:
                    tv_cal.setText("合计：¥");
                    tv_cal.setText("去结算（" + 2 + ")");
                    tv_cal.setOnClickListener(CartActivity.this);
                    cb_radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {

                            }
                        }
                    });
                    cartAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
        MyHttpUtils.handData(handler, 271, "", null);
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv_cart = (ListView) findViewById(R.id.lv_cart);
        tv_cal = (TextView) findViewById(R.id.tv_cal);
        tv_total = (TextView) findViewById(R.id.tv_total);
        cb_radio = (CheckBox) findViewById(R.id.cb_radio);
        cartAdapter = new CartAdapter(this, list);
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
}
