package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wzy.mhealth.R;

public class StepCountActivity extends Activity {
    private ImageView leftBtn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);
        init();
    }

    private void init() {
        leftBtn_back=(ImageView) findViewById(R.id.leftBtn_back);
        leftBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
