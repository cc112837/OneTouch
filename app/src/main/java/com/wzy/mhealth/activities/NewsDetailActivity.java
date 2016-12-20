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
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.NewDetail;
import com.wzy.mhealth.model.TiUser;
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
                    DisplayMetrics dm = new DisplayMetrics();
                    //取得窗口属性
                    getWindowManager().getDefaultDisplay().getMetrics(dm);
                    //窗口的宽度
                    float density = dm.density;
                    float width = dm.widthPixels/density-15;
                    WebSettings webSettings = wv_dis.getSettings();
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setUseWideViewPort(false);  //将图片调整到适合webview的大小
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
                    wv_dis.loadDataWithBaseURL(null, "<head><style>img{max-width:" + width + "px!important;}</style></head>" + newDetail.getMedicalContext(), "text/html", "utf-8", null);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        Intent intent = getIntent();
        if (intent != null) {
            String contentid = intent.getStringExtra("content");
            if (contentid == null) {
                Toast.makeText(NewsDetailActivity.this, "没有具体内容", Toast.LENGTH_LONG).show();
            } else {
                wv_dis = (WebView) findViewById(R.id.wv_dis);
                String url = Constants.SERVER_URL+"MedicalArticleDetailServlet";
                TiUser user=new TiUser();
                user.setName(contentid+"");
                MyHttpUtils.handData(handler, 22, url, user);
            }
            leftBtn = (ImageView) findViewById(R.id.leftBtn);
            leftBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

}
