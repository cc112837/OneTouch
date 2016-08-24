package com.wzy.mhealth.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TaoCanAdapter;
import com.wzy.mhealth.model.ZhixingTaocan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PersonTaocan extends Activity implements AMapLocationListener {
    private ImageView leftBtn;
    private ListView lv_show;
    double lat, log;
    private AMapLocationClient mlocationClient;
    public AMapLocationClientOption mLocationOption = null;
    private ArrayList<ZhixingTaocan> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_taocan);
        init();
    }


    public void loaction() {
        mlocationClient = new AMapLocationClient(PersonTaocan.this);
//初始化定位参数
        mLocationOption = new AMapLocationClientOption();
//设置定位监听
        mlocationClient.setLocationListener(PersonTaocan.this);
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
                lat=amapLocation.getLatitude();//获取纬度
                log=amapLocation.getLongitude();//获取经度
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

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv_show = (ListView) findViewById(R.id.lv_show);
        View headview = LayoutInflater.from(this).inflate(R.layout.zhidetail_header, null);
        list.add(new ZhixingTaocan("入职套餐", 228, 114));
        list.add(new ZhixingTaocan("青年男宾体检套餐", 468, 234));
        list.add(new ZhixingTaocan("青年已婚女宾体检套餐", 713, 357));
        list.add(new ZhixingTaocan("青年未婚女宾体检套餐", 558, 279));
        list.add(new ZhixingTaocan("青年男宾深度体检套餐", 840, 420));
        list.add(new ZhixingTaocan("青年已婚女宾深度体检套餐", 995, 498));
        list.add(new ZhixingTaocan("中年男宾体检套餐", 1065, 533));
        list.add(new ZhixingTaocan("中年已婚女宾体检套餐", 1220, 610));
        list.add(new ZhixingTaocan("中老年男宾体检套餐", 1155, 578));
        list.add(new ZhixingTaocan("中老年女宾体检套餐", 1310, 655));
        list.add(new ZhixingTaocan("孕前男宾体检套餐", 678, 339));
        list.add(new ZhixingTaocan("孕前女宾体检套餐", 1148, 574));
        TaoCanAdapter adapter = new TaoCanAdapter(this, list);
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
                Intent intent = new Intent(PersonTaocan.this, GPSNaviActivity.class);
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
                Toast.makeText(PersonTaocan.this, "您点击了" + list.get(position - 1).getName(), Toast.LENGTH_LONG).show();
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
