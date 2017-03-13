package com.wzy.mhealth.activities;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.HospitalAdapter;
import com.wzy.mhealth.adapter.LocationAdapter;
import com.wzy.mhealth.adapter.ProvinceAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Hospital;
import com.wzy.mhealth.model.Provice;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wzy.mhealth.R.id.text_address;
import static com.wzy.mhealth.R.id.total_location;

/**
 * 创建人：吴聪聪
 * 邮箱:cc112837@163.com
 * 医院列表展示
 */
public class HospitalActivity extends AppCompatActivity {
    private PopupWindow mPopupWindow;
    private LinearLayout layout_left;
    private LocationAdapter locationAdapter;
    private ProvinceAdapter provinceAdapter;
    public List<Provice.DataEntity> proviceList = new ArrayList<>();
    @Bind(R.id.titleView)
    TextView titleView;
    @Bind(R.id.leftBtn)
    ImageView leftBtn;
    @Bind(text_address)
    TextView textAddress;
    @Bind(total_location)
    LinearLayout totalLocation;
    @Bind(R.id.order_text)
    TextView orderText;
    @Bind(R.id.order)
    LinearLayout order;
    @Bind(R.id.lv_show)
    ListView lvShow;
    List<Provice.DataEntity.CityArrEntity> cityArr;
    private ListView  locationListView, cityListView;
    List<Hospital.DataEntity> list = new ArrayList<>();
    private HospitalAdapter hospitalAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 306:
                    Hospital hospital = (Hospital) msg.obj;
                    list.addAll(hospital.getData());
                    hospitalAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);
        ButterKnife.bind(this);
        hospitalAdapter = new HospitalAdapter(HospitalActivity.this, list);
        lvShow.setAdapter(hospitalAdapter);
        String url = Constants.SERVER_URL + "MhealthHospitalServlet";
        TiUser tiUser = new TiUser();
        tiUser.setName("");
        MyHttpUtils.handData(handler, 306, url, tiUser);
    }


    @OnClick({total_location, R.id.order,R.id.leftBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case total_location:
                String url = Constants.SERVER_URL + "MhealthProviceCeShiServlet";
                MyHttpUtils.handData(handler, 160, url, null);
                showPopupWindow(lvShow.getWidth(),
                        lvShow.getHeight());
                break;
            case R.id.order:
                break;
            case R.id.leftBtn:
                finish();
        }
    }
    private void showPopupWindow(int width, int height) {
        layout_left = (LinearLayout) LayoutInflater.from(
                HospitalActivity.this).inflate(R.layout.popup_category, null);
        locationListView = (ListView) layout_left
                .findViewById(R.id.rootcategory);
        cityListView = (ListView) layout_left.findViewById(R.id.childcategory);
        cityListView.setVisibility(View.INVISIBLE);
        locationAdapter = new LocationAdapter(HospitalActivity.this,
                proviceList);
        locationListView.setAdapter(locationAdapter);

        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                locationAdapter.setSelectItem(position);
                cityListView.setVisibility(View.VISIBLE);
                cityArr = proviceList.get(position).getCityArr();
                provinceAdapter = new ProvinceAdapter(
                        HospitalActivity.this, cityArr);
                cityListView.setAdapter(provinceAdapter);
            }
        });
        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPopupWindow.dismiss();
                String url = Constants.SERVER_URL + "MhealthHospitalServlet";
                TiUser user = new TiUser();
                if(cityArr.get(position).getCityId()==920003){
                    user.setName("");
                    textAddress.setText("全部");
                }
                else{
                    user.setName("" + cityArr.get(position).getCityId());
                    textAddress.setText(cityArr.get(position).getCity() + "");
                }
                MyHttpUtils.handData(handler, 306, url, user);
            }
        });
        mPopupWindow = new PopupWindow(layout_left, width, height * 2 / 3, true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                textAddress.setTextColor(0xff000000);
            }
        });
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAsDropDown(totalLocation, 5, 1);
        mPopupWindow.update();
    }
}
