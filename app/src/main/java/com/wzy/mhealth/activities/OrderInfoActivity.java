package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wzy.mhealth.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInfoActivity extends AppCompatActivity {

    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(R.id.photo)
    ImageView photo;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.zhicheng)
    TextView zhicheng;
    @Bind(R.id.department)
    TextView department;
    @Bind(R.id.hospital)
    TextView hospital;
    @Bind(R.id.tv_data)
    TextView tvData;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.ll_name)
    LinearLayout llName;
    @Bind(R.id.et_address)
    EditText etAddress;
    @Bind(R.id.et_aidness)
    EditText etAidness;
    @Bind(R.id.et_descri)
    EditText etDescri;
    @Bind(R.id.rb_agree)
    RadioButton rbAgree;
    @Bind(R.id.rb_no)
    RadioButton rbNo;
    @Bind(R.id.rg_agree)
    RadioGroup rgAgree;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.leftBtn, R.id.ll_name, R.id.ll_aid, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                break;
            case R.id.ll_name:
                break;
            case R.id.tv_submit:
                break;
        }
    }
}
