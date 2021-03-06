package com.wzy.mhealth.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avoscloud.leanchatlib.controller.ChatManager;
import com.avoscloud.leanchatlib.model.ConversationType;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.LeanChat.activity.ChatRoomActivity;
import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.DoctorDetail;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.UserEvaluation;
import com.wzy.mhealth.utils.ImageUtil;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.utils.Tool;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 医生详情页面
 */
public class DoctorDetailActivity extends BaActivity {

    private TextView name, departmenTextView, zhichengTextView,
            hospitalTextView, xianqingTextView, tuwenfeiyong, dianhuafeiyong,
            jiahaofeiyong, privatefeiyong, vedioefeiyong;
    private TextView recommend, attitude, level;
    private TextView yonghu1, yonghu1Degree, yonghu1Pingjia, youbianzi;
    private TextView yonghu2, yonghu2Degree, yonghu2Pingjia, zuobian;
    private LinearLayout tuwenLayout, vedioyuyue;
    private String doctor, doctorid;
    private List<UserEvaluation.DataEntity> userEvaluationList;
    private LinearLayout pingjia1, pingjia2;
    private ImageView youbian, tuwentu, photo, dianhuatu, jiahaotu, privatetu, vediotu, p;
    private RelativeLayout yonghupingjia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        Intent intent = getIntent();
        doctor = intent.getStringExtra("doctor");
        doctorid = intent.getStringExtra("id");
        userEvaluationList = new ArrayList<>();
        init();
        String url = Constants.SERVER_URL + "MhealthOrderEvaluateCheckServlet";
        TiUser user = new TiUser();
        user.setName(doctorid + "");
        user.setPass(1 + "");
        user.setTel("0");
        MyHttpUtils.handData(handler, 264, url, user);


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 153:
                    final DoctorDetail doctorDetail = (DoctorDetail) msg.obj;
                    name.setText(doctorDetail.getUserName());
                    ImageLoader.getInstance().displayImage(doctorDetail.getImage(), photo, ImageUtil.avatarlistdoctor);
                    departmenTextView.setText(doctorDetail.getFirstdep());
                    zhichengTextView.setText(doctorDetail.getDoctorTilte());
                    hospitalTextView.setText(doctorDetail.getHospital());
                    xianqingTextView.setText(doctorDetail.getSpecialization());
                    recommend.setText(doctorDetail.getRecommend() + "");
                    attitude.setText(doctorDetail.getPriceAdd() + "");
                    level.setText(doctorDetail.getRecommend() + "");

