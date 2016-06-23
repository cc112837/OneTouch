package com.wzy.mhealth.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wzy.mhealth.R;

import java.util.Calendar;
import java.util.Locale;

public class ManageActivity extends Activity implements View.OnClickListener{
    private ImageView leftBtn_back,dui;
    private EditText tv_birth,et_hunyu,et_guominid,et_aid,et_home,et_habit;
    private RadioGroup rg_sex;
    private RadioButton rb_male;
    private RadioButton rb_female;
    String sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        initView();
    }

    private void initView() {
        tv_birth = ((EditText) findViewById(R.id.tv_birthid));
        et_hunyu = ((EditText) findViewById(R.id.et_hunyu));
        et_guominid = ((EditText) findViewById(R.id.et_guominid));
        et_aid = ((EditText) findViewById(R.id.et_aid));
        et_home = ((EditText) findViewById(R.id.et_home));
        et_habit = ((EditText) findViewById(R.id.et_habit));
        leftBtn_back= ((ImageView) findViewById(R.id.leftBtn_back));
        dui= ((ImageView) findViewById(R.id.dui));
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        rb_male=(RadioButton) findViewById(R.id.rb_male);
        rb_female=(RadioButton) findViewById(R.id.rb_female);
        leftBtn_back.setOnClickListener(this);
        dui.setOnClickListener(this);
        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.rb_male) {
                    sex= rb_male.getText().toString();
                }
                else  if(checkedId==R.id.rb_female)
                    sex= rb_female.getText().toString();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.leftBtn_back:
                finish();
                break;
            case R.id.dui:
                String birth=tv_birth.getText().toString();
                String hunyu=et_hunyu.getText().toString();
                String guominid=et_guominid.getText().toString();
                String aid=et_aid.getText().toString();
                String home=et_home.getText().toString();
                String habit=et_habit.getText().toString();
                if(sex.equals(null)) sex="男";
                Toast.makeText(ManageActivity.this,"看看效果",Toast.LENGTH_LONG).show();

                break;


            case R.id.tv_birthid:
                final Calendar calendar = Calendar.getInstance(Locale.CHINA);
                int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        calendar.set(year,monthOfYear,dayOfMonth);
                        tv_birth.setText(year + "-" + (int) ((int) monthOfYear + 1) + "-" + dayOfMonth);
                    }
                },year,month,day);
                dialog.show();

                break;
        }

    }
}
