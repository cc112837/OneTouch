package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.QuestionActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment9 extends Fragment {

    private Button tv_down, tv_up;
    private RadioButton cb_a, cb_b;
    private RadioGroup rg_data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_question_fragment9, container, false);
        init(v);
        return  v;
    }

    private void init(View v) {
        tv_down=(Button) v.findViewById(R.id.tv_down);
        tv_up=(Button) v.findViewById(R.id.tv_up);
        tv_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager=getFragmentManager();
                manager.popBackStack();
            }
        });
        tv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new QuestionFragmentt();
                ChangeFragmentHelper helper = new ChangeFragmentHelper();
                helper.setTargetFragment(fragment);
                helper.setTargetFragmentTag("fragment9");
                ((QuestionActivity) getActivity()).changeFragment(helper);
            }
        });
        cb_a=(RadioButton) v.findViewById(R.id.cb_a);
        cb_b=(RadioButton) v.findViewById(R.id.cb_b);
        tv_down.setEnabled(false);
        rg_data=(RadioGroup) v.findViewById(R.id.rg_data);
        rg_data.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == cb_a.getId()) {
                    cb_a.getText().toString();
                    tv_down.setEnabled(true);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                } else if (checkedId == cb_b.getId()) {
                    cb_b.getText().toString();
                    tv_down.setEnabled(true);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                }   else {
                    tv_down.setEnabled(false);
                    tv_down.setBackgroundResource(R.drawable.textview3);
                }
            }
        });

    }


}
