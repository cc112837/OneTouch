package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Address;
import com.wzy.mhealth.model.Recommend;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.ToastUtil;

public class UpdaAddressActivity extends Activity implements View.OnClickListener {
    private TextView title;
    private ImageView leftBtn;
    private String flag;
    private CheckBox sw_default;
    private EditText et_adddetail, et_city, et_tel, tv_who;
    private Button btn_confirm;
    private Address.DataEntity addre;
    int flagdefault = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 279:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (("1").equals(stepInfo.getStatus())) {
                        ToastUtil.show(UpdaAddressActivity.this, "保存成功");
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upda_address);
        Intent intent = getIntent();
        flag = intent.getStringExtra("flag");
        addre = (Address.DataEntity) intent.getSerializableExtra("id");
        init();
    }

    private void init() {
        title = (TextView) findViewById(R.id.title);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        et_adddetail = (EditText) findViewById(R.id.et_adddetail);
        et_city = (EditText) findViewById(R.id.et_city);
        et_tel = (EditText) findViewById(R.id.et_tel);
        tv_who = (EditText) findViewById(R.id.tv_who);
        sw_default = (CheckBox) findViewById(R.id.sw_default);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        leftBtn.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        sw_default.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {//开
                    flagdefault = 1;
                } else {//关
                    flagdefault = 0;
                }
            }
        });
        if ("new".equals(flag)) {
            title.setText("新增收货地址");
        } else {
            title.setText("修改收货地址");
            tv_who.setText(addre.getName() + "");
            et_city.setText(addre.getAddress() + "");
            et_tel.setText(addre.getTelephone() + "");
            et_adddetail.setText(addre.getAddress() + "");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.btn_confirm:
                String adddetail = et_adddetail.getText().toString();
                String city = et_city.getText().toString();
                String tel = et_tel.getText().toString();
                String who = tv_who.getText().toString();
                String url = Constants.SERVER_URL + "MhealthShopAddressSaveServlet";
                Recommend recommend = new Recommend();
                recommend.setName(who);
                recommend.setData(city);
                recommend.setContext(adddetail);
                recommend.setTaoId(flagdefault);
                if (flag.equals("update")) {
                    Log.e("打印id",addre.getAddressId() + "");
                    recommend.setStatus(addre.getAddressId() + "");
                }
                else{
                    recommend.setStatus("");
                }
                recommend.setTaocanNum(tel);
                MyHttpUtils.handData(handler, 279, url, recommend);
                break;
        }
    }
}
