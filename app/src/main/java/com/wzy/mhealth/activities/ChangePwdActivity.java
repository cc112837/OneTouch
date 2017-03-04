package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.UpdatePasswordCallback;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.Tool;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 修改密码页面
*/
public class ChangePwdActivity extends BaActivity {
    EditText oldPwdView, pwdView, pwdView1;
    Button subBtn;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 181:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (stepInfo.getStatus().equals("1")) {
                        Tool.initToast(getApplicationContext(),
                                "修改密码成功");
                        finish();
                    } else {
                        Tool.initToast(getApplicationContext(),
                                "修改密码失败");
                    }
                    break;
            }
        }
    };
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

                    final AVUser user = AVUser.getCurrentUser();
                    user.updatePasswordInBackground(oldPwdView.getText()
                                    .toString(), pwdView.getText().toString(),
                            new UpdatePasswordCallback() {

                                @Override
                                public void done(AVException arg0) {
                                    if (arg0 == null) {
                                        String uti = Constants.SERVER_URL + "MhealthUserPasswordServlet";
                                        TiUser user1 = new TiUser();
                                        user1.setName(user.getUsername()+"");
                                        user1.setPass(pwdView.getText().toString());
                                        MyHttpUtils.handData(handler, 181, uti, user1);
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
}
