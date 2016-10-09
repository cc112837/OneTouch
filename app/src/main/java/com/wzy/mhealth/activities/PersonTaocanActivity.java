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
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.wzy.mhealth.R;
import com.wzy.mhealth.adapter.TaoCanAdapter;
import com.wzy.mhealth.constant.Constants;
import com.wzy.mhealth.model.TiUser;
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
    private String id,name,tel,add,content,img;
    private ImageView iv_img;
    private TextView tv_name;
    private TextView tv_add;
    private TextView tv_tel;
    private View headview;
    private ImageView iv_tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_taocan);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        tel = intent.getStringExtra("tel");
        add = intent.getStringExtra("add");
        content = intent.getStringExtra("content");
        img = intent.getStringExtra("img");
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
                    final ZhixingTaocan zhixing = (ZhixingTaocan) msg.obj;
                    if (zhixing != null) {
                        list.addAll(zhixing.getData());
                        adapter.notifyDataSetChanged();
                        tv_name.setText("" + name);
                        headview.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(PersonTaocanActivity.this, ZhixingIntroduceActivity.class);
                                intent.putExtra("content", content + "");
                                startActivity(intent);
                            }
                        });
                        tv_add.setText("地址：" + add);
                        tv_tel.setText("电话：" + tel);
                        iv_tel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+tel));
                                startActivity(intent);
                            }
                        });
                        ImageLoader.getInstance().displayImage(img, iv_img);

                    }
                    break;
            }
        }
    };

    private void init() {
        leftBtn = (ImageView) findViewById(R.id.leftBtn);
        lv_show = (ListView) findViewById(R.id.lv_show);
        headview = LayoutInflater.from(this).inflate(R.layout.zhidetail_header, null);
        adapter = new TaoCanAdapter(this, list);
        String url = Constants.SERVER_URL + "TaoCanCenterServlet";
        TiUser user = new TiUser();
        Log.e("setTel", id + "");
        user.setTel("" + id);
        MyHttpUtils.handData(handler, 115, url, user);
        iv_img = (ImageView) headview.findViewById(R.id.iv_img);
        tv_name = (TextView) headview.findViewById(R.id.tv_name);
        tv_add = (TextView) headview.findViewById(R.id.tv_add);
        tv_tel = (TextView) headview.findViewById(R.id.tv_tel);
        ImageView iv_addr = (ImageView) headview.findViewById(R.id.iv_address);
        iv_tel = (ImageView) headview.findViewById(R.id.iv_tel);

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
                if (position != 0) {
                    Intent intent = new Intent(PersonTaocanActivity.this, TaocanDetailAcitivty.class);
                    intent.putExtra("id", list.get(position - 1).getId() + "");
                    startActivityForResult(intent, 456);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 456:
                finish();
                break;
            default:
                break;
        }
    }
}
