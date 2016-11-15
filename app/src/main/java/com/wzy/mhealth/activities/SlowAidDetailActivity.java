package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
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
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        int screenWidth = dm.widthPixels/2-20;
        wv_show.loadDataWithBaseURL(null, "<head><style>img{max-width:" + screenWidth + "px!important;}</style></head>" +detail, "text/html", "utf-8", null);
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
