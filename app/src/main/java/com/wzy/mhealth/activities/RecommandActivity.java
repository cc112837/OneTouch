package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.ReDefine;
import com.wzy.mhealth.model.Recommend;
import com.wzy.mhealth.utils.MyHttpUtils;

public class RecommandActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private LinearLayout ll_recom;
    private ImageView recom_img;
    private TextView tv_recommend, tv_restart;
    String id = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 222:
                    Recommend recommend = (Recommend) msg.obj;
                    if (recommend.getStatus().equals("1")) {
                        id = recommend.getTaocanId()+"";
                        ImageLoader.getInstance().displayImage("",recom_img);
                        tv_recommend.setText(recommend.getTaocanName()+"");
                    }
                    break;
                case 261:
                    ReDefine reDefine=(ReDefine) msg.obj;
                    if(reDefine.getStatus().equals("1")){
                        Intent intent1 = new Intent(RecommandActivity.this, QuestionActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand);
        init();
    }

    private void init() {
        String url = Constants.SERVER_URL + "MhealthUserTaocanSurveyServlet";
        MyHttpUtils.handData(handler, 222, url, null);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        tv_recommend = (TextView) findViewById(R.id.tv_recommend);
        recom_img = (ImageView) findViewById(R.id.recom_img);
        tv_restart = (TextView) findViewById(R.id.tv_restart);
        ll_recom = (LinearLayout) findViewById(R.id.ll_recom);
        leftBtn.setOnClickListener(this);
        ll_recom.setOnClickListener(this);
        tv_restart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.ll_recom:
                Intent intent = new Intent(RecommandActivity.this, TaocanDetailAcitivty.class);
                intent.putExtra("id",id+"");
                startActivity(intent);
                break;
            case R.id.tv_restart:
                String ut=Constants.SERVER_URL+"MhealthUserReSurveyServlet";
                MyHttpUtils.handData(handler, 261, ut, null);
                break;
        }
    }
}
