package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;
import com.avos.avoscloud.UpdatePasswordCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.wzy.mhealth.LeanChat.service.PushManager;
import com.wzy.mhealth.LeanChat.util.Utils;
import com.wzy.mhealth.MyApplication;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.ForgetPass;
import com.wzy.mhealth.model.Regmodel;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyAndroidUtil;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.Tool;

public class CheckActivity extends Activity implements View.OnClickListener {
    private EditText register_password, register_password_again;
    private String pass;
    private String userPhone;
    private Button check_btn;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        Intent intent = getIntent();
        userPhone = intent.getStringExtra("phone");
        flag = intent.getStringExtra("flag");
        init();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 180:
                    ForgetPass forgetPass = (ForgetPass) msg.obj;
                    if (forgetPass.getStatus().equals("1")) {
                        final String forpass = forgetPass.getPassword();
                        LeanchatUser.logInInBackground(userPhone, forpass,
                                new LogInCallback<LeanchatUser>() {
                                    @Override
                                    public void done(LeanchatUser avUser, AVException e) {
                                        if (e == null) {
                                            AVUser aUser = AVUser.getCurrentUser();
                                            aUser.get("property");
                                            if (aUser.get("property").equals("user")) {
                                                // 第二个参数：登录标记 Tag
                                                ChatManager chatManager = ChatManager.getInstance();
                                                chatManager.setupManagerWithUserId(AVUser.getCurrentUser().getObjectId());
                                                chatManager.openClient(null);
                                                aUser.updatePasswordInBackground(forpass, pass,
                                                        new UpdatePasswordCallback() {

                                                            @Override
                                                            public void done(AVException arg0) {
                                                                if (arg0 == null) {
                                                                    String uti = Constants.SERVER_URL + "MhealthUserPasswordServlet";
                                                                    TiUser user1 = new TiUser();
                                                                    user1.setName(userPhone);
                                                                    user1.setPass(pass);
                                                                    MyHttpUtils.handData(handler, 181, uti, user1);
                                                                } else
                                                                    Tool.initToast(getApplicationContext(),
                                                                            "设置密码失败");
                                                            }
                                                        });

                                            } else {
                                                ChatManager chatManager = ChatManager.getInstance();
                                                chatManager.closeWithCallback(new AVIMClientCallback() {
                                                    @Override
                                                    public void done(AVIMClient avimClient, AVIMException e) {
                                                    }
                                                });
                                                PushManager.getInstance().unsubscribeCurrentUserChannel();
                                                AVUser.logOut();
                                                Tool.initToast(CheckActivity.this,
                                                        getResources().getString(R.string.login_error));
                                            }
                                        } else
                                            Tool.initToast(CheckActivity.this,
                                                    getResources().getString(R.string.login_error));
                                    }
                                }, LeanchatUser.class);

                    } else {
                        Toast.makeText(CheckActivity.this, forgetPass.getData() + "", Toast.LENGTH_LONG).show();
                    }

                    break;
                case 155:
                    Regmodel regmodel = (Regmodel) msg.obj;
                    if (regmodel.getStatus().equals("1")) {
                        Utils.toast(R.string.registerSucceed);
                        LeanchatUser.getCurrentUser().setMobilePhoneNumber(userPhone);
                        MyAndroidUtil.editXmlByString(
                                Constants.LOGIN_ACCOUNT, userPhone);
                        MyAndroidUtil.editXmlByString("pass", pass);
                        Intent intent = new Intent(CheckActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Utils.toast(R.string.register_fail);
                    }
                    break;
                case 181:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (stepInfo.getStatus().equals("1")) {
                        Tool.initToast(getApplicationContext(),
                                "设置密码成功，请重新登录");

                        finish();
                    } else {
                        Tool.initToast(getApplicationContext(),
                                "设置密码失败");
                    }
                    break;
            }
        }
    };

    private void init() {
        register_password = (EditText) findViewById(R.id.register_password);
        register_password_again = (EditText) findViewById(R.id.register_password_again);
        check_btn = (Button) findViewById(R.id.check_btn);
        check_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        pass = register_password.getText().toString();
        String again = register_password_again.getText().toString();
        switch (v.getId()) {
            case R.id.check_btn:
                if (pass.length() < 6 || again.length() < 6) {
                    Toast.makeText(this, "请输入6位或者6位以上密码", Toast.LENGTH_SHORT).show();
                }
                if (!pass.equals(again)) {
                    Toast.makeText(this, "请确认密码是否输入一致", Toast.LENGTH_SHORT).show();

                }
                if ("reg".equals(flag)) {
                    LeanchatUser.signUpByNameAndPwdAndProperty(userPhone, pass, "user", new SignUpCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e != null) {
                                Utils.toast(MyApplication.getInstance().getString(
                                        R.string.registerFailed)
                                        + e.getMessage());
                            } else {
                                String url = Constants.SERVER_URL + "MhealthUserRegisterServlet";
                                TiUser user = new TiUser();
                                user.setName(userPhone + "");
                                user.setPass(pass + "");
                                MyHttpUtils.handData(handler, 155, url, user);
                            }
                        }
                    });
                } else {
                    String uri = Constants.SERVER_URL + "MhealthUserOldPasswordServlet";
                    TiUser user = new TiUser();
                    user.setName(userPhone + "");
                    MyHttpUtils.handData(handler, 180, uri, user);
                }

                break;
        }
    }
}
