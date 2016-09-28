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
import com.wzy.mhealth.adapter.TaocanListAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.Tijian;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class TaocanListActivity extends Activity {
    private ImageView leftBtn;
    private TaocanListAdapter taocanListAdapter;
    private ListView lv_show;
    private List<Tijian.DataEntity>list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_list);
        taocanListAdapter=new TaocanListAdapter(TaocanListActivity.this,list);
        init();
    }
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 157:
                Tijian tijian=(Tijian)msg.obj;
                list.addAll(tijian.getData());
                taocanListAdapter.notifyDataSetChanged();
                lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent=new Intent(TaocanListActivity.this,PersonTaocanActivity.class);
                        intent.putExtra("id",list.get(position).getId()+"");
                        startActivity(intent);
                    }
                });
                break;
        }
    }
};
    private void init() {
        lv_show=(ListView) findViewById(R.id.lv_show);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv_show.setAdapter(taocanListAdapter);
        String url= Constants.SERVER_URL+"MhealthOneCityServlet";
        TiUser user =new TiUser();
        user.setTel("610100");
        MyHttpUtils.handData(handler,157,url,user);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
