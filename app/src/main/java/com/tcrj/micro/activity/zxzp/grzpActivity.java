package com.tcrj.micro.activity.zxzp;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.adpater.ListDropDownAdapter;

import com.tcrj.micro.adpater.grzpAdapter;
import com.tcrj.micro.adpater.qyzpAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.fpDataInfo;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.grzpListInfo;
import com.tcrj.micro.entity.jcInfo;
import com.tcrj.micro.entity.projectInfo;
import com.tcrj.micro.entity.qyzpListInfo;
import com.tcrj.micro.entity.zcgsInfo;
import com.tcrj.micro.entity.zwCodeList;
import com.tcrj.micro.entity.zwInfo;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.CustomLoadMoreView;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;

import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.tsy.sdk.myokhttp.response.JsonResponseHandler;
import com.yyydjk.library.DropDownMenu;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class grzpActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {


    MyTextViewZH txtTitle;
    RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrameLayout;
    RelativeLayout rl_loding;
    ImageView btnback;

    TextView tv_empty;
    DropDownMenu mDropDownMenu;


    private ListDropDownAdapter cityAdapter;

    private MyOkHttp mMyOkhttp;
    private qyzpAdapter detailAdapter;
    private boolean canPull = true;
    private int pageNum = 1;
    private List<grzpListInfo.DataBean.ContentBean> beanList;

    private String headers[] = {"职位"};
    private List<View> popupViews = new ArrayList<>();

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grzp);
        EventBus.getDefault().register(this);
        token = ACache.get(this).getAsString("token");
        initView();
        getData();
    }


    @Override
    public void initView() {

        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        mDropDownMenu = findViewById(R.id.dropDownMenu);

        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);
        txtTitle.setText("企业招聘");
        btnback.setOnClickListener(this);

        initview();
        getTypeData();
        getProjectData();

    }
    List<zwCodeList> resultProject;
    /**
     * 获取项目
     */
    private void getProjectData() {

        resultProject = new ArrayList<>();
        CharSequence[] result = this.getResources().getStringArray(R.array.quick_menu_sign_in);
        for (int i = 0; i < result.length; i++) {
            zwCodeList quick = new zwCodeList();
            quick.setName(result[i].toString());

            resultProject.add(quick);
        }
        cityAdapter = new ListDropDownAdapter(this, resultProject);
        cityView.setDividerHeight(0);
        cityView.setAdapter(cityAdapter);

    }


    /**
     * 获取交通路口下拉数据
     */
    private void getTypeData() {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("code", 107);



        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.zw_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {


                        dismisProgressDialog();

                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo response) {
                        Log.e("TAG","JSONArray"+response.toString());
                        dismisProgressDialog();


                        zwInfoList = parseNoHeaderJArray(response.getData());


                        zwCodeList resultBean = new zwCodeList();
                        resultBean.setName("不限");
                        resultBean.setId("-1");
                        zwInfoList.add(0,resultBean);

                        ageView.setDividerHeight(0);
                        cityAdapter = new ListDropDownAdapter(grzpActivity.this, zwInfoList);
                        ageView.setAdapter(cityAdapter);



                    }
                });

    }

    List<zwCodeList> zwInfoList;
    View inflate;
    ListView cityView;
    ListView ageView;

    private void initview() {
        inflate = View.inflate(this, R.layout.activity_dw_content, null);

//        //项目
        cityView = new ListView(this);

        //点位分类
        ageView = new ListView(this);

        //init popupViews
        popupViews.add(cityView);
//        popupViews.add(ageView);

        //add item click event
        cityView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : resultProject.get(position).getName());
                mDropDownMenu.closeMenu();

                pageNum = 1;
                rl_loding.setVisibility(View.VISIBLE);
                mPtrFrameLayout.setVisibility(View.GONE);

                getData(pageNum);
            }
        });

        ageView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ageAdapter.setCheckItem(position);
