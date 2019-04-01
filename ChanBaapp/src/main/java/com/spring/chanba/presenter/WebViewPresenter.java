package com.spring.chanba.presenter;

import com.spring.chanba.bean.NoticeDetailEntity;
import com.spring.chanba.contract.WebViewContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebViewPresenter implements WebViewContract.Presenter {
    private WebViewContract.View view;

    public WebViewPresenter(WebViewContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getCmsDetails(map).enqueue(new Callback<NoticeDetailEntity>() {
            @Override
            public void onResponse(Call<NoticeDetailEntity> call, Response<NoticeDetailEntity> response) {
                NoticeDetailEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<NoticeDetailEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
