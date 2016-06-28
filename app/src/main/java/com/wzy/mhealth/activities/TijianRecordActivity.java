package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;

public class TijianRecordActivity extends Activity {
    private ImageView leftBtn;
    private TextView select;
    private EditText tijianid, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_record);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        tijianid = (EditText) findViewById(R.id.tijianid);
        name = (EditText) findViewById(R.id.name);
        select = (TextView) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/6/24
                String tijian = tijianid.getText().toString();
                String nameid = name.getText().toString();
                Toast.makeText(TijianRecordActivity.this, tijian + "" + nameid, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TijianRecordActivity.this, RecordShowActivity.class);
                startActivity(intent);
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
