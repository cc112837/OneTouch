package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.wzy.mhealth.R;

public class JiankangActivity extends Activity {

    TextView basic, history, shoushu;
    String bas,his,sho;
    ImageView leftBtn;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jiankang);
        Intent intent = getIntent();
        bas = intent.getStringExtra("basic");
        his =  intent.getStringExtra("history");
        sho =  intent.getStringExtra("shoushu");
        basic = (TextView) findViewById(R.id.basic);
        history = (TextView) findViewById(R.id.histroy);
        shoushu = (TextView) findViewById(R.id.shoushu);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(bas != null)
            basic.setText(bas+"岁");
        else
            basic.setText("");
        if(his != null)
            history.setText(his);
        else
            history.setText("");
        if(sho != null)
            shoushu.setText(sho);
        else
            shoushu.setText("");


    }
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }
}
