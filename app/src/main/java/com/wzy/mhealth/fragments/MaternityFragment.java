package com.wzy.mhealth.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TaocanAllAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Tijian;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 妇产中心
*/
public class MaternityFragment extends Fragment {
    private RecyclerView lv_show;
    TaocanAllAdapter maternityAdapter;
    private List<Tijian.DataEntity> list = new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 171:
                    Tijian taocanEntity=(Tijian) msg.obj;
                    list.clear();
                    list.addAll(taocanEntity.getData());
                    maternityAdapter.notifyDataSetChanged();

                break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_fu_chan, container, false);
        String url= Constants.SERVER_URL+"WhosiptalServlet";
        MyHttpUtils.handData(handler, 171, url, null);
        init(v);
        return v;
    }

    private void init(View v) {
        lv_show = (RecyclerView) v.findViewById(R.id.lv_show);
        maternityAdapter = new TaocanAllAdapter(getContext());
        lv_show.setAdapter(maternityAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        lv_show.setLayoutManager(manager);
        lv_show.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL_LIST));
        maternityAdapter.setData(list);
    }


}
