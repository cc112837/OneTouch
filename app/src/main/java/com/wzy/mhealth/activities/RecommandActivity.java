package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class RecommandActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private LinearLayout ll_recom;
    private ImageView recom_img;
    private TextView tv_recommend, tv_restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand);
        init();
    }

    private void init() {
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
                intent.putExtra("id", "");
                startActivity(intent);
                break;
            case R.id.tv_restart:
                Intent intent1 = new Intent(RecommandActivity.this, QuestionActivity.class);
                startActivity(intent1);
                finish();
                break;
        }
    }
}
