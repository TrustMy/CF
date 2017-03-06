package com.sy.cfproject.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sy.cfproject.BaseFragment;
import com.sy.cfproject.R;
import com.sy.cfproject.acyivity.CarTrajectoryActivity;

/**
 * Created by Trust on 2017/3/6.
 */
public class DriveFragment extends BaseFragment {
    private View v;
    private Button hirstoryBtn,alarmBtn;
    private Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_drive,null);
        initView();
        return v;
    }

    private void initView() {
        hirstoryBtn = (Button) v.findViewById(R.id.drive_hirstory);
        alarmBtn = (Button) v.findViewById(R.id.drive_alarm);

        hirstoryBtn.setOnClickListener(this);
        alarmBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId())
        {
            case R.id.drive_hirstory:
                intent.setClass(context, CarTrajectoryActivity.class);
                break;
            case R.id.drive_alarm:
                break;
        }
        context.startActivity(intent);
    }
}
