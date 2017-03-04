package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Recommend;
import com.wzy.mhealth.model.UserManageCaseHis;
import com.wzy.mhealth.model.UserManger;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.Util;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 添加用户信息页面
*/
public class UserInfoAddActivity extends AppCompatActivity implements TextWatcher {
    @Bind(R.id.ll_aid)
    LinearLayout llAid;
    String aid = "aid";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 299:
                    UserManageCaseHis stepInfo = (UserManageCaseHis) msg.obj;
                    Toast.makeText(UserInfoAddActivity.this, stepInfo.getData(), Toast.LENGTH_LONG).show();
                    if ("new".equals(flag) && "1".equals(stepInfo.getStatus())&&aid.equals("aidll")) {
                        Intent intent = new Intent(UserInfoAddActivity.this, BookMangerActivity.class);
                        intent.putExtra("name", stepInfo.getUserManageId() + "");
                        startActivity(intent);
                    }
                    break;
            }
        }
    };

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
    private UserManger.DataEntity user;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_add);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        etCard.addTextChangedListener(this);
        etName.addTextChangedListener(this);
        flag = intent.getStringExtra("flag");
        if ("new".equals(flag)) {

        } else {
            user = (UserManger.DataEntity) intent.getSerializableExtra("user");
            titleView.setText("" + user.getName());
            etCard.setText("" + user.getUserID());
            etName.setText(user.getName() + "");
            tvSex.setText(user.getSex() + "");
            tvBirth.setText(user.getBirth() + "");
            tvAge.setText(user.getAge() + "");
        }
    }

    @OnClick({R.id.leftBtn, R.id.tv_save, R.id.ll_aid})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_save:
                aid = "aid";
                aidUse();
                break;
            case R.id.ll_aid:
                aid = "aidll";
                if ("new".equals(flag)) {
                    aidUse();
                } else {
                    Intent intent = new Intent(UserInfoAddActivity.this, BookMangerActivity.class);
                    intent.putExtra("name", user.getUserManageId() + "");
                    startActivity(intent);
                }
                break;

        }
    }

    public void aidUse() {
        if (("").equals(etName.getText().toString()) || ("").equals(etCard.getText().toString())) {
            Toast.makeText(UserInfoAddActivity.this, "输入不能为空", Toast.LENGTH_LONG).show();
        } else {
            if (!Util.getInstance().isCardId(etCard.getText().toString())) {
                Toast.makeText(UserInfoAddActivity.this, "请输入正确的身份证号", Toast.LENGTH_LONG).show();
            } else {
                String url = Constants.SERVER_URL + "UserManagerSaveServlet";
                Recommend tiUser = new Recommend();
                tiUser.setName(etName.getText().toString() + "");
                if ("new".equals(flag)) {
                    tiUser.setStatus("");
                } else {
                    tiUser.setStatus(user.getUserManageId() + "");
                }
                tiUser.setNewPrice(etCard.getText().toString() + "");
                tiUser.setImage(tvSex.getText().toString() + "");
                tiUser.setData(tvAge.getText().toString() + "");
                tiUser.setContext(tvBirth.getText().toString() + "");
                MyHttpUtils.handData(handler, 299, url, tiUser);
            }
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
        } else if (!Util.getInstance().isCardId(card)) {
            Toast.makeText(UserInfoAddActivity.this, "请输入正确的身份证号", Toast.LENGTH_LONG).show();
        } else {
            String birth = card.substring(6, 14);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(birth.substring(0, 4)).append("-").append(birth.substring(4, 6)).append("-").append(birth.substring(6));
            int age = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(etCard.getText().toString().substring(6, 10));
            if (Calendar.getInstance().get(Calendar.MONTH) <= Integer.parseInt(birth.substring(4, 6))) {
                if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) <= Integer.parseInt(birth.substring(6)))
                    age--;
            } else {
                age--;
            }
            tvBirth.setText(stringBuffer.toString());
            tvAge.setText(age + "");
            int i = Integer.parseInt(String.valueOf(card.charAt(16)));
            if (i % 2 == 0) {
                tvSex.setText("女");
            } else {
                tvSex.setText("男");
            }

        }
    }

    @OnClick(R.id.ll_aid)
    public void onClick() {
    }
}
