package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.MyApplication;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.Info;
import com.wzy.mhealth.model.TaocanInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;


public class TijianYueActivity extends Activity {
    private ImageView leftBtn;
    private TextView submit;
    private EditText nameid, idcard, et_pass, et_tel;//姓名，身份证号
    String cardid, pass, name, tel, tag;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tijian_yue);
        String phone = MyApplication.sharedPreferences.getString("phone",
                null);
        String pass = MyApplication.sharedPreferences.getString("pass",
                null);
        et_pass = (EditText) findViewById(R.id.et_pass);
        et_tel = (EditText) findViewById(R.id.et_tel);
        et_tel.setText(phone);
        et_pass.setText(pass);
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", 1);
        init();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case 1:
                    Info obj = (Info) msg.obj;
                    if (!obj.isSuccess() && obj.getMsg().equals("非法用户！")) {
                        Toast.makeText(TijianYueActivity.this, "您公司没有预约项目", Toast.LENGTH_LONG).show();
                    } else if ((!obj.isSuccess()) && obj.getMsg().equals("用户注册失败！")) {
                        Toast.makeText(TijianYueActivity.this, "请检查您的输入数据，确定是否有误!", Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences sp = getSharedPreferences("reg", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(name, "cc");
                        editor.commit();
                        String loginurl = "http://113.201.59.226:8081/Healwis/base/personAction!app_login.action?idnumber=" + cardid + "&passwd=" + pass;
                        MyHttpUtils.handData(handler, 2, loginurl, null);
                    }
                    break;
                case 2:
                    Info info = (Info) msg.obj;
                    if (info.isSuccess()) {
                        Toast.makeText(TijianYueActivity.this, "校验成功!", Toast.LENGTH_LONG).show();
                        String renurl = "http://113.201.59.226:8081/Healwis/base/recordAction!app_matchOrder.action?sessid=" + info.getMsg();
                        tag = info.getMsg();
                        MyHttpUtils.handData(handler, 33, renurl, null);
                    } else {
                        Toast.makeText(TijianYueActivity.this, "校验失败!", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 33:
                    TaocanInfo inf = (TaocanInfo) msg.obj;
                    if (flag == 1) {
                        Intent intent = new Intent(TijianYueActivity.this, TestSelfActivity.class);
                        intent.putExtra("session", tag);
                        intent.putExtra("id", inf.getRows().get(0).getEID());
                        intent.putExtra("name", inf.getRows().get(0).getXM());
                        intent.putExtra("sex", inf.getRows().get(0).getXB());
                        intent.putExtra("taocan", inf.getRows().get(0).getNAME());
                        intent.putExtra("tiid", inf.getRows().get(0).getTJID());
                        startActivity(intent);
                        TijianYueActivity.this.finish();
                    }
                    if (flag == 2) {
                        Intent intent = new Intent(TijianYueActivity.this, MyYuyueActivity.class);
                        intent.putExtra("session", tag);
                        intent.putExtra("id", inf.getRows().get(0).getEID());
                        intent.putExtra("extra",inf.getRows().get(0).getTJID()+"20160713");
                        startActivity(intent);
                        TijianYueActivity.this.finish();
                    }
                    break;
            }
        }
    };

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        SharedPreferences sp = getSharedPreferences("et", MODE_PRIVATE);
        String name1 = sp.getString("name", null);
        String card1 = sp.getString("card", null);
        String tel1 = sp.getString("tel", null);
        String pass1 = sp.getString("pass", null);
        submit = (TextView) findViewById(R.id.submit);
        nameid = (EditText) findViewById(R.id.nameid);
        idcard = (EditText) findViewById(R.id.idcard);

        nameid.setText(name1);
        idcard.setText(card1);
        et_pass.setText(pass1);
        et_tel.setText(tel1);


        //相当于旧版本

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2016/6/24
                name = nameid.getText().toString();
                cardid = idcard.getText().toString();
                pass = et_pass.getText().toString();
                tel = et_tel.getText().toString();
                if (cardid.length() != 18) {
                    Toast.makeText(TijianYueActivity.this, "请输入18位身份证号码", Toast.LENGTH_LONG).show();
                }
                if (pass.length() < 6) {
                    Toast.makeText(TijianYueActivity.this, "请输入6位以上密码", Toast.LENGTH_LONG).show();
                }
                if (tel.length() != 11) {
                    Toast.makeText(TijianYueActivity.this, "请输入11位手机号码", Toast.LENGTH_LONG).show();
                }
                SharedPreferences et = getSharedPreferences("et", MODE_PRIVATE);
                SharedPreferences.Editor edit = et.edit();
                edit.putString("name", name);
                edit.putString("card", cardid);
                edit.putString("pass", pass);
                edit.putString("tel", tel);
                edit.commit();
                SharedPreferences sp = getSharedPreferences("reg", MODE_PRIVATE);
                final String reg = sp.getString(name, null);
                if ("cc".equals(reg)) {
                    String loginurl = "http://113.201.59.226:8081/Healwis/base/personAction!app_login.action?idnumber=" + cardid + "&passwd=" + pass;
                    MyHttpUtils.handData(handler, 2, loginurl, null);
                } else {
                    String regurl = "http://113.201.59.226:8081/Healwis/base/personAction!app_reg.action";
                    TiUser user = new TiUser();
                    user.setName(name);
                    user.setCardId(cardid);
                    user.setPass(pass);
                    user.setTel(tel);
                    MyHttpUtils.handData(handler, 1, regurl, user);
                }


            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
