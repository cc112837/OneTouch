package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.SlowAidAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlowAidActivity extends Activity {
    private ListView lv_show;
    private ImageView leftBtn;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slow_aid);
        init();
    }

    private void init() {
        lv_show = (ListView) findViewById(R.id.lv_show);
        SlowAidAdapter slowAidAdapter = new SlowAidAdapter(SlowAidActivity.this, list);
        lv_show.setAdapter(slowAidAdapter);

        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
