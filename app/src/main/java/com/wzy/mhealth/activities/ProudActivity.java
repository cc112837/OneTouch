package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Proud;
import com.wzy.mhealth.utils.MyHttpUtils;

public class ProudActivity extends Activity{
    private TextView tv_how;
    private ImageView leftBtn;
    private ImageView iv_1,iv_2,iv_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proud);
        init();
    }
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 170:
                Proud proud=(Proud) msg.obj;
                if (proud.isStepNum()){
                    iv_1.setImageResource(R.mipmap.actvit_red);
                }
                if(proud.isShop()){
                    iv_2.setImageResource(R.mipmap.redblood_proud);
                }
                if (proud.isBlood()){
                    iv_3.setImageResource(R.mipmap.shop_proud);
                }
            break;
        }
    }
};
    private void init() {
        tv_how=(TextView) findViewById(R.id.tv_how);
        iv_1=(ImageView) findViewById(R.id.iv_1);
        iv_2=(ImageView) findViewById(R.id.iv_2);
        iv_3=(ImageView) findViewById(R.id.iv_3);
        String url= Constants.SERVER_URL+"MedalServlet";
        MyHttpUtils.handData(handler,170,url,null);
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_how.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ProudActivity.this,LightActivity.class);
                startActivity(intent);
            }
        });




    }
}
