package com.sy.cfproject.acyivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.sy.cfproject.BaseActivity;
import com.sy.cfproject.R;
import com.sy.cfproject.tool.gpstool.AMapTool;

public class CarLocationActivity extends BaseActivity {
    private Context context = CarLocationActivity.this;
    private MapView mMapView;
    private AMap aMap;
   private LatLng myLatLng;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;


    private Button myLocationBtn;

    private LocationSource.OnLocationChangedListener  mListener;

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {

              myLatLng = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());

                AMapTool.showMark(context,aMap,myLatLng,R.mipmap.ic_launcher,"坐标",
                        aMapLocation.getAddress(),true);
//                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点

            } else {

            }
        }

    };


    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_location);

        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.car_location_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        initMap();

    }

    private void initMap() {
        if (aMap == null) {
            aMap = mMapView.getMap();
        }


//        MyLocationStyle myLocationStyle = new MyLocationStyle();
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
//                .fromResource(R.mipmap.ic_launcher));// 设置小蓝点的图标
//        myLocationStyle.strokeColor(Color.parseColor("#5fff0000"));// 设置圆形的边框颜色
//        myLocationStyle.radiusFillColor(Color.parseColor("#5fff00ff"));// 设置圆形的填充颜色
//        myLocationStyle.strokeWidth(1f);// 设置圆形的边框粗细
//        aMap.setMyLocationStyle(myLocationStyle);


        /*
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        // 设置定位的类型为定位模式，有定位、跟随或地图根据面向方向旋转几种
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        */

        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());


        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);

        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        mLocationOption.setLocationMode(
                AMapLocationClientOption.AMapLocationMode.Hight_Accuracy); //高精度定位
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，
        // 启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，
        // setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        mLocationOption.setHttpTimeOut(20000); //设置定位最大时间

        //启动定位
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();





    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，
        // 保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

}
