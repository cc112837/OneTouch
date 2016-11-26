package com.wzy.mhealth.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.AddressAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Address;
import com.wzy.mhealth.model.StepInfo;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class AddressActivity extends Activity implements View.OnClickListener {
    private ImageView leftBtn;
    private TextView tv_newadress;
    private ListView lv_address;
    private List<Address.DataEntity> list = new ArrayList<>();
    private AddressAdapter addressAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 278:
                    Address address = (Address) msg.obj;
                    list.clear();
                    list.addAll(address.getData());
                    addressAdapter.notifyDataSetChanged();
                    break;
                case 280:
                    StepInfo stepInfo = (StepInfo) msg.obj;
                    if (("1").equals(stepInfo.getStatus())) {
                        String url = Constants.SERVER_URL + "MhealthShopAddressServlet";
                        MyHttpUtils.handData(handler, 278, url, null);
                    }
                    break;
            }
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
        tv_newadress = (TextView) findViewById(R.id.tv_newadress);
        tv_newadress.setOnClickListener(this);
        lv_address = (ListView) findViewById(R.id.lv_address);
        addressAdapter = new AddressAdapter(this, list);
        lv_address.setAdapter(addressAdapter);
        String url = Constants.SERVER_URL + "MhealthShopAddressServlet";
        MyHttpUtils.handData(handler, 278, url, null);
        lv_address.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(AddressActivity.this).setTitle("删除提示")
                        .setMessage("您确定要删除这条地址吗？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String uri = Constants.SERVER_URL + "MhealthShopAddressDeleteServlet";
                        TiUser user = new TiUser();
                        user.setName(list.get(position).getAddressId() + "");
                        MyHttpUtils.handData(handler, 280, uri, user);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String url = Constants.SERVER_URL + "MhealthShopAddressServlet";
        MyHttpUtils.handData(handler, 278, url, null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBtn:
                finish();
                break;
            case R.id.tv_newadress:
                Intent intent = new Intent(AddressActivity.this, UpdaAddressActivity.class);
                intent.putExtra("flag", "new");
                startActivity(intent);
                break;
        }
    }
}
