package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.wzy.mhealth.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AidsManagerActivity extends AppCompatActivity {
    private TimePickerView pvOptions;
    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.iv_jiantou)
    ImageView ivJiantou;
    @Bind(R.id.et_per)
    EditText etPer;
    @Bind(R.id.tv_examrecord)
    RadioButton tvExamrecord;
    @Bind(R.id.tv_dooraid)
    RadioButton tvDooraid;
    @Bind(R.id.tv_hisaid)
    RadioButton tvHisaid;
    @Bind(R.id.tv_change)
    RadioButton tvChange;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    String type="体检报告";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aids_manager);
        ButterKnife.bind(this);
        pvOptions = new TimePickerView(AidsManagerActivity.this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvOptions.setTime(new Date());
        pvOptions.setCyclic(false);
        pvOptions.setCancelable(true);
        //时间选择后回调
        pvOptions.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvDate.setText(getTime(date));
            }
        });
        String flag = getIntent().getStringExtra("flag");
        if ("new".equals("flag")) {

        } else {
        }
    }
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (pvOptions.isShowing()) {
                pvOptions.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
    @OnClick({R.id.leftBtn, R.id.tv_date, R.id.iv_jiantou, R.id.tv_examrecord, R.id.tv_dooraid, R.id.tv_hisaid, R.id.tv_change, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_date:
            case R.id.iv_jiantou:
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).
                        hideSoftInputFromWindow(AidsManagerActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                pvOptions.show();
                break;
            case R.id.tv_examrecord:
                type="体检报告";
                tvHisaid.setChecked(false);
                tvChange.setChecked(false);
                tvDooraid.setChecked(false);
                tvExamrecord.setChecked(true);
                break;
            case R.id.tv_dooraid:
                type="门诊病历";
                tvDooraid.setChecked(true);
                tvChange.setChecked(false);
                tvHisaid.setChecked(false);
                tvExamrecord.setChecked(false);
                break;
            case R.id.tv_hisaid:
                type="住院病历";
                tvHisaid.setChecked(true);
                tvChange.setChecked(false);
                tvDooraid.setChecked(false);
                tvExamrecord.setChecked(false);
                break;
            case R.id.tv_change:
                type="诊断处方";
                tvChange.setChecked(true);
                tvHisaid.setChecked(false);
                tvDooraid.setChecked(false);
                tvExamrecord.setChecked(false);
                break;
            case R.id.tv_submit:
                break;
        }
    }


}
