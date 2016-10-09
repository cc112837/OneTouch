package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 创建时间：2016/7/18 14:20
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.wzy.mhealth.R;

public class ZhixingIntroduceActivity extends BaActivity {
    private ImageView leftBtn;
    private WebView wv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhixing_introduce);
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        wv_show = (WebView) findViewById(R.id.wv_show);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WebSettings webSettings = wv_show.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setBuiltInZoomControls(true);
        webSettings.setTextZoom(240);
//        webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
        wv_show.loadData(content, "text/html; charset=utf-8", "utf-8");
    }
}
