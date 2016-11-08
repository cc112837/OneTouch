package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;

public class PopupActivity extends Activity {
    private Spinner sp_time;
    private Spinner sp_data;
    private EditText et_phone;
    private TextView tv_cancle;
    private TextView tv_confirm;
    private String time;
    private AdapterView.OnItemSelectedListener listener;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        Window window=getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.flags=WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        wl.alpha=1.0f;   // 　　这句就是设置窗口里控件的透明度的．０.０全透明．１.０不透明．
        window.setAttributes(wl);
        init();
    }

    private void init() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        sp_data = (Spinner) findViewById(R.id.sp_data);
        sp_time = (Spinner) findViewById(R.id.sp_time);
        tv_cancle = (TextView) findViewById(R.id.tv_cancle);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        sp_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_data.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et_phone.getText().toString();
                Toast.makeText(PopupActivity.this,data + "***" + time + "dianhua" + phone,Toast.LENGTH_LONG).show();
                Log.e("jieguoshi", data + "***" + time + "dianhua" + phone);
            }
        });
    }


}
