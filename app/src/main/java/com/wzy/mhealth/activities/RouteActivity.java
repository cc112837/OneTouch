package com.wzy.mhealth.activities;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.overlay.DrivingRouteOverlay;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.amap.api.services.poisearch.PoiSearch.OnPoiSearchListener;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.RouteSearch.DriveRouteQuery;
import com.amap.api.services.route.RouteSearch.OnRouteSearchListener;
import com.amap.api.services.route.WalkRouteResult;
import com.wzy.mhealth.R;
import com.wzy.mhealth.utils.AMapUtil;
import com.wzy.mhealth.utils.ToastUtil;
import com.wzy.mhealth.view.RouteSearchPoiDialog;

import java.util.List;

/**
 * AMapV1地图中简单介绍route搜索
 */
public class RouteActivity extends BaActivity implements OnMarkerClickListener,
        OnMapClickListener, OnInfoWindowClickListener, InfoWindowAdapter,
        OnPoiSearchListener, OnRouteSearchListener, OnClickListener,
        LocationSource, AMapLocationListener {
    private AMap aMap;
    private MapView mapView;

    private ImageButton startImageButton;
    private ImageButton endImageButton;
    private ImageButton routeSearchImagebtn;

    private EditText startTextView;
    private EditText endTextView;
    private ProgressDialog progDialog = null;// 搜索时进度条
    private int drivingMode = RouteSearch.DrivingDefault;// 驾车默认模式
    private DriveRouteResult driveRouteResult;// 驾车模式查询结果
    private int routeType = 2;// 1代表公交模式，2代表驾车模式，3代表步行模式
    private String strStart;
    private String strEnd;
    private LatLonPoint startPoint = null;
    private LatLonPoint endPoint = null;
    private PoiSearch.Query startSearchQuery;
    private PoiSearch.Query endSearchQuery;

    private boolean isClickStart = false;
    private boolean isClickTarget = false;
    private Marker startMk, targetMk;
    private RouteSearch routeSearch;
    public ArrayAdapter<String> aAdapter;

    private OnLocationChangedListener mListener;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    private boolean is = true;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_route);
        ImageView leftBtn=(ImageView) findViewById(R.id.leftBtn);
        leftBtn.setOnClickListener(this);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(bundle);// 此方法必须重写
        init();
        thread.start();
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();

            MyLocationStyle myLocationStyle = new MyLocationStyle();
            myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                    .fromResource(R.mipmap.heart04));// 设置小蓝点的图标
            myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
            myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
            // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
            myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
            aMap.setMyLocationStyle(myLocationStyle);
            aMap.setLocationSource(this);// 设置定位监听
            aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(true);
            registerListener();
        }
        routeSearch = new RouteSearch(this);
        routeSearch.setRouteSearchListener(this);
        startTextView = (EditText) findViewById(R.id.autotextview_roadsearch_start);
        endTextView = (EditText) findViewById(R.id.autotextview_roadsearch_goals);
        startImageButton = (ImageButton) findViewById(R.id.imagebtn_roadsearch_startoption);
        startImageButton.setOnClickListener(this);
        endImageButton = (ImageButton) findViewById(R.id.imagebtn_roadsearch_endoption);
        endImageButton.setOnClickListener(this);
        routeSearchImagebtn = (ImageButton) findViewById(R.id.imagebtn_roadsearch_search);
        routeSearchImagebtn.setOnClickListener(this);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        is = false;
        while(thread.isAlive());
        super.onDestroy();
        mapView.onDestroy();
    }

    /**
     * 在地图上选取起点
     */
    private void startImagePoint() {
        ToastUtil.show(RouteActivity.this, "在地图上点击您的起点");
        isClickStart = true;
        isClickTarget = false;
        registerListener();
    }

    /**
     * 在地图上选取终点
     */
    private void endImagePoint() {
        ToastUtil.show(RouteActivity.this, "在地图上点击您的终点");
        isClickTarget = true;
        isClickStart = false;
        registerListener();
    }

    /**
     * 点击搜索按钮开始Route搜索
     */
    public void searchRoute() {
        strStart = startTextView.getText().toString().trim();
        strEnd = endTextView.getText().toString().trim();
        if (strStart == null || strStart.length() == 0) {
            ToastUtil.show(RouteActivity.this, "请选择起点");
            return;
        }
        if (strEnd == null || strEnd.length() == 0) {
            ToastUtil.show(RouteActivity.this, "请选择终点");
            return;
        }
        if (strStart.equals(strEnd)) {
            ToastUtil.show(RouteActivity.this, "起点与终点距离很近，您可以步行前往");
            return;
        }

        startSearchResult();// 开始搜终点
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        isClickStart = false;
        isClickTarget = false;
        if (marker.equals(startMk)) {
            startTextView.setText("地图上的起点");
            startPoint = AMapUtil.convertToLatLonPoint(startMk.getPosition());
            startMk.hideInfoWindow();
            startMk.remove();
        } else if (marker.equals(targetMk)) {
            endTextView.setText("地图上的终点");
            endPoint = AMapUtil.convertToLatLonPoint(targetMk.getPosition());
            targetMk.hideInfoWindow();
            targetMk.remove();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.isInfoWindowShown()) {
            marker.hideInfoWindow();
        } else {
            marker.showInfoWindow();
        }
        return false;
    }

    @Override
    public void onMapClick(LatLng latng) {
        if (isClickStart) {
            startMk = aMap.addMarker(new MarkerOptions().anchor(0.5f, 1)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.point))
                    .position(latng).title("点击选择为起点"));
            startMk.showInfoWindow();
        } else if (isClickTarget) {
            targetMk = aMap.addMarker(new MarkerOptions().anchor(0.5f, 1)
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.point))
                    .position(latng).title("点击选择为目的地"));
            targetMk.showInfoWindow();
        }
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    /**
     * 注册监听
     */
    private void registerListener() {
        aMap.setOnMapClickListener(RouteActivity.this);
        aMap.setOnMarkerClickListener(RouteActivity.this);
        aMap.setOnInfoWindowClickListener(RouteActivity.this);
        aMap.setInfoWindowAdapter(RouteActivity.this);
    }

    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在搜索");
        progDialog.show();
    }

    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }

    /**
     * 查询路径规划起点
     */
    public void startSearchResult() {
        strStart = startTextView.getText().toString().trim();
        if (startPoint != null && strStart.equals("地图上的起点")) {
            endSearchResult();
        } else {
            showProgressDialog();
            startSearchQuery = new PoiSearch.Query(strStart, "", "010"); // 第一个参数表示查询关键字，第二参数表示poi搜索类型，第三个参数表示城市区号或者城市名
            startSearchQuery.setPageNum(0);// 设置查询第几页，第一页从0开始
            startSearchQuery.setPageSize(20);// 设置每页返回多少条数据
            PoiSearch poiSearch = new PoiSearch(RouteActivity.this, startSearchQuery);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.searchPOIAsyn();// 异步poi查询
        }
    }

    /**
     * 查询路径规划终点
     */
    public void endSearchResult() {
        strEnd = endTextView.getText().toString().trim();
        if (endPoint != null && strEnd.equals("地图上的终点")) {
            searchRouteResult(startPoint, endPoint);
        } else {
            showProgressDialog();
            endSearchQuery = new PoiSearch.Query(strEnd, "", "010"); // 第一个参数表示查询关键字，第二参数表示poi搜索类型，第三个参数表示城市区号或者城市名
            endSearchQuery.setPageNum(0);// 设置查询第几页，第一页从0开始
            endSearchQuery.setPageSize(20);// 设置每页返回多少条数据

            PoiSearch poiSearch = new PoiSearch(RouteActivity.this, endSearchQuery);
            poiSearch.setOnPoiSearchListener(this);
            poiSearch.searchPOIAsyn(); // 异步poi查询
        }
    }

    /**
     * 开始搜索路径规划方案
     */
    public void searchRouteResult(LatLonPoint startPoint, LatLonPoint endPoint) {
        showProgressDialog();
        final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
                startPoint, endPoint);
        DriveRouteQuery query = new DriveRouteQuery(fromAndTo, drivingMode, null,
                null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
        routeSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
    }

    /**
     * POI搜索结果回调
     */
    @Override
    public void onPoiSearched(PoiResult result, int rCode) {
        dissmissProgressDialog();
        if (rCode == 0) {// 返回成功
            if (result != null && result.getQuery() != null
                    && result.getPois() != null && result.getPois().size() > 0) {// 搜索poi的结果
                if (result.getQuery().equals(startSearchQuery)) {
                    List<PoiItem> poiItems = result.getPois();// 取得poiitem数据
                    RouteSearchPoiDialog dialog = new RouteSearchPoiDialog(
                            RouteActivity.this, poiItems);
                    dialog.setTitle("您要找的起点是:");
                    dialog.show();
                    dialog.setOnListClickListener(new RouteSearchPoiDialog.OnListItemClick() {
                        @Override
                        public void onListItemClick(RouteSearchPoiDialog dialog,
                                                    PoiItem startpoiItem) {
                            startPoint = startpoiItem.getLatLonPoint();
                            strStart = startpoiItem.getTitle();
                            startTextView.setText(strStart);
                            endSearchResult();// 开始搜终点
                        }

                    });
                } else if (result.getQuery().equals(endSearchQuery)) {
                    List<PoiItem> poiItems = result.getPois();// 取得poiitem数据
                    RouteSearchPoiDialog dialog = new RouteSearchPoiDialog(
                            RouteActivity.this, poiItems);
                    dialog.setTitle("您要找的终点是:");
                    dialog.show();
                    dialog.setOnListClickListener(new RouteSearchPoiDialog.OnListItemClick() {
                        @Override
                        public void onListItemClick(RouteSearchPoiDialog dialog,
                                                    PoiItem endpoiItem) {
                            endPoint = endpoiItem.getLatLonPoint();
                            strEnd = endpoiItem.getTitle();
                            endTextView.setText(strEnd);
                            searchRouteResult(startPoint, endPoint);// 进行路径规划搜索
                        }

                    });
                }
            } else {
                ToastUtil.show(RouteActivity.this, R.string.no_result);
            }
        } else if (rCode == 27) {
            ToastUtil.show(RouteActivity.this, R.string.error_network);
        }
    }

    /**
     * 驾车结果回调
     */
    @Override
    public void onDriveRouteSearched(DriveRouteResult result, int rCode) {
        dissmissProgressDialog();
        if (rCode == 0) {
            if (result != null && result.getPaths() != null
                    && result.getPaths().size() > 0) {
                driveRouteResult = result;
                DrivePath drivePath = driveRouteResult.getPaths().get(0);
                aMap.clear();// 清理地图上的所有覆盖物
                DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(this,
                        aMap, drivePath, driveRouteResult.getStartPos(),
                        driveRouteResult.getTargetPos());
                drivingRouteOverlay.removeFromMap();
                drivingRouteOverlay.addToMap();
                drivingRouteOverlay.zoomToSpan();
            } else {
                ToastUtil.show(RouteActivity.this, R.string.no_result);
            }
        } else if (rCode == 27) {
            ToastUtil.show(RouteActivity.this, R.string.error_network);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imagebtn_roadsearch_startoption:
                startImagePoint();
                break;
            case R.id.imagebtn_roadsearch_endoption:
                endImagePoint();
                break;
            case R.id.imagebtn_roadsearch_search:
                searchRoute();
                break;
            case R.id.leftBtn:
                RouteActivity.this.finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBusRouteSearched(BusRouteResult arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onWalkRouteSearched(WalkRouteResult arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void activate(OnLocationChangedListener arg0) {
        mListener = arg0;
        if (mlocationClient == null) {
            mlocationClient = new AMapLocationClient(this);
            mLocationOption = new AMapLocationClientOption();
            // 设置定位监听
            mlocationClient.setLocationListener(this);
            // 设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
            // 设置定位参数
            mlocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mlocationClient.startLocation();
        }
    }

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
    public void onLocationChanged(AMapLocation amapLocation) {
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                Toast.makeText(this, errText, Toast.LENGTH_LONG);
            }
        }
    }

    public void leftBtn(View leftBtn) {
        finish();
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg)

        {

            MyLocationStyle myLocationStyle = new MyLocationStyle();
            switch (msg.what) {
                case 1:
                    myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                            .fromResource(R.mipmap.heart04));// 设置小蓝点的图标

                    break;
                case 2:
                    myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                            .fromResource(R.mipmap.heart03));// 设置小蓝点的图标

                    break;
                default:
                    break;
            }

            myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
            myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
            // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
            myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
            aMap.setMyLocationStyle(myLocationStyle);
        }

    };

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (is) {
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Message msg2 = new Message();
                msg2.what = 2;
                handler.sendMessage(msg2);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    });
}
