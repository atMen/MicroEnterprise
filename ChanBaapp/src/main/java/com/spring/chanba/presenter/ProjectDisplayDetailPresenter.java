package com.spring.chanba.presenter;

import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.LawServiceDetailEntity;
import com.spring.chanba.contract.ProjectDisplayDetailContract;
import com.spring.chanba.utils.RetrofitUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 项目详情
 */
public class ProjectDisplayDetailPresenter implements ProjectDisplayDetailContract.Presenter {
    private ProjectDisplayDetailContract.View view;

    public ProjectDisplayDetailPresenter(ProjectDisplayDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getProjectServerDetail(map).enqueue(new Callback<LawServiceDetailEntity>() {
            @Override
            public void onResponse(Call<LawServiceDetailEntity> call, Response<LawServiceDetailEntity> response) {
                LawServiceDetailEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<LawServiceDetailEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }

    @Override
    public void getHave(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getCompanyInfoDetail(map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String strResponse = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(strResponse);
                        String strValue = jsonObject.getString("data");
                        if (jsonObject.getInt("state") == 1) {
                            if (strValue.length() > 1) {
                                view.initHaven(1);
                            } else {
                                view.initHaven(0);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }

    @Override
    public void getApply(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getApplyProjectServer(map).enqueue(new Callback<HandleEntity>() {
            @Override
            public void onResponse(Call<HandleEntity> call, Response<HandleEntity> response) {
                HandleEntity entity = response.body();
                view.initApply(entity);
            }

            @Override
            public void onFailure(Call<HandleEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}