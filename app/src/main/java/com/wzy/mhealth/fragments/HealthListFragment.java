package com.wzy.mhealth.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.avos.avoscloud.AVAnalytics;
import com.wzy.mhealth.LeanChat.view.XListView;
import com.wzy.mhealth.R;
import com.wzy.mhealth.activities.NewsDetailActivity;
import com.wzy.mhealth.adapter.NewsItemAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.NewsYang;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 资讯列表页面
 */
public class HealthListFragment extends Fragment implements XListView.IXListViewListener {
    String contentid;
    public HealthListFragment(String contentid) {
        this.contentid = contentid;
    }

    private XListView lv_show;
    private NewsItemAdapter adapter;
    int page;
    private List<NewsYang.DataEntity> list;
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
                    list.addAll(newsYang.getData());
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        AVAnalytics.onFragmentEnd("health-list-fragment");
    }

    public void onResume() {
        super.onResume();
        AVAnalytics.onFragmentStart("health-list-fragment");
    }
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
                intent.putExtra("content", list.get(position-1).getMedicalId()+"");
                startActivity(intent);
            }
        });
        page = 1;
        String url= Constants.SERVER_URL+"MedicalArticleServlet";
        TiUser user=new TiUser();
        user.setName(contentid+"");
        user.setPass(page+"");
        MyHttpUtils.handData(handler, 21, url, user);
    }


    @Override
    public void onRefresh() {
        page = 1;
        String url= Constants.SERVER_URL+"MedicalArticleServlet";
        TiUser user=new TiUser();
        user.setName(contentid + "");
        user.setPass(page + "");
        MyHttpUtils.handData(handler, 21, url, user);
    }

    @Override
    public void onLoadMore() {
        page++;
        String url= Constants.SERVER_URL+"MedicalArticleServlet";
        TiUser user=new TiUser();
        user.setName(contentid+"");
        user.setPass(page+"");
        MyHttpUtils.handData(handler, 21, url, user);
    }
}
