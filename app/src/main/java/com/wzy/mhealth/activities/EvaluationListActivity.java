package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.EvaluationListAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.UserEvaluation;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class EvaluationListActivity extends BaActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private List<UserEvaluation.DataEntity> evaluationList;
    private ListView listView;
    private EvaluationListAdapter adapter;
    private String doctorId;
    private ImageView leftBtn;
    private RadioGroup rg_all;
    private RadioButton tv_all, tv_satis, tv_nosatis;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_list);
        doctorId = getIntent().getStringExtra("doctorId");
        status = getIntent().getStringExtra("status");
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        rg_all = (RadioGroup) findViewById(R.id.rg_all);
        tv_all = (RadioButton) findViewById(R.id.tv_all);
        tv_satis = (RadioButton) findViewById(R.id.tv_satis);
        tv_nosatis = (RadioButton) findViewById(R.id.tv_nosatis);
        leftBtn.setOnClickListener(this);
        rg_all.setOnCheckedChangeListener(this);
        listView = (ListView) findViewById(R.id.doctorlist);
        evaluationList = new ArrayList<>();
        adapter = new EvaluationListAdapter(this, evaluationList);
        listView.setAdapter(adapter);
        String url = Constants.SERVER_URL + "MhealthOrderEvaluateCheckServlet";
        TiUser user = new TiUser();
        user.setName(doctorId + "");
        user.setPass(status + "");
        MyHttpUtils.handData(handler, 264, url, user);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 264:
                    UserEvaluation userEvaluation = (UserEvaluation) msg.obj;
                    evaluationList.clear();
                    evaluationList.addAll(userEvaluation.getData());
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;

        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == tv_all.getId()){

        }else if(checkedId == tv_satis.getId()){

        }else{//tv_nosatis

        }
    }
}
