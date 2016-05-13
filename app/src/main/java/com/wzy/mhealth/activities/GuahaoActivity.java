package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.model.HospitalEntity;
import com.wzy.mhealth.utils.Tool;

public class GuahaoActivity extends Activity {
    TextView tex;
    public String name;
    public HospitalEntity names;
    final public int CODE = 0x717;
    GuahaoActivity guahaoActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guahao);
        initView();
    }

    private void initView() {
        RelativeLayout yiyuan_layout = (RelativeLayout)
                findViewById(R.id.yiyuan_layout);
        RelativeLayout keshi_layout = (RelativeLayout)
                findViewById(R.id.keshi_layout);
        guahaoActivity=GuahaoActivity.this;
        tex = (TextView) findViewById(R.id.Select);
        ImageView leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        yiyuan_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuahaoActivity.this,
                        YiyuanSelectActivity.class);
                Log.e("选择医院了","");
                startActivityForResult(intent, guahaoActivity.CODE);
            }

        });

        keshi_layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (guahaoActivity.name == null) {
                    tex.setText(guahaoActivity.name);
                    Tool.initToast(GuahaoActivity.this, "请选择医院");
                } else {
                    Intent intent = new Intent(GuahaoActivity.this,
                            KeshiselectActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("hospital", guahaoActivity.names);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

        });
        Button guahao = (Button) findViewById(R.id.guanhao_btn);
        guahao.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (guahaoActivity.names == null) {
                    Tool.initToast(GuahaoActivity.this, "请选择医院");
                } else {
                    Intent intent = new Intent(GuahaoActivity.this,
                            GuahaoDoctorListActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("hospital", guahaoActivity.names);
                    intent.putExtras(bundle);
                    startActivity(intent);

                }
            }

        });
    }

    public void update(String name) {
        tex.setText(name);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("返回数据了",data+"***"+requestCode+"&&&&"+resultCode);
        if (requestCode == CODE && resultCode == CODE) {
            guahaoActivity.names = (HospitalEntity) data.getSerializableExtra("name");
            String flag = data.getStringExtra("flag");
            guahaoActivity.name = guahaoActivity.names.getName();
            if (flag.equals("1")) {
                tex.setText(guahaoActivity.name);

            }
        }
    }
}
