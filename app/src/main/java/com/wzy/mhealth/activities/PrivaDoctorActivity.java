package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.BingZhen;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

public class PrivaDoctorActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private WebView wv_show;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 265:
                    BingZhen bingZhen = (BingZhen) msg.obj;
                    WebSettings webSettings = wv_show.getSettings();
                    webSettings.setUseWideViewPort(true);
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
                    WindowManager wm = PrivaDoctorActivity.this.getWindowManager();
                    int width = wm.getDefaultDisplay().getWidth()/2-10;
                    wv_show.loadDataWithBaseURL(null, "<head><style>img{max-width:" + width + "px !important;}</style></head>" + bingZhen.getData().get(0).getDetails(), "text/html", "utf-8", null);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priva_doctor);
        String url = Constants.SERVER_URL + "MedicalCommonServlet";
        TiUser user = new TiUser();
        user.setName("1");
        MyHttpUtils.handData(handler, 265, url, user);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(this);
        wv_show=(WebView) findViewById(R.id.wv_show);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
        }
    }
}
