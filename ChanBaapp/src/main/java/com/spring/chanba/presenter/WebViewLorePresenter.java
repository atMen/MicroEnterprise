package com.spring.chanba.presenter;

import com.spring.chanba.bean.LawServiceDetailEntity;
import com.spring.chanba.contract.WebViewLoreContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 法律知识
 */
public class WebViewLorePresenter implements WebViewLoreContract.Presenter {
    public WebViewLoreContract.View view;

    public WebViewLorePresenter(WebViewLoreContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getLawInfoDetail(map).enqueue(new Callback<LawServiceDetailEntity>() {
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
}