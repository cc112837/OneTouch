package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.avos.avoscloud.AVAnalytics;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TijianAdapter;
import com.wzy.mhealth.fragments.ConclusionFragment;
import com.wzy.mhealth.fragments.TotalFragment;

import java.util.ArrayList;
import java.util.List;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 体检报告页面展示
*/
public class RecordShowActivity extends FragmentActivity {
    private ImageView leftBtn;
    private Button rb_conclusion,rb_total;
    private List<Fragment> fragments;
    private ViewPager viewPager;
    String studid,session;

    public String getStudid() {
        return studid;
    }

    public void setStudid(String studid) {
        this.studid = studid;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_show);
        Intent intent=getIntent();
        String studid=intent.getStringExtra("stuid");
        String session=intent.getStringExtra("session");
        setSession(session);
        setStudid(studid);
        init();
    }

    private void init() {
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        viewPager=(ViewPager) findViewById(R.id.vp_yuyue);
        rb_conclusion=(Button) findViewById(R.id.rb_conclusion);
        rb_total=(Button) findViewById(R.id.rb_total);
        rb_conclusion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_conclusion, rb_total);
                viewPager.setCurrentItem(1);
            }
        });
        rb_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_total, rb_conclusion);
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
        ConclusionFragment concluFragment = new ConclusionFragment();
        fragments.add(concluFragment);
        TotalFragment totalFragment = new TotalFragment();
        fragments.add(totalFragment);
        TijianAdapter adapter = new TijianAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        /*添加监听器*/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    rbTextColorSet(rb_total, rb_conclusion);
                } else {
                    rbTextColorSet(rb_conclusion, rb_total);
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
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }
}

