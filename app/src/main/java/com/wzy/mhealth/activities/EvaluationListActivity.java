package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.EvaluationListAdapter;
import com.wzy.mhealth.model.UserEvaluation;

import java.util.ArrayList;
import java.util.List;

public class EvaluationListActivity extends BaActivity implements View.OnClickListener {
    private List<UserEvaluation> evaluationList;
    private ListView listView;
    private EvaluationListAdapter adapter;
    private String doctorId;
    private ImageView leftBtn;
    private TextView tv_all, tv_satis, tv_nosatis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_list);
        doctorId = getIntent().getStringExtra("doctorId");
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        tv_all = (TextView) findViewById(R.id.tv_all);
        tv_satis = (TextView) findViewById(R.id.tv_satis);
        tv_nosatis = (TextView) findViewById(R.id.tv_nosatis);
        leftBtn.setOnClickListener(this);
        tv_all.setOnClickListener(this);
        tv_satis.setOnClickListener(this);
        tv_nosatis.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.doctorlist);
        evaluationList = new ArrayList<>();
        evaluationList.addAll(null);
        adapter = new EvaluationListAdapter(this, evaluationList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_all:
                tv_all.setBackgroundResource(R.drawable.textview4);
                tv_all.setTextColor(R.color.title_green);
                tv_satis.setTextColor(R.color.dark_grey);
                tv_satis.setBackgroundResource(R.drawable.textview);
                tv_nosatis.setTextColor(R.color.dark_grey);
                tv_nosatis.setBackgroundResource(R.drawable.textview);
                break;
            case R.id.tv_satis:
                tv_satis.setBackgroundResource(R.drawable.textview4);
                tv_satis.setTextColor(R.color.title_green);
                tv_all.setTextColor(R.color.dark_grey);
                tv_all.setBackgroundResource(R.drawable.textview);
                tv_nosatis.setTextColor(R.color.dark_grey);
                tv_nosatis.setBackgroundResource(R.drawable.textview);
                break;
            case R.id.tv_nosatis:
                tv_nosatis.setBackgroundResource(R.drawable.textview4);
                tv_nosatis.setTextColor(R.color.title_green);
                tv_all.setTextColor(R.color.dark_grey);
                tv_all.setBackgroundResource(R.drawable.textview);
                tv_satis.setTextColor(R.color.dark_grey);
                tv_satis.setBackgroundResource(R.drawable.textview);
                break;
        }
    }
}
