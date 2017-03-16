package com.wzy.mhealth.activities;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.DoctorListAdapter;
import com.wzy.mhealth.adapter.FirstDepAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Doctor;
import com.wzy.mhealth.model.FirstDep;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 医生列表显示
*/
public class DoctorListActivity extends BaActivity {

    private List<Doctor.DataEntity> doctorlist = new ArrayList<>();
    private List<FirstDep.DataEntity> firstDeplist = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private ListView lv;
    private DoctorListAdapter adapter;
    private PopupWindow mPopupWindow;
    private LinearLayout location, order;
    private LinearLayout layout_left;
    private LinearLayout doctorlistLayout;
    private TextView locationtTextView,order_text;
    private ListView lv_keshi;
    private FirstDepAdapter firstDepAdapter;
    String firstDepid = "";
    String flag = "";
    boolean change = true;
    private ArrayAdapter arrayAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 149:
                    FirstDep firstDep = (FirstDep) msg.obj;
                    firstDeplist.clear();
                    firstDeplist.addAll(firstDep.getData());
                    firstDepAdapter.notifyDataSetChanged();
                    break;
                case 152:
                    final Doctor doctor = (Doctor) msg.obj;
                    doctorlist.clear();
                    doctorlist.addAll(doctor.getData());
                    adapter.notifyDataSetChanged();
                    lv.setOnItemClickListener(new OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
                            Intent intent = new Intent();
                            if ("0".equals(doctor.getData().get(position).getId())) {
                                intent.setClass(DoctorListActivity.this, NoContentActivity.class);
                            } else {
                                intent.setClass(DoctorListActivity.this, DoctorDetailActivity.class);
                                intent.putExtra("id", doctor.getData().get(position).getDoctorId() + "");
                                intent.putExtra("doctor", doctor.getData().get(position).getId() + "");
                            }
                            startActivity(intent);
                        }
                    });
                    break;
            }
        }
    };
    private String hosid;
    private String flaghos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        Intent intent = getIntent();
        flaghos = intent.getStringExtra("flag");
        if("hos".equals(flaghos)){
            hosid = intent.getStringExtra("id");
            Log.e("id",hosid);
        }
        else{
            hosid="";
        }
        adapter = new DoctorListAdapter(this, doctorlist);
        init();
    }

    private void init() {
        list.add("评价最高");
        list.add("人气最高");
        list.add("默认");
        ImageView imageback = (ImageView) findViewById(R.id.leftBtn);
        imageback.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        location = (LinearLayout) findViewById(R.id.total_location);
        order = (LinearLayout) findViewById(R.id.order);
        order.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                change = false;
                showPopupWindow(doctorlistLayout.getWidth(),
                        doctorlistLayout.getHeight());
            }
        });
        location.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                change = true;
                String url = Constants.SERVER_URL + "MhealthFirstDepServlet";
                MyHttpUtils.handData(handler, 149, url, null);
                showPopupWindow(doctorlistLayout.getWidth(),
                        doctorlistLayout.getHeight());
            }
        });
        locationtTextView = (TextView) location.findViewById(R.id.text_category);
        order_text = (TextView) order.findViewById(R.id.order_text);
        doctorlistLayout = (LinearLayout) findViewById(R.id.doctorlistLinear);
        lv = (ListView) findViewById(R.id.doctorlist);
        lv.setAdapter(adapter);
        String url = Constants.SERVER_URL + "MhealthDoctorOrderServlet";
        TiUser user = new TiUser();
        user.setName("");
        user.setPass("");
        user.setTel(hosid);
        MyHttpUtils.handData(handler, 152, url, user);
    }


    private void showPopupWindow(int width, int height) {
        layout_left = (LinearLayout) LayoutInflater.from(
                DoctorListActivity.this).inflate(R.layout.popup_keshi, null);
        lv_keshi = (ListView) layout_left
                .findViewById(R.id.lv_keshi);
        if (change) {
            firstDepAdapter = new FirstDepAdapter(DoctorListActivity.this, firstDeplist);
            lv_keshi.setAdapter(firstDepAdapter);
            lv_keshi.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mPopupWindow.dismiss();
                    locationtTextView.setText(firstDeplist.get(position).getFirstDepName());
                    String url = Constants.SERVER_URL + "MhealthDoctorOrderServlet";
                    TiUser user = new TiUser();
                    firstDepid = firstDeplist.get(position).getFirstDepId() + "";
                    user.setName(firstDepid);
                    user.setPass(flag);
                    user.setTel(hosid);
                    MyHttpUtils.handData(handler, 152, url, user);
                }
            });
        } else {
            arrayAdapter = new ArrayAdapter(DoctorListActivity.this, R.layout.keshi_item, R.id.tv_keshi, list);
            lv_keshi.setAdapter(arrayAdapter);
            lv_keshi.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    mPopupWindow.dismiss();
                    order_text.setText(list.get(position)+"");
                    String url = Constants.SERVER_URL + "MhealthDoctorOrderServlet";
                    TiUser user = new TiUser();
                    flag = list.get(position) + "";
                    user.setName(firstDepid);
                    user.setPass(flag);
                    user.setTel(hosid);
                    MyHttpUtils.handData(handler, 152, url, user);
                }
            });
        }


        mPopupWindow = new PopupWindow(layout_left, width, height * 1/ 3, true);
        mPopupWindow.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                locationtTextView.setTextColor(0xff000000);
                order_text.setTextColor(0xff000000);
            }
        });
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAsDropDown(location, 5, 1);
        mPopupWindow.update();
    }
}
