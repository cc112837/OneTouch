package com.wzy.mhealth.activities;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.wzy.mhealth.adapter.TaocanAllAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.Provice;
import com.wzy.mhealth.model.TiUser;
import com.wzy.mhealth.model.Tijian;
import com.wzy.mhealth.utils.MyHttpUtils;
import com.wzy.mhealth.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class TaocanListActivity extends Activity {
    private ImageView leftBtn;
    private PopupWindow mPopupWindow;
    public List<Provice.DataEntity> proviceList = new ArrayList<>();
    private TaocanAllAdapter taocanListAdapter;
    private LocationAdapter locationAdapter;
    private ProvinceAdapter provinceAdapter;
    private RecyclerView rv_show;
    private ListView  locationListView, cityListView;
    private LinearLayout total_location, order, layout_left, ll_wrap;
    private TextView text_address, order_text;
    private List<Tijian.DataEntity> list = new ArrayList<>();
    List<Provice.DataEntity.CityArrEntity> cityArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taocan_list);
        taocanListAdapter = new TaocanAllAdapter(TaocanListActivity.this);
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
                    break;
                case 160:
                    Provice provice = (Provice) msg.obj;
                    proviceList.clear();
                    proviceList.addAll(provice.getData());
                    locationAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    private void init() {
        rv_show = (RecyclerView) findViewById(R.id.lv_show);
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        ll_wrap = (LinearLayout) findViewById(R.id.ll_wrap);
        total_location = (LinearLayout) findViewById(R.id.total_location);
        order = (LinearLayout) findViewById(R.id.order);
        order_text = (TextView) findViewById(R.id.order_text);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rv_show.setLayoutManager(manager);
        rv_show.setAdapter(taocanListAdapter);
        rv_show.addItemDecoration(new DividerItemDecoration(
                TaocanListActivity.this, DividerItemDecoration.VERTICAL_LIST));
        taocanListAdapter.setData(list);
        String url = Constants.SERVER_URL + "MhealthOneCityServlet";
        TiUser user = new TiUser();
        user.setTel("");
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
                        TaocanListActivity.this, cityArr);
                cityListView.setAdapter(provinceAdapter);
            }
        });
        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPopupWindow.dismiss();
                String url = Constants.SERVER_URL + "MhealthOneCityServlet";
                TiUser user = new TiUser();
                if(cityArr.get(position).getCityId()==920003){
                    user.setTel("");
                    text_address.setText("全部");
                }
                else{
                    user.setTel("" + cityArr.get(position).getCityId());
                    text_address.setText(cityArr.get(position).getCity() + "");
                }

                MyHttpUtils.handData(handler, 157, url, user);
            }
        });
        mPopupWindow = new PopupWindow(layout_left, width, height * 2 / 3, true);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                text_address = (TextView) total_location.findViewById(R.id.text_address);
                text_address.setTextColor(0xff000000);
            }
        });
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAsDropDown(total_location, 5, 1);
        mPopupWindow.update();
    }
}
