package com.sy.cfproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sy.cfproject.acyivity.CarLocationActivity;
import com.sy.cfproject.acyivity.CarTrajectoryActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Button Location , trajectory;
    private Context context = MainActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        Location = (Button) findViewById(R.id.one);
        trajectory = (Button) findViewById(R.id.two);

        Location.setOnClickListener(this);
        trajectory.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId())
        {

            case R.id.one:
                intent.setClass(context, CarLocationActivity.class);
                 break;

            case R.id.two:
                intent.setClass(context, CarTrajectoryActivity.class);
                break;
        }
        startActivity(intent);
    }
}
