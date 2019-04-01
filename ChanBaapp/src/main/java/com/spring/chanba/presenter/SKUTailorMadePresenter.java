package com.spring.chanba.presenter;

import com.spring.chanba.bean.DictionaryTypeEntity;
import com.spring.chanba.contract.SKUTailorMadeContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SKUTailorMadePresenter implements SKUTailorMadeContract.Presenter {
    private SKUTailorMadeContract.View view;

    public SKUTailorMadePresenter(SKUTailorMadeContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    /**
     * 行业
     *
     * @param map
     */
    @Override
    public void getTradeData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getDictionaryList(map).enqueue(new Callback<DictionaryTypeEntity>() {
            @Override
            public void onResponse(Call<DictionaryTypeEntity> call, Response<DictionaryTypeEntity> response) {
                DictionaryTypeEntity entity = response.body();
                view.initTradeData(entity);
            }

            @Override
            public void onFailure(Call<DictionaryTypeEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }

    /**
     * 规模
     *
     * @param map
     */
    @Override
    public void getScaleData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getDictionaryList(map).enqueue(new Callback<DictionaryTypeEntity>() {
            @Override
            public void onResponse(Call<DictionaryTypeEntity> call, Response<DictionaryTypeEntity> response) {
                DictionaryTypeEntity entity = response.body();
                view.initScaleData(entity);
            }

            @Override
            public void onFailure(Call<DictionaryTypeEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }

    /**
     * 企业类型
     *
     * @param map
     */
    @Override
    public void getTypeData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getDictionaryList(map).enqueue(new Callback<DictionaryTypeEntity>() {
            @Override
            public void onResponse(Call<DictionaryTypeEntity> call, Response<DictionaryTypeEntity> response) {
                DictionaryTypeEntity entity = response.body();
                view.initTypeData(entity);
            }

            @Override
            public void onFailure(Call<DictionaryTypeEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}