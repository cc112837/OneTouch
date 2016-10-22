package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.QuestionActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment1 extends Fragment implements TextWatcher {
    private Button tv_down;
    private EditText et_weight, et_height, et_age;

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
                Fragment fragment = new QuestionFragment2();
                ChangeFragmentHelper helper = new ChangeFragmentHelper();
                helper.setTargetFragment(fragment);
                helper.setTargetFragmentTag("fragment1");
                ((QuestionActivity) getActivity()).changeFragment(helper);
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
