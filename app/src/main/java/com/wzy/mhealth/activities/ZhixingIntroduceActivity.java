package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 创建时间：2016/7/18 14:20
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
        WindowManager wm = ZhixingIntroduceActivity.this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth()/2-20;
        wv_show.loadDataWithBaseURL(null, "<head><style>img{max-width:"+width+"px !important;}</style></head>"+content, "text/html", "utf-8", null);
    }
}
