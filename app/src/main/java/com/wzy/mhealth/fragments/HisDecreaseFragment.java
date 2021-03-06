package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.DecreaseAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Decrease;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 过期优惠券
*/

public class HisDecreaseFragment extends Fragment {
    private ListView lv_decrease;
    private DecreaseAdapter decreaseAdapter;
    private List<Decrease.DataEntity> list=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 274:
                    Decrease decrease=(Decrease) msg.obj;
                    if(decrease!=null){
                        list.addAll(decrease.getData());
                    }
                    break;

            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_his_decrease, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        lv_decrease=(ListView) v.findViewById(R.id.lv_decrease);
        decreaseAdapter = new DecreaseAdapter(getActivity(), list);
        lv_decrease.setAdapter(decreaseAdapter);
        String url= Constants.SERVER_URL+"MhealthShopCouponServlet";
        TiUser user=new TiUser();
        user.setPass("1");
        MyHttpUtils.handData(handler, 274, url, user);
    }
}
