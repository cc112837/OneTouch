package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.GuahaoDoctorListAdapter;
import com.wzy.mhealth.inter.XmppConnection;
import com.wzy.mhealth.model.GuahaoDoctorEntity;
import com.wzy.mhealth.model.HospitalEntity;

import java.util.ArrayList;
import java.util.List;

public class GuahaoDoctorListActivity extends Activity {
    private List<GuahaoDoctorEntity> doctorlist;

    private ListView lv;
    private TextView total;
    private GuahaoDoctorListAdapter adapter;
    private HospitalEntity hos;
    private String keshi;
    private ImageView leftBtn;
    public static  GuahaoDoctorListActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guahao_doctor_list);
        instance=this;
        Intent intent = getIntent();
        hos = (HospitalEntity) intent.getSerializableExtra("hospital");
        keshi = (String) intent.getStringExtra("keshi");
        if (keshi == null) {
            keshi = "";
        }

        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv = (ListView) findViewById(R.id.guahaodoctorlist);
        doctorlist = new ArrayList<>();

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        init();
        total = (TextView) findViewById(R.id.total);
        if (doctorlist.size() > 0)
            total.setText("共有" + doctorlist.size() + "名医生");
        else
            total.setText("该科室没有医生");
        adapter = new GuahaoDoctorListAdapter(this, doctorlist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(GuahaoDoctorListActivity.this, YuyueActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("doctor", doctorlist.get(position));
                bundle.putSerializable("hospital", hos);
                bundle.putString("keshi", keshi);
                intent.putExtras(bundle);
                startActivity(intent);
//				finish();
            }
        });
    }

    public void leftBtn(View leftBtn) {
        finish();
    }

    private void init() {
        //// TODO: 2016/5/25 (修改成无网络)
//        doctorlist.addAll(XmppConnection.getInstance().getGuahaoDoctorList(hos.getId(), keshi));
		doctorlist.addAll(XmppConnection.getInstance().getGuahaoDoctorList(hos.getId(), keshi));
        doctorlist.add(new GuahaoDoctorEntity("邓珊","dengshan", "主任医师",
				"9.4","接诊量457", "心血管常见病,各种疑难杂症，祖传秘方，童叟无欺",23));

		doctorlist.add(new GuahaoDoctorEntity("李芳","lifang", "主任医师",
				"9.4","接诊量457", "心血管常见病,各种疑难杂症，祖传秘方，童叟无欺",11));
		doctorlist.add(new GuahaoDoctorEntity("刘卫华","liuweihua", "主任医师",
				"9.4","接诊量457", "心血管常见病,各种疑难杂症，祖传秘方，童叟无欺",23));
		doctorlist.add(new GuahaoDoctorEntity("左丽珊","dengshan", "主任医师",
				"9.4","接诊量457", "心血管常见病,各种疑难杂症，祖传秘方，童叟无欺",11));
		doctorlist.add(new GuahaoDoctorEntity("赵荣","dengshan", "主任医师",
				"9.4","接诊量457", "心血管常见病,各种疑难杂症，祖传秘方，童叟无欺",33));
		doctorlist.add(new GuahaoDoctorEntity("谷雨","dengshan", "主任医师",
				"9.4","接诊量457", "心血管常见病,各种疑难杂症，祖传秘方，童叟无欺",34));
		doctorlist.add(new GuahaoDoctorEntity("李凌云","dengshan", "主任医师",
				"9.4","接诊量457", "心血管常见病,各种疑难杂症，祖传秘方，童叟无欺",15));
    }
}
