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
import com.wzy.mhealth.fragments.MaternityFragment;
import com.wzy.mhealth.fragments.YueZiFragment;

import java.util.ArrayList;
import java.util.List;

public class MarryHospitalActivity extends FragmentActivity {
    private Button rb_fuke,rb_yuezi;
    private ViewPager vp_order;
    private ImageView leftBtn;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marry_hospital);
        init();
    }
    private void init() {
        rb_yuezi=(Button) findViewById(R.id.rb_yuezi);
        rb_fuke=(Button) findViewById(R.id.rb_fuke);
        vp_order=(ViewPager) findViewById(R.id.vp_order);
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rb_fuke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_fuke, rb_yuezi);
                vp_order.setCurrentItem(0);
            }
        });
        rb_yuezi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_yuezi, rb_fuke);
                vp_order.setCurrentItem(1);
            }
        });
        addViewpager();
    }
    private void addViewpager() {
        fragments = new ArrayList<>();
        MaternityFragment nowFragment = new MaternityFragment();
        fragments.add(nowFragment);
        YueZiFragment hisFragment = new YueZiFragment();
        fragments.add(hisFragment);
        TijianAdapter adapter = new TijianAdapter(getSupportFragmentManager(), fragments);
        vp_order.setAdapter(adapter);
        /*添加监听器*/
        vp_order.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    rbTextColorSet(rb_fuke, rb_yuezi);
                } else {
                    rbTextColorSet(rb_yuezi, rb_fuke);
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
