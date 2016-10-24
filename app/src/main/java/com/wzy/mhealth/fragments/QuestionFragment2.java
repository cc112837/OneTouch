package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.QuestionActivity;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment2 extends Fragment {
    private Button tv_down, tv_up;
    private CheckBox cb_a, cb_b, cb_c, cb_d;
    private List<String> list = new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 224:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (stepInfo.getStatus().equals("1")) {
                        Fragment fragment = new QuestionFragment3();
                        ChangeFragmentHelper helper = new ChangeFragmentHelper();
                        helper.setTargetFragment(fragment);
                        helper.setTargetFragmentTag("fragment2");
                        ((QuestionActivity) getActivity()).changeFragment(helper);
                    }
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_fragment2, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        cb_a = (CheckBox) v.findViewById(R.id.cb_a);
        cb_b = (CheckBox) v.findViewById(R.id.cb_b);
        cb_c = (CheckBox) v.findViewById(R.id.cb_c);
        cb_d = (CheckBox) v.findViewById(R.id.cb_d);
        tv_down = (Button) v.findViewById(R.id.tv_down);
        tv_down.setEnabled(false);
        tv_up = (Button) v.findViewById(R.id.tv_up);
        tv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Constants.SERVER_URL + "MhealthUserSurveyCountServlet";
                TiUser user = new TiUser();
                StringBuffer s=new StringBuffer();
                for(int i=0;i<list.size();i++){
                    s.append(list.get(i));
                }
                user.setName(s + "");
                MyHttpUtils.handData(handler, 224, url, user);

            }
        });


        tv_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                manager.popBackStack();
            }
        });
        cb_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(1 + "");
                    cb_d.setChecked(false);
                    if(list.contains(4+"")){
                        list.remove(4+"");
                    }
                    tv_down.setEnabled(true);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                } else {
                    list.remove(1 + "");
                    if (list.size() == 0) {
                        tv_down.setEnabled(false);
                        tv_down.setBackgroundResource(R.drawable.textview3);
                    }
                }
            }
        });
        cb_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(2 + "");
                    cb_d.setChecked(false);
                    if(list.contains(4+"")){
                        list.remove(4+"");
                    }
                    tv_down.setEnabled(true);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                } else {
                    list.remove(2 + "");
                    if (list.size() == 0) {
                        tv_down.setEnabled(false);
                        tv_down.setBackgroundResource(R.drawable.textview3);
                    }
                }
            }
        });
        cb_c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(3 + "");
                    if(list.contains(4+"")){
                        list.remove(4+"");
                    }
                    cb_d.setChecked(false);
                    tv_down.setEnabled(true);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                } else {
                    list.remove(3 + "");
                    if (list.size() == 0) {
                        tv_down.setEnabled(false);
                        tv_down.setBackgroundResource(R.drawable.textview3);
                    }
                }
            }
        });
        cb_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.clear();
                    cb_a.setChecked(false);
                    cb_b.setChecked(false);
                    cb_c.setChecked(false);
                    list.add(4 + "");
                    tv_down.setEnabled(true);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                } else {
                    list.remove(4 + "");
                    if (list.size() == 0) {
                        tv_down.setEnabled(false);
                        tv_down.setBackgroundResource(R.drawable.textview3);
                    }
                }
            }
        });
    }


}
