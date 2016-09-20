package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TaoCanAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.ZhixingTaocan;
import com.wzy.mhealth.utils.MyHttpUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PersonTaocanActivity extends Activity implements AMapLocationListener {
    private ImageView leftBtn;
    private ListView lv_show;
    double lat, log;
    TaoCanAdapter adapter;
    private AMapLocationClient mlocationClient;
    public AMapLocationClientOption mLocationOption = null;
    private ArrayList<ZhixingTaocan.DataEntity> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_taocan);
        init();
    }


    public void loaction() {
        mlocationClient = new AMapLocationClient(PersonTaocanActivity.this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(PersonTaocanActivity.this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(2000);
        mlocationClient.setLocationOption(mLocationOption);
        mlocationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                lat = amapLocation.getLatitude();//获取纬度
                log = amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                df.format(date);//定位时间
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.getErrorCode() + ", errInfo:"
                        + amapLocation.getErrorInfo());
            }
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 115:
                    ZhixingTaocan zhixing = (ZhixingTaocan) msg.obj;
                    list.addAll(zhixing.getData());
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv_show = (ListView) findViewById(R.id.lv_show);
        View headview = LayoutInflater.from(this).inflate(R.layout.zhidetail_header, null);
        adapter = new TaoCanAdapter(this, list);
        String url = Constants.SERVER_URL + "TaoCanServlet";
        MyHttpUtils.handData(handler, 115, url, null);
        ImageView iv_addr = (ImageView) headview.findViewById(R.id.iv_address);
        ImageView iv_tel = (ImageView) headview.findViewById(R.id.iv_tel);
        iv_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:02968550666"));
                startActivity(intent);
            }
        });
        iv_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaction();
                Intent intent = new Intent(PersonTaocanActivity.this, GPSNaviActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", lat);
                bundle.putDouble("log", log);
                bundle.putDouble("endLat", 34.250156);
                bundle.putDouble("endLog", 108.895032);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        lv_show.addHeaderView(headview);
        lv_show.setAdapter(adapter);
        lv_show.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent intent=new Intent(PersonTaocanActivity.this,ZhixingIntroduceActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(PersonTaocanActivity.this, TaocanDetailAcitivty.class);
                    intent.putExtra("id", list.get(position - 1).getId() + "");
                    startActivity(intent);
                }
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