                    if (doctorDetail.getPricePicture() >= 0)
                        tuwenfeiyong.setText(doctorDetail.getPricePicture() + "元/次");
                    else {
                        tuwenfeiyong.setText("未开通");
                        tuwentu = (ImageView) findViewById(R.id.tuwentu);
                        tuwentu.setImageResource(R.mipmap.camera_doctor_grey);
                    }
                    dianhuatu = (ImageView) findViewById(R.id.dianhuatu);
                    if (doctorDetail.getPricePhone() >= 0) {
                        dianhuafeiyong.setText(doctorDetail.getPricePicture() + "元/次");
                        dianhuatu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + doctorDetail.getUserEvaluate()));
                                startActivity(intent);
                            }
                        });
                    } else {
                        dianhuafeiyong.setText("未开通");
                        dianhuatu.setImageResource(R.mipmap.phone_doctor_grey);
                    }
                    jiahaotu = (ImageView) findViewById(R.id.jiahaotu);
                    if (doctorDetail.getPriceVideo() >= 0) {
                        jiahaofeiyong.setText(doctorDetail.getPriceVideo() + "元/次");
                        jiahaotu.setImageResource(R.mipmap.video_doctor);
                    } else {
                        jiahaofeiyong.setText("未开通");
                        jiahaotu.setImageResource(R.mipmap.video_doctor_grey);
                    }
                    privatetu = (ImageView) findViewById(R.id.privatetu);
                    if (doctorDetail.getPricePrivate() >= 0) {
                        privatefeiyong.setText(doctorDetail.getPricePrivate() + "元/次");
                        privatetu.setImageResource(R.mipmap.person_doctor);
                    } else {
                        privatefeiyong.setText("未开通");
                        privatetu.setImageResource(R.mipmap.person_doctor_grey);
                    }
                    vediotu = (ImageView) findViewById(R.id.vediotu);
                    if (doctorDetail.getPriceAdd() >= 0) {
                        vedioefeiyong.setText(doctorDetail.getPriceAdd() + "元");
                        vediotu.setImageResource(R.mipmap.before_doctor);
                    } else {
                        vedioefeiyong.setText("未开通");
                        vediotu.setImageResource(R.mipmap.before_doctor_grey);
                    }
                    vediotu.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(DoctorDetailActivity.this, OrderDoctorActivity.class);
                            intent.putExtra("flag", doctorid);
                            startActivity(intent);
                        }
                    });

                    tuwenLayout.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (doctorDetail.getPricePicture() == 0) {
                                ChatManager chatManager = ChatManager.getInstance();
                                chatManager.fetchConversationWithUserId(doctorid,
                                        new AVIMConversationCreatedCallback() {
                                            @Override
                                            public void done(AVIMConversation conversation, AVIMException e) {
                                                if (e != null) {
                                                    Toast.makeText(DoctorDetailActivity.this, e.getMessage(),
                                                            Toast.LENGTH_LONG).show();
                                                } else {
                                                    Intent intent = new Intent(DoctorDetailActivity.this,
                                                            ChatRoomActivity.class);
                                                    intent.putExtra(com.avoscloud.leanchatlib.utils.Constants.CONVERSATION_ID,
                                                            conversation.getConversationId());
                                                    Tool.initToast(DoctorDetailActivity.this,
                                                            "支付成功，请24小时内与" + doctorDetail.getUserName() + "医生咨询");
                                                    startActivity(intent);
                                                }
                                            }
                                        }, ConversationType.Doctor.getValue());
                            } else {
                                Intent intent = new Intent();
                                intent.setClass(DoctorDetailActivity.this, BuActivity.class);
                                intent.putExtra("type", "1");
                                intent.putExtra("id", doctorDetail.getId() + "");
                                intent.putExtra("price", doctorDetail.getPricePicture() + "");
                                intent.putExtra("doctor", doctorDetail.getDoctorId() + "");
                                intent.putExtra("name", doctorDetail.getUserName() + "");
                                startActivity(intent);
                            }

                        }
                    });
                    break;
                case 264:
                    UserEvaluation userEvaluation = (UserEvaluation) msg.obj;
                    if (userEvaluation != null) {
                        userEvaluationList.clear();
                        userEvaluationList.addAll(userEvaluation.getData());
                    }
                    yonghupingjia.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (userEvaluationList.size() == 0) {
                            } else if (userEvaluationList.size() > 2) {
                                Intent intent = new Intent();
                                intent.setClass(DoctorDetailActivity.this, EvaluationListActivity.class);
                                intent.putExtra("doctorId", doctorid + "");
                                intent.putExtra("status", "1");
                                startActivity(intent);
                            }
                        }
                    });
                    if (userEvaluationList.size() == 0) {
                        zuobian.setText("暂无任何用户评价");
                        youbianzi.setVisibility(View.GONE);
                        youbian.setVisibility(View.GONE);
                    } else {
                        zuobian.setText("用户评价（" + userEvaluationList.size() + "人）");
                        youbianzi.setVisibility(View.GONE);
                        youbian.setVisibility(View.GONE);
                        if (userEvaluationList.size() > 2) {
                            youbianzi.setVisibility(View.VISIBLE);
                            youbian.setVisibility(View.VISIBLE);
                        }
                    }
                    if (userEvaluationList.size() == 0) {
                        pingjia1.setVisibility(View.GONE);
                        pingjia2.setVisibility(View.GONE);
                    } else if (userEvaluationList.size() == 1) {
                        pingjia2.setVisibility(View.GONE);
                        pingjia1.setVisibility(View.VISIBLE);
                        String str = userEvaluationList.get(0).getUserName();
                        yonghu1.setText(str.charAt(0) + "****"
                                + str.charAt(str.length() - 1));
                        yonghu1Degree.setText("满意度" + userEvaluationList.get(0).getSatisfy());
                        yonghu1Pingjia.setText(userEvaluationList.get(0).getEvaluate());
                    } else if (userEvaluationList.size() >= 2) {
                        pingjia2.setVisibility(View.VISIBLE);
                        pingjia1.setVisibility(View.VISIBLE);
                        String str = userEvaluationList.get(0).getUserName();
                        yonghu1.setText(str.charAt(0) + "****"
                                + str.charAt(str.length() - 3));
                        yonghu1Degree.setText("满意度" + userEvaluationList.get(0).getSatisfy() + "");
                        yonghu1Pingjia.setText(userEvaluationList.get(0).getEvaluate());

                        str = userEvaluationList.get(1).getUserName();
                        yonghu2.setText(str.charAt(0) + "****"
                                + str.charAt(str.length() - 3));
                        yonghu2Degree.setText("满意度" + userEvaluationList.get(1).getSatisfy() + "");
                        yonghu2Pingjia.setText(userEvaluationList.get(1).getEvaluate());
                    }

                    break;
            }
        }
    };

    private void init() {
        photo = (ImageView) findViewById(R.id.photo);
        tuwenLayout = (LinearLayout) findViewById(R.id.tuwenzixun);
        vedioyuyue = (LinearLayout) findViewById(R.id.vedioyuyue);
        yonghupingjia = (RelativeLayout) findViewById(R.id.yonghupingjia);
        yonghu1 = (TextView) findViewById(R.id.yonghu1);
        yonghu1Degree = (TextView) findViewById(R.id.yonghu1Degree);
        yonghu1Pingjia = (TextView) findViewById(R.id.yonghu1pingjia);
        yonghu2 = (TextView) findViewById(R.id.yonghu2);
        yonghu2Degree = (TextView) findViewById(R.id.yonghu2Degree);
        yonghu2Pingjia = (TextView) findViewById(R.id.yonghu2pingjia);
        youbianzi = (TextView) findViewById(R.id.youbianzi);
        youbian = (ImageView) findViewById(R.id.youbian);
        pingjia1 = (LinearLayout) findViewById(R.id.pingjia1);
        pingjia2 = (LinearLayout) findViewById(R.id.pingjia2);
        zuobian = (TextView) findViewById(R.id.zuobian);
        name = (TextView) findViewById(R.id.name);
        departmenTextView = (TextView) findViewById(R.id.department);
        zhichengTextView = (TextView) findViewById(R.id.zhicheng);
        hospitalTextView = (TextView) findViewById(R.id.hospital);
        xianqingTextView = (TextView) findViewById(R.id.xiangqing);
        tuwenfeiyong = (TextView) findViewById(R.id.tuwenfeiyong);
        recommend = (TextView) findViewById(R.id.recommend);
        level = (TextView) findViewById(R.id.level);
        attitude = (TextView) findViewById(R.id.attitude);
        privatefeiyong = (TextView) findViewById(R.id.privatefeiyong);
        jiahaofeiyong = (TextView) findViewById(R.id.jiahaofeiyong);
        vedioefeiyong = (TextView) findViewById(R.id.vediofeiyong);
        dianhuafeiyong = (TextView) findViewById(R.id.dianhuafeiyong);
        String url = Constants.SERVER_URL + "MhealthOneDoctorServlet";
        TiUser user = new TiUser();
        user.setName(doctor + "");
        MyHttpUtils.handData(handler, 153, url, user);
    }

    public void leftBtnClick(View v) {
        finish();
    }
}
