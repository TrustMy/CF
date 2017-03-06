package com.sy.cfproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sy.cfproject.BaseFragment;
import com.sy.cfproject.R;

/**
 * Created by Trust on 2017/3/6.
 */
public class ControllFragment extends BaseFragment {
    private View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_controll,null);
        return v;
    }
}
