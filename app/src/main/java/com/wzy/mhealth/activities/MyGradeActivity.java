package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 我的积分页面
*/
public class MyGradeActivity extends Activity {
    private ImageView leftBtn;
    private TextView tv_count;
    private String count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_grade);
        Intent intent = getIntent();
         count = intent.getStringExtra("count");
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        tv_count=(TextView) findViewById(R.id.tv_count);
        tv_count.setText(count);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
