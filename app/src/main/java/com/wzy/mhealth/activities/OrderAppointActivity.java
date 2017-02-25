package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.OrderAppiontAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.OrderAppiont;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderAppointActivity extends AppCompatActivity {
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 304:
                    OrderAppiont orderAppiont=(OrderAppiont) msg.obj;
                    if(orderAppiont!=null){
                        list.addAll(orderAppiont.getData());
                        orderAppiontAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    };

    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(R.id.rightBtn)
    TextView rightBtn;
    @Bind(R.id.lv_show)
    ListView lvShow;
    private OrderAppiontAdapter orderAppiontAdapter;
    private List<OrderAppiont.DataEntity> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_appoint);
        ButterKnife.bind(this);
        String url= Constants.SERVER_URL+"PatientControlShowServlet";
        MyHttpUtils.handData(handler,304,url,null);
        orderAppiontAdapter = new OrderAppiontAdapter(this, list);
        lvShow.setAdapter(orderAppiontAdapter);
    }

    @OnClick({R.id.leftBtn,R.id.rightBtn})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rightBtn:
                break;
            case R.id.leftBtn:
                finish();
                break;
        }

    }
}
