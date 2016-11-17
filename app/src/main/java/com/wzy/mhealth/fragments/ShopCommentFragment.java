package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wzy.mhealth.R;

public class ShopCommentFragment extends Fragment implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup rg_all;
    private RadioButton tv_all, tv_satis, tv_nosatis;
    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == tv_all.getId()) {

        } else if (checkedId == tv_satis.getId()) {

        } else {//tv_nosatis

        }
    }
}
