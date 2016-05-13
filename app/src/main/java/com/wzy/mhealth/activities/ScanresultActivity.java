package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class ScanresultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanresult);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String num = bundle.getString("num");
        TextView scanresult_tv=(TextView)findViewById(R.id.scanresult_tv);
        scanresult_tv.setText(num);
    }



}