package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bigkoo.snappingstepper.SnappingStepper;
import com.bigkoo.snappingstepper.listener.SnappingStepperValueChangeListener;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.wzy.mhealth.R;
import com.wzy.mhealth.ali.PayResult;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.AliPayBack;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TestWeChat;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.PackageUtils;
import com.wzy.mhealth.utils.ToastUtil;
import com.wzy.mhealth.view.PayRadioGroup;
import com.wzy.mhealth.view.PayRadioPurified;

import java.util.Map;

public class TaocanBuyActivity extends Activity implements Handler.Callback{
    private ImageView leftBtn;
    private SnappingStepper stepperCustom;
    private Button btn_pay;
    private IWXAPI api;
    private LinearLayout ll_youhui;
    private PayRadioGroup pay_fun;
    private PayRadioPurified bank, alipay;
    private TextView tv_price, newid, tv_name;
    private String name, price, old,id;
    private Handler mHandler = null;
    int number=1;
    Intent intent;
    private static final int SDK_ALIPAY_FLAG = 1;
   String flag = "bank";
    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (flag == "bank") {
                String url= Constants.SERVER_URL+"AliPayServlet";
                TiUser user=new TiUser();
                user.setName(id);
                user.setCardId(number+"");
                user.setPass("1");
                MyHttpUtils.handData(mHandler, 40, url, user);

            } else {
                //微信支付
                if (PackageUtils.isWeixinAvilible(TaocanBuyActivity.this)) {
                    String url = com.wzy.mhealth.constant.Constants.SERVER_URL + "AliPayceishiServlet";
                    TiUser user = new TiUser();
                    user.setName(id);
                    user.setCardId(number+"");
                    user.setPass("1");
                    MyHttpUtils.handData(mHandler, 297, url, user);
                } else {
                    ToastUtil.show(TaocanBuyActivity.this, "请先安装微信");
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_buy);
        api = WXAPIFactory.createWXAPI(this, "wxee8f5f748fbea43c");
        mHandler = new Handler(this);
        intent = getIntent();
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        old = intent.getStringExtra("old");
        id=intent.getStringExtra("id");
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bank = (PayRadioPurified) findViewById(R.id.bank);
        pay_fun = (PayRadioGroup) findViewById(R.id.pay_fun);
        alipay = (PayRadioPurified) findViewById(R.id.alipay);
        tv_price = (TextView) findViewById(R.id.tv_price);
        ll_youhui = (LinearLayout) findViewById(R.id.ll_youhui);
        tv_name = (TextView) findViewById(R.id.tv_name);
        newid = (TextView) findViewById(R.id.newid);
        stepperCustom = (SnappingStepper) findViewById(R.id.stepperCustom);
        if(null==old){
            stepperCustom.setVisibility(View.INVISIBLE);
            tv_price.setText(Double.parseDouble(price) + "元");
        }
        else {
            stepperCustom.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
                @Override
                public void onValueChange(View view, int value) {
                    switch (view.getId()) {
                        case R.id.stepperCustom:
                            number = value;
                            tv_price.setText(Double.parseDouble(price) * value + "元");
                            break;
                    }
                }
            });
        }
        tv_price.setText(price + "元");
        tv_name.setText(name + "");
        newid.setText(price + "元");
        btn_pay = (Button) findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(mClickListener);
        pay_fun.setOnCheckedChangeListener(new PayRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(PayRadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                PayRadioPurified rl = (PayRadioPurified) TaocanBuyActivity.this
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
        ll_youhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TaocanBuyActivity.this, "暂无优惠劵！", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 120:
                StepInfo info= (StepInfo)msg.obj;
                if(info.getStatus().equals("1")){
                    Intent intent1=new Intent(TaocanBuyActivity.this,ExaminationOrderActivity.class);
                    startActivity(intent1);
                    TaocanBuyActivity.this.finish();
                }

                break;
            case 40:
                AliPayBack stepInfo=(AliPayBack) msg.obj;
                final String orderInfo=stepInfo.getData();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(TaocanBuyActivity.this);
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
            case 297:
                TestWeChat testWeChat = (TestWeChat) msg.obj;
                Toast.makeText(TaocanBuyActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
                try {

                    if (null != testWeChat && "SUCCESS".equals(testWeChat.getResult_code())) {
                        PayReq req = new PayReq();
                        req.appId = testWeChat.getAppid();
                        req.partnerId = testWeChat.getPartnerid();
                        req.prepayId = testWeChat.getPrepayid();
                        req.nonceStr = testWeChat.getNoncestr();
                        req.timeStamp = testWeChat.getTimestamp();
                        req.packageValue = testWeChat.getPackageX();
                        req.sign = testWeChat.getSign();
                        req.extData = testWeChat.getTrade_type(); // optional
                        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                        api.sendReq(req);
                        TaocanBuyActivity.this.finish();
                    } else {
                        Toast.makeText(TaocanBuyActivity.this, "返回错误" + testWeChat.getReturn_msg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("PAY_GET", "异常：" + e.getMessage());
                    Toast.makeText(TaocanBuyActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
                break;
            case SDK_ALIPAY_FLAG:
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    String url=Constants.SERVER_URL+"PayServlet";
                    TiUser user=new TiUser();
                    user.setName(resultInfo);
                    user.setTel(id+"");
                    MyHttpUtils.handData(mHandler,120,url,user);
                    Toast.makeText(TaocanBuyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    Toast.makeText(TaocanBuyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
                break;


        }
        return false;
    }



}
