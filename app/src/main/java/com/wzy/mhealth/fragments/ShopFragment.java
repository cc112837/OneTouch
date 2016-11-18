package com.wzy.mhealth.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.CartActivity;
import com.wzy.mhealth.activities.ShopDetailActivity;
import com.wzy.mhealth.activities.ShoporderActivity;
import com.wzy.mhealth.adapter.ShopAdapter;
import com.wzy.mhealth.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment implements View.OnClickListener {
    private ImageView leftBtn, rightBtn, shop_header;
    private TextView tv_count;
    private List<Shop.DataEntity> list = new ArrayList<>();
    private ShopAdapter shopAdapter;
    private GridView gv_shop;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 270:
                    tv_count.setText("");
                    ImageLoader.getInstance().displayImage("", shop_header);
                    shopAdapter.notifyDataSetChanged();
                    gv_shop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent=new Intent(getActivity(),ShopDetailActivity.class);
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };


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
        tv_count = (TextView) v.findViewById(R.id.tv_count);
        shop_header = (ImageView) v.findViewById(R.id.shop_header);
        gv_shop = (GridView) v.findViewById(R.id.gv_shop);
        shopAdapter = new ShopAdapter(getActivity(), list);
        gv_shop.setAdapter(shopAdapter);
//        String url= Constants.SERVER_URL+"";
//        MyHttpUtils.handData(handler,270,url,null);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.leftBtn:
                intent = new Intent(getActivity(), ShoporderActivity.class);
                startActivity(intent);
                break;
            case R.id.rightBtn:
                intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
                break;
        }
    }
}
