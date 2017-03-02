package com.sy.cfproject.gpstools;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.sy.cfproject.R;

/**
 * Created by Trust on 2017/3/2.
 */
public class Mark {

    public static boolean showMark(Context context, AMap aMap, LatLng latLng,String name)
    {
        if(latLng != null && latLng.longitude!= 0.0)
        {
            aMap.addMarker(new MarkerOptions().
                    position(latLng).
                    title("当前位置:")
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(context.getResources(),
                                    R.mipmap.ic_launcher))).
                            snippet(name)).showInfoWindow();

//            aMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(
//                    new LatLng(latLng.latitude,latLng.longitude),//新的中心点坐标
//                    15, //新的缩放级别
//                    0, //俯仰角0°~45°（垂直与地图时为0）
//                    0  ////偏航角 0~360° (正北方为0)
//            )));

            return true;
        }else
        {
            return false;
        }

    }
}
