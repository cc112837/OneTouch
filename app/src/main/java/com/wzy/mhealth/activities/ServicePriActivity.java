package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.wzy.mhealth.R;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 服务条款
*/
public class ServicePriActivity extends BaActivity {
    private ImageView leftBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_pri);
        WebView wv_show=(WebView)findViewById(R.id.wv_show);
        WebSettings webSettings = wv_show.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);//support zoom
        webSettings.setUseWideViewPort(true);// 这个很关键
        webSettings.setLoadWithOverviewMode(true);
        wv_show.loadUrl("http://www.yidianjiuhao.com/yinsi/index.html");//// TODO: 2016/7/25 (服务协议)
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
