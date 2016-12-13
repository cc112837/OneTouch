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
import android.widget.ListView;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.OrderStatusActivity;
import com.wzy.mhealth.adapter.OrderAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.OrderInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowOrderFragment extends Fragment {
    private ListView lv_show;
    private OrderAdapter adapter;
    private ArrayList<OrderInfo.DataEntity> list = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 121:
                    final OrderInfo orderInfo = (OrderInfo) msg.obj;
                    if (orderInfo.getData()==null) {

                    } else {
                        list.addAll(orderInfo.getData());
                        adapter.notifyDataSetChanged();
                        lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(), OrderStatusActivity.class);
                                intent.putExtra("id", list.get(position).getShopId() + "");
                                intent.putExtra("name", list.get(position).getShopName() + "");
                                intent.putExtra("price", list.get(position).getPayMoney() + "");
                                intent.putExtra("bought", list.get(position).getTradeTime() + "");
                                intent.putExtra("status", list.get(position).getStatus() + "");
                                intent.putExtra("creat", list.get(position).getCreateTime() + "");
                                intent.putExtra("num", list.get(position).getShopOrder() + "");
                                intent.putExtra("orderid", list.get(position).getOrderId() + "");
                                intent.putExtra("image", list.get(position).getImage() + "");
                                intent.putExtra("type",list.get(position).getShopType()+"");
                                startActivityForResult(intent, 345);
                            }
                        });
                    }
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_now_order, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        String url = Constants.SERVER_URL + "PayCurrentServlet";
        TiUser user = new TiUser();
        user.setName(LeanchatUser.getCurrentUser().getUsername());
        MyHttpUtils.handData(handler, 121, url, user);
        lv_show = (ListView) v.findViewById(R.id.lv_show);
        adapter = new OrderAdapter(getActivity(), list);
        lv_show.setAdapter(adapter);
    }


}
