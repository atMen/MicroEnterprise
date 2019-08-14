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
import com.tcrj.micro.activity.left.NewLeftListActivity;
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
    private MyTextViewXH mtv_dfzf;
    private MyTextViewXH mtv_zyzf;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmetView = inflater.inflate(R.layout.main_left_fragment, container, false);
        initView();
        getDate();
        return fragmetView;

    }

    public void initView() {
        mtv_dfzf = fragmetView.findViewById(R.id.mtv_dfzf);
        mtv_zyzf = fragmetView.findViewById(R.id.mtv_zyzf);

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
        mtv_dfzf.setOnClickListener(new OnClick());
        mtv_zyzf.setOnClickListener(new OnClick());


    }

    public void getDate() {

    }


    class OnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getActivity(), NewLeftListActivity.class);
            CityEntity entity = list.get(position);
            intent.putExtra("cityId", entity.getId());
            intent.putExtra("id", entity.getId());
            intent.putExtra("title", entity.getName());
            startActivity(intent);
        }
    }

    class OnClick implements OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getActivity(), NewLeftListActivity.class);
            switch (v.getId()){
                case R.id.gddt:
                    intent.putExtra("id", "qQZbUf");
                    intent.putExtra("title", "要闻动态");
                    startActivity(intent);
                    break;
                case R.id.fcxx:
                    intent.putExtra("id", "NzqQNn");
                    intent.putExtra("title", "通知公告");
                    startActivity(intent);
                    break;

                //中央政府
                case R.id.mtv_zyzf:
                    intent.putExtra("id", "eqQN32");
                    intent.putExtra("title", "国家法律法规");
                    startActivity(intent);
                    break;

                //地方政府
                case R.id.mtv_dfzf:
                    intent.putExtra("id", "3ymYJr");
                    intent.putExtra("title", "地方政策法规");
                    startActivity(intent);
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

                default:
                    break;
            }

        }
    }


}
