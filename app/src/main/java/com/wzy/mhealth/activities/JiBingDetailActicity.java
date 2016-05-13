package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.MeNewsDa;

public class JiBingDetailActicity extends Activity {
    private ImageView leftBtn;
    private TextView tv_title;
    private WebView wb_content;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 77:
                    MeNewsDa meNewsDa = (MeNewsDa) msg.obj;
                    tv_title.setText(meNewsDa.getTitle());
                    WebSettings webSettings= wb_content.getSettings();
                    webSettings.setUseWideViewPort(true);
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setTextZoom(300);
                    wb_content.loadData(meNewsDa.getMessage(), "text/html; charset=utf-8", "utf-8");

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_bing_detail_acticity);

        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        new HttpUtils().send(HttpRequest.HttpMethod.GET, "http://www.tngou.net/api/info/show?id=" + id, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                MeNewsDa meNewsDa = new Gson().fromJson(responseInfo.result, MeNewsDa.class);
                Message message = new Message();
                message.what=77;
                message.obj=meNewsDa;
                handler.sendMessage(message);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.e("失败",msg);
            }
        });
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        tv_title=(TextView) findViewById(R.id.tv_title);
        wb_content=(WebView) findViewById(R.id.wb_content);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
