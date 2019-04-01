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
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;

import com.tcrj.micro.adpater.MainAdapter;
import com.tcrj.micro.adpater.MoreAdapter;
import com.tcrj.micro.adpater.grzpAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.BumenInfo;
import com.tcrj.micro.entity.jcInfo;
import com.tcrj.micro.entity.qyzpListInfo;
import com.tcrj.micro.view.CustomLoadMoreView;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;
import com.yyydjk.library.DropDownMenu;

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

    LinearLayout ll_cs;
    EditText edt_search_result;
    ImageView iv_search;

    TextView tv_xzfw;


    private MyOkHttp mMyOkhttp;
    private grzpAdapter detailAdapter;
    private boolean canPull = true;
    private int pageNum = 1;
    private List<qyzpListInfo.ContentBean> beanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qyzp);
        dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        initView();
        getData();
    }


    @Override
    public void initView() {
        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();

        content = findViewById(R.id.content);
        tv_xzfw = findViewById(R.id.tv_xzfw);
        ll_cs = findViewById(R.id.ll_cs);
        edt_search_result = findViewById(R.id.edt_search_result);
        iv_search = findViewById(R.id.iv_search);

        txtTitle = findViewById(R.id.txtTitle);
        btnback = findViewById(R.id.btnback);

        txtTitle.setText("个人招聘");

        ll_cs.setOnClickListener(this);
        btnback.setOnClickListener(this);
        tv_xzfw.setOnClickListener(this);

        initview();
    }

    private void initview() {
        edt_search_result.setHint("请输入基础分类查询");

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
                String s = edt_search_result.getText().toString();
                getData(pageNum,s);

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
                getData(pageNum,s);
            }
        }, mRecyclerView);

    }



    //获取网络数据
    private void getData(final int num,String s) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("p", num+"");
            jsonObject.put("size", 20);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        mMyOkhttp.post()
                .url(ApiConstants.zp_list_Api)
                .jsonParams(jsonObject.toString())
                .enqueue(new GsonResponseHandler<qyzpListInfo>() {
                    @Override
                    public void onFailure(int statusCode, String error_msg) {
                        Log.e("TAG","error_msg"+error_msg);

//                        rl_loding.setVisibility(View.GONE);
                        if(num > 1){
                            loadMoreData(null,true);
                        }else{
                            loadData(null,true);
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, qyzpListInfo response) {
                        rl_loding.setVisibility(View.GONE);
                        if(response.getContent() == null){

                            if(num > 1){//上拉加载
                                detailAdapter.loadMoreEnd();
                            }else{//下拉刷新
                                EmptyView();
                            }

                        }else if(response.getContent() != null){

                            if(num > 1){//上拉加载
                                Log.e("TAG","上拉加载更多数据");
                                loadMoreData(response,false);
                            }else{//下拉刷新
                                loadData(response.getContent(),false);
                            }
//                          loadMoreData(response,false);
                            Log.e("TAG","加载错误");
                        }
                    }
                });

    }

    //上拉加载更多数据
    private void loadMoreData(qyzpListInfo response, boolean isError) {
        Log.e("TAG","loadMoreData");

        if (response.getContent() == null) {
            if(isError){
                detailAdapter.loadMoreFail();
            }else{
                detailAdapter.loadMoreEnd();
            }

        } else {
            List<qyzpListInfo.ContentBean> result = response.getContent();
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
    private void loadData(List<qyzpListInfo.ContentBean> response,boolean isError) {

        if (response == null || response.size() == 0) {
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
            detailAdapter.setNewData(response);
            if(mPtrFrameLayout != null){
                mPtrFrameLayout.refreshComplete();
            }

            Success();
            disableLoadMoreIfNotFullPage(mRecyclerView,response.size());
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
        getData(pageNum,"");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.ll_cs:
                //显示城市选择dialog
                downPopwindow();

                break;

            case R.id.btnback:
                finish();

                break;

            case R.id.tv_xzfw:

                //显示薪资选择
                showxzdialog();
                break;

            case R.id.iv_search:
                rl_loding.setVisibility(View.VISIBLE);
                mPtrFrameLayout.setVisibility(View.GONE);

                String s = edt_search_result.getText().toString();
                getData(1,s);
                break;

            default:
                break;
        }
    }
    OptionsPickerView<String> mOptionsPickerView;
    private void showxzdialog(){

        if(mOptionsPickerView != null){
            mOptionsPickerView.show();
        }else {

            mOptionsPickerView = new OptionsPickerView<>(this);
            final ArrayList<String> list = new ArrayList<>();
            list.add("1000");
            list.add("2000");
            // 设置数据
            mOptionsPickerView.setPicker(list);
            // 设置选项单位
//        mOptionsPickerView.setLabels("性");
            mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int option1, int option2, int option3) {
                    String sex = list.get(option1);
                    Toast.makeText(qyzpActivity.this, sex, Toast.LENGTH_SHORT).show();
                }
            });
            mOptionsPickerView.show();
        }

    }


    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

