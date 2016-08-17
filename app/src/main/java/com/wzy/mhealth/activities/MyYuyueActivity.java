package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TijianAdapter;
import com.wzy.mhealth.fragments.TijianYuyueFragment;
import com.wzy.mhealth.fragments.XianChaFragment;
import com.wzy.mhealth.model.ChaTiContent;
import com.wzy.mhealth.model.ItemInfo;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class MyYuyueActivity extends FragmentActivity {
    private ImageView leftBtn;
    private Button rb_chati,rb_yuyue;
    private List<Fragment> fragments;
    private ViewPager viewPager;
    ArrayList<ChaTiContent> childTemp;
    private ArrayList<List<ChaTiContent>> childList;
    String session,id,ex;

    public ArrayList<List<ChaTiContent>> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<List<ChaTiContent>> childList) {
        this.childList = childList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_yuyue);
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent=getIntent();
        session= intent.getStringExtra("session");
        id=intent.getStringExtra("id");
        ex=intent.getStringExtra("extra");
        setEx(ex);
        childTemp = new ArrayList<>();
        childList = new ArrayList<>();
        if (id=="") {
            Toast.makeText(MyYuyueActivity.this, "请先进行体检预约", 2000).show();
        } else {
            String itemurl = "http://113.201.59.226:8081/Healwis/base/itemAction!app_jcxm.action?sessid=" + session + "&id=" + id;
            MyHttpUtils.handData(handler, 6, itemurl, null);
        }
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 6:
                    ItemInfo info = (ItemInfo) msg.obj;
                    if (info.getTotal() == -1 || info.getTotal() == 0) {
                        Toast.makeText(MyYuyueActivity.this, "没有体检套餐数据", Toast.LENGTH_SHORT).show();
                    } else {
                        init();
                        for (int i = 0; i < info.getRows().size(); i++) {
                            ChaTiContent content = new ChaTiContent();
                            content.setId(info.getRows().get(i).getID());
                            content.setItemcode(info.getRows().get(i).getITEMCODE());
                            content.setStuyid(info.getRows().get(i).getSTUDYID());
                            content.setItemname(info.getRows().get(i).getXMMC());
                            childTemp.add(content);
                        }
                        childList.add(childTemp);
                        setChildList(childList);
                    }
            }
        }
    };
    private void init() {


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
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }
}
