package com.wzy.mhealth.activities;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 优惠劵
 * 创建时间：2016/11/18 16:18
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TijianAdapter;
import com.wzy.mhealth.fragments.HisDecreaseFragment;
import com.wzy.mhealth.fragments.NowDecreaseFragment;

import java.util.ArrayList;
import java.util.List;

public class DecreseActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView leftBtn;
    private Button rb_now,rb_his;
    private ViewPager vp_decrease;
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrese);
        init();
    }

    private void init() {
        rb_his=(Button) findViewById(R.id.rb_his);
        rb_now=(Button) findViewById(R.id.rb_now);
        vp_decrease=(ViewPager) findViewById(R.id.vp_decrease);
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(this);
        rb_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_now, rb_his);
                vp_decrease.setCurrentItem(0);
            }
        });
        rb_his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_his, rb_now);
                vp_decrease.setCurrentItem(1);
            }
        });
        addViewpager();
    }
    private void addViewpager() {
        fragments = new ArrayList<>();
        NowDecreaseFragment nowFragment = new NowDecreaseFragment();
        fragments.add(nowFragment);
        HisDecreaseFragment hisFragment = new HisDecreaseFragment();
        fragments.add(hisFragment);
        TijianAdapter adapter = new TijianAdapter(getSupportFragmentManager(), fragments);
        vp_decrease.setAdapter(adapter);
        /*添加监听器*/
        vp_decrease.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    rbTextColorSet(rb_now, rb_his);
                } else {
                    rbTextColorSet(rb_his, rb_now);
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
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
        }
    }
}
