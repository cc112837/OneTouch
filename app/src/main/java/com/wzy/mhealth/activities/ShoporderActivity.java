package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 创建时间：2016/11/15 15:41
 * 商品订单页面
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.ShopOrderAdapter;
import com.wzy.mhealth.model.ShopOrder;

import java.util.ArrayList;
import java.util.List;

public class ShoporderActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private ImageView leftBtn;
    private RadioGroup rg_all;
    private RadioButton rb_allshop, rb_wait, rb_shop, rb_complete;
    private ListView lv_shoporder;
    private ShopOrderAdapter shopOrderAdapter;
    private List<ShopOrder.DataEntity> list = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

            }
        }
    };


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoporder);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        rg_all = (RadioGroup) findViewById(R.id.rg_all);
        rb_allshop = (RadioButton) findViewById(R.id.rb_allshop);
        rb_wait = (RadioButton) findViewById(R.id.rb_wait);
        lv_shoporder = (ListView) findViewById(R.id.lv_shoporder);
        rb_complete = (RadioButton) findViewById(R.id.rb_complete);
        rb_shop = (RadioButton) findViewById(R.id.rb_shop);
        shopOrderAdapter = new ShopOrderAdapter(this, list);
        lv_shoporder.setAdapter(shopOrderAdapter);
        leftBtn.setOnClickListener(this);
        rg_all.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == rb_allshop.getId()) {

        } else if (checkedId == rb_wait.getId()) {

        } else if (checkedId == rb_shop.getId()) {

        } else {//已完成

        }
    }
}
