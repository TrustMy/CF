package com.sy.cfproject.acyivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.google.gson.Gson;
import com.sy.cfproject.BaseActivity;
import com.sy.cfproject.R;
import com.sy.cfproject.bean.CarTrajectoryBean;
import com.sy.cfproject.tool.gpstool.CoordinateTransformation;
import com.sy.cfproject.tool.gpstool.GPSHistoryLine;

import java.util.ArrayList;
import java.util.List;

public class CarTrajectoryActivity extends BaseActivity {
    private MapView mapView;
    private AMap aMap;

    private Context context = CarTrajectoryActivity.this;

    private CoordinateTransformation coordinateTransformation;

    private String testDate = "{\"content\":{\"totalElements\":109,\"gps\"" +
            ":[{\"gpsTime\":1484547022000,\"engine\":0,\"station\":31241,\"" +
            "block\":22541,\"lng\":120.245552,\"acc\":0,\"termId\":862180034558954,\"" +
            "course\":189,\"voltage\":0,\"fix\":1,\"alt\":11.6,\"gpsSpeed\":0.5,\"" +
            "rssi\":30,\"lat\":30.440264},{\"gpsTime\":1484547022000,\"engine\":0,\"" +
            "station\":31241,\"block\":22541,\"lng\":120.245552,\"acc\":0,\"termId\"" +
            ":862180034558954,\"course\":189,\"voltage\":0,\"fix\":1,\"alt\":11.6,\"" +
            "gpsSpeed\":0.5,\"rssi\":26,\"lat\":30.440264},{\"gpsTime\"" +
            ":1484547022000,\"engine\":0,\"station\":11241,\"block\":22541,\"lng\"" +
            ":120.245552,\"acc\":0,\"termId\":862180034558954,\"course\":189,\"" +
            "voltage\":0,\"fix\":1,\"alt\":11.6,\"gpsSpeed\":0.5,\"rssi\":25,\"" +
            "lat\":30.440264},{\"gpsTime\":1484547020000,\"engine\":0,\"station\"" +
            ":60727,\"block\":22541,\"lng\":120.245544,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":189,\"voltage\":0,\"fix\":1,\"alt\":11.8,\"" +
            "gpsSpeed\":0.2,\"rssi\":25,\"lat\":30.440264},{\"gpsTime\"" +
            ":1484547016000,\"engine\":0,\"station\":60727,\"block\":22541,\"lng\"" +
            ":120.245536,\"acc\":1,\"termId\":862180034558954,\"course\":236,\"" +
            "voltage\":0,\"fix\":1,\"alt\":12,\"gpsSpeed\":0.9,\"rssi\":24,\"lat\"" +
            ":30.440264},{\"gpsTime\":1484547012000,\"engine\":0,\"station\":60727,\"" +
            "block\":22541,\"lng\":120.245544,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":236,\"voltage\":0,\"fix\":1,\"alt\":12.8,\"gpsSpeed\":0,\"" +
            "rssi\":24,\"lat\":30.440268},{\"gpsTime\":1484547008000,\"engine\"" +
            ":0,\"station\":60727,\"block\":22541,\"lng\":120.245552,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":236,\"voltage\":0,\"fix\":1,\"" +
            "alt\":13.3,\"gpsSpeed\":4.2,\"rssi\":25,\"lat\":30.440264},{\"gpsTime\"" +
            ":1484547004000,\"engine\":0,\"station\":60727,\"block\":22541,\"lng\"" +
            ":120.245672,\"acc\":1,\"termId\":862180034558954,\"course\":241,\"" +
            "voltage\":0,\"fix\":1,\"alt\":11,\"gpsSpeed\":20.6,\"rssi\":18,\"lat\"" +
            ":30.440332},{\"gpsTime\":1484547000000,\"engine\":0,\"station\"" +
            ":60727,\"block\":22541,\"lng\":120.245888,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":241,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":10.4,\"gpsSpeed\":20.3,\"rssi\":18,\"lat\":30.440436},{\"gpsTime\"" +
            ":1484546996000,\"engine\":0,\"station\":60727,\"block\":22541,\"lng\"" +
            ":120.24612,\"acc\":1,\"termId\":862180034558954,\"course\":242,\"" +
            "voltage\":0,\"fix\":1,\"alt\":10.5,\"gpsSpeed\":21,\"rssi\":17,\"lat\"" +
            ":30.44054},{\"gpsTime\":1484546992000,\"engine\":0,\"station\":60727,\"" +
            "block\":22541,\"lng\":120.246296,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":290,\"voltage\":0,\"fix\":1,\"alt\":9.8,\"" +
            "gpsSpeed\":14.8,\"rssi\":19,\"lat\":30.440604},{\"gpsTime\"" +
            ":1484546989000,\"engine\":0,\"station\":30727,\"block\":22541,\"lng\"" +
            ":120.246408,\"acc\":1,\"termId\":862180034558954,\"course\":325,\"" +
            "voltage\":0,\"fix\":1,\"alt\":9,\"gpsSpeed\":16.4,\"rssi\":20,\"" +
            "lat\":30.440526},{\"gpsTime\":1484546985000,\"engine\":0,\"station\"" +
            ":30727,\"block\":22541,\"lng\":120.246528,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":304,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":9.6,\"gpsSpeed\":14.1,\"rssi\":20,\"lat\":30.44039},{\"gpsTime\"" +
            ":1484546981000,\"engine\":0,\"station\":30727,\"block\":22541,\"" +
            "lng\":120.246664,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":295,\"voltage\":0,\"fix\":1,\"alt\":9.5,\"gpsSpeed\":8.5,\"rssi\"" +
            ":18,\"lat\":30.440328},{\"gpsTime\":1484546977000,\"engine\":0,\"" +
            "station\":30727,\"block\":22541,\"lng\":120.246696,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":319,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":9.2,\"gpsSpeed\":0.3,\"rssi\":17,\"lat\":30.440316},{\"" +
            "gpsTime\":1484546973000,\"engine\":0,\"station\":30727,\"block\"" +
            ":22541,\"lng\":120.246696,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":319,\"voltage\":0,\"fix\":1,\"alt\":8.8,\"gpsSpeed\"" +
            ":0.1,\"rssi\":19,\"lat\":30.440316},{\"gpsTime\":1484546969000,\"" +
            "engine\":0,\"station\":30727,\"block\":22541,\"lng\":120.246704," +
            "acc\":1,\"termId\":862180034558954,\"course\":319,\"voltage\":0,\"" +
            "fix\":1,\"alt\":7.9,\"gpsSpeed\":0.1,\"rssi\":19,\"lat\"" +
            ":30.440314},{\"gpsTime\":1484546965000,\"engine\":0,\"station\"" +
            ":30727,\"block\":22541,\"lng\":120.246696,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":319,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":6.7,\"gpsSpeed\":0,\"rssi\":18,\"lat\":30.44031},{\"gpsTime\"" +
            ":1484546961000,\"engine\":0,\"station\":30727,\"block\":22541,\"" +
            "lng\":120.246696,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":319,\"voltage\":0,\"fix\":1,\"alt\":6.7,\"gpsSpeed\":2.4,\"rssi\"" +
            ":21,\"lat\":30.440308},{\"gpsTime\":1484546957000,\"engine\":0,\"" +
            "station\":30727,\"block\":22541,\"lng\":120.24676,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":352,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":6.1,\"gpsSpeed\":9.1,\"rssi\":19,\"lat\":30.44024},{\"" +
            "gpsTime\":1484546953000,\"engine\":0,\"station\":30727,\"block\"" +
            ":22541,\"lng\":120.246744,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":24,\"voltage\":0,\"fix\":1,\"alt\":6.2,\"gpsSpeed\"" +
            ":14.8,\"rssi\":16,\"lat\":30.440126},{\"gpsTime\"" +
            ":1484546949000,\"engine\":0,\"station\":30727,\"block\":22541,\"" +
            "lng\":120.246632,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":36,\"voltage\":0,\"fix\":1,\"alt\":6,\"gpsSpeed\":21,\"rssi\"" +
            ":16,\"lat\":30.439976},{\"gpsTime\":1484546945000,\"engine\":0,\"" +
            "station\":30727,\"block\":22541,\"lng\":120.246456,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":45,\"voltage\":0,\"fix\":1,\"" +
            "alt\":5.8,\"gpsSpeed\":26.7,\"rssi\":16,\"lat\":30.439796},{\"" +
            "gpsTime\":1484546942000,\"engine\":0,\"station\":30727,\"block\"" +
            ":22541,\"lng\":120.246264,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":60,\"voltage\":0,\"fix\":1,\"alt\":5.9,\"gpsSpeed\":31,\"" +
            "rssi\":22,\"lat\":30.439666},{\"gpsTime\":1484546938000,\"engine\"" +
            ":0,\"station\":30727,\"block\":22541,\"lng\":120.245936,\"acc\"" +
            ":1,\"termId\":862180034558954,\"course\":62,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":6.3,\"gpsSpeed\":31.1,\"rssi\":25,\"lat\":30.439518},{\"" +
            "gpsTime\":1484546934000,\"engine\":0,\"station\":30727,\"block\"" +
            ":22541,\"lng\":120.245624,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":75,\"voltage\":0,\"fix\":1,\"alt\":6.7,\"gpsSpeed\":26.7,\"" +
            "rssi\":25,\"lat\":30.439394},{\"gpsTime\":1484546930000,\"engine\"" +
            ":0,\"station\":30727,\"block\":22541,\"lng\":120.24536,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":113,\"voltage\":0,\"fix\":1,\"" +
            "alt\":7,\"gpsSpeed\":19.8,\"rssi\":30,\"lat\":30.439392},{\"gpsTime\"" +
            ":1484546926000,\"engine\":0,\"station\":30727,\"block\":22541,\"lng\"" +
            ":120.245184,\"acc\":1,\"termId\":862180034558954,\"course\":150,\"" +
            "voltage\":0,\"fix\":1,\"alt\":7.1,\"gpsSpeed\":23.4,\"rssi\":22,\"" +
            "lat\":30.439522},{\"gpsTime\":1484546922000,\"engine\":0,\"" +
            "station\":30727,\"block\":22541,\"lng\":120.245048,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":154,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":6.5,\"gpsSpeed\":28.8,\"rssi\":22,\"lat\"" +
            ":30.439744},{\"gpsTime\":1484546918000,\"engine\":0,\"station\"" +
            ":30727,\"block\":22541,\"lng\":120.244888,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":151,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":6,\"gpsSpeed\":32.2,\"rssi\":27,\"lat\":30.440014},{\"gpsTime\"" +
            ":1484546914000,\"engine\":0,\"station\":30727,\"block\":22541,\"" +
            "lng\":120.244704,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":152,\"voltage\":0,\"fix\":1,\"alt\":5.7,\"gpsSpeed\":33.1,\"rssi\"" +
            ":27,\"lat\":30.4403},{\"gpsTime\":1484546910000,\"engine\":0,\"" +
            "station\":30727,\"block\":22541,\"lng\":120.244528,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":153,\"voltage\":0,\"fix\":1,\"" +
            "alt\":5.7,\"gpsSpeed\":33,\"rssi\":26,\"lat\":30.440596},{\"" +
            "gpsTime\":1484546906000,\"engine\":0,\"station\":30727,\"block\"" +
            ":22541,\"lng\":120.244352,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":151,\"voltage\":0,\"fix\":1,\"alt\":5.6,\"gpsSpeed\"" +
            ":32.8,\"rssi\":21,\"lat\":30.440894},{\"gpsTime\":1484546902000,\"" +
            "engine\":0,\"station\":30727,\"block\":22541,\"lng\"" +
            ":120.244176,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":154,\"voltage\":0,\"fix\":1,\"alt\":5.6,\"gpsSpeed\":32.8,\"" +
            "rssi\":24,\"lat\":30.441188},{\"gpsTime\":1484546898000,\"" +
            "engine\":0,\"station\":30727,\"block\":22541,\"lng\"" +
            ":120.244008,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":152,\"voltage\":0,\"fix\":1,\"alt\":4.9,\"gpsSpeed\":32.4,\"" +
            "rssi\":24,\"lat\":30.441482},{\"gpsTime\":1484546895000,\"engine\"" +
            ":0,\"station\":30727,\"block\":22541,\"lng\":120.24388,\"acc\"" +
            ":1,\"termId\":862180034558954,\"course\":152,\"voltage\":0,\"" +
            "fix\":1,\"alt\":4.7,\"gpsSpeed\":31.6,\"rssi\":29,\"lat\"" +
            ":30.441696},{\"gpsTime\":1484546891000,\"engine\":0,\"station\"" +
            ":30727,\"block\":22541,\"lng\":120.243712,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":153,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":4.9,\"gpsSpeed\":30.3,\"rssi\":29,\"lat\":30.441972},{\"" +
            "gpsTime\":1484546887000,\"engine\":0,\"station\":30727,\"block\"" +
            ":22541,\"lng\":120.243552,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":151,\"voltage\":0,\"fix\":1,\"alt\":5.6,\"gpsSpeed\"" +
            ":30.7,\"rssi\":29,\"lat\":30.44224},{\"gpsTime\":1484546883000,\"" +
            "engine\":0,\"station\":30727,\"block\":22541,\"lng\"" +
            ":120.243392,\"acc\":1,\"termId\":862180034558954,\"course\":153,\"" +
            "voltage\":0,\"fix\":1,\"alt\":6.3,\"gpsSpeed\":27.2,\"rssi\"" +
            ":31,\"lat\":30.442508},{\"gpsTime\":1484546879000,\"engine\":0,\"" +
            "station\":30727,\"block\":22541,\"lng\":120.243256,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":151,\"voltage\":0,\"fix\":1,\"" +
            "alt\":6.9,\"gpsSpeed\":27,\"rssi\":31,\"lat\":30.442742},{\"" +
            "gpsTime\":1484546875000,\"engine\":0,\"station\":30727,\"block\"" +
            ":22541,\"lng\":120.243144,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":161,\"voltage\":0,\"fix\":1,\"alt\":7.1,\"gpsSpeed\"" +
            ":17,\"rssi\":28,\"lat\":30.44295},{\"gpsTime\":1484546871000,\"" +
            "engine\":0,\"station\":30727,\"block\":22541,\"lng\":120.243128,\"" +
            "acc\":1,\"termId\":862180034558954,\"course\":203,\"voltage\"" +
            ":0,\"fix\":1,\"alt\":7.1,\"gpsSpeed\":15.5,\"rssi\":22,\"lat\"" +
            ":30.44309},{\"gpsTime\":1484546867000,\"engine\":0,\"station\"" +
            ":30727,\"block\":22541,\"lng\":120.243192,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":217,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":6.7,\"gpsSpeed\":7.6,\"rssi\":22,\"lat\":30.443222},{\"gpsTime\"" +
            ":1484546863000,\"engine\":0,\"station\":30727,\"block\":22541,\"" +
            "lng\":120.243208,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":240,\"voltage\":0,\"fix\":1,\"alt\":6.7,\"gpsSpeed\":0,\"rssi\"" +
            ":21,\"lat\":30.44324},{\"gpsTime\":1484546859000,\"engine\":0,\"" +
            "station\":30727,\"block\":22541,\"lng\":120.243208,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":240,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":6.6,\"gpsSpeed\":0,\"rssi\":21,\"lat\":30.44324},{\"" +
            "gpsTime\":1484546856000,\"engine\":0,\"station\":30727,\"block\"" +
            ":22541,\"lng\":120.243216,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":240,\"voltage\":0,\"fix\":1,\"alt\":6.6,\"gpsSpeed\"" +
            ":8.2,\"rssi\":21,\"lat\":30.443242},{\"gpsTime\":1484546852000,\"" +
            "engine\":0,\"station\":30727,\"block\":22541,\"lng\":120.243376,\"" +
            "acc\":1,\"termId\":862180034558954,\"course\":240,\"voltage\":0,\"" +
            "fix\":1,\"alt\":6.5,\"gpsSpeed\":20.4,\"rssi\":19,\"lat\"" +
            ":30.443324},{\"gpsTime\":1484546848000,\"engine\":0,\"station\"" +
            ":30727,\"block\":22541,\"lng\":120.243624,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":239,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":6.7,\"gpsSpeed\":29.3,\"rssi\":17,\"lat\":30.443442},{\"gpsTime\"" +
            ":1484546844000,\"engine\":0,\"station\":30727,\"block\":22541,\"" +
            "lng\":120.243952,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":243,\"voltage\":0,\"fix\":1,\"alt\":6.4,\"gpsSpeed\":35.9,\"" +
            "rssi\":17,\"lat\":30.443606},{\"gpsTime\":1484546840000,\"" +
            "engine\":0,\"station\":30727,\"block\":22541,\"lng\"" +
            ":120.244352,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":243,\"voltage\":0,\"fix\":1,\"alt\":6.2,\"gpsSpeed\":38.7,\"" +
            "rssi\":16,\"lat\":30.44378},{\"gpsTime\":1484546836000,\"engine\"" +
            ":0,\"station\":30727,\"block\":22541,\"lng\":120.244744,\"acc\"" +
            ":1,\"termId\":862180034558954,\"course\":242,\"voltage\":0,\"" +
            "fix\":1,\"alt\":6.2,\"gpsSpeed\":37.2,\"rssi\":19,\"lat\"" +
            ":30.443948},{\"gpsTime\":1484546832000,\"engine\":0,\"station\"" +
            ":11241,\"block\":22541,\"lng\":120.24512,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":242,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":6.4,\"gpsSpeed\":35,\"rssi\":19,\"lat\":30.444116},{\"gpsTime\"" +
            ":1484546828000,\"engine\":0,\"station\":11241,\"block\":22541,\"" +
            "lng\":120.245472,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":242,\"voltage\":0,\"fix\":1,\"alt\":6.4,\"gpsSpeed\":33.2,\"rssi\"" +
            ":19,\"lat\":30.444274},{\"gpsTime\":1484546824000,\"engine\":0,\"" +
            "station\":11241,\"block\":22541,\"lng\":120.245832,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":242,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":6.4,\"gpsSpeed\":36.8,\"rssi\":19,\"lat\"" +
            ":30.444436},{\"gpsTime\":1484546820000,\"engine\":0,\"station\"" +
            ":11241,\"block\":22541,\"lng\":120.246208,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":242,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":6.2,\"gpsSpeed\":35.6,\"rssi\":20,\"lat\":30.4446},{\"gpsTime\"" +
            ":1484546816000,\"engine\":0,\"station\":11241,\"block\":22541,\"" +
            "lng\":120.24656,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":243,\"voltage\":0,\"fix\":1,\"alt\":6.2,\"gpsSpeed\":32.4,\"rssi\"" +
            ":19,\"lat\":30.444762},{\"gpsTime\":1484546812000,\"engine\":0,\"" +
            "station\":11241,\"block\":22541,\"lng\":120.24688,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":249,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":6.8,\"gpsSpeed\":25.5,\"rssi\":21,\"lat\"" +
            ":30.444884},{\"gpsTime\":1484546809000,\"engine\":0,\"" +
            "station\":11241,\"block\":22541,\"lng\":120.247104,\"acc\"" +
            ":1,\"termId\":862180034558954,\"course\":256,\"voltage\"" +
            ":0,\"fix\":1,\"alt\":6.6,\"gpsSpeed\":28.9,\"rssi\":22,\"" +
            "lat\":30.44495},{\"gpsTime\":1484546805000,\"engine\":0,\"station\"" +
            ":11241,\"block\":22541,\"lng\":120.247408,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":291,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":6.8,\"gpsSpeed\":24.2,\"rssi\":22,\"lat\":30.44495},{\"gpsTime\"" +
            ":1484546801000,\"engine\":0,\"station\":11241,\"block\":22541,\"" +
            "lng\":120.2476,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":320,\"voltage\":0,\"fix\":1,\"alt\":7.1,\"gpsSpeed\":19.1,\"" +
            "rssi\":22,\"lat\":30.44483},{\"gpsTime\":1484546797000,\"engine\"" +
            ":0,\"station\":11241,\"block\":22541,\"lng\":120.247712,\"acc\"" +
            ":1,\"termId\":862180034558954,\"course\":332,\"voltage\":0,\"" +
            "fix\":1,\"alt\":7.3,\"gpsSpeed\":14.3,\"rssi\":21,\"lat\"" +
            ":30.444684},{\"gpsTime\":1484546793000,\"engine\":0,\"station\"" +
            ":11241,\"block\":22541,\"lng\":120.247824,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":331,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":7.4,\"gpsSpeed\":25.1,\"rssi\":21,\"lat\":30.4445},{\"gpsTime\"" +
            ":1484546789000,\"engine\":0,\"station\":11241,\"block\":22541,\"" +
            "lng\":120.247968,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":331,\"voltage\":0,\"fix\":1,\"alt\":7.7,\"gpsSpeed\":29.4,\"" +
            "rssi\":21,\"lat\":30.444258},{\"gpsTime\":1484546785000,\"" +
            "engine\":0,\"station\":11241,\"block\":22541,\"lng\"" +
            ":120.248136,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":332,\"voltage\":0,\"fix\":1,\"alt\":8.2,\"gpsSpeed\":35.8,\"" +
            "rssi\":23,\"lat\":30.443982},{\"gpsTime\":1484546781000,\"" +
            "engine\":0,\"station\":11241,\"block\":22541,\"lng\"" +
            ":120.248344,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":332,\"voltage\":0,\"fix\":1,\"alt\":7.6,\"gpsSpeed\":39,\"" +
            "rssi\":23,\"lat\":30.443646},{\"gpsTime\":1484546777000,\"" +
            "engine\":0,\"station\":11241,\"block\":22541,\"lng\":120.248544,\"" +
            "acc\":1,\"termId\":862180034558954,\"course\":331,\"voltage\"" +
            ":0,\"fix\":1,\"alt\":7.2,\"gpsSpeed\":38.5,\"rssi\":23,\"lat\"" +
            ":30.443294},{\"gpsTime\":1484546773000,\"engine\":0,\"station\"" +
            ":11241,\"block\":22541,\"lng\":120.24876,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":332,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":6.8,\"gpsSpeed\":38.6,\"rssi\":22,\"lat\":30.442954},{\"gpsTime" +
            "\":1484546769000,\"engine\":0,\"station\":11241,\"block\":22541,\"" +
            "lng\":120.248952,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":332,\"voltage\":0,\"fix\":1,\"alt\":6.7,\"gpsSpeed\":34.8,\"rssi" +
            "\":22,\"lat\":30.442622},{\"gpsTime\":1484546766000,\"engine\":0,\"" +
            "station\":11241,\"block\":22541,\"lng\":120.249088,\"acc\":1,\"termId" +
            "\":862180034558954,\"course\":333,\"voltage\":0,\"fix\":1,\"alt\":6.6,\"" +
            "gpsSpeed\":29.9,\"rssi\":22,\"lat\":30.442402},{\"gpsTime\"" +
            ":1484546762000,\"engine\":0,\"station\":11241,\"block\":22541,\"" +
            "lng\":120.249256,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":332,\"voltage\":0,\"fix\":1,\"alt\":6.7,\"gpsSpeed\":32.4,\"" +
            "rssi\":31,\"lat\":30.44212},{\"gpsTime\":1484546758000,\"" +
            "engine\":0,\"station\":11241,\"block\":22541,\"lng\":120.249408,\"" +
            "acc\":1,\"termId\":862180034558954,\"course\":332,\"voltage\"" +
            ":0,\"fix\":1,\"alt\":6.8,\"gpsSpeed\":28,\"rssi\":31,\"lat\"" +
            ":30.441844},{\"gpsTime\":1484546754000,\"engine\":0,\"station\"" +
            ":11241,\"block\":22541,\"lng\":120.249552,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":339,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":7,\"gpsSpeed\":28.6,\"rssi\":31,\"lat\":30.44157},{\"gpsTime\"" +
            ":1484546750000,\"engine\":0,\"station\":11241,\"block\":22541,\"" +
            "lng\":120.249584,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":12,\"voltage\":0,\"fix\":1,\"alt\":7.1,\"gpsSpeed\":15.2,\"rssi\"" +
            ":31,\"lat\":30.441358},{\"gpsTime\":1484546746000,\"engine\":0,\"" +
            "station\":11241,\"block\":22541,\"lng\":120.249536,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":21,\"voltage\":0,\"fix\":1,\"" +
            "alt\":7.4,\"gpsSpeed\":16.2,\"rssi\":30,\"lat\":30.441228},{\"" +
            "gpsTime\":1484546742000,\"engine\":0,\"station\":11241,\"block\"" +
            ":22541,\"lng\":120.24948,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":57,\"voltage\":0,\"fix\":1,\"alt\":7.4,\"gpsSpeed\":2.1,\"" +
            "rssi\":31,\"lat\":30.44113},{\"gpsTime\":1484546738000,\"engine\"" +
            ":0,\"station\":11241,\"block\":22541,\"lng\":120.249472,\"acc\"" +
            ":1,\"termId\":862180034558954,\"course\":61,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":7.5,\"gpsSpeed\":0.1,\"rssi\":31,\"lat\"" +
            ":30.441126},{\"gpsTime\":1484546734000,\"engine\":0,\"station\"" +
            ":11241,\"block\":22541,\"lng\":120.249472,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":61,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":7.7,\"gpsSpeed\":0,\"rssi\":31,\"lat\":30.441126},{\"gpsTime\"" +
            ":1484546730000,\"engine\":0,\"station\":11241,\"block\":22541,\"" +
            "lng\":120.249472,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":61,\"voltage\":0,\"fix\":1,\"alt\":7.9,\"gpsSpeed\":0,\"rssi\"" +
            ":24,\"lat\":30.441126},{\"gpsTime\":1484546726000,\"engine\":0,\"" +
            "station\":11241,\"block\":22541,\"lng\":120.249472,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":61,\"voltage\":0,\"fix\":1,\"alt\":8," +
            "\"gpsSpeed\":0,\"rssi\":30,\"lat\":30.441128},{\"gpsTime\":1484546723000," +
            "\"engine\":0,\"station\":11241,\"block\":22541,\"lng\":120.249472,\"acc\"" +
            ":1,\"termId\":862180034558954,\"course\":61,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":8.1,\"gpsSpeed\":0.1,\"rssi\":30,\"lat\":30.441128},{\"gpsTime\"" +
            ":1484546719000,\"engine\":0,\"station\":11241,\"block\":22541,\"" +
            "lng\":120.249456,\"acc\":1,\"termId\":862180034558954,\"course\"" +
            ":61,\"voltage\":0,\"fix\":1,\"alt\":8.1,\"gpsSpeed\":6.5,\"rssi\"" +
            ":30,\"lat\":30.44112},{\"gpsTime\":1484546715000,\"engine\":0,\"" +
            "station\":11241,\"block\":22541,\"lng\":120.249352,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":60,\"voltage\":0,\"fix\":1,\"" +
            "alt\":8,\"gpsSpeed\":14.6,\"rssi\":30,\"lat\":30.441072},{\"" +
            "gpsTime\":1484546711000,\"engine\":0,\"station\":11241,\"block\"" +
            ":22541,\"lng\":120.24916,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":59,\"voltage\":0,\"fix\":1,\"alt\":8,\"gpsSpeed\":23.2,\"" +
            "rssi\":31,\"lat\":30.44098},{\"gpsTime\":1484546707000,\"engine\"" +
            ":0,\"station\":11241,\"block\":22541,\"lng\":120.248904,\"acc\"" +
            ":1,\"termId\":862180034558954,\"course\":54,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":7.8,\"gpsSpeed\":29.2,\"rssi\":31,\"lat\":30.440826},{\"" +
            "gpsTime\":1484546703000,\"engine\":0,\"station\":11241,\"block\"" +
            ":22541,\"lng\":120.2486,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":63,\"voltage\":0,\"fix\":1,\"alt\":7.9,\"gpsSpeed\":30.6,\"" +
            "rssi\":31,\"lat\":30.440682},{\"gpsTime\":1484546699000,\"" +
            "engine\":0,\"station\":11241,\"block\":22541,\"lng\":120.24828,\"" +
            "acc\":1,\"termId\":862180034558954,\"course\":63,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":8.4,\"gpsSpeed\":32.9,\"rssi\":31,\"lat\":30.44055},{\"" +
            "gpsTime\":1484546695000,\"engine\":0,\"station\":11241,\"block\":22541," +
            "\"lng\":120.247952,\"acc\":1,\"termId\":862180034558954,\"course\":66,\"" +
            "voltage\":0,\"fix\":1,\"alt\":8.8,\"gpsSpeed\":29.9,\"rssi\":31,\"lat\"" +
            ":30.44041},{\"gpsTime\":1484546691000,\"engine\":0,\"station\":11241,\"" +
            "block\":22541,\"lng\":120.247648,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":65,\"voltage\":0,\"fix\":1,\"alt\":9.2,\"gpsSpeed\":28.6,\"" +
            "rssi\":31,\"lat\":30.440298},{\"gpsTime\":1484546687000,\"engine\":0,\"" +
            "station\":11241,\"block\":22541,\"lng\":120.247344,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":67,\"voltage\":0,\"fix\":1,\"alt\":9.7,\"" +
            "gpsSpeed\":26,\"rssi\":26,\"lat\":30.440186},{\"gpsTime\"" +
            ":1484546683000,\"engine\":0,\"station\":11241,\"block\":22541,\"lng\"" +
            ":120.247112,\"acc\":1,\"termId\":862180034558954,\"course\":78,\"" +
            "voltage\":0,\"fix\":1,\"alt\":10.5,\"gpsSpeed\":17.7,\"rssi\":26,\"" +
            "lat\":30.440104},{\"gpsTime\":1484546679000,\"engine\":0,\"station\"" +
            ":31241,\"block\":22541,\"lng\":120.24692,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":116,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":11.2,\"gpsSpeed\":15.1,\"rssi\":26,\"lat\":30.440096},{\"gpsTime\"" +
            ":1484546676000,\"engine\":0,\"station\":31241,\"block\":22541,\"lng\"" +
            ":120.246824,\"acc\":1,\"termId\":862180034558954,\"course\":150,\"" +
            "voltage\":0,\"fix\":1,\"alt\":12.1,\"gpsSpeed\":13.8,\"rssi\":26,\"" +
            "lat\":30.440156},{\"gpsTime\":1484546672000,\"engine\":0,\"station\"" +
            ":31241,\"block\":22541,\"lng\":120.246776,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":163,\"voltage\":0,\"fix\":1,\"alt\":11.5,\"" +
            "gpsSpeed\":3.8,\"rssi\":30,\"lat\":30.440254},{\"gpsTime\"" +
            ":1484546668000,\"engine\":0,\"station\":31241,\"block\":22541,\"lng\"" +
            ":120.246768,\"acc\":1,\"termId\":862180034558954,\"course\":141,\"" +
            "voltage\":0,\"fix\":1,\"alt\":11.1,\"gpsSpeed\":1,\"rssi\":31,\"lat\"" +
            ":30.440274},{\"gpsTime\":1484546664000,\"engine\":0,\"station\":31241,\"" +
            "block\":22541,\"lng\":120.246768,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":141,\"voltage\":0,\"fix\":1,\"alt\":11.4,\"gpsSpeed\":0.4,\"rssi\"" +
            ":28,\"lat\":30.440276},{\"gpsTime\":1484546660000,\"engine\":0,\"station\"" +
            ":31241,\"block\":22541,\"lng\":120.246776,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":141,\"voltage\":0,\"fix\":1,\"alt\":11.4,\"" +
            "gpsSpeed\":0,\"rssi\":28,\"lat\":30.440276},{\"gpsTime\":1484546656000,\"" +
            "engine\":0,\"station\":31241,\"block\":22541,\"lng\":120.246768,\"acc\"" +
            ":1,\"termId\":862180034558954,\"course\":141,\"voltage\":0,\"fix\":1,\"" +
            "alt\":11.5,\"gpsSpeed\":2,\"rssi\":31,\"lat\":30.44028},{\"gpsTime\"" +
            ":1484546652000,\"engine\":0,\"station\":31241,\"block\":22541,\"lng\"" +
            ":120.246752,\"acc\":1,\"termId\":862180034558954,\"course\":135,\"" +
            "voltage\":0,\"fix\":1,\"alt\":11.4,\"gpsSpeed\":3.9,\"rssi\":28,\"" +
            "lat\":30.440302},{\"gpsTime\":1484546648000,\"engine\":0,\"station\"" +
            ":31241,\"block\":22541,\"lng\":120.246704,\"acc\":1,\"termId\"" +
            ":862180034558954,\"course\":132,\"voltage\":0,\"fix\":1,\"alt\":11.5,\"" +
            "gpsSpeed\":9.4,\"rssi\":28,\"lat\":30.440346},{\"gpsTime\":1484546644000,\"" +
            "engine\":0,\"station\":31241,\"block\":22541,\"lng\":120.246576,\"acc\":1,\"" +
            "termId\":862180034558954,\"course\":131,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":11.7,\"gpsSpeed\":18.9,\"rssi\":26,\"lat\":30.440438},{\"gpsTime\"" +
            ":1484546640000,\"engine\":0,\"station\":31241,\"block\":22541,\"lng\"" +
            ":120.24644,\"acc\":1,\"termId\":862180034558954,\"course\":134,\"voltage\"" +
            ":0,\"fix\":1,\"alt\":12.5,\"gpsSpeed\":18.1,\"rssi\":26,\"lat\":30.440572}" +
            ",{\"gpsTime\":1484546636000,\"engine\":0,\"station\":31241,\"block\"" +
            ":22541,\"lng\":120.246296,\"acc\":1,\"termId\":862180034558954,\"" +
            "course\":71,\"voltage\":0,\"fix\":1,\"alt\":10.9,\"gpsSpeed\":12.8,\"" +
            "rssi\":24,\"lat\":30.440624},{\"gpsTime\":1484546633000,\"engine\":0,\"" +
            "station\":31241,\"block\":22541,\"lng\":120.246176,\"acc\":1," +
            "\"termId\":862180034558954,\"course\":62,\"voltage\":0,\"fix\":1" +
            ",\"alt\":11.3,\"gpsSpeed\":17.2,\"rssi\":23,\"lat\":30.440578},{\"" +
            "gpsTime\":1484546629000,\"engine\":0,\"station\":31241,\"block\":" +
            "22541,\"lng\":120.245984,\"acc\":1,\"termId\":862180034558954,\"co" +
            "urse\":62,\"voltage\":0,\"fix\":1,\"alt\":11.4,\"gpsSpeed\":18.4,\"" +
            "rssi\":27,\"lat\":30.440492},{\"gpsTime\":1484546625000,\"engine\":" +
            "0,\"station\":31241,\"block\":22541,\"lng\":120.2458,\"acc\":1,\"te" +
            "rmId\":862180034558954,\"course\":61,\"voltage\":0,\"fix\":1,\"alt\"" +
            ":11.2,\"gpsSpeed\":16.1,\"rssi\":31,\"lat\":30.440412},{\"gpsTime\"" +
            ":1484546621000,\"engine\":0,\"station\":31241,\"block\":22541,\"lng" +
            "\":120.245656,\"acc\":1,\"termId\":862180034558954,\"course\":57,\"" +
            "voltage\":0,\"fix\":1,\"alt\":10.8,\"gpsSpeed\":15.5,\"rssi\":31,\"" +
            "lat\":30.440346},{\"gpsTime\":1484546617000,\"engine\":0,\"station\"" +
            ":31241,\"block\":22541,\"lng\":120.24556,\"acc\":1,\"termId\":862180" +
            "034558954,\"course\":33,\"voltage\":0,\"fix\":1,\"alt\":11.6,\"gpsSp" +
            "eed\":4.1,\"rssi\":31,\"lat\":30.44028},{\"gpsTime\":1484546615000,\"" +
            "engine\":0,\"station\":31241,\"block\":22541,\"lng\":120.245552,\"ac" +
            "c\":0,\"termId\":862180034558954,\"course\":59,\"voltage\":0,\"fix\"" +
            ":1,\"alt\":12,\"gpsSpeed\":1.2,\"rssi\":31,\"lat\":30.440272},{\"gpsT" +
            "ime\":1484546607000,\"engine\":0,\"station\":31241,\"block\":22541,\"" +
            "lng\":120.24556,\"acc\":0,\"termId\":862180034558954,\"course\":59,\"" +
            "voltage\":0,\"fix\":1,\"alt\":13.5,\"gpsSpeed\":0.1,\"rssi\":31,\"la" +
            "t\":30.440276}]},\"status\":true}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_trajectory);


        //获取地图控件引用
        mapView = (MapView) findViewById(R.id.car_trajectory_map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mapView.getMap();
        }

        initView();
    }

    private void initView() {
        coordinateTransformation = new CoordinateTransformation(context);
        Gson gson = new Gson();
        CarTrajectoryBean  carTrajectoryBean = gson.fromJson(testDate, CarTrajectoryBean.class);

        if(carTrajectoryBean.getContent().getGps().size() != 0)
        {
            List<LatLng> ml = new ArrayList<>();
            for (int i = 0; i < carTrajectoryBean.getContent().getGps().size(); i++) {
                if(carTrajectoryBean.getContent().getGps().get(i).getLat() == 0.0)
                {
                    continue;
                }else
                {
                    ml.add(coordinateTransformation.transformation(
                            new LatLng(carTrajectoryBean.getContent().getGps().get(i).getLat(),
                            carTrajectoryBean.getContent().getGps().get(i).getLng())));
                }

            }

            GPSHistoryLine  gpsHistoryLine = new GPSHistoryLine(aMap,context);
            gpsHistoryLine.setLatLngs(ml);
            gpsHistoryLine.startHistory("start","end");
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

}
