package com.wzy.mhealth.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.CartActivity;
import com.wzy.mhealth.activities.ShopDetailActivity;
import com.wzy.mhealth.activities.ShoporderActivity;
import com.wzy.mhealth.adapter.ShopAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.inter.MyRecyItemClickListener;
import com.wzy.mhealth.model.Shop;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class ShopFragment extends Fragment implements View.OnClickListener {
    private ImageView leftBtn, rightBtn;
    private TextView tv_count;
    private List<Shop.DataEntity> list = new ArrayList<>();
    private ShopAdapter shopAdapter;
    private RecyclerView gv_shop;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 270:
                     Shop shop=(Shop) msg.obj;
                    list.clear();
                    list.addAll(shop.getData());
                    tv_count.setText("" + shop.getProductNum());
                    shopAdapter.notifyDataSetChanged();
                    shopAdapter.setOnItemClickListener(new MyRecyItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent=new Intent(getActivity(),ShopDetailActivity.class);
                            intent.putExtra("id",list.get(position).getProductId()+"");
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
        gv_shop = (RecyclerView) v.findViewById(R.id.gv_shop);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        gv_shop.setLayoutManager(manager);
        shopAdapter = new ShopAdapter(getActivity(), list);
        gv_shop.setAdapter(shopAdapter);
        String url= Constants.SERVER_URL+"MhealthProductServlet";
        MyHttpUtils.handData(handler, 270, url, null);
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
