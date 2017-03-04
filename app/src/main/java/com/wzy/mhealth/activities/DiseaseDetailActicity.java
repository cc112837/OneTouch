package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 疾病诊疗详情
*/
public class DiseaseDetailActicity extends BaActivity {
    private ImageView leftBtn;
    private TextView tv_title,tv_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_bing_detail_acticity);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String title = intent.getStringExtra("title");
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        tv_title=(TextView) findViewById(R.id.tv_title);
        tv_content=(TextView) findViewById(R.id.tv_content);
        tv_title.setText(title);
        tv_content.setText(content);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
