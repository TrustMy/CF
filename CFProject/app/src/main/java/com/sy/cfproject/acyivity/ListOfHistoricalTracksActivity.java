package com.sy.cfproject.acyivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sy.cfproject.R;

public class ListOfHistoricalTracksActivity extends AppCompatActivity {
    private Context context = ListOfHistoricalTracksActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_historical_tracks);
    }


    public void checkHirstroy(View v)
    {
        startActivity(new Intent(context,CarTrajectoryActivity.class));
    }
}
