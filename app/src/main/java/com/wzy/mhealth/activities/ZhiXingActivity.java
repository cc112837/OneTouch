package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TaoCanAdapter;
import com.wzy.mhealth.model.ZhixingTaocan;
import com.wzy.mhealth.view.LocalImageHolderView;

import java.util.ArrayList;

public class ZhiXingActivity extends BaActivity {
    private ImageView leftBtn;
    private ConvenientBanner convenientBanner;
    private TextView tv_display;
    private ListView lv_showid;
    private LinearLayout ll_private,ll_intro;
    private RadioButton rb_group, rb_taocan, rb_yuyue, rb_record;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private ArrayList<ZhixingTaocan> list=new ArrayList<>();

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
        list.add(new ZhixingTaocan("入职套餐", 228, 114));
        list.add(new ZhixingTaocan("青年男宾体检套餐",468,234));
        list.add(new ZhixingTaocan("青年已婚女宾体检套餐",713,357));
        list.add(new ZhixingTaocan("青年未婚女宾体检套餐", 558, 279));
        list.add(new ZhixingTaocan("青年男宾深度体检套餐",840,420));
        list.add(new ZhixingTaocan("青年已婚女宾深度体检套餐",995,498));
        list.add(new ZhixingTaocan("中年男宾体检套餐",1065,533));
        list.add(new ZhixingTaocan("中年已婚女宾体检套餐",1220,610));
        list.add(new ZhixingTaocan("中老年男宾体检套餐",1155,578));
        list.add(new ZhixingTaocan("中老年女宾体检套餐",1310,655));
        list.add(new ZhixingTaocan("孕前男宾体检套餐",678,339));
        list.add(new ZhixingTaocan("孕前女宾体检套餐", 1148, 574));
        TaoCanAdapter adapter=new TaoCanAdapter(this,list);
        lv_showid.setAdapter(adapter);
        lv_showid.addHeaderView(headview);
        lv_showid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ZhiXingActivity.this, "您点击了" + list.get(position-1).getName(), Toast.LENGTH_LONG).show();
            }
        });

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
        ll_intro=(LinearLayout)findViewById(R.id.ll_intro);
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
        ll_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ZhiXingActivity.this,ZhixingIntroduceActivity.class);
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
                Intent intent=new Intent(ZhiXingActivity.this,PersonTaocan.class);
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
