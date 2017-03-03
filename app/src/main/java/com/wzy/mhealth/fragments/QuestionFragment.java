package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.QuestionActivity;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

/**
 * 问卷调查
 */
public class QuestionFragment extends Fragment {
    private TextView tv_submit;
    private RadioButton rb_woman, rb_man, cb_self, cb_parent, cb_love, cb_friend, cb_other;
    private RadioGroup rg_sex, rg_classfy;
    String identity;
    String sex;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 221:
                    StepInfo stepInfo=(StepInfo) msg.obj;
                    if(stepInfo.getStatus().equals("1")){
                        Fragment fragment = new QuestionFragment1();
                        ChangeFragmentHelper helper = new ChangeFragmentHelper();
                        helper.setTargetFragment(fragment);
                        helper.setIsClearStackBack(false);
                        ((QuestionActivity) getActivity()).changeFragment(helper);
                    }
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        tv_submit = (TextView) v.findViewById(R.id.tv_submit);
        rb_woman = (RadioButton) v.findViewById(R.id.rb_woman);

        cb_self = (RadioButton) v.findViewById(R.id.cb_self);
        cb_parent = (RadioButton) v.findViewById(R.id.cb_parent);
        cb_love = (RadioButton) v.findViewById(R.id.cb_love);
        cb_friend = (RadioButton) v.findViewById(R.id.cb_friend);
        cb_other = (RadioButton) v.findViewById(R.id.cb_other);
        rg_classfy = (RadioGroup) v.findViewById(R.id.rg_classfy);
        cb_self.setChecked(true);
        rg_classfy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == cb_self.getId()) {
                    identity="1";
                } else if (checkedId == cb_parent.getId()) {
                    identity="2";
                } else if (checkedId == cb_love.getId()) {
                    identity="3";
                } else if (checkedId == cb_friend.getId()) {
                    identity="4";
                } else if (checkedId == cb_other.getId()) {
                    identity="5";
                } else {
                    identity="1";
                }
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url= Constants.SERVER_URL+"MhealthUserSurveyCountServlet";
                TiUser user=new TiUser();
                user.setTel(sex + "");
                user.setName(identity + "");
                MyHttpUtils.handData(handler, 221, url, user);
            }
        });
        rb_man = (RadioButton) v.findViewById(R.id.rb_man);
        rb_man.setChecked(true);
        rg_sex = (RadioGroup) v.findViewById(R.id.rg_sex);
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb_man.getId()) {
                    sex="1";
                } else if (checkedId == rb_woman.getId()) {
                    sex="2";
                } else {
                    sex="1";
                }
            }
        });
    }


}
