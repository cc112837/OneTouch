package com.wzy.mhealth.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Info;
import com.wzy.mhealth.utils.MyHttpUtils;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 体检报告页面
*/
public class ExaminationRecordActivity extends BaActivity {
    private ImageView leftBtn;
    private Button select;
    private String tag;
    private EditText tijianid, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_record);
        init();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    Info info = (Info) msg.obj;
                    if (info.isSuccess()) {
                        tag = info.getMsg();
                        Intent intent = new Intent(ExaminationRecordActivity.this, RecordListActivity.class);
                        intent.putExtra("session", tag);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ExaminationRecordActivity.this, "请核实信息是否正确!", Toast.LENGTH_LONG).show();
                    }

                    break;
            }


        }
    };

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        tijianid = (EditText) findViewById(R.id.tijianid);
        name = (EditText) findViewById(R.id.name);
        SharedPreferences sp = getSharedPreferences("et", MODE_PRIVATE);
        String tijian = sp.getString("card", null);
        String pass = sp.getString("pass", null);
        tijianid.setText(tijian);
        name.setText(pass);
        select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String card = tijianid.getText().toString();
                String mi = name.getText().toString();
                if (("").equals(card) ||("").equals(mi)) {
                    Toast.makeText(ExaminationRecordActivity.this, "输入不能为空!", Toast.LENGTH_LONG).show();
                } else {
                    String loginurl = Constants.SERVER_ZHIXING+"personAction!app_login.action?idnumber=" + card + "&passwd=" + mi;
                    MyHttpUtils.handData(handler, 2, loginurl, null);
                }

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
