package com.wzy.mhealth.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

public class YearPrideActivity extends AppCompatActivity {
    private ImageView leftBtn, iv_gift;
    private EditText et_gift;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 298:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    new  AlertDialog.Builder(YearPrideActivity.this)
                            .setTitle("礼包提示" )
                            .setMessage(stepInfo.getData())
                            .setPositiveButton("确定",null )
                            .show();
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_year_pride);
        iv_gift = (ImageView) findViewById(R.id.iv_gift);
        et_gift = (EditText) findViewById(R.id.et_gift);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        iv_gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!("").equals(et_gift.getText().toString())) {
                    String url = Constants.SERVER_URL + "MhealthGoodUserNameServlet";
                    TiUser user = new TiUser();
                    user.setName(et_gift.getText().toString());
                    user.setCardId(LeanchatUser.getCurrentUser().getObjectId() + "");
                    MyHttpUtils.handData(handler, 298, url, user);

                } else {
                    Toast.makeText(YearPrideActivity.this, "输入不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
