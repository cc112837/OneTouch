package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.OrderDoctorHeader;
import com.wzy.mhealth.model.Recommend;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInfoActivity extends AppCompatActivity {

    String identity="0";
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
    TextView etName;
    @Bind(R.id.ll_name)
    LinearLayout llName;
    @Bind(R.id.et_address)
    TextView etAddress;
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
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 302:
                    OrderDoctorHeader orderDoctorHeader = (OrderDoctorHeader) msg.obj;
                    ImageLoader.getInstance().displayImage(orderDoctorHeader.getDoctorImage(), photo, PhotoUtils.avatarImageOptions);
                    etAddress.setText(orderDoctorHeader.getAdrress());
                    zhicheng.setText(orderDoctorHeader.getDoctorTitle());
                    department.setText(orderDoctorHeader.getFirstdep());
                    hospital.setText(orderDoctorHeader.getHospital());
                    name.setText(orderDoctorHeader.getDoctorName());
                    tvData.setText(orderDoctorHeader.getAppointTime());
                    break;
                case 303:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    Toast.makeText(OrderInfoActivity.this, stepInfo.getData(), Toast.LENGTH_LONG).show();
                    if("1".equals(stepInfo.getStatus())){
                      //前往支付页面
                    }
                    break;
            }
        }
    };
    private String id;
    private String id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String time = intent.getStringExtra("time");
        String flag = intent.getStringExtra("flag");
        id = intent.getStringExtra("id");
        TiUser tiUser = new TiUser();
        tiUser.setName(time);
        tiUser.setCardId(id);
        tiUser.setPass(flag);
        String url = Constants.SERVER_URL + "PatientCounselingServlet";
        MyHttpUtils.handData(handler, 302, url, tiUser);
        rgAgree.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == rbAgree.getId()) {
                    identity = "0";
                } else {
                    identity = "1";
                }
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b = data.getExtras(); //data为B中回传的Intent
                String str = b.getString("name");//str即为回传的值
                //str即为回传的值
                id1 = b.getString("id");
                etName.setText(str);
                break;
            default:
                break;
        }
    }


    @OnClick({R.id.leftBtn, R.id.ll_name, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.ll_name:
                Intent intent = new Intent(OrderInfoActivity.this, UserManagerActivity.class);
                intent.putExtra("flag", "see");
                startActivityForResult(intent, 19);
                break;
            case R.id.tv_submit:
                String name = etName.getText().toString();
                String addre = etAidness.getText().toString();
                String desci = etDescri.getText().toString();
                if ("".equals(name) || "".equals(addre) || "".equals(desci)) {
                    Toast.makeText(OrderInfoActivity.this, "输入不能为空", Toast.LENGTH_LONG).show();
                } else {
                    String url = Constants.SERVER_URL + "PatientCounselingSaveServlet";
                    Recommend recommend = new Recommend();
                    recommend.setNewPrice(tvData.getText().toString());
                    recommend.setName(id1);
                    recommend.setData(addre);
                    recommend.setContext(desci);
                    recommend.setImage(LeanchatUser.getCurrentUser().getUsername());
                    recommend.setOldPrice(id);
                    recommend.setStatus(identity);
                    MyHttpUtils.handData(handler, 303, url, recommend);
                }
                break;
        }
    }
}
