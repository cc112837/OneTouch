package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 智行介绍页面
 * 邮箱:cc112837@163.com
 * 创建时间：2016/7/18 14:20
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        float density = dm.density;
        float width = dm.widthPixels/density-15;
        WebSettings webSettings = wv_show.getSettings();
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(false);  //将图片调整到适合webview的大小
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
        wv_show.loadDataWithBaseURL(null, "<head><style>img{max-width:"+width+"px!important;}</style></head>"+content, "text/html", "utf-8", null);
    }
}
