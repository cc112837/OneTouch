package com.wzy.mhealth.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.wzy.mhealth.LeanChat.service.PushManager;
import com.wzy.mhealth.MyApplication;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.utils.MyAndroidUtil;
import com.wzy.mhealth.utils.Tool;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * @author cc112837@163.com
 *
 */
public class LoginActivity extends BaseActivity implements TextWatcher ,PlatformActionListener,OnClickListener{
    private Button loginBtn, regButton,forgetButton;
    private EditText nameText, pwdText;
    private String name, pwd;
    private ImageView iv_qqlogin,iv_weibologin;
    private LinearLayout loginLinear, qidongLinear;
    private static final int MSG_AUTH_CANCEL = 2;
    private static final int MSG_AUTH_ERROR= 3;
    private static final int MSG_AUTH_COMPLETE = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ShareSDK.initSDK(LoginActivity.this);

        String appId = "cirdf9pJrnd6XpNW1Xn3OVf5-gzGzoHsz";
        String appKey = "eFwqv2nwhEDg9qdqzPUr3fga";
        AVOSCloud.initialize(LoginActivity.this, appId, appKey);
        if (AVUser.getCurrentUser() != null) {
            name = MyApplication.sharedPreferences.getString(Constants.LOGIN_ACCOUNT,
                    null);
            finishLogin();
        }
        initTitle();
        loginLinear = (LinearLayout) findViewById(R.id.loginLinear);
        qidongLinear = (LinearLayout) findViewById(R.id.qidongLinear);
        iv_qqlogin=(ImageView) findViewById(R.id.iv_qqlogin);
        iv_weibologin=(ImageView) findViewById(R.id.iv_weibologin);
        nameText = (EditText) findViewById(R.id.nameText);
        pwdText = (EditText) findViewById(R.id.pwdText);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        regButton = (Button) findViewById(R.id.regButton);
        forgetButton = (Button) findViewById(R.id.forgetButton);
        forgetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, NoContentActivity.class);
                startActivity(intent);
            }
        });
        nameText.addTextChangedListener(this);
        loginBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                name = nameText.getText().toString();
                pwd = pwdText.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Tool.initToast(LoginActivity.this, getString(R.string.register_name));
                } else if (TextUtils.isEmpty(pwd)) {
                    Tool.initToast(LoginActivity.this,
                            getString(R.string.register_password));
                } else {
                    loginAccount(name, pwd);
                }
            }
        });
        iv_qqlogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 2016/3/22
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                if (qq.isValid ()) {
                    qq.removeAccount();
                }
                qq.SSOSetting(false);  //设置false表示使用SSO授权方式
                qq.setPlatformActionListener(LoginActivity.this); // 设置分享事件回调
                qq.showUser(null);
                qq.authorize();
            }
        });
        iv_weibologin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/3/22
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                if (weibo.isValid ()) {
                    weibo.removeAccount();
                }
                weibo.SSOSetting(false);  //设置false表示使用SSO授权方式
                weibo.setPlatformActionListener(LoginActivity.this); // 设置分享事件回调
                weibo.showUser(null);
                weibo.authorize();
            }});
        regButton.setOnClickListener(this);

        // 已登录过,自动登录
        name = MyApplication.sharedPreferences.getString(Constants.LOGIN_ACCOUNT,
                null);
        pwd = MyApplication.sharedPreferences.getString(Constants.LOGIN_PWD, null);
        if (name != null)
            nameText.setText(name);
        thread.start();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                name = nameText.getText().toString();
                pwd = pwdText.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Tool.initToast(LoginActivity.this, getString(R.string.register_name));
                } else if (TextUtils.isEmpty(pwd)) {
                    Tool.initToast(LoginActivity.this,
                            getString(R.string.register_password));
                } else {
                    loginAccount(name, pwd);
                }
                break;
            case R.id.regButton:
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);

            default:
                break;
        }
    }

    private void loginAccount(final String userName, final String password) {
        final ProgressDialog dialog = showSpinnerDialog();
        LeanchatUser.logInInBackground(userName, password,
                new LogInCallback<LeanchatUser>() {
                    @Override
                    public void done(LeanchatUser avUser, AVException e) {
                        dialog.dismiss();
                        if (e == null) {
                            AVUser aUser = AVUser.getCurrentUser();

                            aUser.get("property");
                            if (aUser.get("property").equals("user")) {
                                // 第二个参数：登录标记 Tag
                                finishLogin();
                            } else {
                                ChatManager chatManager = ChatManager.getInstance();
                                chatManager.closeWithCallback(new AVIMClientCallback() {
                                    @Override
                                    public void done(AVIMClient avimClient, AVIMException e) {
                                    }
                                });
                                PushManager.getInstance().unsubscribeCurrentUserChannel();
                                AVUser.logOut();
                                Tool.initToast(LoginActivity.this,
                                        getResources().getString(R.string.login_error));
                            }
                        } else
                            Tool.initToast(LoginActivity.this,
                                    getResources().getString(R.string.login_error));
                    }
                }, LeanchatUser.class);
    }

    private void finishLogin() {
        AVIMClient currentClient = AVIMClient.getInstance("android", "Mobile");
        currentClient.open(new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (e == null) {
                }
            }
        });
        Constants.USER_NAME = name;
        ChatManager chatManager = ChatManager.getInstance();
        chatManager.setupManagerWithUserId(AVUser.getCurrentUser().getObjectId());
        chatManager.openClient(null);
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getIntent().getBooleanExtra("isRelogin", false)) {
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        name = MyApplication.sharedPreferences.getString(Constants.LOGIN_ACCOUNT,
                null);
        if (name != null)
            nameText.setText(name);
        super.onResume();
    }

    @Override
    public void afterTextChanged(Editable arg0) {
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        MyAndroidUtil.editXmlByString(Constants.LOGIN_ACCOUNT, nameText.getText()
                .toString());
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    qidongLinear.setVisibility(View.GONE);
                    loginLinear.setVisibility(View.VISIBLE);
                    break;
                case MSG_AUTH_CANCEL: {
                    //取消授权
                    Toast.makeText(getApplicationContext(), "取消授权", Toast.LENGTH_SHORT).show();
                } break;
                case MSG_AUTH_ERROR: {
                    //授权失败
                    Toast.makeText(LoginActivity.this,"授权错误", Toast.LENGTH_SHORT).show();
                } break;
                case MSG_AUTH_COMPLETE: {
                    //授权成功
                    Toast.makeText(LoginActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    Object[] objs = (Object[]) msg.obj;
                    String platform = (String) objs[0];
                    HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                    final PlatformDb plat =(PlatformDb)objs[2];
                    if(QQ.NAME.equals(platform)){
                        final String  nickname=res.get("nickname").toString();
                        final String icon=res.get("figureurl_qq_2").toString();
                        AVUser.AVThirdPartyUserAuth auth =
                                new AVUser.AVThirdPartyUserAuth(plat.getToken(), String.valueOf(plat
                                        .getExpiresTime()),AVUser.AVThirdPartyUserAuth.SNS_TENCENT_WEIBO, plat
                                        .getUserId());
                        AVUser.loginWithAuthData(auth, new LogInCallback<AVUser>(){
                            @Override
                            public void done(AVUser user, AVException e) {
                                if (e == null) {
                                    //恭喜你，已经和我们的 AVUser 绑定成功

                                    MyAndroidUtil.editXmlByString(
                                            Constants.LOGIN_ACCOUNT, nickname);
                                    user.setUsername(nickname);
                                    user.put("property", "user");
                                    AVFile avFile = new AVFile(nickname,icon,null);
                                    user.put("avatar", avFile);
                                    user.signUpInBackground(new SignUpCallback() {
                                        @Override
                                        public void done(AVException e) {

                                        }
                                    });
                                    name=nickname;
                                    finishLogin();
                                } else {
                                    e.printStackTrace();
                                }

                            }
                        });

                    }
                    if(SinaWeibo.NAME.equals(platform)){
                        final String  nickname=res.get("nickname").toString();
                        final String icon=res.get("figureurl_qq_2").toString();
                        AVUser.AVThirdPartyUserAuth auth =
                                new AVUser.AVThirdPartyUserAuth(plat.getToken(), String.valueOf(plat
                                        .getExpiresTime()),AVUser.AVThirdPartyUserAuth.SNS_SINA_WEIBO , plat
                                        .getUserId());
                        AVUser.loginWithAuthData(auth, new LogInCallback<AVUser>(){
                            @Override
                            public void done(AVUser user, AVException e) {
                                if (e == null) {
                                    MyAndroidUtil.editXmlByString(
                                            Constants.LOGIN_ACCOUNT, nickname);
                                    user.setUsername(nickname);
                                    user.put("property", "user");
                                    AVFile avFile = new AVFile(nickname,icon,null);
                                    user.put("avatar", avFile);
                                    user.signUpInBackground(new SignUpCallback() {
                                        @Override
                                        public void done(AVException e) {

                                        }
                                    });
                                    name=nickname;
                                    finishLogin();
                                } else {
                                    Log.e("user执行失败了",user+"");
                                    e.printStackTrace();
                                }

                            }
                        });
                    }



                } break;

                default:
                    break;
            }
        }

    };

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            boolean isFirst = MyApplication.sharedPreferences.getBoolean("IsFirst",
                    true);
            if (isFirst) {
                MyAndroidUtil.editXml("IsFirst", false);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);

        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(LoginActivity.this);
    }

    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        if (action == Platform.ACTION_USER_INFOR) {
            Message msg = new Message();
            msg.what = MSG_AUTH_COMPLETE;
            PlatformDb platDB = platform.getDb();//获取数平台数据DB
            msg.obj = new Object[]{platform.getName(), res,platDB};
            handler.sendMessage(msg);


        }

    }



    @Override
    public void onError(Platform platform, int action, Throwable throwable) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_ERROR);
        }
        throwable.printStackTrace();
    }

    @Override
    public void onCancel(Platform platform, int action) {
        if (action == Platform.ACTION_USER_INFOR) {
            handler.sendEmptyMessage(MSG_AUTH_CANCEL);
        }

    }

}
