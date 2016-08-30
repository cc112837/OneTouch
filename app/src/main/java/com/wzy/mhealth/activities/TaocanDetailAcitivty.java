package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class TaocanDetailAcitivty extends Activity {
    private ImageView leftBtn;
    private TextView tv_old, tv_new, tv_buy,titleView;
    private String name,price,old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_detail_acitivty);
        Intent intent = getIntent();
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
        tv_old = (TextView) findViewById(R.id.tv_old);
        tv_new = (TextView) findViewById(R.id.tv_new);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        titleView=(TextView) findViewById(R.id.titleView);
        tv_old.setText(old+"元");
        titleView.setText(name+"");
        tv_new.setText(price+"元");
        tv_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaocanDetailAcitivty.this, TaocanBuyActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("price",price);
                intent.putExtra("old",old);
                startActivity(intent);
            }
        });
        tv_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
    }
}
