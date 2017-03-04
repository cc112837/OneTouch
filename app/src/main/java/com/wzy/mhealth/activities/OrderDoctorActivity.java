package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.OrderDoctorAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.AppointDoctor;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 预约医生选择时间
*/
public class OrderDoctorActivity extends AppCompatActivity {
    List<AppointDoctor.DataEntity> friends=new ArrayList<>();
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 301:
                    AppointDoctor appointDoctor =(AppointDoctor) msg.obj;
                    friends.addAll(appointDoctor.getData());
                    doctorAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };
    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(R.id.rv_show)
    RecyclerView rvShow;
    private OrderDoctorAdapter doctorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_doctor);
        String flag = getIntent().getStringExtra("flag");
        ButterKnife.bind(this);
        String url= Constants.SERVER_URL+"AppointTimeServlet";
        TiUser tiUser=new TiUser();
        tiUser.setCardId(flag+"");
        MyHttpUtils.handData(handler,301,url,tiUser);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvShow.setLayoutManager(linearLayoutManager);
        doctorAdapter = new OrderDoctorAdapter(getApplicationContext(), friends);
        doctorAdapter.setId(flag);
        rvShow.setAdapter(doctorAdapter);
    }

    @OnClick(R.id.leftBtn)
    public void onClick() {
        finish();
    }
}
