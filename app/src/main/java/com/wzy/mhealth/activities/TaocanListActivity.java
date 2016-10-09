package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.LocationAdapter;
import com.wzy.mhealth.adapter.ProvinceAdapter;
import com.wzy.mhealth.adapter.TaocanListAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.City;
import com.wzy.mhealth.model.Provice;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.Tijian;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.util.ArrayList;
import java.util.List;

public class TaocanListActivity extends Activity {
    private ImageView leftBtn;
    private PopupWindow mPopupWindow;
    public List<String> cityList, locationList;
    private TaocanListAdapter taocanListAdapter;
    private LocationAdapter locationAdapter;
    private ProvinceAdapter provinceAdapter;
    private ListView lv_show, locationListView, cityListView;
    private LinearLayout total_location, order, layout_left, ll_wrap;
    private TextView text_address, order_text;
    private List<Tijian.DataEntity> list = new ArrayList<>();
    private List<String> listid = new ArrayList<>();
    private List<String> listcityid = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_list);
        locationList = new ArrayList<>();
        cityList = new ArrayList<>();
        taocanListAdapter = new TaocanListAdapter(TaocanListActivity.this, list);
        init();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 157:
                    Tijian tijian = (Tijian) msg.obj;
                    list.clear();
                    list.addAll(tijian.getData());
                    taocanListAdapter.notifyDataSetChanged();
                    lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(TaocanListActivity.this, PersonTaocanActivity.class);
                            intent.putExtra("id", list.get(position).getId() + "");
                            intent.putExtra("name",list.get(position).getName()+"");
                            intent.putExtra("tel",list.get(position).getPhone()+"");
                            intent.putExtra("add",list.get(position).getAdress()+"");
                            intent.putExtra("content",list.get(position).getDetails()+"");
                            intent.putExtra("img",list.get(position).getImg()+"");
                            startActivity(intent);
                        }
                    });
                    break;
                case 160:
                    Provice provice = (Provice) msg.obj;
                    locationList.clear();
                    listid.clear();
                    for (int i = 0; i < provice.getData().size(); i++) {
                        locationList.add(provice.getData().get(i).getProvice());
                        listid.add(provice.getData().get(i).getId() + "");
                    }
                    locationAdapter.notifyDataSetChanged();
                    break;

                case 162:
                    City city = (City) msg.obj;
                    cityList.clear();
                    listcityid.clear();
                    for (int i = 0; i < city.getData().size(); i++) {
                        cityList.add(city.getData().get(i).getCity());
                        listcityid.add(city.getData().get(i).getId() + "");
                    }
                    provinceAdapter.notifyDataSetChanged();
                    cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            mPopupWindow.dismiss();
                            text_address.setText(cityList.get(position)+"");
                            String url = Constants.SERVER_URL + "MhealthOneCityServlet";
                            TiUser user = new TiUser();
                            user.setTel("" + listcityid.get(position));
                            MyHttpUtils.handData(handler, 157, url, user);
                        }
                    });
                    break;
            }
        }
    };

    private void init() {
        lv_show = (ListView) findViewById(R.id.lv_show);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        ll_wrap = (LinearLayout) findViewById(R.id.ll_wrap);
        total_location = (LinearLayout) findViewById(R.id.total_location);
        order = (LinearLayout) findViewById(R.id.order);
        order_text = (TextView) findViewById(R.id.order_text);
        lv_show.setAdapter(taocanListAdapter);
        String url = Constants.SERVER_URL + "MhealthOneCityServlet";
        TiUser user = new TiUser();
        user.setTel("610100");
        MyHttpUtils.handData(handler, 157, url, user);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        total_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Constants.SERVER_URL + "MhealthProviceServlet";
                MyHttpUtils.handData(handler, 160, url, null);
                showPopupWindow(ll_wrap.getWidth(),
                        ll_wrap.getHeight());
            }
        });
    }

    private void showPopupWindow(int width, int height) {
        layout_left = (LinearLayout) LayoutInflater.from(
                TaocanListActivity.this).inflate(R.layout.popup_category, null);
        locationListView = (ListView) layout_left
                .findViewById(R.id.rootcategory);
        cityListView = (ListView) layout_left.findViewById(R.id.childcategory);
        cityListView.setVisibility(View.INVISIBLE);
        locationAdapter = new LocationAdapter(TaocanListActivity.this,
                locationList);
        locationListView.setAdapter(locationAdapter);

        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                locationAdapter.setSelectItem(position);
                cityListView.setVisibility(View.VISIBLE);
                cityList.clear();
                provinceAdapter = new ProvinceAdapter(
                        TaocanListActivity.this, cityList);
                cityListView.setAdapter(provinceAdapter);

                TiUser user = new TiUser();
                user.setName("" + listid.get(position));
                String url = Constants.SERVER_URL + "MhealthOneProviceServlet";
                MyHttpUtils.handData(handler, 162, url, user);
            }
        });
        mPopupWindow = new PopupWindow(layout_left, width, height * 2 / 3, true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                text_address = (TextView) total_location.findViewById(R.id.text_address);
                text_address.setTextColor(0xff000000);
            }
        });
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAsDropDown(total_location, 5, 1);
        mPopupWindow.update();
    }
}
