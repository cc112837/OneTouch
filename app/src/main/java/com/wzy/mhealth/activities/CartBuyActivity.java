package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.wzy.mhealth.R;
import com.wzy.mhealth.ali.PayResult;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.AliPayBack;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.view.PayRadioGroup;
import com.wzy.mhealth.view.PayRadioPurified;

import java.util.Map;

public class CartBuyActivity extends Activity implements Handler.Callback{
    private ImageView leftBtn;
    private Button btn_pay;
    private PayRadioGroup pay_fun;
    private PayRadioPurified bank, alipay;
    private TextView tv_price;
    private Handler mHandler = null;
    Intent intent;
    String addressId,price;
    private static final int SDK_ALIPAY_FLAG = 1;
    private static final int SDK_WECHAT_FLAG = 2;
   String flag = "bank";
    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (flag == "bank") {
                String url = Constants.SERVER_URL + "MhealthShopingPayServlet";
                TiUser user = new TiUser();
                user.setName(price);
                user.setCardId(addressId);
                MyHttpUtils.handData(mHandler, 287, url, user);

            } else {
                Toast.makeText(CartBuyActivity.this, "暂未开通其他服务，敬请期待", Toast.LENGTH_LONG).show();

            }
        }

    };


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cartbuy);
            mHandler = new Handler(this);
            intent = getIntent();
            price = intent.getStringExtra("price");
            addressId = intent.getStringExtra("addressId");
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
            tv_price.setText(price + "元");
            btn_pay = (Button) findViewById(R.id.btn_pay);
            btn_pay.setOnClickListener(mClickListener);
            pay_fun.setOnCheckedChangeListener(new PayRadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(PayRadioGroup group, int checkedId) {
                    int radioButtonId = group.getCheckedRadioButtonId();
                    PayRadioPurified rl = (PayRadioPurified) CartBuyActivity.this
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

        }

        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_WECHAT_FLAG:
                    //其他支付
                    break;
                case 288:
                    StepInfo info = (StepInfo) msg.obj;
                    if (info.getStatus().equals("1")) {
                        Intent intent1 = new Intent(CartBuyActivity.this, ShoporderActivity.class);
                        startActivity(intent1);
                        CartBuyActivity.this.finish();
                    }

                    break;
                case 287:
                    AliPayBack stepInfo = (AliPayBack) msg.obj;
                    final String orderInfo = stepInfo.getData();
                    Runnable payRunnable = new Runnable() {
                        @Override
                        public void run() {
                            PayTask alipay = new PayTask(CartBuyActivity.this);
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
                        String url = Constants.SERVER_URL + "MhealthShopPayServlet";
                        TiUser user = new TiUser();
                        user.setName(resultInfo);
                        user.setCardId(addressId + "");
                        MyHttpUtils.handData(mHandler, 288, url, user);
                        Toast.makeText(CartBuyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(CartBuyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;


            }
            return false;
        }


}
