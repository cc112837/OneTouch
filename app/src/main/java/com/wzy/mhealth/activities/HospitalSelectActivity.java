package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.HospitalListAdapter;
import com.wzy.mhealth.inter.XmppConnection;
import com.wzy.mhealth.model.HospitalEntity;

import java.util.ArrayList;
import java.util.List;

public class HospitalSelectActivity extends BaActivity {
    private List<HospitalEntity> hospitallist;

    private ListView lv;
    private HospitalListAdapter adapter;
    private ImageView leftBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yiyuan_select);

        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv = (ListView) findViewById(R.id.hospitallist);
        hospitallist = new ArrayList<>();
        init();
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter = new HospitalListAdapter(this, hospitallist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("name", hospitallist.get(position));
                bundle.putString("flag", "1");
                intent.putExtras(bundle);
                setResult(0x717, intent);
                finish();
            }
        });
    }






    private void init() {
        // TODO: 2016/5/25 (修改成无网络)
        hospitallist.addAll(XmppConnection.getInstance().getHospitalList());
		hospitallist.add(new HospitalEntity("中国人民解放军总医院","三级甲等", "预约量 124.6万",
				"患者评价 5.9万"));
		hospitallist.add(new HospitalEntity("上海交通大学医学院附属医院","三级甲等", "预约量 90.6万",
				"患者评价 3.8万"));
		hospitallist.add(new HospitalEntity("华中科技大学同济医学院","三级甲等", "预约量 172.8万",
				"患者评价 1.0万"));
		hospitallist.add(new HospitalEntity("中山大学附属第三医院","三级甲等", "预约量 124.6万",
				"患者评价 7.0万"));
		hospitallist.add(new HospitalEntity("北京天坛医院特需门诊","三级甲等", "预约量 186.3万",
				"患者评价 10.0万"));
		hospitallist.add(new HospitalEntity("中国人民解放军总医院","三级甲等", "预约量 124.6万",
				"患者评价 5.9万"));
		hospitallist.add(new HospitalEntity("中国人民解放军总医院","三级甲等", "预约量 124.6万",
				"患者评价 5.9万"));
		hospitallist.add(new HospitalEntity("上海交通大学医学院附属医院","三级甲等", "预约量 90.6万",
				"患者评价 3.8万"));
    }
}
