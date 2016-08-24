package com.wzy.mhealth.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.AMap.InfoWindowAdapter;
import com.amap.api.maps2d.AMap.OnInfoWindowClickListener;
import com.amap.api.maps2d.AMap.OnMapClickListener;
import com.amap.api.maps2d.AMap.OnMarkerClickListener;
import com.amap.api.maps2d.AMapUtils;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.SupportMapFragment;
import com.amap.api.maps2d.model.BitmapDescriptor;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.overlay.PoiOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.core.SuggestionCity;
import com.amap.api.services.nearby.NearbySearch;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.poisearch.PoiSearch.SearchBound;
import com.avos.avoscloud.AVAnalytics;
import com.wzy.mhealth.R;

import java.util.ArrayList;
import java.util.List;


/**
 * AMapV1地图中简单介绍poisearch搜索
 */
public class PoiKeywordSearchActivity extends FragmentActivity implements OnMarkerClickListener, InfoWindowAdapter,
        OnPoiSearchListener, OnMapClickListener, OnInfoWindowClickListener, LocationSource, AMapLocationListener {

    private AMap aMap;
    private MapView mapView;

    private PoiResult poiResult; // poi返回的结果

    private PoiSearch.Query query;// Poi查询条件类


    private Marker locationMarker; // 选择的点
    private PoiSearch poiSearch;
    private PoiOverlay poiOverlay;// poi图层
    private List<PoiItem> poiItems;// poi数据
    private Marker detailMarker;// 显示Marker的详情

    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private MarkerOptions markerOptions = new MarkerOptions();
    private double lat;
    private double log;
    private double endLog;
    private double endLat;
    private ImageView iv_save;
    private TextView tv_myloc;

    private NearbySearch mNearbySearch;
    int count;
    private int SIZE;
    private String values;
    private double a;
    private double b;
    private ImageButton button;
    private String address;
    private LatLng start;
    private LatLng end;
    private TextView distance;
    private TextView titleView;
    private String keyWord;
    private String NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_poi_keyword_search);
        ImageView imageView = (ImageView) findViewById(R.id.leftBtn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PoiKeywordSearchActivity.this.finish();
            }
        });
        mNearbySearch = NearbySearch.getInstance(getApplicationContext());

