package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wzy.mhealth.R;
import com.wzy.mhealth.inter.XmppConnection;
import com.wzy.mhealth.model.DoctorEntity;
import com.wzy.mhealth.model.UserEvaluation;

import java.util.ArrayList;
import java.util.List;

public class DoctorDetailActivity extends BaActivity {

    private TextView name, departmenTextView, zhichengTextView,
            hospitalTextView, xianqingTextView, tuwenfeiyong, dianhuafeiyong,
            jiahaofeiyong, privatefeiyong, vedioefeiyong;
    private TextView guanzhuTextView, recommend, attitude, level;
    private TextView xinyiTextView;
    private TextView yonghu1, yonghu1Degree, yonghu1Pingjia, youbianzi;
    private TextView yonghu2, yonghu2Degree, yonghu2Pingjia, zuobian;
    private LinearLayout tuwenLayout;
    private DoctorEntity doctor;
    private List<UserEvaluation> userEvaluationList;
    private LinearLayout pingjia1, pingjia2;
    private ImageView youbian, tuwentu, dianhuatu, jiahaotu, privatetu, vediotu;
    private RelativeLayout yonghupingjia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        Intent intent = getIntent();
        doctor = (DoctorEntity) intent.getSerializableExtra("doctor");
        userEvaluationList = new ArrayList<>();

        init();
        guanzhuTextView = (TextView) findViewById(R.id.guanzhu);
        xinyiTextView = (TextView) findViewById(R.id.songxinyi);
        tuwenLayout = (LinearLayout) findViewById(R.id.tuwenzixun);
        tuwenLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setClass(DoctorDetailActivity.this, BuActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("doctor",doctor);
                bundle.putSerializable("price",doctor.getPrice_picture());
                bundle.putInt("type",1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        guanzhuTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(DoctorDetailActivity.this, "1111",
                        Toast.LENGTH_LONG).show();
            }
        });
        yonghupingjia = (RelativeLayout) findViewById(R.id.yonghupingjia);
        yonghupingjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEvaluationList.size() == 0){
                }else if (userEvaluationList.get(0).getTotalRecord() > 2) {
                    Intent intent = new Intent();
                    intent.setClass(DoctorDetailActivity.this, EvaluationListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("doctorId", doctor.getDoctorUsername());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });


    }

    private void init() {
        userEvaluationList.addAll(XmppConnection.getInstance()
                .getUserEvaluation(doctor.getDoctorUsername(), "2"));
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
        if (userEvaluationList.size() == 0){
            zuobian.setText("暂无任何用户评价");
            youbianzi.setVisibility(View.GONE);
            youbian.setVisibility(View.GONE);
        }else {
            zuobian.setText("用户评价（"+userEvaluationList.get(0).getTotalRecord()+"人）");
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
            yonghu1Degree.setText(userEvaluationList.get(0).getDegree()+"");
            yonghu1Pingjia.setText(userEvaluationList.get(0).getComment());
        } else if (userEvaluationList.size() >= 2) {
            pingjia2.setVisibility(View.VISIBLE);
            pingjia1.setVisibility(View.VISIBLE);
            String str = userEvaluationList.get(0).getUserid();
            yonghu1.setText(str.charAt(0) + "****"
                    + str.charAt(str.length() - 3));
            yonghu1Degree.setText(userEvaluationList.get(0).getDegree()+"");
            yonghu1Pingjia.setText(userEvaluationList.get(0).getComment());

            str = userEvaluationList.get(1).getUserid();
            yonghu2.setText(str.charAt(0) + "****"
                    + str.charAt(str.length() - 3));
            yonghu2Degree.setText(userEvaluationList.get(1).getDegree()+"");
            yonghu2Pingjia.setText(userEvaluationList.get(1).getComment());
        }
        name = (TextView) findViewById(R.id.name);
        name.setText(doctor.getName());
        departmenTextView = (TextView) findViewById(R.id.department);
        departmenTextView.setText(doctor.getFirstdepName());
        zhichengTextView = (TextView) findViewById(R.id.zhicheng);
        zhichengTextView.setText(doctor.getDoctorTitle());
        hospitalTextView = (TextView) findViewById(R.id.hospital);
        hospitalTextView.setText(doctor.getHospitalName());
        xianqingTextView = (TextView) findViewById(R.id.xiangqing);
        xianqingTextView.setText(doctor.getSpecialization());
        tuwenfeiyong = (TextView) findViewById(R.id.tuwenfeiyong);
        recommend = (TextView) findViewById(R.id.recommend);
        recommend.setText(doctor.getRecommend());
        attitude = (TextView) findViewById(R.id.attitude);
        attitude.setText(doctor.getAttitude());
        level = (TextView) findViewById(R.id.level);
        level.setText(doctor.getLevel());

        if (doctor.getPrice_picture() != null
                && !doctor.getPrice_picture().equals("null"))
            tuwenfeiyong.setText(doctor.getPrice_picture() + "元/次");
        else{
            tuwenfeiyong.setText("未开通");
            tuwentu = (ImageView) findViewById(R.id.tuwentu);
            tuwentu.setImageResource(R.mipmap.tuwen_grey);
        }

        dianhuafeiyong = (TextView) findViewById(R.id.dianhuafeiyong);
        if (doctor.getPrice_phone() != null
                && !doctor.getPrice_phone().equals("null"))
            dianhuafeiyong.setText(doctor.getPrice_phone() + "元/分钟");
        else{
            dianhuafeiyong.setText("未开通");
            dianhuatu = (ImageView) findViewById(R.id.dianhuatu);
            dianhuatu.setImageResource(R.mipmap.phone_grey);
        }

        jiahaofeiyong = (TextView) findViewById(R.id.jiahaofeiyong);
        if (doctor.getPrice_add() != null
                && !doctor.getPrice_add().equals("null"))
            jiahaofeiyong.setText(doctor.getPrice_add() + "元/次");
        else{
            jiahaofeiyong.setText("未开通");
            jiahaotu = (ImageView) findViewById(R.id.jiahaotu);
            jiahaotu.setImageResource(R.mipmap.add_grey);
        }

        privatefeiyong = (TextView) findViewById(R.id.privatefeiyong);
        if (doctor.getPrice_private() != null
                && !doctor.getPrice_private().equals("null"))
            privatefeiyong.setText(doctor.getPrice_private() + "元/次");
        else{
            privatefeiyong.setText("未开通");
            privatetu = (ImageView) findViewById(R.id.privatetu);
            privatetu.setImageResource(R.mipmap.private_grey);
        }

        vedioefeiyong = (TextView) findViewById(R.id.vediofeiyong);
        if (doctor.getPrice_vedio() != null
                && !doctor.getPrice_vedio().equals("null"))
            vedioefeiyong.setText(doctor.getPrice_vedio() + "元/次");
        else{
            vedioefeiyong.setText("未开通");
            vediotu = (ImageView) findViewById(R.id.vediotu);
            vediotu.setImageResource(R.mipmap.vedio_grey);
        }

    }

    public void leftBtnClick(View v) {
        finish();
    }
}
