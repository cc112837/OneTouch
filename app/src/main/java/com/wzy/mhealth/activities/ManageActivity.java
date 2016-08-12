package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wzy.mhealth.R;

public class ManageActivity extends BaActivity implements View.OnClickListener{
    private ImageView leftBtn_back;
    private Button submit;
    private EditText nameView,rg_sex,tv_birth,proView,cheView,et_hunyu,et_habit,medicalHistory;

    String sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        initView();
    }

    private void initView() {
        submit=(Button) findViewById(R.id.submit);
        et_habit = ((EditText) findViewById(R.id.et_habit));
        medicalHistory = ((EditText) findViewById(R.id.medicalHistory));
        et_hunyu = ((EditText) findViewById(R.id.et_hunyu));
        rg_sex = ((EditText) findViewById(R.id.rg_sex));
        nameView = ((EditText) findViewById(R.id.nameView));
        tv_birth = ((EditText) findViewById(R.id.tv_birthid));
        proView = ((EditText) findViewById(R.id.proView));
        cheView = ((EditText) findViewById(R.id.cheView));
        leftBtn_back= ((ImageView) findViewById(R.id.leftBtn_back));
        leftBtn_back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.leftBtn_back:
                finish();
                break;
            case R.id.submit:
                String guominid=cheView.getText().toString();
                String aid=proView.getText().toString();
                String home=nameView.getText().toString();
                String habit=rg_sex.getText().toString();
                String medical=medicalHistory.getText().toString();
                String habi=et_habit.getText().toString();
                String hun=et_hunyu.getText().toString();
                String birth=tv_birth.getText().toString();
                Toast.makeText(ManageActivity.this,"看看效果",Toast.LENGTH_LONG).show();
                break;



        }

    }
}
