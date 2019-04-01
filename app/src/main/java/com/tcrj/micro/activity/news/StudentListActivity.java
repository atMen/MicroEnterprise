package com.tcrj.micro.activity.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.newui.hzwlistview.xlist.XListView;
import com.newui.hzwlistview.xlist.XListView.IXListViewListener;
import com.tcrj.micro.R;
import com.tcrj.micro.adpater.NewsListAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.entity.InfoEntity;
import com.tcrj.micro.until.DateUtil;

import java.util.ArrayList;

import static com.newui.waterlistview.WaterDropListView.OnClickListener;
import static com.newui.waterlistview.WaterDropListView.OnItemClickListener;

public class StudentListActivity extends BaseActivity {

    private TextView tvtitle;
    private ImageView backBtn;
    private ImageView btnsearch;
    private XListView listview;
    private ArrayList<InfoEntity> list = new ArrayList<InfoEntity>();
    public static NewsListAdapter adapter;
    private int pageIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        initView();
        getData();
    }

    @Override
    public void initView() {
        tvtitle = (TextView) findViewById(R.id.txtTitle);
        backBtn = (ImageView) findViewById(R.id.btnback);
        btnsearch = (ImageView) findViewById(R.id.btnsearch);
        backBtn.setVisibility(View.VISIBLE);
        //btnsearch.setVisibility(View.VISIBLE);
        tvtitle.setText("资讯");
        listview = (XListView) findViewById(R.id.listview);
        listview.setPullLoadEnable(true);
        listview.setXListViewListener(new IXListViewListener() {

            @Override
            public void onRefresh() {
                listview.setRefreshTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
                pageIndex = 1;
                getFreshVolley(pageIndex);
            }

            @Override
            public void onLoadMore() {
                pageIndex++;
                getLoadVolley(pageIndex);
            }
        });
        adapter = new NewsListAdapter(this,list);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new OnItemClick());
        backBtn.setOnClickListener(new OnClick());
        btnsearch.setOnClickListener(new OnClick());
    }



    @Override
    public void getData() {
        InfoEntity infoEntity = new InfoEntity();
        list.add(infoEntity);
        list.add(infoEntity);
        list.add(infoEntity);
        list.add(infoEntity);
        list.add(infoEntity);
        list.add(infoEntity);
        adapter.notifyDataSetChanged();

    }

    // 刷新
    public void getFreshVolley(int pageNo) {


    }

    // 加载
    public void getLoadVolley(int pageNo) {


    }

    class OnItemClick implements  OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(StudentListActivity.this,StudentDetailActivity.class);
            intent.putExtra("id",list.get(position-1).getId());
            startActivity(intent);
        }
    }



    class OnClick implements  OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnback:
                    finish();
                    break;
                case R.id.btnsearch:
                    Intent intent = new Intent();
                    intent.setClass(StudentListActivity.this, NewsFindActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }
}
