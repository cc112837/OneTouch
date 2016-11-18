package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 优惠劵
 * 创建时间：2016/11/18 16:18
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wzy.mhealth.R;

public class DecreseActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrese);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(this);
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
