package com.spring.chanba.presenter;

import com.spring.chanba.bean.BannerListEntity;
import com.spring.chanba.bean.LawServiceGridEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.contract.LawLoreCaseContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 法律知识/法律案例
 */
public class LawLoreCasePresenter implements LawLoreCaseContract.Presenter {
    public LawLoreCaseContract.View view;

    public LawLoreCasePresenter(LawLoreCaseContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    /**
     * 法律知识
     *
     * @param map
     */
    @Override
    public void getLawLore(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getLawInfoList(map).enqueue(new Callback<LawServiceListEntity>() {
            @Override
            public void onResponse(Call<LawServiceListEntity> call, Response<LawServiceListEntity> response) {
                LawServiceListEntity entity = response.body();
                view.initLawLore(entity);
            }

            @Override
            public void onFailure(Call<LawServiceListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }

    /**
     * 法律案例
     *
     * @param map
     */
    @Override
    public void getLawCase(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getLawCaseList(map).enqueue(new Callback<LawServiceGridEntity>() {
            @Override
            public void onResponse(Call<LawServiceGridEntity> call, Response<LawServiceGridEntity> response) {
                LawServiceGridEntity entity = response.body();
                view.initLawCase(entity);
            }

            @Override
            public void onFailure(Call<LawServiceGridEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }

    /**
     * 轮播图
     *
     * @param map
     */
    @Override
    public void getLBanner(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getCmsList(map).enqueue(new Callback<BannerListEntity>() {
            @Override
            public void onResponse(Call<BannerListEntity> call, Response<BannerListEntity> response) {
                BannerListEntity entity = response.body();
                view.initLBanner(entity);
            }

            @Override
            public void onFailure(Call<BannerListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}