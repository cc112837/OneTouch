package com.wzy.mhealth.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.ForgetPass;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.ToastUtil;
import com.wzy.mhealth.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 用户注册
*/
public class RegActivity extends BaActivity implements View.OnClickListener {
    private EditText et_phone, et_code;
    private Button Message_btn, register_btn;
    private Button btn_back;
    private String userPhone;
    private String flag;
    private String phonecode;
    private String forgetPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        Intent intent = getIntent();
        flag = intent.getStringExtra("flag");
        initSms();
        initView();
        initEvent();
    }

    private void initEvent() {
        register_btn.setOnClickListener(this);
        Message_btn.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    private void initView() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_code = (EditText) findViewById(R.id.et_code);
        Message_btn = (Button) findViewById(R.id.Message_btn);
        register_btn = (Button) findViewById(R.id.register_btn);
        btn_back = (Button) findViewById(R.id.btn_back);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 180:
                    final ForgetPass forgetPass = (ForgetPass) msg.obj;
                    if (forgetPass.getStatus().equals("1")) {
                        forgetPassWord = forgetPass.getPassword();
                        if ("reg".equals(flag)) {
                            ToastUtil.show(RegActivity.this, "该手机号已经注册过");
                        } else {
                            sendMes();
                        }

                    } else {
                        if ("reg".equals(flag)) {
                            sendMes();
                        } else {
                            ToastUtil.show(RegActivity.this, "该号码未注册");
                        }
                    }

                    break;
            }
        }
    };
    public void sendMes() {
        SMSSDK.getSupportedCountries();
        SMSSDK.getVerificationCode("86", userPhone);
        Message_btn.setClickable(false);
        Message_btn.setBackgroundColor(Color.GRAY);
        Toast.makeText(RegActivity.this, "验证码发送成功，请尽快使用", Toast.LENGTH_SHORT).show();
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Message_btn.setBackgroundResource(R.drawable.btn_default_small_normal_disable);
                Message_btn.setText(millisUntilFinished / 1000 + "秒");
            }

            @Override
            public void onFinish() {
                Message_btn.setClickable(true);
                Message_btn.setBackgroundResource(R.drawable.btn_default_small_normal);
                Message_btn.setText("重新发送");
            }
        }.start();
        //进行获取验证码操作和倒计时1分钟操作
        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    private void initSms() {
        SMSSDK.initSDK(this, "159b5bdf78770", "fb8e5913caefd25208c85911cc52bd82");
    }

    @Override
    public void onClick(View v) {
        userPhone = et_phone.getText().toString();
        phonecode = et_code.getText().toString();
        switch (v.getId()) {
            case R.id.Message_btn:
                if (Util.getInstance().isMobileNumber(userPhone)) {
                    String uri = Constants.SERVER_URL + "MhealthUserOldPasswordServlet";
                    TiUser user = new TiUser();
                    user.setName(userPhone + "");
                    MyHttpUtils.handData(handler, 180, uri, user);
                } else {
                    Toast.makeText(this, "请输入11位有效手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.register_btn:
                if ((Util.getInstance().isMobileNumber(userPhone)) && (phonecode.length() == 4)) {
                    RequestParams params = new RequestParams();
                    params.addBodyParameter("appkey", "159b5bdf78770");
                    params.addBodyParameter("phone", userPhone);
                    params.addBodyParameter("zone", "86");
                    params.addBodyParameter("code", phonecode);
                    new HttpUtils().send(HttpRequest.HttpMethod.POST, "https://webapi.sms.mob.com/sms/verify",
                            params, new RequestCallBack<Object>() {
                                @Override
                                public void onSuccess(ResponseInfo<Object> responseInfo) {
                                    try {
                                        JSONObject object = new JSONObject(responseInfo.result.toString());
                                        String s = object.getString("status");
                                        if ("200".equals(s)) {
                                            Intent intent = new Intent(RegActivity.this, CheckActivity.class);
                                            if ("reg".equals(flag)) {
                                                intent.putExtra("pass", "");
                                            } else {
                                                intent.putExtra("pass", forgetPassWord + "");
                                            }
                                            intent.putExtra("phone", userPhone + "");
                                            intent.putExtra("flag", flag + "");
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(RegActivity.this, "验证失败", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                }

                                @Override
                                public void onFailure(HttpException error, String msg) {
                                }
                            });
                } else {
                    ToastUtil.show(RegActivity.this, "请确保手机号和验证码输入正确");
                }
                break;
            case R.id.btn_back:
                finish();
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }


}

