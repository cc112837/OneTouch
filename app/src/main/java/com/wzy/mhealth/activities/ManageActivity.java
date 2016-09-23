package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.SelfHealth;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.utils.MyHttpUtils;

public class ManageActivity extends BaActivity implements View.OnClickListener {
    private ImageView leftBtn_back;
    private Button submit;
    private EditText nameView, rg_sex, tv_birth, proView, cheView, et_hunyu, medicalHistory;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 150:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if ("1".equals(stepInfo.getStatus())) {
                        Toast.makeText(ManageActivity.this, stepInfo.getData(), Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(ManageActivity.this, stepInfo.getData(), Toast.LENGTH_LONG).show();
                    break;
                case 151:
                    SelfHealth selfHealth = (SelfHealth) msg.obj;
                    nameView.setText("" + selfHealth.getName());
                    tv_birth.setText("" + selfHealth.getAge());
                    rg_sex.setText("" + selfHealth.getSex());
                    et_hunyu.setText("" + selfHealth.getMarrage());
                    proView.setText("" + selfHealth.getProfession());
                    cheView.setText("" + selfHealth.getRelator());
                    medicalHistory.setText("" + selfHealth.getRelate());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        initView();
        String uri = Constants.SERVER_URL + "MedicalHealthQueryServlet";
        MyHttpUtils.handData(handler, 151, uri, null);

    }

    private void initView() {
        submit = (Button) findViewById(R.id.submit);
        medicalHistory = ((EditText) findViewById(R.id.medicalHistory));
        et_hunyu = ((EditText) findViewById(R.id.et_hunyu));
        rg_sex = ((EditText) findViewById(R.id.rg_sex));
        nameView = ((EditText) findViewById(R.id.nameView));
        tv_birth = ((EditText) findViewById(R.id.tv_birthid));
        proView = ((EditText) findViewById(R.id.proView));
        cheView = ((EditText) findViewById(R.id.cheView));
        leftBtn_back = ((ImageView) findViewById(R.id.leftBtn_back));
        leftBtn_back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn_back:
                finish();
                break;
            case R.id.submit:
                String guominid = cheView.getText().toString();
                String aid = proView.getText().toString();
                String name = nameView.getText().toString();
                String sex = rg_sex.getText().toString();
                String medical = medicalHistory.getText().toString();
                String hun = et_hunyu.getText().toString();
                String birth = tv_birth.getText().toString();
                if (name.equals("")) {
                    Toast.makeText(ManageActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
                } else {
                    String url = Constants.SERVER_URL + "MedicalHealthServlet";
                    SelfHealth selfHealth = new SelfHealth();
                    selfHealth.setName(name);
                    selfHealth.setAge(birth);
                    selfHealth.setMarrage(hun);
                    selfHealth.setProfession(aid);
                    selfHealth.setSex(sex);
                    selfHealth.setRelate(medical);
                    selfHealth.setRelator(guominid);
                    MyHttpUtils.handData(handler, 150, url, selfHealth);
                }
                break;


        }

    }
}
