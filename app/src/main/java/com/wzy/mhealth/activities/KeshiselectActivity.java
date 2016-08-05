package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.avos.avoscloud.AVAnalytics;
import com.wzy.mhealth.R;
import com.wzy.mhealth.model.HospitalEntity;

public class KeshiselectActivity extends Activity {
    public static KeshiselectActivity instance;
    String[][] officess = { { "心血管内科", "消化内科", "呼吸内科", "血液内科", "内分泌内科", "肾病内科" },
            { "普通外科", "神经外科", "小儿外科", "骨科", "泌尿外科", "胸外科", "胆胰外科", "肠胃外科" },
            { "妇科", "生殖医学科" }, { "儿科" }, { "感染科肝病", "肝病感染发热" },
            { "中西医结核科", "中医妇科", "中医内分泌" },
            { "神经内科", "焦虑抑郁专科", "心身障碍专科", "心理咨询", "精神障碍" }, { "急诊心血管", "急诊呼吸" },
            { "创伤外科", "创伤外科伤口修复", "运动损伤" }, { "肿瘤科" }, { "耳鼻咽喉-头颈外科", "鼻科" },
            { "眼科" }, { "口腔科" }, { "放射介入" } };
    String[] dess = { "内科", "外科", "妇产科", "儿科", "感染科", "中医科", "神经内科", "急诊内科",
            "急诊外科", "肿瘤科", "耳鼻喉-头颈外科", "眼科", "口腔科", "放射科" };
    private HospitalEntity hos;
    private int pos;
    private ImageView leftBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keshiselect);
        instance=this;
        ListView departList = (ListView) findViewById(R.id.deparment);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        departList.setAdapter(new ArrayAdapter<String>(KeshiselectActivity.this,
                R.layout.list_item_text, dess));
        final ListView officeList = (ListView) findViewById(R.id.office);
        ArrayAdapter adapter = new ArrayAdapter<String>(KeshiselectActivity.this,
                R.layout.list_item_text_black, officess[0]);
        officeList.setAdapter(adapter);

        Intent intent = getIntent();
        hos = (HospitalEntity) intent.getSerializableExtra("hospital");
        leftBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        departList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                // TODO Auto-generated method stub
                ArrayAdapter adapter = new ArrayAdapter<String>(
                        KeshiselectActivity.this, R.layout.list_item_text_black,
                        officess[position]);
                officeList.setAdapter(adapter);
                pos = position;

            }

        });
        officeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(KeshiselectActivity.this,
                        GuahaoDoctorListActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("hospital", hos);
                bundle.putString("keshi", pos + "");
                intent.putExtras(bundle);

                startActivity(intent);
//				finish();
            }

        });
    }
    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }
}
