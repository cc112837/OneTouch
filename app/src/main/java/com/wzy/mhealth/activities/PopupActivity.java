package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.Util;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 个人联系方式
*/
public class PopupActivity extends Activity {
    private Spinner sp_time;
    private Spinner sp_data;
    private EditText et_phone;
    private TextView tv_cancle;
    private String flag;
    private String name, id;
    private TextView tv_confirm;
    private String time;
    private String data, price;
    private String content;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 267:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (stepInfo.getStatus().equals("1")) {
                        if ("private".equals(flag)) {
                            Toast.makeText(PopupActivity.this, "提交成功，请支付订金", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(PopupActivity.this, TaocanBuyActivity.class);
                            intent.putExtra("name", name);
                            intent.putExtra("price", price);
                            intent.putExtra("id", id);
                            startActivity(intent);
                        }else{
                            Toast.makeText(PopupActivity.this, "提交成功，请注意接听电话", Toast.LENGTH_LONG).show();
                        }
                    }
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        Intent intent = getIntent();
        content = intent.getStringExtra("content");
        flag = intent.getStringExtra("flag");
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        id = intent.getStringExtra("id");
        Window window = getWindow();
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        wl.alpha = 1.0f;   // 　　这句就是设置窗口里控件的透明度的．０.０全透明．１.０不透明．
        window.setAttributes(wl);
        init();
    }

    private void init() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        sp_data = (Spinner) findViewById(R.id.sp_data);
        sp_time = (Spinner) findViewById(R.id.sp_time);
        tv_cancle = (TextView) findViewById(R.id.tv_cancle);
        tv_confirm = (TextView) findViewById(R.id.tv_confirm);
        sp_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_data.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                data = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et_phone.getText().toString();
                if (Util.getInstance().isMobileNumber(phone)) {
                    String url = Constants.SERVER_URL + "MedicalCommonConsultServlet";
                    TiUser user = new TiUser();
                    user.setName(content);
                    user.setTel(phone);
                    user.setPass(data);
                    user.setCardId(time);
                    MyHttpUtils.handData(handler, 267, url, user);
                }else{
                    Toast.makeText(PopupActivity.this, "请输入正确的手机号", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}
