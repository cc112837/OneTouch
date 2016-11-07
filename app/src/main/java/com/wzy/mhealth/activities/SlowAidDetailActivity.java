package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.wzy.mhealth.R;

public class SlowAidDetailActivity extends Activity {
    private WebView wv_show;
    private ImageView leftBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slow_aid_detail);
        wv_show = (WebView) findViewById(R.id.wv_show);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        String detail = intent.getStringExtra("detail");
        WebSettings webSettings = wv_show.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setTextZoom(120);
        wv_show.loadData(detail, "text/html; charset=utf-8", "utf-8");
    }
}
