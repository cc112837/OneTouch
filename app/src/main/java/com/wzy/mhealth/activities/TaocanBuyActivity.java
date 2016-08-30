package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.view.PayRadioGroup;
import com.wzy.mhealth.view.PayRadioPurified;

public class TaocanBuyActivity extends Activity {
    private ImageView leftBtn;
    private Button btn_pay;
    private LinearLayout ll_youhui;
    private PayRadioGroup pay_fun;
    private PayRadioPurified bank, alipay;
    private TextView tv_price, newid,tv_name;
    private String name,price,old;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_buy);
        Intent intent=getIntent();
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        old = intent.getStringExtra("old");
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bank = (PayRadioPurified) findViewById(R.id.bank);
        pay_fun = (PayRadioGroup) findViewById(R.id.pay_fun);
        alipay = (PayRadioPurified) findViewById(R.id.alipay);
        tv_price = (TextView) findViewById(R.id.tv_price);
        ll_youhui = (LinearLayout) findViewById(R.id.ll_youhui);
        tv_name=(TextView) findViewById(R.id.tv_name);
        newid = (TextView) findViewById(R.id.newid);
        tv_name.setText(name+"");
        newid.setText(price + "元");
        tv_price.setText(price + "元");
        pay_fun.setOnCheckedChangeListener(new PayRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(PayRadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                PayRadioPurified rl = (PayRadioPurified) TaocanBuyActivity.this
                        .findViewById(radioButtonId);
                for (int i = 0; i < group.getChildCount(); i++) {
                    ((PayRadioPurified) group.getChildAt(i)).setChangeImg(checkedId);
                }
                Toast.makeText(TaocanBuyActivity.this, rl.getTextTitle(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
        ll_youhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaocanBuyActivity.this, "暂无优惠劵！", Toast.LENGTH_LONG).show();
            }
        });
        btn_pay = (Button) findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
