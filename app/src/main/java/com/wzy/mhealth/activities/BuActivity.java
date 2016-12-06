package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.utils.Constants;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.wzy.mhealth.LeanChat.activity.ChatRoomActivity;
import com.wzy.mhealth.R;
import com.wzy.mhealth.ali.PayResult;
import com.wzy.mhealth.model.AliPayBack;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.Tool;
import com.wzy.mhealth.view.PayRadioGroup;
import com.wzy.mhealth.view.PayRadioPurified;

import java.util.Map;


public class BuActivity extends BaActivity implements Handler.Callback {
    private TextView doctorname, price, price1, titleName;
    private Button buy;
    private IWXAPI api;
    private String stringOfPrice, type, doctorid, name, id;
    private Handler mHandler = null;
    private static final int SDK_ALIPAY_FLAG = 1;
    private static final int SDK_WeChat_FLAG = 2;
    String flag = "bank";
    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (flag == "bank") {
                String url = com.wzy.mhealth.constant.Constants.SERVER_URL + "AliPayceishiServlet";
                TiUser user = new TiUser();
                user.setName(id + "");
                MyHttpUtils.handData(mHandler, 40, url, user);
            } else {
                // TODO: 2016/12/6 微信支付
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu);
        api = WXAPIFactory.createWXAPI(this, "wxee8f5f748fbea43c");
        mHandler = new Handler(this);
        doctorid = getIntent().getStringExtra("doctor");
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        stringOfPrice = getIntent().getStringExtra("price");
        type = getIntent().getStringExtra("type");// 1表示是图文咨询
        init();
        PayRadioGroup pay_fun = (PayRadioGroup) findViewById(R.id.pay_fun);
        titleName = (TextView) findViewById(R.id.titleName);
        if (type.equals("1"))
            titleName.setText("购买：图文咨询");
        price = (TextView) findViewById(R.id.price);
        price1 = (TextView) findViewById(R.id.price1);
        price.setText(stringOfPrice + "元/次");
        price1.setText("会员价" + stringOfPrice + "元/次");
        buy = (Button) findViewById(R.id.btn_pay);
        pay_fun.setOnCheckedChangeListener(new PayRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(PayRadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                PayRadioPurified rl = (PayRadioPurified) BuActivity.this
                        .findViewById(radioButtonId);
                for (int i = 0; i < group.getChildCount(); i++) {
                    ((PayRadioPurified) group.getChildAt(i)).setChangeImg(checkedId);
                }
                if ("微信支付".equals(rl.getTextTitle())) {
                    flag = "yinlian";
                } else {
                    flag = "bank";
                }
            }
        });

        buy.setOnClickListener(mClickListener);


    }

    private void init() {
        doctorname = (TextView) findViewById(R.id.doctorname);
        doctorname.setText(name);

    }

    public void leftBtnClick(View v) {
        finish();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case SDK_WeChat_FLAG:


                break;
            case 120:
                StepInfo info = (StepInfo) msg.obj;
                if (info.getStatus().equals("1")) {
                    final ChatManager chatManager = ChatManager.getInstance();
                    chatManager.fetchConversationWithUserId(doctorid,
                            new AVIMConversationCreatedCallback() {
                                @Override
                                public void done(AVIMConversation conversation, AVIMException e) {
                                    if (e != null) {
                                        Toast.makeText(BuActivity.this, e.getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Intent intent = new Intent(BuActivity.this,
                                                ChatRoomActivity.class);
                                        intent.putExtra(Constants.CONVERSATION_ID,
                                                conversation.getConversationId());
                                        Tool.initToast(BuActivity.this,
                                                "支付成功，请24小时内与" + name + "医生咨询");
                                        startActivity(intent);
                                    }
                                }
                            });
                }
                break;
            case 40:
                AliPayBack stepInfo = (AliPayBack) msg.obj;
                final String orderInfo = stepInfo.getData();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(BuActivity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Message msg = new Message();
                        msg.what = SDK_ALIPAY_FLAG;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };
                Thread payThread = new Thread(payRunnable);
                payThread.start();
                break;
            case SDK_ALIPAY_FLAG:
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    String url = com.wzy.mhealth.constant.Constants.SERVER_URL + "PayDoctorServlet";
                    TiUser user = new TiUser();
                    user.setName(resultInfo);
                    user.setTel(id + "");
                    user.setCardId(LeanchatUser.getCurrentUser().getUsername());
                    MyHttpUtils.handData(mHandler, 120, url, user);
                    Toast.makeText(BuActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    Toast.makeText(BuActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
                break;


        }
        return false;
    }

}
