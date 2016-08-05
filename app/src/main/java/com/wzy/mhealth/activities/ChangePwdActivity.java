package com.wzy.mhealth.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.UpdatePasswordCallback;
import com.wzy.mhealth.R;
import com.wzy.mhealth.utils.Tool;

public class ChangePwdActivity extends Activity {
    EditText oldPwdView, pwdView, pwdView1;
    Button subBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        oldPwdView = (EditText) findViewById(R.id.oldPwdView);
        pwdView = (EditText) findViewById(R.id.pwdView);
        pwdView1 = (EditText) findViewById(R.id.pwdView1);
        subBtn = (Button) findViewById(R.id.subBtn);
        subBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (oldPwdView.getText().toString().equals("")) {
                    Tool.initToast(getApplicationContext(), "请输入旧密码");
                } else if (pwdView.getText().toString().equals("")) {
                    Tool.initToast(getApplicationContext(), "请输入新密码");
                } else if (pwdView1.getText().toString().equals("")) {
                    Tool.initToast(getApplicationContext(), "请确认新密码");
                } else if (!pwdView.getText().toString()
                        .equals(pwdView1.getText().toString())) {
                    Tool.initToast(getApplicationContext(), "两次密码不一致");
                } else {

                    AVUser user = AVUser.getCurrentUser();
                    user.updatePasswordInBackground(oldPwdView.getText()
                                    .toString(), pwdView.getText().toString(),
                            new UpdatePasswordCallback() {

                                @Override
                                public void done(AVException arg0) {
                                    if (arg0 == null) {
                                        Tool.initToast(getApplicationContext(),
                                                "修改密码成功");
                                        finish();
                                    } else
                                        Tool.initToast(getApplicationContext(),
                                                "修改密码失败");
                                }
                            });
                }
            }
        });

        back();
    }

    // 返回箭头
    private void back() {
        // TODO Auto-generated method stub
        ImageView imageback = (ImageView) findViewById(R.id.leftBtn);

        imageback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }
}
