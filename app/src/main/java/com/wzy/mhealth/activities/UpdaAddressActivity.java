package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class UpdaAddressActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftBtn;
    private String flag;
    private Switch sw_default;
    private EditText et_adddetail, et_city, et_tel, tv_who;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upda_address);
        Intent intent = getIntent();
        flag = intent.getStringExtra("flag");
        init();
    }

    private void init() {
        title = (TextView) findViewById(R.id.title);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        et_adddetail = (EditText) findViewById(R.id.et_adddetail);
        et_city = (EditText) findViewById(R.id.et_city);
        et_tel = (EditText) findViewById(R.id.et_tel);
        tv_who = (EditText) findViewById(R.id.tv_who);

        sw_default = (Switch) findViewById(R.id.sw_default);
        sw_default.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    sw_default.setText("开");
                } else {
                    sw_default.setText("关");
                }
            }
        });
        if ("new".equals(flag)) {
            title.setText("新增收货地址");
        } else {
            title.setText("修改收货地址");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
        }
    }
}
