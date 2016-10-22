package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.QuestionActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {
    private TextView tv_submit;
    private RadioButton rb_woman, rb_man, cb_self, cb_parent, cb_love, cb_friend, cb_other;
    private RadioGroup rg_sex, rg_classfy;

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
                    cb_self.getText().toString();
                } else if (checkedId == cb_parent.getId()) {
                    cb_parent.getText().toString();
                } else if (checkedId == cb_love.getId()) {
                    cb_love.getText().toString();
                } else if (checkedId == cb_friend.getId()) {
                    cb_friend.getText().toString();
                } else if (checkedId == cb_other.getId()) {
                    cb_other.getText().toString();
                } else {
                    cb_self.getText().toString();
                }
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new QuestionFragment1();
                ChangeFragmentHelper helper = new ChangeFragmentHelper();
                helper.setTargetFragment(fragment);
                helper.setIsClearStackBack(false);
                ((QuestionActivity) getActivity()).changeFragment(helper);
            }
        });
        rb_man = (RadioButton) v.findViewById(R.id.rb_man);
        rb_man.setChecked(true);
        rg_sex = (RadioGroup) v.findViewById(R.id.rg_sex);
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rb_man.getId()) {
                    rb_man.getText().toString();
                } else if (checkedId == rb_woman.getId()) {
                    rb_woman.getText().toString();
                } else {
                    rb_man.getText().toString();
                }
            }
        });
    }


}
