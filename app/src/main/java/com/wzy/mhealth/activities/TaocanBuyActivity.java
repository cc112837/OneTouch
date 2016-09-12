package com.wzy.mhealth.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.unionpay.UPPayAssistEx;
import com.wzy.mhealth.R;
import com.wzy.mhealth.ali.PayResult;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.AliPayBack;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.view.PayRadioGroup;
import com.wzy.mhealth.view.PayRadioPurified;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class TaocanBuyActivity extends Activity implements Handler.Callback,
        Runnable {
    private ImageView leftBtn;
    private SnappingStepper stepperCustom;
    private Button btn_pay;
    private LinearLayout ll_youhui;
    private PayRadioGroup pay_fun;
    private PayRadioPurified bank, alipay;
    private TextView tv_price, newid, tv_name;
    private String name, price, old,id;
    private Context mContext = null;
    private Handler mHandler = null;
    int number=1;
    private ProgressDialog mLoadingDialog = null;
    private static final int SDK_ALIPAY_FLAG = 1;
    private static final int SDK_UNIONPAY_FLAG = 2;
    /*****************************************************************
     * mMode参数解释： "00" - 启动银联正式环境 "01" - 连接银联测试环境
     *****************************************************************/
    private final String mMode = "01";
    private static final String TN_URL_01 = "http://101.231.204.84:8091/sim/getacptn";
    String flag = "bank";
    private final View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (flag == "bank") {
//                if (TextUtils.isEmpty(APPID) || TextUtils.isEmpty(RSA_PRIVATE)) {
//                    new AlertDialog.Builder(TaocanBuyActivity.this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
//                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialoginterface, int i) {
//                                    //
//                                    finish();
//                                }
//                            }).show();
//                    return;
//                }
//                Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID);
//                String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
//                String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE);
//                final String orderInfo = orderParam + "&" + sign;
                String url= Constants.SERVER_URL+"AliPayServlet";
                TiUser user=new TiUser();
                user.setName(id);
                user.setCardId(number+"");
                MyHttpUtils.handData(mHandler, 40, url, user);

            } else {
                mLoadingDialog = ProgressDialog.show(mContext, // context
                        "", // title
                        "正在努力的获取tn中,请稍候...", // message
                        true); // 进度是否是不确定的，这只和创建进度条有关
                //*步骤1：从网络开始,获取交易流水号即TN
                new Thread(TaocanBuyActivity.this).start();

            }
        }
    };

    public void doStartUnionPayPlugin(Activity activity, String tn,
                                      String mode) {
        UPPayAssistEx.startPay(activity, null, null, tn, mode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_buy);
        mContext = this;
        mHandler = new Handler(this);
        Intent intent = getIntent();
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
        stepperCustom.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
            @Override
            public void onValueChange(View view, int value) {
                switch (view.getId()) {
                    case R.id.stepperCustom:
                        number=value;
                        tv_price.setText(Integer.parseInt(price) * value + "元");
                        break;
                }
            }
        });
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
                if ("银联支付".equals(rl.getTextTitle())) {
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
            case SDK_UNIONPAY_FLAG:
                if (mLoadingDialog.isShowing()) {
                    mLoadingDialog.dismiss();
                }

                String tn = "";
                if (msg.obj == null || ((String) msg.obj).length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("错误提示");
                    builder.setMessage("网络连接失败,请重试!");
                    builder.setNegativeButton("确定",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.create().show();
                } else {
                    tn = (String) msg.obj;
                    /*************************************************
                     * 步骤2：通过银联工具类启动支付插件
                     ************************************************/
                    doStartUnionPayPlugin(this, tn, mMode);
                }
                break;
            case 40:
                AliPayBack stepInfo=(AliPayBack) msg.obj;
                final String orderInfo=stepInfo.getData();
                Log.e("qinming",orderInfo);
                Runnable payRunnable = new Runnable() {

                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(TaocanBuyActivity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Log.i("msp", result.toString());
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
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                String resultStatus = payResult.getResultStatus();
                // 判断resultStatus 为9000则代表支付成功
                if (TextUtils.equals(resultStatus, "9000")) {
                    // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    Toast.makeText(TaocanBuyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    Toast.makeText(TaocanBuyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                }
                break;


        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         ************************************************/
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {
            // 支付成功后，extra中如果存在result_data，取出校验
            // result_data结构见c）result_data参数说明
            if (data.hasExtra("result_data")) {
                String result = data.getExtras().getString("result_data");
                try {
                    JSONObject resultJson = new JSONObject(result);
                    String sign = resultJson.getString("sign");
                    String dataOrg = resultJson.getString("data");
                    // 验签证书同后台验签证书
                    // 此处的verify，商户需送去商户后台做验签
                    boolean ret = verify(dataOrg, sign, mMode);
                    if (ret) {
                        // 验证通过后，显示支付结果
                        msg = "支付成功！";
                    } else {
                        // 验证不通过后的处理
                        // 建议通过商户后台查询支付结果
                        msg = "支付失败！";
                    }
                } catch (JSONException e) {
                }
            } else {
                // 未收到签名信息
                // 建议通过商户后台查询支付结果
                msg = "支付成功！";
            }
        } else if (str.equalsIgnoreCase("fail")) {
            msg = "支付失败！";
        } else if (str.equalsIgnoreCase("cancel")) {
            msg = "用户取消了支付";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("支付结果通知");
        builder.setMessage(msg);
        builder.setInverseBackgroundForced(true);
        // builder.setCustomTitle();
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    @Override
    public void run() {
        String tn = null;
        InputStream is;
        try {

            String url = TN_URL_01;
            URL myURL = new URL(url);
            URLConnection ucon = myURL.openConnection();
            ucon.setConnectTimeout(120000);
            is = ucon.getInputStream();
            int i = -1;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((i = is.read()) != -1) {
                baos.write(i);
            }

            tn = baos.toString();
            is.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Message msg = mHandler.obtainMessage();
        msg.obj = tn;
        msg.what=SDK_UNIONPAY_FLAG;
        mHandler.sendMessage(msg);
    }

    private boolean verify(String msg, String sign64, String mode) {
        return true;
    }

}
