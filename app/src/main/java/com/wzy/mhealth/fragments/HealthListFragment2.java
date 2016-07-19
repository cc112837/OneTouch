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

import com.wzy.mhealth.LeanChat.view.XListView;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.NewsDetailActivity;
import com.wzy.mhealth.adapter.NewsItemAdapter;
import com.wzy.mhealth.model.NewsYang;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthListFragment2 extends Fragment implements XListView.IXListViewListener {
    private XListView lv_show;
    private NewsItemAdapter adapter;
    int page;
    private List<NewsYang.DataEntity.FlowEntity.ItemsEntity> list;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 21:
                    if (page == 1) {
                        list.clear();
                    }
                    NewsYang newsYang = (NewsYang) msg.obj;
                    lv_show.stopRefresh();
                    list.addAll(newsYang.getData().getFlow().getItems());
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_health_list_fragment, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        lv_show = (XListView) v.findViewById(R.id.lv_show);
        lv_show.setPullLoadEnable(true);
        lv_show.setPullRefreshEnable(true);
        lv_show.setXListViewListener(this);
        list = new ArrayList<>();
        adapter = new NewsItemAdapter(getContext(), list);
        lv_show.setAdapter(adapter);
        lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("content", list.get(position-1).getContentid()+"");
                startActivity(intent);
            }
        });
        page=1;
        String url = "http://api.m.vodjk.com/v1/content?channelid=19&amp;height=210.9375&ip=192.168.1.107&modules=flow%3A1%2Cslider%3A1&page="+page+"&pagesize=20&sign=b21c13f97d721a254bcaeb57761b2a4e&amp;time=1468474076&token=3&type=android&width=375";
        MyHttpUtils.handData(handler, 21, url, null);
    }


    @Override
    public void onRefresh() {
        page=1;
        String url = "http://api.m.vodjk.com/v1/content?channelid=19&amp;height=210.9375&ip=192.168.1.107&modules=flow%3A1%2Cslider%3A1&page="+page+"&pagesize=20&sign=b21c13f97d721a254bcaeb57761b2a4e&amp;time=1468474076&token=3&type=android&width=375";
        MyHttpUtils.handData(handler, 21, url, null);
    }

    @Override
    public void onLoadMore() {
        page++;
        String url = "http://api.m.vodjk.com/v1/content?channelid=19&amp;height=210.9375&ip=192.168.1.107&modules=flow%3A1%2Cslider%3A1&page=" + page + "&pagesize=20&sign=b21c13f97d721a254bcaeb57761b2a4e&amp;time=1468474076&token=3&type=android&width=375";
        MyHttpUtils.handData(handler, 21, url, null);
    }
}
