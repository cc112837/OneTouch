package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.SlowAidAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.BingZhen;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 慢病诊疗
*/
public class SlowAidActivity extends Activity {
    private ListView lv_show;
    private ImageView leftBtn;
    private SlowAidAdapter slowAidAdapter;
    private List<String> list = new ArrayList<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 265:
                    final BingZhen bingZhen = (BingZhen) msg.obj;
                    list.clear();
                    for (int i = 0; i < bingZhen.getData().size(); i++) {
                        list.add(bingZhen.getData().get(i).getName());
                    }
                    slowAidAdapter.notifyDataSetChanged();
                    lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            Intent intent=new Intent(SlowAidActivity.this,SlowAidDetailActivity.class);
                            intent.putExtra("detail",bingZhen.getData().get(position).getDetails());
                            intent.putExtra("id",bingZhen.getData().get(position).getId()+"");
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slow_aid);
        String url = Constants.SERVER_URL + "MedicalCommonServlet";
        TiUser user = new TiUser();
        user.setName("2");
        MyHttpUtils.handData(handler, 265, url, user);
        init();
    }

    private void init() {
        lv_show = (ListView) findViewById(R.id.lv_show);
        slowAidAdapter = new SlowAidAdapter(SlowAidActivity.this, list);
        lv_show.setAdapter(slowAidAdapter);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
