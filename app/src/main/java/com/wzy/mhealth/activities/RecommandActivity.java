package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class RecommandActivity extends Activity {
private ImageView leftBtn,iv_img;
    private TextView tv_start,tv_name;
    private LinearLayout ll_taocan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand);
       init();
}

    private void init() {
        leftBtn=(ImageView)findViewById(R.id.leftBtn);
        tv_start=(TextView) findViewById(R.id.tv_start);
        ll_taocan=(LinearLayout) findViewById(R.id.ll_taocan);
        iv_img=(ImageView) findViewById(R.id.iv_img);
        tv_name=(TextView) findViewById(R.id.tv_name);
        ll_taocan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecommandActivity.this, TaocanDetailAcitivty.class);
                intent.putExtra("id", "");
                startActivity(intent);
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecommandActivity.this,QuestionActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
