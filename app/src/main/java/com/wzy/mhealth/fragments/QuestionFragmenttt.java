package com.wzy.mhealth.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.RecommandActivity;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragmenttt extends Fragment {
    private Button tv_down, tv_up;
    private RadioButton cb_a, cb_b;
    private RadioGroup rg_data;
    private EditText et_advice;
    String choice;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 230:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (stepInfo.getStatus().equals("1")) {
                        Intent intent = new Intent(getActivity(), RecommandActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_question_fragmenttt, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        tv_down = (Button) v.findViewById(R.id.tv_down);
        tv_up = (Button) v.findViewById(R.id.tv_up);
        et_advice = (EditText) v.findViewById(R.id.et_advice);
        tv_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        });
        tv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Constants.SERVER_URL + "MhealthUserSurveyCountServlet";
                TiUser user = new TiUser();
                user.setName(choice + "");
                MyHttpUtils.handData(handler, 230, url, user);
            }
        });
        cb_a = (RadioButton) v.findViewById(R.id.cb_a);
        cb_b = (RadioButton) v.findViewById(R.id.cb_b);
        tv_down.setEnabled(false);
        rg_data = (RadioGroup) v.findViewById(R.id.rg_data);
        rg_data.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == cb_a.getId()) {
                    choice="1";
                    et_advice.setVisibility(View.VISIBLE);
                    if (!("").equals(et_advice.getText().toString())) {
                        tv_down.setEnabled(true);
                        tv_down.setBackgroundResource(R.drawable.textview_1);
                    } else {
                        Toast.makeText(getContext(), "输入不能为空", Toast.LENGTH_LONG).show();
                    }

                } else if (checkedId == cb_b.getId()) {
                    choice="2";
                    tv_down.setEnabled(true);
                    et_advice.setVisibility(View.GONE);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                } else {
                    et_advice.setVisibility(View.GONE);
                    tv_down.setEnabled(false);
                    tv_down.setBackgroundResource(R.drawable.textview3);
                }
            }
        });

    }


}
