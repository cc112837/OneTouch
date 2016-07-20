package com.wzy.mhealth.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private ViewPager vp_news;
    String channelid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        init(v);
        return v;
    }

    private void init(View v) {
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabid);
        TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setText("热点");
        tabLayout.addTab(tab1);

        TabLayout.Tab tab2 = tabLayout.newTab();
        tab2.setText("养生");
        tabLayout.addTab(tab2);

        TabLayout.Tab tab3 = tabLayout.newTab();
        tab3.setText("情感");
        tabLayout.addTab(tab3);

        TabLayout.Tab tab4 = tabLayout.newTab();
        tab4.setText("美容");
        tabLayout.addTab(tab4);

        TabLayout.Tab tab5 = tabLayout.newTab();
        tab5.setText("男性");
        tabLayout.addTab(tab5);

        TabLayout.Tab tab6 = tabLayout.newTab();
        tab6.setText("女性");
        tabLayout.addTab(tab6);

        vp_news = (ViewPager) v.findViewById(R.id.vp_news);
        List<Fragment> fragmentList = new ArrayList<>();
        HealthListFragment health1 = new HealthListFragment(13+"");
        HealthListFragment health2 = new HealthListFragment(19+"");
        HealthListFragment health3 = new HealthListFragment(31+"");
        HealthListFragment health4 = new HealthListFragment(35+"");
        HealthListFragment health5 = new HealthListFragment(33+"");
        HealthListFragment health6 = new HealthListFragment(25+"");

        fragmentList.add(health1);
        fragmentList.add(health2);
        fragmentList.add(health3);
        fragmentList.add(health4);
        fragmentList.add(health5);
        fragmentList.add(health6);

        NewsAdapter adapter = new NewsAdapter(getChildFragmentManager(), fragmentList);
        vp_news.setAdapter(adapter);

        vp_news.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //切换viewpager
                vp_news.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
