package com.spring.chanba.presenter;

import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.PromptlyConsultEntity;
import com.spring.chanba.contract.PromptlyConsultContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 立即咨询
 */
public class PromptlyConsultPresenter implements PromptlyConsultContract.Presenter {
    public PromptlyConsultContract.View view;

    public PromptlyConsultPresenter(PromptlyConsultContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getLawConsultList(map).enqueue(new Callback<PromptlyConsultEntity>() {
            @Override
            public void onResponse(Call<PromptlyConsultEntity> call, Response<PromptlyConsultEntity> response) {
                PromptlyConsultEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<PromptlyConsultEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }

    /**
     * 提交咨询
     *
     * @param map
     */
    @Override
    public void submit(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getLawConsult(map).enqueue(new Callback<HandleEntity>() {
            @Override
            public void onResponse(Call<HandleEntity> call, Response<HandleEntity> response) {
                HandleEntity entity = response.body();
                view.initSend(entity);
            }

            @Override
            public void onFailure(Call<HandleEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
