package com.spring.chanba.presenter;

import com.spring.chanba.bean.NoticeBannerEntity;
import com.spring.chanba.contract.BannerListContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 轮播图
 */
public class BannerListPresenter implements BannerListContract.Presenter {
    private BannerListContract.View view;

    public BannerListPresenter(BannerListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getIndexList(map).enqueue(new Callback<NoticeBannerEntity>() {
            @Override
            public void onResponse(Call<NoticeBannerEntity> call, Response<NoticeBannerEntity> response) {
                NoticeBannerEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<NoticeBannerEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
