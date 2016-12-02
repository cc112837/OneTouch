package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.avoscloud.leanchatlib.utils.PhotoUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.ToastUtil;

public class ShopCommentActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn,iv_show;
    private TextView tv_how;
    private EditText tv_advise;
    private String image;
    private String id;
    private CheckBox iv_1, iv_2, iv_3, iv_4, iv_5;
    int grade=5;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 290:
                    StepInfo stepInfo=(StepInfo) msg.obj;
                    if("1".equals(stepInfo.getStatus())){
                        ToastUtil.show(ShopCommentActivity.this,"评价成功");
                        finish();
                    }
                    break;
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_comment);
        Intent intent = getIntent();
        image = intent.getStringExtra("image");
        id = intent.getStringExtra("id");
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(this);
        tv_how=(TextView) findViewById(R.id.tv_how);
        iv_show=(ImageView) findViewById(R.id.iv_show);
        tv_advise=(EditText) findViewById(R.id.tv_advise);
        ImageLoader.getInstance().displayImage(image,iv_show, PhotoUtils.avatarImageOptions);
        iv_1 = (CheckBox) findViewById(R.id.iv_1);
        iv_2 = (CheckBox) findViewById(R.id.iv_2);
        iv_3 = (CheckBox) findViewById(R.id.iv_3);
        iv_4 = (CheckBox) findViewById(R.id.iv_4);
        iv_5 = (CheckBox) findViewById(R.id.iv_5);
        tv_how.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_how:
                String advice = tv_advise.getText().toString();
                String url= Constants.SERVER_URL+"MhealthProductEvaluateServlet";
                TiUser user=new TiUser();
                user.setCardId(id + "");
                user.setPass(advice);
                user.setTel(grade+"");
                MyHttpUtils.handData(handler, 290, url, user);
                break;
            case R.id.iv_1:
                grade=1;
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.greystar2);
                iv_3.setBackgroundResource(R.mipmap.greystar2);
                iv_4.setBackgroundResource(R.mipmap.greystar2);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
                break;
            case R.id.iv_2:
                grade = 2;
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.greystar2);
                iv_4.setBackgroundResource(R.mipmap.greystar2);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
                break;
            case R.id.iv_3:
                grade = 3;
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.goldstar1);
                iv_4.setBackgroundResource(R.mipmap.greystar2);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
                break;
            case R.id.iv_4:
                grade = 4;
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.goldstar1);
                iv_4.setBackgroundResource(R.mipmap.goldstar1);
                iv_5.setBackgroundResource(R.mipmap.greystar2);
                break;
            case R.id.iv_5:
                grade = 5;
                iv_1.setBackgroundResource(R.mipmap.goldstar1);
                iv_2.setBackgroundResource(R.mipmap.goldstar1);
                iv_3.setBackgroundResource(R.mipmap.goldstar1);
                iv_4.setBackgroundResource(R.mipmap.goldstar1);
                iv_5.setBackgroundResource(R.mipmap.goldstar1);
                break;
        }
    }
}
