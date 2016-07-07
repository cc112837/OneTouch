package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzy.mhealth.R;

public class GreenPassActivity extends Activity  implements View.OnClickListener{
    private TextView ll_child;
    private TextView ll_fuke;
    private TextView ll_chanke;
    private ImageView leftBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_pass);
        init();
    }
    private void init() {
        ll_child=(TextView) findViewById(R.id.erke);
        ll_fuke=(TextView) findViewById(R.id.fuke);
        ll_chanke=(TextView) findViewById(R.id.chanke);
        leftBtn=(ImageView) findViewById(R.id.leftBtn);
        ll_child.setOnClickListener(this);
        ll_fuke.setOnClickListener(this);
        ll_chanke.setOnClickListener(this);
        leftBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.erke:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("keshi", 1 + "");
                intent.setClass(this,DoctorListActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.fuke:
                Intent intent1 = new Intent();
                Bundle bundle1 = new Bundle();
                bundle1.putString("keshi",2+"");
                intent1.setClass(this,DoctorListActivity.class);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
            case R.id.chanke:
                Intent intent2 = new Intent();
                Bundle bundle2 = new Bundle();
                bundle2.putString("keshi",3+"");
                intent2.setClass(this,DoctorListActivity.class);
                intent2.putExtras(bundle2);
                startActivity(intent2);
                break;
            case R.id.leftBtn:
                GreenPassActivity.this.finish();
                break;
        }

    }
}
