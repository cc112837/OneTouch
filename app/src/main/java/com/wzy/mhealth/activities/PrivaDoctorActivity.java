package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class PrivaDoctorActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priva_doctor);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        title=(TextView) findViewById(R.id.title);
        leftBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
        }
    }
}
