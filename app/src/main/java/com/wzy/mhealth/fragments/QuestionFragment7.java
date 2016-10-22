package com.wzy.mhealth.fragments;


import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment7 extends Fragment {
    private Button tv_down, tv_up;
    private CheckBox cb_a, cb_b, cb_c, cb_d,cb_e,cb_f,cb_g,cb_h;
    private List<String> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_fragment7, container, false);
        init(v);
        return v;
    }
    private void init(View v) {
        cb_a = (CheckBox) v.findViewById(R.id.cb_a);
        cb_b = (CheckBox) v.findViewById(R.id.cb_b);
        cb_c = (CheckBox) v.findViewById(R.id.cb_c);
        cb_d = (CheckBox) v.findViewById(R.id.cb_d);
        cb_e = (CheckBox) v.findViewById(R.id.cb_e);
        cb_f = (CheckBox) v.findViewById(R.id.cb_f);
        cb_g=(CheckBox) v.findViewById(R.id.cb_g);
        cb_h=(CheckBox) v.findViewById(R.id.cb_h);
        tv_down = (Button) v.findViewById(R.id.tv_down);
        tv_down.setEnabled(false);
        tv_up = (Button) v.findViewById(R.id.tv_up);
        tv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new QuestionFragment8();
                ChangeFragmentHelper helper = new ChangeFragmentHelper();
                helper.setTargetFragment(fragment);
                helper.setTargetFragmentTag("fragment7");
                ((QuestionActivity) getActivity()).changeFragment(helper);
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
        cb_e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(5 + "");
                    tv_down.setEnabled(true);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                } else {
                    list.remove(5 + "");
                    if (list.size() == 0) {
                        tv_down.setEnabled(false);
                        tv_down.setBackgroundResource(R.drawable.textview3);
                    }
                }
            }
        });
        cb_f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(6 + "");
                    tv_down.setEnabled(true);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                } else {
                    list.remove(6 + "");
                    if (list.size() == 0) {
                        tv_down.setEnabled(false);
                        tv_down.setBackgroundResource(R.drawable.textview3);
                    }
                }
            }
        });
        cb_g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(7 + "");
                    tv_down.setEnabled(true);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                } else {
                    list.remove(7 + "");
                    if (list.size() == 0) {
                        tv_down.setEnabled(false);
                        tv_down.setBackgroundResource(R.drawable.textview3);
                    }
                }
            }
        });
        cb_h.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    list.add(8 + "");
                    tv_down.setEnabled(true);
                    tv_down.setBackgroundResource(R.drawable.textview_1);
                } else {
                    list.remove(8 + "");
                    if (list.size() == 0) {
                        tv_down.setEnabled(false);
                        tv_down.setBackgroundResource(R.drawable.textview3);
                    }
                }
            }
        });
    }


}
