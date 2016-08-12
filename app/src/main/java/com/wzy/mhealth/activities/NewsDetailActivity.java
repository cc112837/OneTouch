package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 创建时间：2016/7/18
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.NewDetail;
import com.wzy.mhealth.utils.MyHttpUtils;

public class NewsDetailActivity extends BaActivity {
    private ImageView leftBtn;
    private WebView wv_dis;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 22:
                    NewDetail newDetail = (NewDetail) msg.obj;
                    WebSettings webSettings= wv_dis.getSettings();
                    webSettings.setUseWideViewPort(true);
                    webSettings.setTextZoom(120);
                    wv_dis.loadData(newDetail.getData().getContent().getArticle().getHtml(), "text/html; charset=utf-8", "utf-8");

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Intent intent = getIntent();
        String contentid = intent.getStringExtra("content");
        wv_dis=(WebView) findViewById(R.id.wv_dis);
        String url = "http://api.m.vodjk.com/v1/article?contentid=" + contentid + "&height=667.0&ip=192.168.1.107&modules=content%3A1&sign=983a172c96d973a4a528c02844bea991&time=1468463373&token=3&type=android&width=375.0";
        MyHttpUtils.handData(handler, 22, url, null);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
