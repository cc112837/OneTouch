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

import com.wzy.mhealth.R;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.DoctorDetail;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.UserEvaluation;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class DoctorDetailActivity extends BaActivity {

    private TextView name, departmenTextView, zhichengTextView,
            hospitalTextView, xianqingTextView, tuwenfeiyong, dianhuafeiyong,
            jiahaofeiyong, privatefeiyong, vedioefeiyong;
    private TextView guanzhuTextView, recommend, attitude, level;
    private TextView yonghu1, yonghu1Degree, yonghu1Pingjia, youbianzi;
    private TextView yonghu2, yonghu2Degree, yonghu2Pingjia, zuobian;
    private LinearLayout tuwenLayout,vedioyuyue;
    private String doctor;
    private List<UserEvaluation> userEvaluationList;
    private LinearLayout pingjia1, pingjia2;
    private ImageView youbian, tuwentu, dianhuatu, jiahaotu, privatetu, vediotu;
    private RelativeLayout yonghupingjia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        Intent intent = getIntent();
        doctor = intent.getStringExtra("doctor");
        userEvaluationList = new ArrayList<>();
        init();


        guanzhuTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(DoctorDetailActivity.this, "1111",
                        Toast.LENGTH_LONG).show();
            }
        });

        yonghupingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEvaluationList.size() == 0) {
                } else if (userEvaluationList.get(0).getTotalRecord() > 2) {
                    Intent intent = new Intent();
                    intent.setClass(DoctorDetailActivity.this, EvaluationListActivity.class);
                    intent.putExtra("doctorId", doctor + "");
                    startActivity(intent);
                }
            }
        });


    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 153:
                    final DoctorDetail doctorDetail = (DoctorDetail) msg.obj;
                    name.setText(doctorDetail.getUserName());
                    departmenTextView.setText(doctorDetail.getFirstdep());
                    zhichengTextView.setText(doctorDetail.getDoctorTilte());
                    hospitalTextView.setText(doctorDetail.getHospital());
                    xianqingTextView.setText(doctorDetail.getSpecialization());
                    recommend.setText(doctorDetail.getRecommend() + "");
                    attitude.setText(doctorDetail.getPriceAdd() + "");
                    level.setText(doctorDetail.getRecommend() + "");

                    if (doctorDetail.getPricePicture() != 0)
                        tuwenfeiyong.setText(doctorDetail.getPricePicture() + "元/次");
                    else {
                        tuwenfeiyong.setText("未开通");
                        tuwentu = (ImageView) findViewById(R.id.tuwentu);
                        tuwentu.setImageResource(R.mipmap.camera_doctor_grey);
                    }
                    dianhuatu = (ImageView) findViewById(R.id.dianhuatu);
                    if (doctorDetail.getPricePhone() != 0) {
                        dianhuafeiyong.setText(doctorDetail.getPricePicture() + "元/分钟");
                        dianhuatu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" +doctorDetail.getUserEvaluate()));
                                startActivity(intent);
                            }
                        });
                    } else {
                        dianhuafeiyong.setText("未开通");
                        dianhuatu.setImageResource(R.mipmap.phone_doctor_grey);
                    }
                    jiahaofeiyong.setText("未开通");
                    jiahaotu = (ImageView) findViewById(R.id.jiahaotu);
                    jiahaotu.setImageResource(R.mipmap.video_doctor_grey);

                    privatefeiyong.setText("未开通");
                    privatetu = (ImageView) findViewById(R.id.privatetu);
                    privatetu.setImageResource(R.mipmap.person_doctor_grey);

                    vedioefeiyong.setText("未开通");
                    vediotu = (ImageView) findViewById(R.id.vediotu);
                    vediotu.setImageResource(R.mipmap.before_doctor_grey);
                    tuwenLayout.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setClass(DoctorDetailActivity.this, BuActivity.class);
                            intent.putExtra("type", "1");
                            intent.putExtra("price", doctorDetail.getPricePicture() + "");
                            intent.putExtra("doctor", doctorDetail.getDoctorId() + "");
                            intent.putExtra("name", doctorDetail.getUserName() + "");
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };

    private void init() {


        String url = Constants.SERVER_URL + "MhealthOneDoctorServlet";
        TiUser user = new TiUser();
        user.setName(doctor + "");
        MyHttpUtils.handData(handler, 153, url, user);
        guanzhuTextView = (TextView) findViewById(R.id.guanzhu);
        TextView xinyiTextView = (TextView) findViewById(R.id.songxinyi);
        tuwenLayout = (LinearLayout) findViewById(R.id.tuwenzixun);
        vedioyuyue=(LinearLayout) findViewById(R.id.vedioyuyue);
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
        if (userEvaluationList.size() == 0) {
            zuobian.setText("暂无任何用户评价");
            youbianzi.setVisibility(View.GONE);
            youbian.setVisibility(View.GONE);
        } else {
            zuobian.setText("用户评价（" + userEvaluationList.get(0).getTotalRecord() + "人）");
            youbianzi.setVisibility(View.GONE);
            youbian.setVisibility(View.GONE);
            if (userEvaluationList.get(0).getTotalRecord() > 2) {
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
            String str = userEvaluationList.get(0).getUserid();
            yonghu1.setText(str.charAt(0) + "****"
                    + str.charAt(str.length() - 1));
            yonghu1Degree.setText(userEvaluationList.get(0).getDegree() + "");
            yonghu1Pingjia.setText(userEvaluationList.get(0).getComment());
        } else if (userEvaluationList.size() >= 2) {
            pingjia2.setVisibility(View.VISIBLE);
            pingjia1.setVisibility(View.VISIBLE);
            String str = userEvaluationList.get(0).getUserid();
            yonghu1.setText(str.charAt(0) + "****"
                    + str.charAt(str.length() - 3));
            yonghu1Degree.setText(userEvaluationList.get(0).getDegree() + "");
            yonghu1Pingjia.setText(userEvaluationList.get(0).getComment());

            str = userEvaluationList.get(1).getUserid();
            yonghu2.setText(str.charAt(0) + "****"
                    + str.charAt(str.length() - 3));
            yonghu2Degree.setText(userEvaluationList.get(1).getDegree() + "");
            yonghu2Pingjia.setText(userEvaluationList.get(1).getComment());
        }


    }

    public void leftBtnClick(View v) {
        finish();
    }
}
