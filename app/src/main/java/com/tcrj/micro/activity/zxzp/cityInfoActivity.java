package com.tcrj.micro.activity.zxzp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.adpater.cityAdapter;
import com.tcrj.micro.adpater.fpdxAdapter;
import com.tcrj.micro.application.BaseActivity;
import com.tcrj.micro.application.MyApplication;
import com.tcrj.micro.entity.MessageEvent;
import com.tcrj.micro.entity.fpStringInfo;
import com.tcrj.micro.entity.qyzpListInfo;
import com.tcrj.micro.entity.zcgsInfo;
import com.tcrj.micro.view.MyTextViewZH;
import com.tsy.sdk.myokhttp.MyOkHttp;
import com.tsy.sdk.myokhttp.response.GsonResponseHandler;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class cityInfoActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {

    private MyTextViewZH txtTitle;
    private RecyclerView mRecyclerView;
    private MyOkHttp mMyOkhttp;
    List<zcgsInfo> zcgsInfo;
    private cityAdapter detailAdapter;
    private List<zcgsInfo> beanList;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_info);

        mMyOkhttp = MyApplication.getInstance().getMyOkHttp();
        initView();
        getData();
    }

    @Override
    public void initView() {

        txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText("城市选择");
        btnback = findViewById(R.id.btnback);
        mRecyclerView = findViewById(R.id.recycler_view);
        beanList = new ArrayList<>();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(detailAdapter = new cityAdapter(beanList));
        detailAdapter.setEnableLoadMore(false);
        detailAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        detailAdapter.setOnItemClickListener(this);
        btnback.setOnClickListener(this);

    }

    @Override
    public void getData() {
        showProgressDialog();

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("parentId", "");



        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.zcgs_listApi)
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
                        zcgsInfo = parseNoHeaderJArray(fpjlListInfos);
                        detailAdapter.setNewData(zcgsInfo);

                    }
                });
    }

    private void getCountList(final String id , final String name){


        showProgressDialog();

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("parentId", id);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        mMyOkhttp.post()
                .url(ApiConstants.zcgs_listApi)
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
                        zcgsInfo = parseNoHeaderJArray(fpjlListInfos);
                        //弹选择框
                        showxzdialog(zcgsInfo,id,name);

                    }
                });
    }

    OptionsPickerView<String> mOptionsPickerView;
    private void showxzdialog(final List<zcgsInfo> zcgsInfo, final String id, final String name){

            mOptionsPickerView = new OptionsPickerView<>(this);
            final ArrayList<String> list = new ArrayList<>();
            list.add("不限");
            for(int i = 0;i < zcgsInfo.size();i++){
                list.add(zcgsInfo.get(i).getName());
            }

            // 设置数据
            mOptionsPickerView.setPicker(list);
            // 设置选项单位
            mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int option1, int option2, int option3) {

                    finish();

                    if(option1 == 0){
                        zcgsInfo zcgsInfo1 = new zcgsInfo();
                        zcgsInfo1.setName(name);
                        zcgsInfo1.setId(id);

                        EventBus.getDefault().post(new MessageEvent(zcgsInfo1,ApiConstants.cityId));
                    }else {
                        EventBus.getDefault().post(new MessageEvent(zcgsInfo.get(option1-1),ApiConstants.countId));
                    }



                }
            });
            mOptionsPickerView.show();


    }

    private List<zcgsInfo> parseNoHeaderJArray(String strByJson) {

        //Json的解析类对象
        JsonParser parser = new JsonParser();
        //将JSON的String 转成一个JsonArray对象
        JsonArray jsonArray = parser.parse(strByJson).getAsJsonArray();

        Gson gson = new Gson();
        List<zcgsInfo> userBeanList = new ArrayList<>();

        //加强for循环遍历JsonArray
        for (JsonElement user : jsonArray) {
            //使用GSON，直接转成Bean对象
            zcgsInfo userBean = gson.fromJson(user, zcgsInfo.class);
            userBeanList.add(userBean);
        }
        return userBeanList;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        zcgsInfo item = (zcgsInfo) adapter.getItem(position);

        String id = item.getId();
        getCountList(id,item.getName());

    }


    @Override
    public void onClick(View v) {
        finish();
    }
}
