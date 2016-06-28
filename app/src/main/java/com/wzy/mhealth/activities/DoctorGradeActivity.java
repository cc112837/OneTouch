package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class DoctorGradeActivity extends Activity {
    private ImageView leftBtn, iv_head;
    private CheckBox iv_1, iv_2, iv_3, iv_4, iv_5;
    private TextView tv_submit;
    private EditText tv_contentid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_grade);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        tv_contentid=(EditText) findViewById(R.id.tv_contentid);
        String content=tv_contentid.getText().toString();
        tv_submit=(TextView) findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/6/27 (提交医生评价)
            }
        });
        iv_1 = (CheckBox) findViewById(R.id.iv_1);
        iv_2 = (CheckBox) findViewById(R.id.iv_2);
        iv_3 = (CheckBox) findViewById(R.id.iv_3);
        iv_4 = (CheckBox) findViewById(R.id.iv_4);
        iv_5 = (CheckBox) findViewById(R.id.iv_5);
        iv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.greystar2);
                iv_3.setBackgroundResource(R.mipmap.greystar2);
                iv_4.setBackgroundResource(R.mipmap.greystar2);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
            }
        });
        iv_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.greystar2);
                iv_4.setBackgroundResource(R.mipmap.greystar2);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
            }
        });
        iv_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.goldstar1);
                iv_4.setBackgroundResource(R.mipmap.greystar2);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
            }
        });
        iv_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.goldstar1);
                iv_4.setBackgroundResource(R.mipmap.goldstar1);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
            }
        });
        iv_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.goldstar1);
                iv_4.setBackgroundResource(R.mipmap.goldstar1);
                iv_5.setBackgroundResource(R.mipmap.goldstar1);
            }
        });

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
