package com.sy.cfproject.gpstools.gpstool;


import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.sy.cfproject.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trust on 2016/12/18.
 */
public class GPSHistory {
    private AMap aMap;
    private List<LatLng> latLngs = new ArrayList<LatLng>();
    private List<LatLng> GpslatLngs = new ArrayList<LatLng>();
    private List<LatLng> MaplatLngs = new ArrayList<LatLng>();



//    private List<LatLng> cacheGPS = new ArrayList<LatLng>();

//    private List<LatLng> cacheMap = new ArrayList<LatLng>();
    private Context context;
    private RegeocodeQuery query;
    private static String name;
    private List<BitmapDescriptor> texTuresList;
    private String startName,endName;
    private boolean  isStop = false;

    public String GPSAddresName = "";

    public String getGPSAddresName() {
        return GPSAddresName;
    }

    public void setGPSAddresName(String GPSAddresName) {
        this.GPSAddresName = GPSAddresName;
    }

    public void setIsStop(boolean isStop)
    {
        this.isStop = isStop;
    }
    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what)
            {
//                case ChunFengMsg.START_LOCATION_NAME:
//
//                        addresIcon(latLngs.get(0),"起点",R.drawable.start,startName);
//                    aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
//                            new LatLng(latLngs.get(0).latitude,latLngs.get(0).longitude),//新的中心点坐标
//                            500, //新的缩放级别
//                            0, //俯仰角0°~45°（垂直与地图时为0）
//                            0  ////偏航角 0~360° (正北方为0)
//                    )));
//
//                    break;
//                case ChunFengMsg.END_LOCATION_NAME:
//                        addresIcon(latLngs.get(latLngs.size()-1),"终点",R.drawable.end,endName);
//                    break;



            }


        }
    };

    public GPSHistory(AMap aMap , Context context ) {
        this.aMap = aMap;
        this.context = context;

        initLiner();


        texTuresList = new ArrayList<BitmapDescriptor>();
//        texTuresList.add(BitmapDescriptorFactory.fromResource(R.drawable.road_1));
    }

    public void setLatLngs(List<LatLng> latLngs) {
        this.latLngs = latLngs;
    }




    private void initLiner() {

    }



    public void startHistory(String startName, String endName)
    {
        aMap.clear();


        aMap.addPolyline(new PolylineOptions().
                addAll(latLngs).width(30).color(Color.parseColor("#020176")).setCustomTextureList(texTuresList));

        centerMark(latLngs.get(0));

         for (int i = 0 ; i<latLngs.size(); i++)
         {
             Log.d("GPSHistory", "坐标  点   :" + latLngs.get(i).latitude + "|" + latLngs.get(i).longitude);
         }

//
        if(startName != null && endName != null)
        {
//            addresIcon(latLngs.get(0),"起点",R.drawable.start,startName);
//            addresIcon(latLngs.get(latLngs.size()-1),"终点",R.drawable.end,endName);
        }


    }


    public void centerMark(LatLng latLng)
    {
        aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
                new LatLng(latLng.latitude,latLng.longitude),//新的中心点坐标
                500, //新的缩放级别
                0, //俯仰角0°~45°（垂直与地图时为0）
                0  ////偏航角 0~360° (正北方为0)
        )));
    }

















    public void hiddenMarker ()
    {
        aMap.clear();
    }


    public void addresIcon (LatLng latLng , String title , int Icon , String msg)
    {
        aMap.addMarker(new MarkerOptions().
                position(latLng).
                title(title).
                snippet(msg).icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(context.getResources(),Icon))));
    }


    public void setName (String startName, String endName)
    {
        this.startName = startName;
        this.endName = endName;

    }


}
