package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.QuestionActivity;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.ToastUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment1 extends Fragment implements TextWatcher {
    private Button tv_down;
    private EditText et_weight, et_height, et_age;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 223:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (stepInfo.getStatus().equals("1")) {
                        Fragment fragment = new QuestionFragment2();
                        ChangeFragmentHelper helper = new ChangeFragmentHelper();
                        helper.setTargetFragment(fragment);
                        helper.setTargetFragmentTag("fragment1");
                        ((QuestionActivity) getActivity()).changeFragment(helper);
                    }

                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_fragment1, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        tv_down = (Button) v.findViewById(R.id.tv_down);
        et_weight = (EditText) v.findViewById(R.id.et_weight);
        et_height = (EditText) v.findViewById(R.id.et_height);
        et_age = (EditText) v.findViewById(R.id.et_age);
        et_weight.addTextChangedListener(this);
        et_age.addTextChangedListener(this);
        et_height.addTextChangedListener(this);
        tv_down.setEnabled(false);
        tv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(et_age.getText().toString()) < 0 || Integer.parseInt(et_age.getText().toString()) > 100) {
                    Toast.makeText(getActivity(), "年龄输入不符合规范", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(et_height.getText().toString()) < 50 || Integer.parseInt(et_height.getText().toString()) > 220) {
                    ToastUtil.show(getActivity(), "身高不符合规范");
                } else if (Integer.parseInt(et_weight.getText().toString()) < 1|| Integer.parseInt(et_weight.getText().toString()) > 150) {
                    ToastUtil.show(getActivity(), "体重不符合规范");
                } else {
                    String url = Constants.SERVER_URL + "MhealthUserSurveyCountServlet";
                    TiUser user = new TiUser();
                    user.setName(et_age.getText().toString() + "");
                    user.setTel(et_height.getText().toString() + "");
                    user.setPass(et_weight.getText().toString() + "");
                    MyHttpUtils.handData(handler, 223, url, user);
                }
            }
        });


    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String weight = et_weight.getText().toString();
        String height = et_height.getText().toString();
        String age = et_age.getText().toString();
        if (("").equals(weight) || ("").equals(height) || ("").equals(age)) {
            tv_down.setEnabled(false);
        } else {
            tv_down.setEnabled(true);
            tv_down.setBackgroundResource(R.drawable.textview_1);
        }
    }
}
