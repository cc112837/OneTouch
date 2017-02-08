package com.wzy.mhealth.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.wzy.mhealth.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScannerImageAcitvity extends AppCompatActivity {

    @Bind(R.id.btn_back)
    Button btnBack;
    @Bind(R.id.scanresult_tv)
    ImageView scanresultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_image_acitvity);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Bitmap bitmap =  bundle.getParcelable("bitmap");
        scanresultTv.setImageBitmap(bitmap);
    }

    @OnClick(R.id.btn_back)
    public void onClick() {
        finish();
    }
}
