package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.avos.avoscloud.AVAnalytics;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.EvaluationListAdapter;
import com.wzy.mhealth.inter.XmppConnection;
import com.wzy.mhealth.model.UserEvaluation;

import java.util.ArrayList;
import java.util.List;

public class EvaluationListActivity extends Activity {
    private List<UserEvaluation> evaluationList;
    private ListView listView;
    private EvaluationListAdapter adapter;
    private String doctorId;
    private ImageView leftBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_list);
        doctorId = getIntent().getStringExtra("doctorId");
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.doctorlist);
        evaluationList = new ArrayList<UserEvaluation>();
        evaluationList.addAll(XmppConnection.getInstance().getUserEvaluation(doctorId, "0"));
        adapter = new EvaluationListAdapter(this, evaluationList);
        listView.setAdapter(adapter);
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