//        jcInfo.ResultBean.ItemsBean item = (jcInfo.ResultBean.ItemsBean) adapter.getItem(position);
//        int id = item.getOBJECTID();
//        Bundle bundle = new Bundle();
//        bundle.putString("ID",id+"");
//        bundle.putInt("type",type);
//        toClass(this,JcInfoActivity.class,bundle);

        Bundle bundle = new Bundle();
        bundle.putString("ID","");
        toClass(this,QyzpItemInfoActivity.class,bundle);

    }


    /**
     * 城市选择控件
     */
    private Boolean isFirst = true;
    private DisplayMetrics dm;
    private PopupWindow popWindow;
    private MoreAdapter moreAdapter;
    private ListView morelist;
    private List<BumenInfo.ComlistBean> mainList = null;
    private ListView mainlist;
    private MainAdapter mainAdapter;
    private TextView tv_all;
    private Button btn_gps_sure;
    private LinearLayout ll_btn_sure;



    //筛选部门id
    private int BuMenId = 3;

    //筛选公司id
    private int GongSiId;

    private void downPopwindow() {


        if(popWindow != null){
            popWindow.showAtLocation(content, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

//          popWindow.showAsDropDown(tv_sx);
        }else {
        // showAsDropDown(View anchor);相对某个控件的位置（正左下方），无偏移
        // showAsDropDown(View anchor, int x, int
        // y);相对某个控件的位置，有偏移;x表示相对x轴的偏移，正表示向左，负表示向右；y表示相对y轴的偏移，正是向下，负是向上；
        View contentView = LayoutInflater.from(this).inflate(R.layout.pop_down, null);
        int screenHeight = dm.heightPixels * 4 / 5;
        // 这里就给具体大小的数字，要不然位置不好计算
        popWindow = new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        popWindow.setAnimationStyle(R.style.popwin_anim_style);// 淡入淡出动画
        // popWindow.setTouchable(false);// 是否响应touch事件
        popWindow.setFocusable(true);// 是否具有获取焦点的能力
        // 点击PopupWindow以外的区域，PopupWindow是否会消失。
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);

        mainlist = (ListView) contentView.findViewById(R.id.classify_mainlist);
        morelist = (ListView) contentView.findViewById(R.id.classify_morelist);
        tv_all = (TextView) contentView.findViewById(R.id.moreitem_txt);

        btn_gps_sure = (Button) contentView.findViewById(R.id.btn_gps_sure);

        ll_btn_sure = (LinearLayout) contentView.findViewById(R.id.ll_btn_sure);


        ll_btn_sure.setVisibility(View.VISIBLE);

        mainAdapter = new MainAdapter(this, mainList);
        mainAdapter.setSelectItem(0);
        mainlist.setAdapter(mainAdapter);
        mainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv_all.setTextColor(0xFF666666);
                List<BumenInfo.ComlistBean.DeptlistBean> lists = mainList.get(position).getDeptlist();
                initAdapter(lists,false);
                mainAdapter.setSelectItem(position);
                mainAdapter.notifyDataSetChanged();
                //设置公司id
                GongSiId = mainList.get(position).getId();

            }
        });



        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tv_all.setTextColor(0xFFFF8C00);
                moreAdapter.setSelectItem(-1);
                moreAdapter.setIsFirst(false);
                moreAdapter.notifyDataSetChanged();
                BuMenId = GongSiId;
            }
        });

        btn_gps_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(BuMenId == 0){
                    Toast.makeText(qyzpActivity.this, "请选择筛选条件", Toast.LENGTH_SHORT).show();
                }else {
                    popWindow.dismiss();
                    //请求数据

                }

            }
        });

        mainlist.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // 一定要设置这个属性，否则ListView不会刷新
        initAdapter(mainList.get(0).getDeptlist(),true);

        morelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                tv_all.setTextColor(0xFF666666);
                BumenInfo.ComlistBean.DeptlistBean s = (BumenInfo.ComlistBean.DeptlistBean) moreAdapter.getItem(position);

//                  Toast.makeText(getApplicationContext(), s.getName(), Toast.LENGTH_LONG).show();
                moreAdapter.setSelectItem(position);
                moreAdapter.setIsFirst(false);
                moreAdapter.notifyDataSetChanged();
//                  popWindow.dismiss();

                //设置部门id
                BuMenId = s.getId();
                //请求数据
//                    new GpsPresenter(GPSActivity.this,1);
//                    presenter.start();



            }
        });

        popWindow.showAtLocation(content, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//            popWindow.showAsDropDown(tv_sx);
        }

    }

    private void initAdapter(List<BumenInfo.ComlistBean.DeptlistBean> lists, boolean first) {
        moreAdapter = new MoreAdapter(this, lists);
        moreAdapter.setIsFirst(first);
        morelist.setAdapter(moreAdapter);
        moreAdapter.notifyDataSetChanged();
    }



}
