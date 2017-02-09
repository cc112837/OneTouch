package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserInfoAddActivity extends AppCompatActivity implements TextWatcher {

    @Bind(R.id.titleView)
    TextView titleView;
    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(R.id.titlebar)
    FrameLayout titlebar;
    @Bind(R.id.et_name)
    EditText etName;
    @Bind(R.id.tv_sex)
    TextView tvSex;
    @Bind(R.id.tv_age)
    TextView tvAge;
    @Bind(R.id.tv_birth)
    TextView tvBirth;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Bind(R.id.et_card)
    EditText etCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_add);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        etCard.addTextChangedListener(this);
        etName.addTextChangedListener(this);
        String flag = intent.getStringExtra("flag");
        if ("new".equals(flag)) {

        } else {
            titleView.setText("");
        }
    }

    @OnClick({R.id.leftBtn, R.id.tv_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_save:
                if (("").equals(etName.getText().toString()) || ("").equals(etCard.getText().toString())) {
                    Toast.makeText(UserInfoAddActivity.this, "输入不能为空", Toast.LENGTH_LONG).show();
                } else {
                    if (etCard.getText().toString().length() != 18) {
                        Toast.makeText(UserInfoAddActivity.this, "请输入正确的身份证号", Toast.LENGTH_LONG).show();
                    } else {

                    }
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String name = etName.getText().toString();
        String card = etCard.getText().toString();
        if (("").equals(name) || ("").equals(card)) {
            Toast.makeText(UserInfoAddActivity.this, "输入不能为空", Toast.LENGTH_LONG).show();
        } else if (card.length() != 18) {
            Toast.makeText(UserInfoAddActivity.this, "请输入正确的身份证号", Toast.LENGTH_LONG).show();
        } else {
            String birth = card.substring(6, 14);
            tvBirth.setText(birth);
            int i = Integer.parseInt(String.valueOf(card.charAt(16)));
            if(1%2==0){
                tvSex.setText("女");
            }else{
                tvSex.setText("男");
            }
        }
    }
}
