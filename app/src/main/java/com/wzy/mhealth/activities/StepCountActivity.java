package com.wzy.mhealth.activities;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.timqi.sectorprogressview.ColorfulRingProgressView;
import com.wzy.mhealth.MyApplication;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.service.StepCounterService;
import com.wzy.mhealth.utils.MyAndroidUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import me.pedometer.StepDetector;

public class StepCountActivity extends BaActivity {
    private ImageView leftBtn_back;
    private ColorfulRingProgressView crpv;
    private TextView stepTv;
    private GraphView linechart;
    private Thread thread;  //定义线程对象
    private ArrayList<HashMap<String, String>> list;
    int step;
    String time;
    private int total_step = 0;   //走的总步数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);
        init();
        String nowstep = MyApplication.sharedPreferences.getString(Constants.STEP,
                null);
        if (nowstep != null)
            step = Integer.parseInt(nowstep);
        time = MyApplication.sharedPreferences.getString(Constants.STEPDATE,
                null);
        if (!getTime().equals(time)) {
            step = 0;
            StepDetector.CURRENT_STEP = 0;
        }
        aboutChart();
        stepTv.setText(step + "");// 显示当前步数
        crpv.setPercent((step) / 10);
        initThreadToPedometer();
        startService(new Intent(StepCountActivity.this, StepCounterService.class));


    }

    private void init() {
        leftBtn_back = (ImageView) findViewById(R.id.leftBtn_back);
        leftBtn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        linechart = (GraphView) findViewById(R.id.linechart);
        crpv = (ColorfulRingProgressView) findViewById(R.id.crpv);
        stepTv = (TextView) findViewById(R.id.activity_main_step_tv);
    }


    private void aboutChart() {
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1, 0),
                new DataPoint(2, 0),
                new DataPoint(3, 0),
                new DataPoint(4, 0),
                new DataPoint(5, 0),
                new DataPoint(6, 0),
                new DataPoint(7, 0)
        });
        linechart.addSeries(series);
        linechart.getLegendRenderer().setTextColor(Color.WHITE);
        // style
        series.setColor(Color.rgb(191, 226, 226));
        series.setThickness(2);
        // legend
        series.setTitle("步数");
        linechart.getLegendRenderer().setVisible(true);
        linechart.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.MIDDLE);


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
            super.handleMessage(msg);// 此处可以更新UI
            countStep();          //调用步数方法
            stepTv.setText(total_step + step + "");// 显示当前步数
            crpv.setPercent((total_step + step) / 10);
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
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String nowdata = sf.format(new Date());
        return nowdata;
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
