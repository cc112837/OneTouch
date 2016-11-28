package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.ShopBuyAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.DefaultAdress;
import com.wzy.mhealth.model.ShopBuy;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class ShopBuyActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private View headview=null;
    private LinearLayout ll_have;
    private TextView tv_name, tv_total, tv_cal,tv_newadd;
    private TextView tv_tel;
    private TextView tv_address;
    private ListView lv_shopbuy;
    private List<ShopBuy.DataEntity> list = new ArrayList<>();
    private ShopBuyAdapter shopBuyAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 281:
                    DefaultAdress defaultAdress = (DefaultAdress) msg.obj;
                    if (defaultAdress.getStatus().equals("1")) {
                       ll_have.setVisibility(View.VISIBLE);
                        tv_newadd.setVisibility(View.GONE);
                        tv_name.setText(defaultAdress.getName() + "");
                        tv_tel.setText(defaultAdress.getTelephone() + "");
                        tv_address.setText(defaultAdress.getAddress());
                    } else {
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
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv_shopbuy = (ListView) findViewById(R.id.lv_shopbuy);
        shopBuyAdapter = new ShopBuyAdapter(this, list);
        lv_shopbuy.setAdapter(shopBuyAdapter);
        headview = LayoutInflater.from(ShopBuyActivity.this).inflate(R.layout.shoporder_header, null);
        lv_shopbuy.addHeaderView(headview);
        tv_name = (TextView) headview.findViewById(R.id.tv_name);
        tv_tel = (TextView) headview.findViewById(R.id.tv_tel);
        tv_address = (TextView) headview.findViewById(R.id.tv_address);
        tv_newadd= (TextView) headview.findViewById(R.id.tv_newadd);
        ll_have=(LinearLayout) headview.findViewById(R.id.ll_have);
        String url = Constants.SERVER_URL + "MhealthShopAddressDefaultServlet";
        MyHttpUtils.handData(handler, 281, url, null);
        tv_cal = (TextView) findViewById(R.id.tv_cal);
        tv_total = (TextView) findViewById(R.id.tv_total);
        tv_total.setText("实付款：¥" + 20);
        tv_cal.setText("去结算（" + 2 + ")");
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
        Intent intent;
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_cal:
                Toast.makeText(ShopBuyActivity.this, "结算", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
