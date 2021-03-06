package com.wzy.mhealth.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.OrderAppiontAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.OrderAppiont;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 预约医生订单列表
*/
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
                case 305:
                    StepInfo stepInfo=(StepInfo) msg.obj;
                    Toast.makeText(OrderAppointActivity.this,stepInfo.getData(),Toast.LENGTH_LONG).show();
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
        orderAppiontAdapter = new OrderAppiontAdapter(this, list,handler);
        lvShow.setAdapter(orderAppiontAdapter);
    }

    @OnClick({R.id.leftBtn,R.id.rightBtn})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rightBtn:
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderAppointActivity.this);
                builder.setMessage("关于预约报到：\n" +
                        "1.用户务必在到达医院后再“点击排队就诊”按钮，以免影响医生安排加号；\n" +
                        "2.“点击排队就诊”按钮只有在预约当天时段才可点击；");
                builder.setTitle("如何预约");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.leftBtn:
                finish();
                break;
        }

    }
}
