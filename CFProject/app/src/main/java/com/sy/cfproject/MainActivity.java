package com.sy.cfproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sy.cfproject.acyivity.CarLocationActivity;
import com.sy.cfproject.acyivity.CarTrajectoryActivity;
import com.sy.cfproject.fragment.ControllFragment;
import com.sy.cfproject.fragment.DriveFragment;
import com.sy.cfproject.fragment.HomeFragment;
import com.sy.cfproject.fragment.MyFragment;
import com.sy.cfproject.fragment.ServerFragment;
import com.sy.cfproject.tool.requesttool.PostTool;
import com.sy.cfproject.tool.url.Constant;
import com.sy.cfproject.tool.url.ServerUrl;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{
    private Context context = MainActivity.this;
    private HomeFragment homeFragment;
    private ServerFragment serverFragment;
    private ControllFragment controllFragmemt;
    private DriveFragment driveFragment;
    private MyFragment myFragment;

    private FragmentTransaction transaction;


    private RadioGroup radioGroup;
    private RadioButton radioButtonHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFragment();
        initHttp();
    }

    private void initFragment() {

        if(serverFragment == null)
        {
            serverFragment = new ServerFragment();
        }
        if(controllFragmemt == null)
        {
            controllFragmemt = new ControllFragment();
        }
        if(homeFragment == null)
        {
            homeFragment = new HomeFragment();
        }
        if(driveFragment == null)
        {
            driveFragment = new DriveFragment();
        }
        if(myFragment == null)
        {
            myFragment = new MyFragment();
        }


        transaction = getSupportFragmentManager().beginTransaction();



        transaction.replace(R.id.home_framelayout,homeFragment);


//        transaction.replace(R.id.home_framelayout,homeFragment);
        transaction.commit();
        radioButtonHome.setChecked(true);


    }

    private void initHttp() {

        PostTool postTool = new PostTool(context,null);
        postTool.toCheckCarLocation(ServerUrl.server_url+ServerUrl.car_location_url, Constant.CAR_LOCATION);
    }

    private void initView() {
        radioButtonHome = (RadioButton) findViewById(R.id.radio_home);
        radioGroup = (RadioGroup) findViewById(R.id.home_radio_group);
        radioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (i)
        {
            case R.id.radio_server:
//                transaction.add(R.id.home_framelayout,serverFragment);
                transaction.replace(R.id.home_framelayout,serverFragment);

                break;

            case R.id.radio_control:
//                transaction.add(R.id.home_framelayout,controlFragment);
                transaction.replace(R.id.home_framelayout,controllFragmemt);
                break;
            case R.id.radio_home:
//                transaction.add(R.id.home_framelayout,homeFragment);
                transaction.replace(R.id.home_framelayout,homeFragment);
                break;
            case R.id.radio_driving:
//                transaction.add(R.id.home_framelayout,drivingFragment);
                transaction.replace(R.id.home_framelayout,driveFragment);
                break;
            case R.id.radio_my:
//                transaction.add(R.id.home_framelayout,myFragment);
                transaction.replace(R.id.home_framelayout,myFragment);
                break;

        }
        transaction.commit();
    }
}
