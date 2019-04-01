package com.spring.chanba.presenter;

import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.contract.ProjectDisplayContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 项目列表
 */
public class ProjectDisplayPresenter implements ProjectDisplayContract.Presenter {
    public ProjectDisplayContract.View view;

    public ProjectDisplayPresenter(ProjectDisplayContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getProjectServerList(map).enqueue(new Callback<LawServiceListEntity>() {
            @Override
            public void onResponse(Call<LawServiceListEntity> call, Response<LawServiceListEntity> response) {
                LawServiceListEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<LawServiceListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}