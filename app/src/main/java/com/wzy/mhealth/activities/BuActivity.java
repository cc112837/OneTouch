package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.model.LeanchatUser;
import com.avoscloud.leanchatlib.utils.Constants;
import com.wzy.mhealth.LeanChat.activity.ChatRoomActivity;
import com.wzy.mhealth.LeanChat.service.CacheService;
import com.wzy.mhealth.R;
import com.wzy.mhealth.utils.Tool;
import com.wzy.mhealth.view.PayRadioGroup;
import com.wzy.mhealth.view.PayRadioPurified;

import java.util.List;


public class BuActivity extends BaActivity {
    // private DoctorEntity doctor;
    private TextView doctorname, price, price1, titleName;
    private Button buy;
    private String stringOfPrice,type,doctorid,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bu);
        doctorid=getIntent().getStringExtra("doctor");
        name=getIntent().getStringExtra("name");
        stringOfPrice = getIntent().getStringExtra("price");
        type = getIntent().getStringExtra("type");// 1表示是图文咨询
        init();
        PayRadioGroup group = (PayRadioGroup) findViewById(R.id.genderGroup);
        titleName = (TextView) findViewById(R.id.titleName);
        if (type.equals("1"))
            titleName.setText("购买：图文咨询");
        price = (TextView) findViewById(R.id.price);
        price1 = (TextView) findViewById(R.id.price1);
        price.setText(stringOfPrice + "元/次");
        price1.setText("会员价" + (Integer.parseInt(stringOfPrice) - 1) + "元/次");
        buy = (Button) findViewById(R.id.btn_pay);
        group.setOnCheckedChangeListener(new PayRadioGroup.OnCheckedChangeListener
                () {

            @Override
            public void onCheckedChanged(PayRadioGroup group, int checkedId) {
                int radioButtonId = group.getCheckedRadioButtonId();

                PayRadioPurified rl = (PayRadioPurified) BuActivity.this
                        .findViewById(radioButtonId);
                for (int i = 0; i < group.getChildCount(); i++) {
                    ((PayRadioPurified) group.getChildAt(i)).setChangeImg(checkedId);
                }
                Toast.makeText(BuActivity.this, rl.getTextTitle(), Toast.LENGTH_SHORT)
                        .show();
            }
        });
        buy.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (doctorid == null
                        || doctorid.equals(""))
                    Tool.initToast(BuActivity.this, "支付失败");
                else {
                    final ChatManager chatManager = ChatManager.getInstance();
                    chatManager.fetchConversationWithUserId(doctorid,
                            new AVIMConversationCreatedCallback() {
                                @Override
                                public void done(AVIMConversation conversation, AVIMException e) {
                                    if (e != null) {
                                        Toast.makeText(BuActivity.this, e.getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Intent intent = new Intent(BuActivity.this,
                                                ChatRoomActivity.class);
                                        intent.putExtra(Constants.CONVERSATION_ID,
                                                conversation.getConversationId());
                                        Tool.initToast(BuActivity.this,
                                                "支付成功，请24小时内与" + name + "医生咨询");
                                        startActivity(intent);
                                    }
                                }
                            });
                }
            }

        });

    }

    private void init() {
        doctorname = (TextView) findViewById(R.id.doctorname);
        doctorname.setText(name);

    }

    public void searchUser() throws AVException {
        AVQuery<LeanchatUser> q = AVUser.getQuery(LeanchatUser.class);
        q.limit(Constants.PAGE_SIZE);
        q.skip(0);
        q.whereEqualTo("property", "doctor");
        q.whereEqualTo(LeanchatUser.USERNAME, "bird");
        q.orderByDescending(Constants.UPDATED_AT);
        q.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
        List<LeanchatUser> users = q.find();
        if (users != null) {
            for (LeanchatUser lcu : users)
                if (lcu.getUsername().equals(name)) {
                    final ChatManager chatManager = ChatManager.getInstance();
                    chatManager.fetchConversationWithUserId(lcu.getObjectId(),
                            new AVIMConversationCreatedCallback() {
                                @Override
                                public void done(AVIMConversation conversation, AVIMException e) {
                                    if (e != null) {
                                        Toast.makeText(BuActivity.this, e.getMessage(),
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Intent intent = new Intent(BuActivity.this,
                                                ChatRoomActivity.class);
                                        intent.putExtra(Constants.CONVERSATION_ID,
                                                conversation.getConversationId());
                                        Tool.initToast(getApplicationContext(), "支付成功，请24小时内与"
                                                + name + "医生咨询");
                                        startActivity(intent);
                                    }
                                }
                            });
                }
        }
        CacheService.registerUsers(users);
    }

    public void leftBtnClick(View v) {
        finish();
    }

}
