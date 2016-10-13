package com.wzy.mhealth.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TabBingli2Adapter;
import com.wzy.mhealth.adapter.TabBingliAdapter;
import com.wzy.mhealth.inter.XmppConnection;
import com.wzy.mhealth.model.Bingli2;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsActivity extends BaActivity {
    private String interactName = "张三";
    private String interactSex = "男";
    private String interactYear = "23";
    private String marriage = "未婚";
    private String haschild = "未生育";
    private String shoushu = "无手术或外伤";
    List<String> string = new ArrayList<String>();
    private Class<?> jiankangdanganActivity = JiankangActivity.class;

    private List<Bingli2> bingli2List = new ArrayList<Bingli2>();
    private TabBingli2Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bingli);
        ListView jiankang = (ListView) findViewById(R.id.tab_binglifragment_listview_jiankang);
        ListView bingli = (ListView) findViewById(R.id.tab_binglifragment_listview_bingli);
        string.clear();
        string.addAll(XmppConnection.getInstance().getOwnBingliInfo());
        if (string != null && string.size() >= 6)
            jiankang.setAdapter(new TabBingliAdapter(string.get(0), string
                    .get(1), string.get(2), string.get(3), string.get(4),
                    string.get(5), this));
        else
            jiankang.setAdapter(new TabBingliAdapter("", "", "", "", "","", this));

        // (string.get(0), interactSex,interactYear,marriage,haschild,shoushu,
        // this));
        jiankang.setOnItemClickListener(new InteractItemSlectedListener());
        bingli2List.addAll(XmppConnection.getInstance().getElectronicMedical());
        adapter = new TabBingli2Adapter(this, bingli2List);

        bingli.setAdapter(adapter);
        //返回箭头
        back();

    }

    private void back() {
        // TODO Auto-generated method stub
        ImageView imageback = (ImageView) findViewById(R.id.leftBtn);

        imageback.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }

    class InteractItemSlectedListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            Intent intent = new Intent();
            switch (parent.getId()) {
                case R.id.tab_binglifragment_listview_jiankang:
                    intent.setClass(MedicalRecordsActivity.this, JiankangActivity.class);
                    if(string.size() == 6){
                        Bundle bundle = new Bundle();
                        bundle.putString("basic", string.get(0)+"  " +string.get(1)+"   "+string.get(2));
                        bundle.putString("history", string.get(3)+"   "+string.get(4));
                        bundle.putString("shoushu", string.get(5));
                        intent.putExtras(bundle);
                    }
                    startActivity(intent);

                case R.id.tab_binglifragment_listview_bingli:


            }
        }
    }


}
