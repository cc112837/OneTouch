package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.avos.avoscloud.AVAnalytics;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.YuyueAdapter;
import com.wzy.mhealth.inter.XmppConnection;
import com.wzy.mhealth.model.YuyueDoctor;

import java.util.ArrayList;
import java.util.List;

public class YuyueListActivity extends Activity {
    private List<YuyueDoctor> yuyueDoctorList;
    private YuyueAdapter adapter;
    private ListView list = null;
    private TextView text = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuyue_list);

        yuyueDoctorList = new ArrayList<YuyueDoctor>();
        init();
        list = (ListView) findViewById(R.id.yuyueList);
        adapter = new YuyueAdapter(this, yuyueDoctorList);
        text = (TextView) findViewById(R.id.number);
        text.setText("你已经预约了"+yuyueDoctorList.size()+"名医生");
        list.setAdapter(adapter);
        //返回箭头
        back();
    }

    private void back() {
        // TODO Auto-generated method stub
        ImageView imageback = (ImageView) findViewById(R.id.leftBtn);

        imageback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void init() {
        yuyueDoctorList.addAll(XmppConnection.getInstance().getOwnGuahaoInfo());

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
