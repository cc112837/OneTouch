package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.wzy.mhealth.R;
import com.wzy.mhealth.ali.PayResult;
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
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 预约医生付款
*/
public class OrderDoctorBuyActivity extends  BaActivity implements Handler.Callback {
    private TextView doctorname, price, price1, titleName;
    private Button buy;
    private IWXAPI api;
    private String stringOfPrice, type, name, id;
    private Handler mHandler = null;
    private static final int SDK_ALIPAY_FLAG = 1;
    String flag = "bank";
    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (flag == "bank") {
                String url = com.wzy.mhealth.constant.Constants.SERVER_URL + "AliPayServlet";
                TiUser user = new TiUser();
                user.setName(id + "");
                user.setCardId("1");
                user.setPass("4");
                MyHttpUtils.handData(mHandler, 40, url, user);
            } else {
                //微信支付
                if (PackageUtils.isWeixinAvilible(OrderDoctorBuyActivity.this)) {
                    String url = com.wzy.mhealth.constant.Constants.SERVER_URL + "AliPayceishiServlet";
                    TiUser user = new TiUser();
                    user.setName(id);
                    user.setCardId("1");
                    user.setPass("4");
                    MyHttpUtils.handData(mHandler, 297, url, user);
                } else {
                    ToastUtil.show(OrderDoctorBuyActivity.this, "请先安装微信");
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu);
        api = WXAPIFactory.createWXAPI(this, "wxee8f5f748fbea43c");
        mHandler = new Handler(this);
        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        stringOfPrice = getIntent().getStringExtra("price");
        type = getIntent().getStringExtra("type");
        init();
        PayRadioGroup pay_fun = (PayRadioGroup) findViewById(R.id.pay_fun);
        titleName = (TextView) findViewById(R.id.titleName);
        if (type.equals("1"))
            titleName.setText("医生预约");
        price = (TextView) findViewById(R.id.price);
        price1 = (TextView) findViewById(R.id.price1);
        price.setText(stringOfPrice + "元/次");
        price1.setText("会员价" + stringOfPrice + "元/次");
        buy = (Button) findViewById(R.id.btn_pay);
        pay_fun.setOnCheckedChangeListener(new PayRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(PayRadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();
                PayRadioPurified rl = (PayRadioPurified) OrderDoctorBuyActivity.this
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

            case 120:
                StepInfo info = (StepInfo) msg.obj;
                if (info.getStatus().equals("1")) {
                    Log.e("jiguo","成功");
                }
                break;
            case 40:
                AliPayBack stepInfo = (AliPayBack) msg.obj;
                final String orderInfo = stepInfo.getData();
                Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(OrderDoctorBuyActivity.this);
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
                Toast.makeText(OrderDoctorBuyActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
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
                        OrderDoctorBuyActivity.this.finish();
                    } else {
                        Toast.makeText(OrderDoctorBuyActivity.this, "返回错误" + testWeChat.getReturn_msg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("PAY_GET", "异常：" + e.getMessage());
                    Toast.makeText(OrderDoctorBuyActivity.this, "异常：" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
                break;
            case SDK_ALIPAY_FLAG:
                PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    String url = com.wzy.mhealth.constant.Constants.SERVER_URL + "PayServlet";
                    TiUser user = new TiUser();
                    user.setName(resultInfo);
                    user.setTel(id + "");
                    MyHttpUtils.handData(mHandler, 120, url, user);
                    Toast.makeText(OrderDoctorBuyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    Toast.makeText(OrderDoctorBuyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
                break;


        }
        return false;
    }

}