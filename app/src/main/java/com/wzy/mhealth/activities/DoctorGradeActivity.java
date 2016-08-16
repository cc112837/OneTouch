package com.wzy.mhealth.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wzy.mhealth.MyApplication;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Info;
import com.wzy.mhealth.utils.MyHttpUtils;

public class DoctorGradeActivity extends BaActivity {
    private ImageView leftBtn;
    private CheckBox iv_1, iv_2, iv_3, iv_4, iv_5;
    private Button tv_submit;
    private EditText tv_contentid;
    int grade,position;
    String sessid, rid, itemcode, studyid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_grade);
        Intent intent = getIntent();
        studyid = intent.getStringExtra("stuid");
        itemcode = intent.getStringExtra("codeid");
        sessid = intent.getStringExtra("session");
        rid = intent.getStringExtra("eid");
        position=intent.getIntExtra("list",100);
        grade = 5;
//        String seleurl="http://113.201.59.226:8081/Healwis/base/evaluationAction!app_getOne.action?sessid="+sessid+"&rid="+rid+"&itemcode="+itemcode;
        init();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Info info = (Info) msg.obj;
                    if (info.isSuccess()) {
                        Toast.makeText(DoctorGradeActivity.this, "评价成功", Toast.LENGTH_LONG).show();
                        SharedPreferences.Editor editor = MyApplication.share.edit();
                        editor.putString(position+"","cc");
                        editor.commit();
                        finish();
                    } else {
                        Toast.makeText(DoctorGradeActivity.this, "评价失败请稍后重试", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        tv_contentid = (EditText) findViewById(R.id.tv_contentid);
        final String content = tv_contentid.getText().toString();
        tv_submit = (Button) findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://113.201.59.226:8081/Healwis/base/evaluationAction!app_evalOne.action?sessid=" + sessid + "&rid=" + rid + "&itemcode=" + itemcode + "&studyid=" + studyid + "&etype=s&marks=" + grade + "&rdesc=" + content;
                MyHttpUtils.handData(handler, 2, url, null);
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
                grade = 1;
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
                grade = 2;
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
                grade = 3;
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
                grade = 4;
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
                grade = 5;
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
