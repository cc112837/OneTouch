package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wzy.mhealth.R;


public class ShopDetailFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private WebView wv_shopdisplay;
    private RadioGroup rg_all;
    private RadioButton tv_intro,tv_detail,tv_bag;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {

            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop_detail, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        wv_shopdisplay = (WebView) v.findViewById(R.id.wv_shopdisplay);
        rg_all = (RadioGroup) v.findViewById(R.id.rg_all);
        tv_intro = (RadioButton)v. findViewById(R.id.tv_intro);
        tv_detail = (RadioButton) v.findViewById(R.id.tv_detail);
        tv_bag = (RadioButton) v.findViewById(R.id.tv_bag);
        rg_all.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == tv_intro.getId()) {

        } else if (checkedId == tv_detail.getId()) {

        } else {//tv_bag

        }
    }
}
