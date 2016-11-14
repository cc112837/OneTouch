package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.wzy.mhealth.R;

public class ShopFragment extends Fragment implements View.OnClickListener {
    private ImageView leftBtn, rightBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shop, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        leftBtn = (ImageView) v.findViewById(R.id.leftBtn);
        rightBtn = (ImageView) v.findViewById(R.id.rightBtn);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                Toast.makeText(getActivity(), "您点击了订单", Toast.LENGTH_LONG).show();
                break;
            case R.id.rightBtn:
                Toast.makeText(getActivity(), "您点击了购物车", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
