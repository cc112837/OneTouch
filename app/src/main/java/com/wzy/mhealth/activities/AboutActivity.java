package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.utils.PackageUtils;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 关于页面
*/
public class AboutActivity extends BaActivity {
    private TextView tv_version, tv_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        tv_service = (TextView) findViewById(R.id.tv_service);
        tv_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, ServicePriActivity.class);
                startActivity(intent);
            }
        });
        tv_version = (TextView) findViewById(R.id.tv_version);
        tv_version.setText("版本：" + PackageUtils.getPackageVersion(this));
        ImageView leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
