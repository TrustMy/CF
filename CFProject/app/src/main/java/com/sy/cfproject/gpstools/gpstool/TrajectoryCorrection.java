package com.sy.cfproject.gpstools.gpstool;

import android.content.Context;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.LatLng;
import com.amap.api.trace.LBSTraceClient;
import com.amap.api.trace.TraceListener;
import com.amap.api.trace.TraceLocation;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trust on 17/1/10.
 */
public class TrajectoryCorrection implements TraceListener {
    private Context context;

    private List<TraceLocation> mList = new ArrayList<TraceLocation>();
    private List<LatLng> latLing = new ArrayList<>();
    private AMap aMap;
    private CoordinateTransformation coordinateTransformation;
    private String startname,endName;

    private List<LatLng> latLngs;
    public TrajectoryCorrection(Context context, List<LatLng> latLngs , AMap aMap) {
        this.context = context;
        this.latLngs = latLngs;
        this.aMap = aMap; coordinateTransformation = new CoordinateTransformation(context);
        init();

    }

    public void setName(String startName, String endName)
    {
        this.startname = startName;
        this.endName = endName;
    }

    public void init ()
    {
        LBSTraceClient mTraceClient = new LBSTraceClient(context);
        /*
        for (int i = 0; i < carHistoryLocationBean.getContent().getGps().size(); i++) {

                LatLng latLng = coordinateTransformation.transformation(new LatLng(carHistoryLocationBean.getContent().getGps().get(i).getLat(),carHistoryLocationBean.getContent().getGps().get(i).getLng()));

                mList.add(new TraceLocation(latLng.latitude,latLng.longitude, 1, 1, 1));
                latLing.add(new LatLng(latLng.latitude,latLng.longitude));


//
        }
        */
        for (int i = 0; i <latLngs.size() ; i++) {
            LatLng latLng = coordinateTransformation.transformation(new LatLng(latLngs.get(i).latitude,latLngs.get(i).longitude));

            mList.add(new TraceLocation(latLngs.get(i).latitude,latLngs.get(i).longitude, 1, 1, 1));
            latLing.add(new LatLng(latLng.latitude,latLng.longitude));

        }

        mTraceClient.queryProcessedTrace(1, mList, LBSTraceClient.TYPE_AMAP, this);

    }

    @Override
    public void onRequestFailed(int i, String s) {

//        GPSHistory mHistory = new GPSHistory(aMap,context);
//        mHistory.setLatLngs(latLing);
//        mHistory.startHistory(startname,endName);
    }

    @Override
    public void onTraceProcessing(int i, int i1, List<LatLng> list) {

    }

    @Override
    public void onFinished(int num, List<LatLng> list, int i1, int i2) {
        if(list.size() != 0)
        {
            List<LatLng> test = new ArrayList<>();
            for (int i = list.size()-1; i >=0 ; i--) {
                test.add(list.get(i));
            }


            GPSHistory mHistory = new GPSHistory(aMap,context);
            mHistory.setLatLngs(test);
            mHistory.startHistory(startname,endName);


        }
    }
}
