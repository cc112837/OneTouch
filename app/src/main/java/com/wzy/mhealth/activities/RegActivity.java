package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.wzy.mhealth.LeanChat.util.Utils;
import com.wzy.mhealth.MyApplication;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.utils.MyAndroidUtil;

import org.json.JSONException;
import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegActivity extends Activity implements View.OnClickListener{
    private EditText et_phone, et_code, register_password;
    private Button Message_btn, register_btn;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
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
        register_password = (EditText) findViewById(R.id.register_password);
        Message_btn = (Button) findViewById(R.id.Message_btn);
        register_btn = (Button) findViewById(R.id.register_btn);
        btn_back = (Button) findViewById(R.id.btn_back);
    }

    private void initSms() {
        SMSSDK.initSDK(this, "10fb5eade1138", "d52a83a2049860c418ea494ea9b4f18b");

    }

    @Override
    public void onClick(View v) {
        final String userPhone = et_phone.getText().toString();
        final String Phonecode = et_code.getText().toString();
        final String pass = register_password.getText().toString();
        switch (v.getId()) {
            case R.id.Message_btn:
                if (userPhone.length() != 11) {
                    Toast.makeText(this, "请输入11位有效手机号码", Toast.LENGTH_SHORT).show();
                } else {
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
                    cn.smssdk.EventHandler eh = new EventHandler() {

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
                break;
            case R.id.register_btn:
                if(pass.length()<6){
                    Toast.makeText(this, "请输入6位或者6位以上密码", Toast.LENGTH_SHORT).show();
                }
                MyAndroidUtil.editXmlByString("phone",userPhone);
                MyAndroidUtil.editXmlByString("pass",pass);
                RequestParams params = new RequestParams();
                params.addBodyParameter("appkey", "10fb5eade1138");
                params.addBodyParameter("phone", userPhone);
                params.addBodyParameter("zone", "86");
                params.addBodyParameter("code", Phonecode);
                new HttpUtils().send(HttpRequest.HttpMethod.POST, "https://webapi.sms.mob.com/sms/verify",
                        params, new RequestCallBack<Object>() {
                            @Override
                            public void onSuccess(ResponseInfo<Object> responseInfo) {
                                try {
                                    JSONObject object = new JSONObject(responseInfo.result.toString());
                                    String s = object.getString("status");
                                    if ("200".equals(s)) {
                                        LeanchatUser.signUpByNameAndPwdAndProperty(userPhone, pass, "user", new SignUpCallback() {
                                            @Override
                                            public void done(AVException e) {
                                                if (e != null) {
                                                    Utils.toast(MyApplication.getInstance().getString(
                                                            R.string.registerFailed)
                                                            + e.getMessage());
                                                } else {
                                                    Utils.toast(R.string.registerSucceed);
                                                    MyAndroidUtil.editXmlByString(
                                                            Constants.LOGIN_ACCOUNT, userPhone);
                                                    Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            }
                                        });
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

