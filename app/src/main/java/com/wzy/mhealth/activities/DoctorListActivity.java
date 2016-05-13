package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
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
import com.wzy.mhealth.inter.XmppConnection;
import com.wzy.mhealth.model.DoctorEntity;

import java.util.ArrayList;
import java.util.List;

public class DoctorListActivity extends Activity {

    private List<DoctorEntity> doctorlist;
    private List<String> locationList;
    public List<String> cityList, list2;
    private ListView lv, locationListView, cityListView;
    private DoctorListAdapter adapter;
    private LocationAdapter locationAdapter;
    private PopupWindow mPopupWindow;
    private LinearLayout location;
    private LinearLayout department;
    private LinearLayout layout_left;
    private LinearLayout layout_middle;
    private LinearLayout layout_right;
    private LinearLayout doctorlistLayout;
    private ProvinceAdapter provinceAdapter;
    private TextView locationtTextView;
    private String keshi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        Intent intent = getIntent();
        keshi = (String) intent.getStringExtra("keshi");
        location = (LinearLayout) findViewById(R.id.total_location);
        locationtTextView = (TextView)location.findViewById(R.id.text_category);
        department = (LinearLayout) findViewById(R.id.department);
        doctorlistLayout = (LinearLayout) findViewById(R.id.doctorlistLinear);
        location.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                locationtTextView.setTextColor(0xf000cf00);
                showPopupWindow(doctorlistLayout.getWidth(),
                        doctorlistLayout.getHeight());
            }
        });
        department.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

            }
        });

        lv = (ListView) findViewById(R.id.doctorlist);
        doctorlist = new ArrayList<DoctorEntity>();
        locationList = new ArrayList<String>();
        cityList = new ArrayList<String>();
        list2 = new ArrayList<String>();
        //initbydatabase();
        //initlist();
        init();
        adapter = new DoctorListAdapter(this, doctorlist);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(DoctorListActivity.this, DoctorDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("doctor", doctorlist.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        //返回箭头
        ImageView imageback = (ImageView) findViewById(R.id.leftBtn);

        imageback.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }



    private void init() {

        doctorlist.addAll(XmppConnection.getInstance().getDoctorListBykeshi(keshi));

        locationList.add("北京市");
        locationList.add("北京市");
        locationList.add("北京市");
        locationList.add("北京市");
        locationList.add("北京市");
        locationList.add("北京市");
        locationList.add("北京市");
        locationList.add("北京市");
        locationList.add("北京市");
        locationList.add("北京市");
        cityList.add("海淀区");
        cityList.add("海淀区");
        cityList.add("海淀区");
        cityList.add("海淀区");
        cityList.add("海淀区");
        cityList.add("海淀区");
        cityList.add("海淀区");
        cityList.add("海淀区");
        cityList.add("海淀区");
        cityList.add("海淀区");
        list2.add("西城区");
        list2.add("西城区");
        list2.add("西城区");
        list2.add("西城区");
        list2.add("西城区");
        list2.add("西城区");
        list2.add("西城区");
        list2.add("西城区");
        list2.add("西城区");
        list2.add("西城区");
        list2.add("西城区");

    }

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
                    // provinceAdapter.list = list2;
                    cityListView.setAdapter(provinceAdapter);
                    provinceAdapter.notifyDataSetChanged();
                } else {
                    provinceAdapter = new ProvinceAdapter(
                            DoctorListActivity.this, cityList);
                    // provinceAdapter.list = list2;
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
        // mPopupWindow.setFocusable(true);
    }
}
