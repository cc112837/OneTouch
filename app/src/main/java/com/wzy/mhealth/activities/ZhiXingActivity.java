package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TaoCanAdapter;
import com.wzy.mhealth.view.LocalImageHolderView;

import java.util.ArrayList;

public class ZhiXingActivity extends Activity {
    private ImageView leftBtn;
    private ConvenientBanner convenientBanner;
    private TextView tv_display;
    private ListView lv_showid;
    private LinearLayout ll_private;
    private RadioButton rb_group, rb_taocan, rb_yuyue, rb_record;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_xing);
        init();
    }

    private void loadLocalImage() {
        localImages.add(R.mipmap.carouse11);
        localImages.add(R.mipmap.carouse22);
        localImages.add(R.mipmap.carouse33);
        localImages.add(R.mipmap.carouse44);
    }


    @Override

    public void onResume() {

        super.onResume();

        //开始自动翻页
        convenientBanner.startTurning(2000);
    }


    // 停止自动翻页

    @Override

    public void onPause() {

        super.onPause();

        //停止翻页
        convenientBanner.stopTurning();

    }

    //初始化界面控件
    private void init() {
        View headview = LayoutInflater.from(this).inflate(R.layout.head_zhixing, null);
        loadLocalImage();
        lv_showid = (ListView) findViewById(R.id.lv_showid);
         ArrayList<String> list=new ArrayList<>();
        list.add(444+"");
        list.add(555+"");
        list.add(666+"");
        list.add(444+"");
        list.add(333+"");

        TaoCanAdapter adapter=new TaoCanAdapter(this,list);
        lv_showid.setAdapter(adapter);
        lv_showid.addHeaderView(headview);
        convenientBanner = ((ConvenientBanner) headview.findViewById(R.id.convenientBanner));
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.dots_gray, R.mipmap.dot_white})
                        //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        ll_private=(LinearLayout) headview.findViewById(R.id.ll_private);
        tv_display = (TextView) headview.findViewById(R.id.tv_display);
        rb_group = (RadioButton) headview.findViewById(R.id.rb_group);
        rb_yuyue = (RadioButton) headview.findViewById(R.id.rb_yuyue);
        rb_taocan = (RadioButton) headview.findViewById(R.id.rb_taocan);
        rb_record = (RadioButton) headview.findViewById(R.id.rb_record);
        ll_private.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //私人订制套餐
                Intent intent=new Intent(ZhiXingActivity.this,NoContentActivity.class);
                startActivity(intent);
            }
        });
        rb_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //团体套餐

                Intent intent=new Intent(ZhiXingActivity.this,TijianYueActivity.class);
                intent.putExtra("flag",1);
                startActivity(intent);
            }
        });
        rb_yuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //我的预约

                Intent intent=new Intent(ZhiXingActivity.this,TijianYueActivity.class);
                intent.putExtra("flag",2);
                startActivity(intent);
            }
        });
        rb_taocan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZhiXingActivity.this,NoContentActivity.class);
                startActivity(intent);
                //套餐介绍
            }
        });
        rb_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //体检报告
                Intent intent=new Intent(ZhiXingActivity.this,TijianRecordActivity.class);
                startActivity(intent);
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
