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
import com.wzy.mhealth.fragments.BookMangerFragment;
import com.wzy.mhealth.fragments.UserMangerFragment;

import java.util.ArrayList;
import java.util.List;


public class ManageActivity extends FragmentActivity{
    private Button rb_noworder,rb_hisorder;
    private ViewPager vp_order;
    private List<Fragment> fragments;
    private ImageView leftBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
    }
    private void init() {
        rb_hisorder=(Button) findViewById(R.id.rb_hisorder);
        rb_noworder=(Button) findViewById(R.id.rb_noworder);
        vp_order=(ViewPager) findViewById(R.id.vp_order);
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rb_noworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_noworder, rb_hisorder);
                vp_order.setCurrentItem(0);
            }
        });
        rb_hisorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_hisorder, rb_noworder);
                vp_order.setCurrentItem(1);
            }
        });
        addViewpager();
    }
    private void addViewpager() {
        fragments = new ArrayList<>();
        BookMangerFragment nowFragment = new BookMangerFragment();
        fragments.add(nowFragment);
        UserMangerFragment hisFragment = new UserMangerFragment();
        fragments.add(hisFragment);
        TijianAdapter adapter = new TijianAdapter(getSupportFragmentManager(), fragments);
        vp_order.setAdapter(adapter);
        /*添加监听器*/
        vp_order.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    rbTextColorSet(rb_noworder, rb_hisorder);
                } else {
                    rbTextColorSet(rb_hisorder, rb_noworder);
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
