package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wzy.mhealth.R;

public class AidsManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aids_manager);
        String flag = getIntent().getStringExtra("flag");
        if ("new".equals("flag")) {

        } else {
        }
    }
}
