package com.tcrj.micro.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.newui.mygridview.MyGridView;
import com.newui.treeview.TreeBean;
import com.newui.treeview.TreeListViewAdapter;
import com.newui.treeview.TreeListViewAdapter.OnTreeNodeClickListener;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.left.LeftFindActivity;
import com.tcrj.micro.activity.left.LeftListActivity;
import com.tcrj.micro.activity.left.LeftListActivity2;
import com.tcrj.micro.adpater.GridViewAdapter;
import com.tcrj.micro.entity.CityEntity;
import com.tcrj.micro.view.MyTextViewXH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LeftFragment extends Fragment {
    private View fragmetView;
    private MyGridView gridView;
    public static GridViewAdapter gridViewAdapter;
    public static List<CityEntity> list = new ArrayList<CityEntity>();
    private MyTextViewXH gddt;
    private MyTextViewXH fcxx;
    private MyTextViewXH flfg;
    private MyTextViewXH gwywj;
    private MyTextViewXH bmwj;
    private MyTextViewXH fggz;
    private MyTextViewXH sjwj;
    private MyTextViewXH ygbmwj;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmetView = inflater.inflate(R.layout.main_left_fragment, container, false);
        initView();
        getDate();
        return fragmetView;

    }

    public void initView() {
        gridView = (MyGridView) fragmetView.findViewById(R.id.c_grid);
        gddt = (MyTextViewXH) fragmetView.findViewById(R.id.gddt);
        fcxx = (MyTextViewXH) fragmetView.findViewById(R.id.fcxx);
        flfg = (MyTextViewXH) fragmetView.findViewById(R.id.flfg);
        gwywj = (MyTextViewXH) fragmetView.findViewById(R.id.gwywj);
        bmwj = (MyTextViewXH) fragmetView.findViewById(R.id.bmwj);
        fggz = (MyTextViewXH) fragmetView.findViewById(R.id.fggz);
        sjwj = (MyTextViewXH) fragmetView.findViewById(R.id.sjwj);
        ygbmwj = (MyTextViewXH) fragmetView.findViewById(R.id.ygbmwj);

        gridViewAdapter = new GridViewAdapter(2, list.size(), getActivity(), list);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new OnItemClick());

       gddt.setOnClickListener(new OnClick());
       fcxx.setOnClickListener(new OnClick());
       flfg.setOnClickListener(new OnClick());
       gwywj.setOnClickListener(new OnClick());
       bmwj.setOnClickListener(new OnClick());
       fggz.setOnClickListener(new OnClick());
       sjwj.setOnClickListener(new OnClick());
       ygbmwj.setOnClickListener(new OnClick());
    }

    public void getDate() {

    }


    class OnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(), LeftListActivity.class);
            CityEntity entity = list.get(position);
            intent.putExtra("id", entity.getId());
            intent.putExtra("title", entity.getName());
            startActivity(intent);
        }
    }

    class OnClick implements OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), LeftListActivity.class);
            switch (v.getId()){
                case R.id.gddt:
                    intent.putExtra("id", "MBZfeq");
                    intent.putExtra("title", "各地动态");
                    startActivity(intent);
                break;
                case R.id.fcxx:
                    Intent intent2 = new Intent();
                    intent2.setClass(getActivity(), LeftListActivity2.class);
                    intent2.putExtra("title", "扶持信息");
                    startActivity(intent2);
                    break;
                case R.id.flfg:
                    intent.putExtra("id", "aeyqae");
                    intent.putExtra("title", "法律法规");
                    startActivity(intent);
                    break;
                case R.id.gwywj:
                    intent.putExtra("id", "fuqiQr");
                    intent.putExtra("title", "国务院文件");
                    startActivity(intent);
                    break;
                case R.id.bmwj:
                    intent.putExtra("id", "mY3iI3");
                    intent.putExtra("title", "部门文件");
                    startActivity(intent);
                    break;
                case R.id.fggz:
                    intent.putExtra("id", "BFVvue");
                    intent.putExtra("title", "法规规章");
                    startActivity(intent);
                    break;
                case R.id.sjwj:
                    intent.putExtra("id", "e6jmaa");
                    intent.putExtra("title", "省级文件");
                    startActivity(intent);
                    break;
                case R.id.ygbmwj:
                    intent.putExtra("id", "zANRj2");
                    intent.putExtra("title", "有关部门文件");
                    startActivity(intent);
                    break;
            }

        }
    }


}
