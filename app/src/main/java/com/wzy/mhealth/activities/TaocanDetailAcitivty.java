package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.TaocanDetail;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

public class TaocanDetailAcitivty extends Activity {
    private ImageView leftBtn, iv_detail;
    private TextView tv_old, tv_new, tv_buy, titleView;
    private String id;
    private WebView wv_show;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 116:
                    final TaocanDetail taocanDetail = (TaocanDetail) msg.obj;
                    tv_old.setText(taocanDetail.getOldPrice() + "元");
                    titleView.setText(taocanDetail.getName() + "");
                    tv_new.setText(taocanDetail.getNewPrice() + "元");
                    tv_buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(TaocanDetailAcitivty.this, TaocanBuyActivity.class);
                            intent.putExtra("name", taocanDetail.getName() + "");
                            intent.putExtra("price", taocanDetail.getNewPrice() + "");
                            intent.putExtra("old", taocanDetail.getOldPrice() + "");
                            intent.putExtra("id", taocanDetail.getId() + "");
                            startActivity(intent);
                        }
                    });
                    WebSettings webSettings = wv_show.getSettings();
                    webSettings.setUseWideViewPort(true);
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setTextZoom(240);
                    webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
                    wv_show.loadData(taocanDetail.getContext(), "text/html; charset=utf-8", "utf-8");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_detail_acitivty);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String url = Constants.SERVER_URL + "TaoCanPayServlet";
        TiUser user = new TiUser();
        user.setName(id + "");
        MyHttpUtils.handData(handler, 116, url, user);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_old = (TextView) findViewById(R.id.tv_old);
        tv_new = (TextView) findViewById(R.id.tv_new);
        tv_buy = (TextView) findViewById(R.id.tv_buy);
        wv_show = (WebView) findViewById(R.id.wv_show);
        titleView = (TextView) findViewById(R.id.titleView);
//        iv_detail = (ImageView) findViewById(R.id.iv_detail);
        tv_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
    }
}
