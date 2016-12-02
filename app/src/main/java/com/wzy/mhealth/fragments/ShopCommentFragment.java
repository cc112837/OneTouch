package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.ShopDetailActivity;
import com.wzy.mhealth.adapter.EvaluationListAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.UserEvaluation;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class ShopCommentFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rg_all;
    private RadioButton tv_all, tv_satis, tv_nosatis;
    private ListView listView;
    private EvaluationListAdapter adapter;
    private List<UserEvaluation.DataEntity> evaluationList;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 291:
                    UserEvaluation userEvaluation=(UserEvaluation) msg.obj;
                    if(userEvaluation!=null){
                        evaluationList.clear();
                        evaluationList.addAll(userEvaluation.getData());
                        adapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_shop_comment, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        rg_all = (RadioGroup) v.findViewById(R.id.rg_all);
        tv_all = (RadioButton)v. findViewById(R.id.tv_all);
        tv_satis = (RadioButton) v.findViewById(R.id.tv_satis);
        tv_nosatis = (RadioButton) v.findViewById(R.id.tv_nosatis);
        rg_all.setOnCheckedChangeListener(this);
        listView = (ListView)v. findViewById(R.id.lv_comment);
        evaluationList = new ArrayList<>();
        adapter = new EvaluationListAdapter(getActivity(), evaluationList);
        listView.setAdapter(adapter);
        String url= Constants.SERVER_URL+"MhealthProductEvaluateCheckServlet";
        TiUser user=new TiUser();
        user.setName("0");
        user.setCardId(((ShopDetailActivity)getActivity()).getId());
        MyHttpUtils.handData(handler,291,url,user);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        String url= Constants.SERVER_URL+"MhealthProductEvaluateCheckServlet";
        TiUser user=new TiUser();
        if (checkedId == tv_all.getId()) {
            user.setName("0");
        } else if (checkedId == tv_satis.getId()) {
            user.setName("1");
        } else {//tv_nosatis
            user.setName("2");
        }
        user.setCardId(((ShopDetailActivity)getActivity()).getId());
        MyHttpUtils.handData(handler,291,url,user);
    }
}
