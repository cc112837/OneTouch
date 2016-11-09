package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.wzy.mhealth.R;

public class SlowAidDetailActivity extends Activity {
    private WebView wv_show;
    private ImageView leftBtn;
    private Button btn_select;

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
        final String id=intent.getStringExtra("id");
        WebSettings webSettings = wv_show.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setTextZoom(120);
        wv_show.loadData(detail, "text/html; charset=utf-8", "utf-8");
        btn_select = (Button) findViewById(R.id.btn_select);
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SlowAidDetailActivity.this,PopupActivity.class);
                intent.putExtra("content",id);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        setBackgroundAlpha(0.5f);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setBackgroundAlpha(1.0f);
    }
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }
}
