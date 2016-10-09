package com.wzy.mhealth.activities;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.DoctorListAdapter;
import com.wzy.mhealth.adapter.LocationAdapter;
import com.wzy.mhealth.adapter.ProvinceAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Doctor;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class DoctorListActivity extends BaActivity {

    private List<Doctor.DataEntity> doctorlist;
    private List<String> locationList;
    public List<String> cityList, list2;
    private ListView lv, locationListView, cityListView;
    private DoctorListAdapter adapter;
    private LocationAdapter locationAdapter;
    private PopupWindow mPopupWindow;
    private LinearLayout location;
    private LinearLayout layout_left;
    private LinearLayout doctorlistLayout;
    private ProvinceAdapter provinceAdapter;
    private TextView locationtTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        location = (LinearLayout) findViewById(R.id.total_location);
        locationtTextView = (TextView)location.findViewById(R.id.text_category);
        doctorlistLayout = (LinearLayout) findViewById(R.id.doctorlistLinear);
        location.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                locationtTextView.setTextColor(0xf000cf00);
                showPopupWindow(doctorlistLayout.getWidth(),
                        doctorlistLayout.getHeight());
            }
        });

        lv = (ListView) findViewById(R.id.doctorlist);
        doctorlist = new ArrayList<>();
        locationList = new ArrayList<>();
        cityList = new ArrayList<>();
        list2 = new ArrayList<>();
        String url= Constants.SERVER_URL+"MhealthDoctorServlet";
        MyHttpUtils.handData(handler, 152, url, null);
        adapter = new DoctorListAdapter(this, doctorlist);
        lv.setAdapter(adapter);

        //返回箭头
        ImageView imageback = (ImageView) findViewById(R.id.leftBtn);

        imageback.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what){
            case 152:
                final Doctor doctor=(Doctor)msg.obj;
                doctorlist.addAll(doctor.getData());
                adapter.notifyDataSetChanged();
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent intent = new Intent();
                        intent.setClass(DoctorListActivity.this, DoctorDetailActivity.class);
                        intent.putExtra("doctor",doctor.getData().get(position).getId()+"" );
                        startActivity(intent);
                    }
                });
            break;
        }
    }
};


    private void showPopupWindow(int width, int height) {
        layout_left = (LinearLayout) LayoutInflater.from(
                DoctorListActivity.this).inflate(R.layout.popup_category, null);
        locationListView = (ListView) layout_left
                .findViewById(R.id.rootcategory);
        cityListView = (ListView) layout_left.findViewById(R.id.childcategory);
        cityListView.setVisibility(View.INVISIBLE);
        locationAdapter = new LocationAdapter(DoctorListActivity.this,
                locationList);
        locationListView.setAdapter(locationAdapter);
        locationAdapter.notifyDataSetChanged();

        locationListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                locationAdapter.setSelectItem(position);
                locationAdapter.notifyDataSetInvalidated();
                cityListView.setVisibility(View.VISIBLE);
                if (position == 1) {
                    provinceAdapter = new ProvinceAdapter(
                            DoctorListActivity.this, list2);
                    cityListView.setAdapter(provinceAdapter);
                    provinceAdapter.notifyDataSetChanged();
                } else {
                    provinceAdapter = new ProvinceAdapter(
                            DoctorListActivity.this, cityList);
                    cityListView.setAdapter(provinceAdapter);
                    provinceAdapter.notifyDataSetChanged();
                }

            }
        });
        mPopupWindow = new PopupWindow(layout_left, width, height * 2 / 3, true);
        mPopupWindow.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                locationtTextView = (TextView)location.findViewById(R.id.text_category);
                locationtTextView.setTextColor(0xff000000);
            }
        });
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAsDropDown(location, 5, 1);
        mPopupWindow.update();
    }
}
