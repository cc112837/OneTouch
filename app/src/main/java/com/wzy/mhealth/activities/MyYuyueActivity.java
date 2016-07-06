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
import com.wzy.mhealth.fragments.TijianYuyueFragment;
import com.wzy.mhealth.fragments.XianChaFragment;

import java.util.ArrayList;
import java.util.List;

public class MyYuyueActivity extends FragmentActivity {
    private ImageView leftBtn;
    private Button rb_chati,rb_yuyue;
    private List<Fragment> fragments;
    private ViewPager viewPager;
    String session,id,ex;

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_yuyue);
        Intent intent=getIntent();
        session= intent.getStringExtra("session");
        setSession(session);
        id=intent.getStringExtra("id");
        setId(id);
        ex=intent.getStringExtra("extra");
        setEx(ex);
        init();
    }

    private void init() {
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        viewPager=(ViewPager) findViewById(R.id.vp_yuyue);
        rb_chati=(Button) findViewById(R.id.rb_chati);
        rb_yuyue=(Button) findViewById(R.id.rb_yuyue);
        rb_chati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_chati, rb_yuyue);
                viewPager.setCurrentItem(1);
            }
        });
        rb_yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rbTextColorSet(rb_yuyue, rb_chati);
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
        TijianYuyueFragment yuyueFragment = new TijianYuyueFragment();
        fragments.add(yuyueFragment);
        XianChaFragment xianchaFragment = new XianChaFragment();
        fragments.add(xianchaFragment);
        TijianAdapter adapter = new TijianAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        /*添加监听器*/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    rbTextColorSet(rb_yuyue, rb_chati);
                } else {
                    rbTextColorSet(rb_chati, rb_yuyue);
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
