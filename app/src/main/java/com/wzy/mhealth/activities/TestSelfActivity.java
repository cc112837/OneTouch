package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TijianAdapter;
import com.wzy.mhealth.fragments.RecentFragment;
import com.wzy.mhealth.fragments.TestItemFragment;

import java.util.ArrayList;
import java.util.List;

public class TestSelfActivity extends FragmentActivity {
    private ImageView leftBtn;
    private Button rb_recent,rb_item;
    private List<Fragment> fragments;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_self);
        init();
    }

    private void init() {
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewPager=(ViewPager) findViewById(R.id.vp_yuyue);
        rb_recent=(Button) findViewById(R.id.rb_recent);
        rb_item=(Button) findViewById(R.id.rb_item);
        rb_recent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_recent, rb_item);
                viewPager.setCurrentItem(1);
            }
        });
        rb_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_item, rb_recent);
                viewPager.setCurrentItem(0);
            }
        });
        addViewpager();
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void addViewpager() {
        fragments = new ArrayList<>();
        TestItemFragment testFragment = new TestItemFragment();
        fragments.add(testFragment);
        RecentFragment recentFragment = new RecentFragment();
        fragments.add(recentFragment);
        TijianAdapter adapter = new TijianAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        /*添加监听器*/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    rbTextColorSet(rb_item, rb_recent);
                } else {
                    rbTextColorSet(rb_recent, rb_item);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void rbTextColorSet(Button b1,Button b2 )
    {
        b1.setTextColor(getResources().getColor(R.color.title_green));
        b2.setTextColor(getResources().getColor(R.color.dark_grey));
    }
}

