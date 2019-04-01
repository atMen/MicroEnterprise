package com.tcrj.micro.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcrj.micro.R;


public class RightFragment extends Fragment {
    private View fragmetView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmetView = inflater.inflate(R.layout.main_right_fragment, container, false);
        initView();
        return fragmetView;

    }

    public void initView() {

    }





}
