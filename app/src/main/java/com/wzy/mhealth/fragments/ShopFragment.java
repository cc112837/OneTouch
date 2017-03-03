package com.wzy.mhealth.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.CartActivity;
import com.wzy.mhealth.activities.ShoporderActivity;
import com.wzy.mhealth.adapter.SectionedSpanSizeLookup;
import com.wzy.mhealth.adapter.ShopAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Shop;
import com.wzy.mhealth.model.ShopBuy;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 商品首页
*/
public class ShopFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {
    private ImageView leftBtn, rightBtn;
    private TextView tv_count;
    private List<Shop.DataEntity> list = new ArrayList<>();
    private ShopAdapter shopAdapter;
    private RecyclerView gv_shop;
    private SearchView searchView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 270:
                    Shop shop = (Shop) msg.obj;
                    list.clear();
                    list.addAll(shop.getData());
                    shopAdapter.notifyDataSetChanged();
                    break;
                case 286:
                    ShopBuy shopBuy = (ShopBuy) msg.obj;
                    if (shopBuy.getStatus().equals("1")) {
                        if (shopBuy.getProductNum() == 0) {
                            tv_count.setVisibility(View.GONE);
                        } else {
                            tv_count.setText(shopBuy.getProductNum() + "");
                        }
                    } else {
                        tv_count.setVisibility(View.GONE);
                    }

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
        searchView = (SearchView) v.findViewById(R.id.searchView);
        leftBtn = (ImageView) v.findViewById(R.id.leftBtn);
        rightBtn = (ImageView) v.findViewById(R.id.rightBtn);
        tv_count = (TextView) v.findViewById(R.id.tv_count);
        String uri = Constants.SERVER_URL + "MhealthShoppingCartDisplayServlet";
        MyHttpUtils.handData(handler, 286, uri, null);
        gv_shop = (RecyclerView) v.findViewById(R.id.gv_shop);
        shopAdapter = new ShopAdapter(getActivity());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(shopAdapter, manager));
        gv_shop.setLayoutManager(manager);
        gv_shop.setAdapter(shopAdapter);
        shopAdapter.setData(list);
        String url = Constants.SERVER_URL + "MhealthProductServlet";
        MyHttpUtils.handData(handler, 270, url, null);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
        //为该SearchView组件设置事件监听器
        searchView.setOnQueryTextListener(this);
        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        //设置该SearchView内默认显示的提示文本
        searchView.setQueryHint("查找");
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

    @Override
    public void onResume() {
        super.onResume();
        String uri = Constants.SERVER_URL + "MhealthShoppingCartDisplayServlet";
        MyHttpUtils.handData(handler, 286, uri, null);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Toast.makeText(getActivity(), "您选择的是：" + query, Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            //清除ListView的过滤
        } else {
            //使用用户输入的内容对ListView的列表项进行过滤
        }
        return true;
    }
}
