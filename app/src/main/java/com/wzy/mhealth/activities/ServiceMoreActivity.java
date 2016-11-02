package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class ServiceMoreActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private TextView tv_hospital, tv_medcine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_more);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(this);
        tv_hospital = (TextView) findViewById(R.id.tv_hospital);
        tv_hospital.setOnClickListener(this);
        tv_medcine=(TextView) findViewById(R.id.tv_medcine);
        tv_medcine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
            case R.id.tv_hospital:
                Intent intentt1 = new Intent();
                intentt1.setClass(ServiceMoreActivity.this, PoiKeywordSearchActivity.class);
                intentt1.putExtra("keyword", "医院");
                startActivity(intentt1);
                break;
            case  R.id.tv_medcine:
                Intent intent4 = new Intent();
                intent4.setClass(ServiceMoreActivity.this, PoiKeywordSearchActivity.class);
                intent4.putExtra("keyword", "药房");
                startActivity(intent4);
                break;
        }
    }
}
