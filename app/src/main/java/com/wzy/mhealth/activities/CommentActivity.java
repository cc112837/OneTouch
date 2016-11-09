package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.ZanAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.Zan;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentActivity extends Activity {
    private ImageView leftBtn;
    private ListView lv_show;
    private ZanAdapter zanAdapter;
    private List<Zan.DataEntity> list=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 269:
                    Zan zan=(Zan) msg.obj;
                    list.clear();
                    list.addAll(zan.getData());
                    zanAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        leftBtn=(ImageView)findViewById(R.id.leftBtn);
        lv_show=(ListView) findViewById(R.id.lv_show);
        zanAdapter = new ZanAdapter(this, list);
        lv_show.setAdapter(zanAdapter);
        String url= Constants.SERVER_URL+"LikeNumRankServlet";
        TiUser user=new TiUser();
        user.setName(LeanchatUser.getCurrentUser().getUsername());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String nowdata = sf.format(new Date());
        user.setPass(nowdata);
        MyHttpUtils.handData(handler,269,url,user);

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
