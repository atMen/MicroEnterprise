package com.tcrj.micro.activity.zxzp;


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


import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;

import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.adpater.MainAdapter;
import com.tcrj.micro.adpater.MoreAdapter;
import com.tcrj.micro.adpater.grzpAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.BumenInfo;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.grzpListInfo;
import com.tcrj.micro.entity.jcInfo;
import com.tcrj.micro.entity.qyzpListInfo;
import com.tcrj.micro.entity.zcgsInfo;
import com.tcrj.micro.entity.zwCodeList;
import com.tcrj.micro.until.ACache;
import com.tcrj.micro.view.CustomLoadMoreView;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.yyydjk.library.DropDownMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class qyzpActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {


    MyTextViewZH txtTitle;
    RecyclerView mRecyclerView;
    PtrFrameLayout mPtrFrameLayout;
    RelativeLayout rl_loding;
    ImageView btnback;
    LinearLayout content;
    TextView tv_empty;
    TextView cityname;

    LinearLayout ll_cs;
    EditText edt_search_result;
    ImageView iv_search;

    TextView tv_pp;
    TextView tv_work_naturejob;
    TextView tv_more;


    private MyOkHttp mMyOkhttp;
    private grzpAdapter detailAdapter;
    private boolean canPull = true;
    private int pageNum = 1;
    private List<qyzpListInfo.DataBean.ContentBean> beanList;

    private boolean iszw = false;

    LinearLayout layout_work_naturejob;
    LinearLayout layout_qx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qyzp);
        EventBus.getDefault().register(this);
        initView();
        getData();
    }

    @Override
    public void initView() {
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();

        layout_work_naturejob = findViewById(R.id.layout_work_naturejob);
        layout_qx = findViewById(R.id.layout_qx);
        tv_work_naturejob = findViewById(R.id.tv_work_naturejob);
        tv_pp = findViewById(R.id.tv_pp);
        tv_more = findViewById(R.id.tv_more);
        tv_more.setVisibility(View.VISIBLE);

        cityname = findViewById(R.id.cityname);
        content = findViewById(R.id.content);
        ll_cs = findViewById(R.id.ll_cs);
        edt_search_result = findViewById(R.id.edt_search_result);
        iv_search = findViewById(R.id.iv_search);

        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);

        txtTitle.setText("个人招聘");

        tv_work_naturejob.setText("职业类别");
        tv_pp.setText("行业类别");

        layout_work_naturejob.setOnClickListener(this);
        layout_qx.setOnClickListener(this);
        ll_cs.setOnClickListener(this);
        btnback.setOnClickListener(this);
        tv_more.setOnClickListener(this);
        iv_search.setOnClickListener(this);

        initview();
    }

    private void initview() {
        edt_search_result.setHint("请输入职位查询");

        mPtrFrameLayout = findViewById(R.id.mPtrFrameLayout);
        mRecyclerView = findViewById(R.id.recycler_view);
        tv_empty = findViewById(R.id.tv_empty);
        rl_loding = findViewById(R.id.rl_loding);

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

                getDataFromNet(pageNum);

            }
        });
        beanList = new ArrayList<>();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(qyzpActivity.this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(detailAdapter = new grzpAdapter(beanList, qyzpActivity.this));
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
                String s = edt_search_result.getText().toString();
                getDataFromNet(pageNum);
            }
        }, mRecyclerView);

    }

    String cityid = "";
    String countyid = "";
    //获取网络数据
    private void getDataFromNet(final int num) {
        String s = edt_search_result.getText().toString();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("jobType", zwCode);//职位id
            jsonObject.put("industryType", hyCode);//行业
            jsonObject.put("jobName", s);//职位名称

            jsonObject.put("jobCity", cityid);//城市名称
            jsonObject.put("jobCounty", countyid);//区县名称
            jsonObject.put("p", num+"");
            jsonObject.put("size", 20);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mMyOkhttp.post()
                .url(ApiConstants.zp_list_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {

//                      rl_loding.setVisibility(View.GONE);
                        if(num > 1){
                            loadMoreData(null,true);
                        }else{
                            loadData(null,true);
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo data) {
                        rl_loding.setVisibility(View.GONE);

                        if("9999".equals(data.getErrorcode())) {

                            qyzpListInfo.DataBean response = new Gson().fromJson(data.getData(), qyzpListInfo.DataBean.class);

                            if(num > 1){//上拉加载
                                loadMoreData(response,false);
                            }else{//下拉刷新
                                loadData(response,false);
                            }
                        }

                    }
                });

    }

    //上拉加载更多数据
    private void loadMoreData(qyzpListInfo.DataBean response, boolean isError) {
        Log.e("TAG","loadMoreData");

        if (response.getContent() == null) {
            if(isError){
                detailAdapter.loadMoreFail();
            }else{
                detailAdapter.loadMoreEnd();
            }

        } else {
            List<qyzpListInfo.DataBean.ContentBean> result = response.getContent();
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
    private void loadData(qyzpListInfo.DataBean response,boolean isError) {

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
        getDataFromNet(pageNum);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ll_cs:
                //显示城市选择dialog

                toClass(this,cityInfoActivity.class,null);

                break;

            case R.id.btnback:
                finish();

                break;

            case R.id.layout_work_naturejob:

                iszw = true;
                getCountList("107");
                break;

            case R.id.layout_qx:
                iszw = false;
                getCountList(zwCode);

                break;

            case R.id.tv_more:

                String type = ACache.get(this).getAsString("logintype");

                if(type == null){
                    toClass(this,LoginActivity.class,null);
                }else if(!"1".equals(type)){
                    Toast.makeText(this, "请登录个人账号查看", Toast.LENGTH_SHORT).show();
                }else {
                    toClass(this,TdjlActivity.class,null);
                }

                break;

            case R.id.iv_search:
                rl_loding.setVisibility(View.VISIBLE);
                mPtrFrameLayout.setVisibility(View.GONE);

                getDataFromNet(1);
                break;

            default:
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        qyzpListInfo.DataBean.ContentBean item = (qyzpListInfo.DataBean.ContentBean) adapter.getItem(position);
        String id = item.getId();

        if(id != null){
            Bundle bundle = new Bundle();
            bundle.putString("ID",id);
            toClass(this,zwItemInfoActivity.class,bundle);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        switch (messageEvent.getType()){

            case 100:
                zcgsInfo cityinfo = messageEvent.getCityinfo();
                cityname.setText(cityinfo.getName());
                cityid = cityinfo.getId();
                countyid = "";
                getDataFromNet(1);
                break;

            case 101:
                zcgsInfo countinfo = messageEvent.getCityinfo();
                cityname.setText(countinfo.getName());
                countyid = countinfo.getId();
                cityid = "";
                getDataFromNet(1);
                break;

            default:
                break;
        }

    }

    private void getCountList(String id){
        showProgressDialog();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("code", id.equals("")? "104":id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.zwcode_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<fpStringInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        dismisProgressDialog();
                    }

                    @Override
                    public void onSuccess(int statusCode, fpStringInfo response) {
                        dismisProgressDialog();

                        String fpjlListInfos = response.getData();
                        List<zwCodeList> zcgsinfo = parseNoHeaderJArray(fpjlListInfos);

                        if(iszw){
                            //弹选择框
                            showxzdialog(zcgsinfo);
                        }else {
                            showhydialog(zcgsinfo);
                        }

                    }
                });
    }

    private String zwCode = "";
    private String hyCode = "";

    OptionsPickerView<String> zwOptionsPickerView;
    private void showxzdialog(final List<zwCodeList> zcgsInfo){

        if(zwOptionsPickerView != null){
            zwOptionsPickerView.show();
        }else {
        zwOptionsPickerView = new OptionsPickerView<>(this);
        final ArrayList<String> list = new ArrayList<>();
        list.add("不限");
        for(int i = 0;i < zcgsInfo.size();i++){
            list.add(zcgsInfo.get(i).getName());
        }

        // 设置数据
        zwOptionsPickerView.setPicker(list);
        // 设置选项单位
        zwOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String sex = list.get(option1);
                tv_work_naturejob.setText(sex);
                tv_pp.setText("不限");
                hyCode = "";

                if(option1 == 0){
                    zwCode = "";
                }else {
                    zwCode = zcgsInfo.get(option1-1).getCode();
                }


                getDataFromNet(1);

//                Toast.makeText(qyzpActivity.this, sex, Toast.LENGTH_SHORT).show();

            }
        });
        zwOptionsPickerView.show();

        }
    }

    OptionsPickerView<String> zwOptionsPickerView1;
    private void showhydialog(final List<zwCodeList> zcgsInfo){


            zwOptionsPickerView1 = new OptionsPickerView<>(this);
            final ArrayList<String> list = new ArrayList<>();
            list.add("不限");
            for(int i = 0;i < zcgsInfo.size();i++){
                list.add(zcgsInfo.get(i).getName());
            }

            // 设置数据
            zwOptionsPickerView1.setPicker(list);
            // 设置选项单位
            zwOptionsPickerView1.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int option1, int option2, int option3) {
                    String sex = list.get(option1);
                    tv_pp.setText(sex);

                    if(option1 == 0){
                        hyCode = "";
                    }else {
                        hyCode = zcgsInfo.get(option1-1).getCode();
                    }

                    getDataFromNet(1);

                }
            });
            zwOptionsPickerView1.show();


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


}
