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
import com.sy.cfproject.acyivity.CarLocationActivity;

/**
 * Created by Trust on 2017/3/6.
 */
public class HomeFragment extends BaseFragment  {
    private View v;
    private Button carLocationBtn;
    private Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home,null);
        initView();
        return v;
    }

    private void initView() {
        carLocationBtn = (Button) v.findViewById(R.id.home_car_location);
        carLocationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.home_car_location:
                context.startActivity(new Intent(context, CarLocationActivity.class));
                break;
        }
    }
}
