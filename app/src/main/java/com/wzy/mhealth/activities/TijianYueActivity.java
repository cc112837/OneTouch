package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;

public class TijianYueActivity extends Activity {
    private ImageView leftBtn;
    private TextView submit;
    private EditText nameid,idcard;//姓名，身份证号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_yue);
        init();
    }

    private void init() {
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        submit=(TextView) findViewById(R.id.submit);
        nameid=(EditText) findViewById(R.id.nameid);
        idcard=(EditText) findViewById(R.id.idcard);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/6/24
                String name = nameid.getText().toString();
                String cardid = idcard.getText().toString();
                Toast.makeText(TijianYueActivity.this,name+""+cardid,Toast.LENGTH_LONG).show();
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