//        mapView = (MapView) findViewById(R.id.map);
//		mapView.onCreate(savedInstanceState);// 此方法必须重写
        init();

        titleView = (TextView) findViewById(R.id.titleView);
        keyWord = getIntent().getStringExtra("keyword");
        if (keyWord.equals("药房")) {
            titleView.setText("附近药房");
            NAME = "药店";
        } else if (keyWord.equals("急救中心|急诊")) {
            titleView.setText("急诊通道");
            NAME = "急诊";
        } else {
            NAME = "医院";
        }
        doSearchQuery();

    }

    protected void doSearchQuery() {

        aMap.setOnMapClickListener(null);// 进行poi搜索时清除掉地图点击事件

        //String [] poi={"急救","急诊医院"};
        //for (int i = 0; i < poi.length; i++) {

        query = new PoiSearch.Query(keyWord, "医疗保健服务", "");// 第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）

        query.setPageSize(30);// 设置每页最多返回多少条poiitem
        query.setPageNum(0);// 设置查第一页
        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);

        poiSearch.setBound(new SearchBound(new LatLonPoint(a, b), 5000, true));
        poiSearch.searchPOIAsyn();// 异步搜索


    }

    /**
     * POI搜索回调方法
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {

        if (rCode == 0) {
            if (result != null && result.getQuery() != null) {// 搜索poi的结果
                if (result.getQuery().equals(query)) {// 是否是同一条
                    poiResult = result;
                    poiItems = poiResult.getPois();
                    SIZE = poiItems.size();// 取得第一页的poiitem数据，页数从数字0开始
                    List<SuggestionCity> suggestionCities = poiResult
                            .getSearchSuggestionCitys();// 当搜索不到poiitem数据时，会返回含有搜索关键字的城市信息
                    if (poiItems != null && poiItems.size() > 0) {
                        //aMap.clear();// 清理之前的图标
                        poiOverlay = new PoiOverlay(aMap, poiItems);
                        poiOverlay.removeFromMap();
                        poiOverlay.addToMap();
                        poiOverlay.zoomToSpan();
                        for (PoiItem po : poiItems) {
                            MarkerOptions markerOption = new MarkerOptions();
                            markerOption.position(new LatLng(po.getLatLonPoint()
                                    .getLatitude(), po.getLatLonPoint().getLongitude()));
                            markerOption.draggable(true);

                            if (keyWord.endsWith("药房"))
                                markerOption.icon(BitmapDescriptorFactory
                                        .fromResource(R.mipmap.yaodian));
                            else if (keyWord.endsWith("医院"))
                                markerOption.icon(BitmapDescriptorFactory
                                        .fromResource(R.mipmap.yiyuan));
                            else
                                markerOption.icon(BitmapDescriptorFactory
                                        .fromResource(R.mipmap.car));

                            String s = "名称：" + po.getTitle() + " \n地址：" + po.getSnippet()
                                    + " \n";
                            markerOption.title(s);
                            aMap.addMarker(markerOption);
                            aMap.setOnMarkerClickListener(this);// 添加点击marker监听事件
                            aMap.setInfoWindowAdapter(this);// 添加显示infowindow监听事件
                        }
                    }
                }
            }
        }
        customPic();

    }

    /**
     * 初始化AMap对象
     */
    private void init() {

        if (aMap == null) {
            aMap = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.poi_map)).getMap();
            setUpMap();
        }

    }


    /**
     * 设置页面监听
     */
    private void setUpMap() {

        aMap.setOnMarkerClickListener(this);// 添加点击marker监听事件
        aMap.setInfoWindowAdapter(this);// 添加显示infowindow监听事件
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        //customPic();
    }

    //定义自动定位动画
    private void customPic() {
        ArrayList<BitmapDescriptor> animations = new ArrayList<BitmapDescriptor>();
        animations.add(BitmapDescriptorFactory.fromResource(R.mipmap.heart04));
        animations.add(BitmapDescriptorFactory.fromResource(R.mipmap.heart03));
        markerOptions.anchor(0.5f, 0.5f);
        markerOptions.position(new LatLng(a, b));
        markerOptions.draggable(true);
        markerOptions.icons(animations);
        markerOptions.period(5);
        locationMarker = aMap.addMarker(markerOptions);

        //locationMarker.showInfoWindow();

        MarkerOptions options = new MarkerOptions();
        TextView textView = new TextView(getApplicationContext());
        textView.setText("附近有" + SIZE + "家" + NAME);
        textView.setTextSize(18);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundResource(R.drawable.custom_info_bubble);
        BitmapDescriptor clickIcon = BitmapDescriptorFactory.fromView(textView);
        options.position(new LatLng(a, b));
        options.icon(clickIcon);
        aMap.addMarker(options);


    }

    /**
     * 定位成功后回调函数
     */
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null
                    && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
                lat = amapLocation.getLatitude();
                log = amapLocation.getLongitude();
                address = amapLocation.getAddress();
                a = lat;
                b = log;
                //customPic();
                doSearchQuery();

            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                Toast.makeText(this, errText, Toast.LENGTH_LONG);

            }
        }

    }


    /**
     * 激活定位
     */
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(getApplicationContext());
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mlocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            mLocationOption.setNeedAddress(true);
            mLocationOption.setOnceLocation(true);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocatit
            mlocationClient.startLocation();
        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        if (poiOverlay != null && poiItems != null && poiItems.size() > 0) {
            detailMarker = marker;
        }
        marker.showInfoWindow();
        return true;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public View getInfoWindow(final Marker marker) {
        View view = getLayoutInflater().inflate(R.layout.poikeywordsearch_uri,
                null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(marker.getTitle());
        TextView snippet = (TextView) view.findViewById(R.id.snippet);
        snippet.setText(marker.getSnippet());
        endLat = marker.getPosition().latitude;
        endLog = marker.getPosition().longitude;
        start = new LatLng(a, b);
        end = new LatLng(endLat, endLog);
        TextView tv_distance = (TextView) view.findViewById(R.id.distance);
        double distance = (int) ((((int) AMapUtils.calculateLineDistance(start, end)) / 1000.0) * 100 + 0.5) / 100.0;
        tv_distance.setText("距离亲" + distance + "公里");

        button = (ImageButton) view
                .findViewById(R.id.start_amap_app);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PoiKeywordSearchActivity.this, GPSNaviActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", lat);
                bundle.putDouble("log", log);
                bundle.putDouble("endLat", endLat);
                bundle.putDouble("endLog", endLog);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        return view;
    }


    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        mListener = null;
        if (mlocationClient != null) {
            mlocationClient.stopLocation();
            mlocationClient.onDestroy();
        }
        mlocationClient = null;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {


    }

    @Override
    public void onMapClick(LatLng latLng) {


    }

    protected void onPause() {
        super.onPause();
        AVAnalytics.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AVAnalytics.onResume(this);
    }

}
