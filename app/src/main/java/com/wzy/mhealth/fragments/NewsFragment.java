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
        tab1.setText("健康1");
        tabLayout.addTab(tab1);

        TabLayout.Tab tab2 = tabLayout.newTab();
        tab2.setText("健康2");
        tabLayout.addTab(tab2);

        TabLayout.Tab tab3 = tabLayout.newTab();
        tab3.setText("健康3");
        tabLayout.addTab(tab3);

        TabLayout.Tab tab4 = tabLayout.newTab();
        tab4.setText("健康4");
        tabLayout.addTab(tab4);

        vp_news = (ViewPager) v.findViewById(R.id.vp_news);
        List<Fragment> fragmentList = new ArrayList<>();

        HealthListFragment1 health1 = new HealthListFragment1();
        HealthListFragment2 health2 = new HealthListFragment2();
        HealthListFragment3 health3 = new HealthListFragment3();
        HealthListFragment4 health4 = new HealthListFragment4();

        fragmentList.add(health1);
        fragmentList.add(health2);
        fragmentList.add(health3);
        fragmentList.add(health4);

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
