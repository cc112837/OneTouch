package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
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
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 套餐详情页面
*/
public class TaocanDetailAcitivty extends Activity {
    private ImageView leftBtn, iv_detail;
    private TextView tv_old, tv_new, tv_buy, titleView,tv_how;
    private String id;
    private WebView wv_show;
    Intent intent;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 116:
                    final TaocanDetail taocanDetail = (TaocanDetail) msg.obj;
                    tv_old.setText("原价：¥" +taocanDetail.getOldPrice() + "元");
                    titleView.setText(taocanDetail.getName() + "");
                    tv_new.setText("¥" +taocanDetail.getNewPrice() + "元");
                    tv_buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1 = new Intent(TaocanDetailAcitivty.this, TaocanBuyActivity.class);
                            intent1.putExtra("name", taocanDetail.getName() + "");
                            intent1.putExtra("price", taocanDetail.getNewPrice() + "");
                            intent1.putExtra("old", taocanDetail.getOldPrice() + "");
                            intent1.putExtra("id", taocanDetail.getTaoId() + "");
                            setResult(456, intent);
                            startActivity(intent1);
                            TaocanDetailAcitivty.this.finish();
                        }
                    });
                    WebSettings webSettings = wv_show.getSettings();
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setJavaScriptEnabled(true);
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setDisplayZoomControls(false); //隐藏webview缩放按钮
                    DisplayMetrics dm = new DisplayMetrics();
                    //取得窗口属性
                    getWindowManager().getDefaultDisplay().getMetrics(dm);
                    //窗口的宽度
                    float density = dm.density;
                    float width = dm.widthPixels/density-15;
                    wv_show.loadDataWithBaseURL(null, "<head><style>img{max-width:" + width + "px!important;}</style></head>" + taocanDetail.getContext(), "text/html", "utf-8", null);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_detail_acitivty);
        intent = getIntent();
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
        tv_how=(TextView) findViewById(R.id.tv_how);
        tv_how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TaocanDetailAcitivty.this,EvaluationListActivity.class);
                intent.putExtra("doctorId",id);
                intent.putExtra("status","0");
                startActivity(intent);
            }
        });
        tv_old.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
    }
}