//                mDropDownMenu.setTabText(position == 0 ? headers[1] : resultType.get(position).getDWMC());
//                mDropDownMenu.closeMenu();
//
//
//                pageNum = 1;
//                String s = edt_search_result.getText().toString();
//                rl_loding.setVisibility(View.VISIBLE);
//                mPtrFrameLayout.setVisibility(View.GONE);
//
//                getData(pageNum,s);
            }
        });


        mPtrFrameLayout = inflate.findViewById(R.id.mPtrFrameLayout);
        mRecyclerView = inflate.findViewById(R.id.recycler_view);
        tv_empty = inflate.findViewById(R.id.tv_empty);
        rl_loding = inflate.findViewById(R.id.rl_loding);


        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                if(!canPull){
                    return false;
                }
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                rl_loding.setVisibility(View.GONE);
                mPtrFrameLayout.setVisibility(View.VISIBLE);
                pageNum = 1;
                getData(pageNum);

            }
        });
        beanList = new ArrayList<>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(grzpActivity.this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(detailAdapter = new qyzpAdapter(beanList, grzpActivity.this));
//      detailAdapter.setPreLoadNumber(1);
        detailAdapter.setLoadMoreView(new CustomLoadMoreView());
        detailAdapter.setEnableLoadMore(true);
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        detailAdapter.setOnItemClickListener(this);

        detailAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e("TAG","点击重新加载数据");
                rl_loding.setVisibility(View.GONE);
                mPtrFrameLayout.setVisibility(View.VISIBLE);
                getData(pageNum);
            }
        }, mRecyclerView);

        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, inflate);
    }



    //获取网络数据
    private void getData(final int num) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("token", token);
            jsonObject.put("pageNum", num+"");
            jsonObject.put("pageSize", 20);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.yp_list_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

                        if(num > 1){
                            loadMoreData(null,true);
                        }else{
                            loadData(null,true);
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo data) {
                        rl_loding.setVisibility(View.GONE);
                        grzpListInfo.DataBean response = null;

                        if("9999".equals(data.getErrorcode())) {

                            response = new Gson().fromJson(data.getData(), grzpListInfo.DataBean.class);

                            if(num > 1){//上拉加载
                                loadMoreData(response,false);
                            }else{//下拉刷新
                                loadData(response,false);
                            }
                        }else if("204".equals(data.getErrorcode())) {
                            Intent intent = new Intent();
                            intent.putExtra("openid",-2);
                            intent.setClass(grzpActivity.this, LoginActivity.class);
                        }


                    }
                });

    }

    //上拉加载更多数据
    private void loadMoreData(grzpListInfo.DataBean response, boolean isError) {
        Log.e("TAG","loadMoreData");

        if (response == null) {
            if(isError){
                detailAdapter.loadMoreFail();
            }else{
                detailAdapter.loadMoreEnd();
            }

        } else {
            List<grzpListInfo.DataBean.ContentBean> result = response.getContent();
            if(result == null || result.size() == 0){//没有更多数据
                detailAdapter.loadMoreEnd();
            }else{
                pageNum++;
                detailAdapter.addData(result);
                detailAdapter.loadMoreComplete();
            }

        }

    }

    //下拉刷新
    private void loadData(grzpListInfo.DataBean response,boolean isError) {

        if (response == null || response.getContent().size() == 0) {
            if(mPtrFrameLayout != null){
                mPtrFrameLayout.refreshComplete();
            }
            if(isError){
                Toast.makeText(this, "刷新失败", Toast.LENGTH_SHORT).show();
            }else{
               EmptyView();
            }
            canPull = false;

        } else {

            canPull = true;
            pageNum++;

            if(mRecyclerView != null){
                mRecyclerView.scrollToPosition(0);
            }
            detailAdapter.setNewData(response.getContent());
            if(mPtrFrameLayout != null){
                mPtrFrameLayout.refreshComplete();
            }
            Success();
//            showSuccess();
            disableLoadMoreIfNotFullPage(mRecyclerView,response.getContent().size());
        }
    }

    private void Success() {
        mPtrFrameLayout.setVisibility(View.VISIBLE);
        tv_empty.setVisibility(View.GONE);

    }

    private void EmptyView() {
        mPtrFrameLayout.setVisibility(View.GONE);
        tv_empty.setVisibility(View.VISIBLE);
    }

    public void disableLoadMoreIfNotFullPage(RecyclerView recyclerView, final int size) {
        detailAdapter.setEnableLoadMore(false);
        if (recyclerView == null) return;
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager == null) return;
        if (manager instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) manager;

            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {

                    //要等到列表显示出来才可以去获取：findLastCompletelyVisibleItemPosition
                    if ((linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1) != size) {
                        detailAdapter.setEnableLoadMore(true);
                        Log.e("TAG","setEnableLoadMore(true)");
                    }

                    Log.e("TAG","测试："+(linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1));
                }
            }, 1000);


        }
    }

    @Override
    public void getData() {
        getData(pageNum);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btnback:
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        grzpListInfo.DataBean.ContentBean item = (grzpListInfo.DataBean.ContentBean) adapter.getItem(position);
        String id = item.getResumeId();

        if(id != null){
            Bundle bundle = new Bundle();
            bundle.putString("ResumeId",item.getResumeId());
            bundle.putString("ID",item.getId());
            bundle.putString("enterpriseId",item.getEnterpriseId());
            bundle.putString("jobId",item.getJobId());
            bundle.putString("userId",item.getUserId());
            toClass(this,QyzpItemInfoActivity.class,bundle);
        }
    }


    @Override
    public void onBackPressed() {
        //退出activity前关闭菜单
        if (mDropDownMenu.isShowing()) {
            mDropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    private List<zwCodeList> parseNoHeaderJArray(String strByJson) {

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();

        Gson gson = new Gson();
        List<zwCodeList> userBeanList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement user : jsonArray) {
            //使用GSON，直接转成Bean对象
            zwCodeList userBean = gson.fromJson(user, zwCodeList.class);
            userBeanList.add(userBean);
        }
        return userBeanList;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {

        switch (messageEvent.getType()){

            case 102:
                Log.e("TAG","获取新数据");
                getData(1);
                break;

            default:
                break;
        }

    }
}
