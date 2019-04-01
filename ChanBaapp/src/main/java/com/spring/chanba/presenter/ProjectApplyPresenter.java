package com.spring.chanba.presenter;

import com.spring.chanba.bean.ProjectApplyEntity;
import com.spring.chanba.contract.ProjectApplyContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 项目申请
 */
public class ProjectApplyPresenter implements ProjectApplyContract.Presenter {
    private ProjectApplyContract.View view;

    public ProjectApplyPresenter(ProjectApplyContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getApplyProjectServerList(map).enqueue(new Callback<ProjectApplyEntity>() {
            @Override
            public void onResponse(Call<ProjectApplyEntity> call, Response<ProjectApplyEntity> response) {
                ProjectApplyEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<ProjectApplyEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}