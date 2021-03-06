package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TijianAdapter;
import com.wzy.mhealth.fragments.HisOrderFragment;
import com.wzy.mhealth.fragments.NowOrderFragment;

import java.util.ArrayList;
import java.util.List;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 体检订单页面
*/
public class ExaminationOrderActivity extends FragmentActivity {
    private Button rb_noworder,rb_hisorder;
    private ViewPager vp_order;
    private ImageView leftBtn;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_order);
        init();
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
        NowOrderFragment nowFragment = new NowOrderFragment();
        fragments.add(nowFragment);
        HisOrderFragment hisFragment = new HisOrderFragment();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 345:
                ExaminationOrderActivity.this.finish();
                break;
            default:
                break;
        }
    }
}
