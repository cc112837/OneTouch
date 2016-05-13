package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.BiLiAdapter;
import com.wzy.mhealth.model.NewsMedi;

import java.util.List;
import java.util.Random;

public class JiBingActivity extends Activity {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 5:
                    NewsMedi obj =(NewsMedi) msg.obj;
                    list = obj.getTngou();
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    private SwipeRefreshLayout sp_refresh;
    private ListView lv_display;
    private BiLiAdapter adapter;
    private List<NewsMedi.TngouEntity> list;
    private ImageView leftBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ji_bing);
        initView();
    }
    //初始化界面控件
    private void initView() {
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        sp_refresh=(SwipeRefreshLayout) findViewById(R.id.sp_refresh);
        lv_display=(ListView) findViewById(R.id.lv_display);
        adapter=new BiLiAdapter(list,JiBingActivity.this);
        lv_display.setAdapter(adapter);
        requeList();
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lv_display.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(JiBingActivity.this, JiBingDetailActicity.class);
                intent.putExtra("id",list.get(position).getId()+"");
                startActivity(intent);
            }
        });
        sp_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sp_refresh.setRefreshing(false);
                adapter.clear();
                requeList();

            }
        });
    }

    private void requeList() {
        Random random = new Random();
        int i = random.nextInt(8);
        new HttpUtils().send(HttpRequest.HttpMethod.GET, "http://www.tngou.net/api/info/list?id=" + i, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                NewsMedi newsMedi = new Gson().fromJson(result, NewsMedi.class);
                Message msg = new Message();
                msg.what = 5;
                msg.obj = newsMedi;
                handler.sendMessage(msg);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.i("下载失败", msg);
            }
        });
    }
}
