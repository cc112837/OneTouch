package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class ShopDetailActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn,rightBtn;
    private TextView tv_count,title_0,title,title_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        rightBtn=(ImageView) findViewById(R.id.rightBtn);
        tv_count=(TextView) findViewById(R.id.tv_count);
        title_0=(TextView) findViewById(R.id.title_0);
        title_2=(TextView) findViewById(R.id.title_2);
        tv_count=(TextView) findViewById(R.id.tv_count);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.rightBtn:
                Intent intent=new Intent(ShopDetailActivity.this,CartActivity.class);
                startActivity(intent);
                break;
        }
    }
}
