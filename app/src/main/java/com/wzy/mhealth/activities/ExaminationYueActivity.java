package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avos.avoscloud.AVAnalytics;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.SignUpCallback;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.wzy.mhealth.MyApplication;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Info;
import com.wzy.mhealth.model.Record;
import com.wzy.mhealth.model.TaocanInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 体检预约信息校验
*/

public class ExaminationYueActivity extends Activity {
    private ImageView leftBtn;
    private Button submit;
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
        init();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case 1:
                    Info obj = (Info) msg.obj;
                    if (!obj.isSuccess() && obj.getMsg().equals("非法用户！")) {
                        Toast.makeText(ExaminationYueActivity.this, "请确保您公司为您预约了项目或者确保和公司统计信息一致", Toast.LENGTH_LONG).show();
                    } else if ((!obj.isSuccess()) && obj.getMsg().equals("用户注册失败！")) {
                        Toast.makeText(ExaminationYueActivity.this, "请检查您的输入数据，确定是否有误!", Toast.LENGTH_LONG).show();
                    } else {
                        SharedPreferences sp = getSharedPreferences("reg", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(name, "cc");
                        editor.commit();
                        LeanchatUser user = LeanchatUser.getCurrentUser();
                        user.put("realName", name);
                        user.put("IDCard", cardid);
                        LeanchatUser.getCurrentUser().setMobilePhoneNumber(tel);
                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(AVException e) {

                            }
                        });
                        String loginurl = Constants.SERVER_ZHIXING+"personAction!app_login.action?idnumber=" + cardid + "&passwd=" + pass;
                        MyHttpUtils.handData(handler, 2, loginurl, null);
                    }
                    break;
                case 2:
                    Info info = (Info) msg.obj;
                    if (info.isSuccess()) {
                        Toast.makeText(ExaminationYueActivity.this, "校验成功!", Toast.LENGTH_LONG).show();
                        tag = info.getMsg();
                        String recurl = Constants.SERVER_ZHIXING+"recordAction!app_matchCheck.action?sessid=" + tag;
                        MyHttpUtils.handData(handler, 23, recurl, null);
                    } else {
                        Toast.makeText(ExaminationYueActivity.this, "校验失败!", Toast.LENGTH_LONG).show();
                    }
                    break;
                case 23:
                    Record rec = (Record) msg.obj;
                    if (rec.getTotal() == -1 || rec.getTotal() == 0) {
                        String renurl =Constants.SERVER_ZHIXING+ "recordAction!app_matchOrder.action?sessid=" + tag;
                        MyHttpUtils.handData(handler, 33, renurl, null);
                    } else {
                        Intent intent = new Intent(ExaminationYueActivity.this, MyYuyueActivity.class);
                        intent.putExtra("session", tag);
                        intent.putExtra("id", rec.getRows().get(0).getEID());
                        intent.putExtra("extra", rec.getRows().get(0).getTJID() + "20160713");
                        startActivity(intent);
                        ExaminationYueActivity.this.finish();
                    }
                    break;
                case 33:
                    TaocanInfo inf = (TaocanInfo) msg.obj;
                    if (inf.getTotal() == -1 || inf.getTotal() == 0) {
                        Toast.makeText(ExaminationYueActivity.this, "没有数据!", Toast.LENGTH_LONG).show();
                    } else {
                        int status = inf.getRows().get(0).getSTATUS();
                        if (0 == status) {
                            Intent intent = new Intent(ExaminationYueActivity.this, TestSelfActivity.class);
                            intent.putExtra("session", tag);
                            intent.putExtra("id", inf.getRows().get(0).getEID());
                            intent.putExtra("name", inf.getRows().get(0).getXM());
                            intent.putExtra("sex", inf.getRows().get(0).getXB());
                            intent.putExtra("taocan", inf.getRows().get(0).getNAME());
                            intent.putExtra("tiid", inf.getRows().get(0).getTJID());
                            startActivity(intent);
                            ExaminationYueActivity.this.finish();
                        } else {
                            Intent intent = new Intent(ExaminationYueActivity.this, MyYuyueActivity.class);
                            intent.putExtra("session", tag);
                            intent.putExtra("id", inf.getRows().get(0).getEID());
                            intent.putExtra("extra", inf.getRows().get(0).getTJID() + "20160713");
                            startActivity(intent);
                            ExaminationYueActivity.this.finish();
                        }

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
        submit = (Button) findViewById(R.id.submit);
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
                    Toast.makeText(ExaminationYueActivity.this, "请输入18位身份证号码", Toast.LENGTH_LONG).show();
                }
                if (pass.length() < 6) {
                    Toast.makeText(ExaminationYueActivity.this, "请输入6位以上密码", Toast.LENGTH_LONG).show();
                }
                if (tel.length() != 11) {
                    Toast.makeText(ExaminationYueActivity.this, "请输入11位手机号码", Toast.LENGTH_LONG).show();
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
                    String loginurl = Constants.SERVER_ZHIXING+"personAction!app_login.action?idnumber=" + cardid + "&passwd=" + pass;
                    MyHttpUtils.handData(handler, 2, loginurl, null);
                } else {
                    String regurl = Constants.SERVER_ZHIXING+"personAction!app_reg.action";
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

    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }
}
