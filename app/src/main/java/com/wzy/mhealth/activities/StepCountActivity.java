package com.wzy.mhealth.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.timqi.sectorprogressview.ColorfulRingProgressView;
import com.wzy.mhealth.MyApplication;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Friend;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.StepResult;
import com.wzy.mhealth.service.StepCounterService;
import com.wzy.mhealth.utils.MyAndroidUtil;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.view.LineView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import me.pedometer.StepDetector;

public class StepCountActivity extends BaActivity {
    private ImageView leftBtn_back;
    private ColorfulRingProgressView crpv;
    private TextView tv_rank;
    private TextView stepTv,tv_today;
    private Thread thread;  //定义线程对象
    private ArrayList<HashMap<String, String>> list;
    int step;
    LineView mLineView;
    String time;
    String[] xvalue,stepvalue;
    private int total_step = 0;   //走的总步数

    @Override
    protected void onStart() {
        super.onStart();
        String nowstep = MyApplication.sharedPreferences.getString(Constants.STEP,
                "0");
        String time = MyApplication.sharedPreferences.getString(Constants.STEPDATE,
                null);
        String url = Constants.SERVER_URL+"StepNumServlet";
        Friend friend = new Friend(nowstep, LeanchatUser.getCurrentUser().getUsername(), time);
        MyHttpUtils.handData(handler, 112, url, friend);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);
        init();
        time = MyApplication.sharedPreferences.getString(Constants.STEPDATE,
                null);
        String nowstep = MyApplication.sharedPreferences.getString(Constants.STEP,
                null);
        if (nowstep != null)
            step = Integer.parseInt(nowstep);
        if (!getTime().equals(time)||time==null) {
            step = 0;
            StepDetector.CURRENT_STEP = 0;
        }
        stepTv.setText(step + "");// 显示当前步数
        crpv.setPercent((step) / 10);
        initThreadToPedometer();
        startService(new Intent(StepCountActivity.this, StepCounterService.class));
    }

    private void init() {
        xvalue=new String[200];
        stepvalue=new String[200];
        aboutLine();
        tv_rank=(TextView) findViewById(R.id.tv_rank);
        tv_rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(StepCountActivity.this,RankActivity.class);
                startActivity(intent);
            }
        });
        leftBtn_back = (ImageView) findViewById(R.id.leftBtn_back);
        leftBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        crpv = (ColorfulRingProgressView) findViewById(R.id.crpv);
        stepTv = (TextView) findViewById(R.id.activity_main_step_tv);
        tv_today=(TextView)findViewById(R.id.tv_today);
    }

    private void addView() {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout view=(LinearLayout) findViewById(R.id.ll_view);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        mLineView=new LineView(StepCountActivity.this);
        mLineView.SetInfo(
                new String[]{xvalue[0], xvalue[1], xvalue[2], xvalue[3], xvalue[4], xvalue[5], xvalue[6]},
                new String[]{"", "2000", "4000", "6000", "8000", "10000"},   //Y轴刻度
                new String[]{stepvalue[0], stepvalue[1], stepvalue[2], stepvalue[3], stepvalue[4], stepvalue[5], total_step + step+""},  //数据
                "计步结果"
        );
        view.addView(mLineView);
    }
    private void aboutLine() {
        String uri = Constants.SERVER_URL+"StepNumQueryServlet";
        StepInfo stepInf = new StepInfo();
        stepInf.setData(LeanchatUser.getCurrentUser().getUsername());
        MyHttpUtils.handData(handler, 113, uri, stepInf);
    }


    private void initThreadToPedometer() {
        if (thread == null) {

            thread = new Thread() {// 子线程用于监听当前步数的变化

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    super.run();
                    int temp = 0;
                    while (true) {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        if (StepCounterService.FLAG) {
                            Message msg = new Message();
                            msg.what=50;
                            if (temp != StepDetector.CURRENT_STEP) {
                                temp = StepDetector.CURRENT_STEP;
                            }
                            handler.sendMessage(msg);
                        }
                    }
                }
            };
            thread.start();
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 113:
                    StepResult stepResult = (StepResult) msg.obj;
                    for (int i=0;i<7;i++){
                        xvalue[i]=stepResult.getData().get(i).getTime().substring(5);
                        stepvalue[i]=String.valueOf(stepResult.getData().get(i).getStepNum());
                    }
                    addView();
                    break;
                case 50:
                    super.handleMessage(msg);// 此处可以更新UI
                    countStep();          //调用步数方法
                    stepTv.setText(total_step + step + "");// 显示当前步数
                    tv_today.setText(""+(total_step + step));
                    crpv.setPercent((total_step + step) / 10);
                    break;
                case 112:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    break;
            }

        }
    };

    private void countStep() {
        if (StepDetector.CURRENT_STEP % 2 == 0) {
            total_step = StepDetector.CURRENT_STEP;
        } else {
            total_step = StepDetector.CURRENT_STEP + 1;
        }
        total_step = StepDetector.CURRENT_STEP;
    }


    private String getTime() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String nowdata = sf.format(new Date());
        return nowdata;
    }

    @Override
    protected void onPause() {
        super.onPause();
        String nowstep = MyApplication.sharedPreferences.getString(Constants.STEP,
                null);
        String time = MyApplication.sharedPreferences.getString(Constants.STEPDATE,
                null);
        String url = Constants.SERVER_URL+"StepNumServlet";
        Friend friend = new Friend(nowstep, LeanchatUser.getCurrentUser().getUsername(), time);
        MyHttpUtils.handData(handler, 112, url, friend);
    }

    @Override
    protected void onDestroy() {
        MyAndroidUtil.editXmlByString(
                Constants.STEP, (total_step + step) + "");
        MyAndroidUtil.editXmlByString(
                Constants.STEPDATE, getTime());
        StepDetector.CURRENT_STEP = 0;
        super.onDestroy();
    }
}
