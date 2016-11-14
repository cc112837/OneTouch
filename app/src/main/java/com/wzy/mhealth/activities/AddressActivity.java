package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.AddressAdapter;
import com.wzy.mhealth.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private TextView tv_newadress;
    private ListView lv_address;
    private List<Address.DataEntity>list=new ArrayList<>();
    private AddressAdapter addressAdapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        init();
    }

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(this);
        tv_newadress=(TextView) findViewById(R.id.tv_newadress);
        tv_newadress.setOnClickListener(this);
        lv_address=(ListView) findViewById(R.id.lv_address);
        addressAdapter = new AddressAdapter(this, list);
        lv_address.setAdapter(addressAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_newadress:
                Intent intent=new Intent(AddressActivity.this,UpdaAddressActivity.class);
                intent.putExtra("flag","new");
                startActivity(intent);
                break;
        }
    }
}
