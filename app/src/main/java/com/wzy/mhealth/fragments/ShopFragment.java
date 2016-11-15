package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.ShopAdapter;
import com.wzy.mhealth.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment implements View.OnClickListener {
    private ImageView leftBtn, rightBtn,shop_header;
    private TextView tv_count;
    private List<Shop.DataEntity>list=new ArrayList<>();
    private ShopAdapter shopAdapter;
    private GridView gv_shop;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 270:
                    tv_count.setText("");
                    ImageLoader.getInstance().displayImage("",shop_header);
                    shopAdapter.notifyDataSetChanged();
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
        shop_header=(ImageView) v.findViewById(R.id.shop_header);
        gv_shop=(GridView) v.findViewById(R.id.gv_shop);
        shopAdapter = new ShopAdapter(getActivity(), list);
        gv_shop.setAdapter(shopAdapter);
//        String url= Constants.SERVER_URL+"";
//        MyHttpUtils.handData(handler,270,url,null);
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
