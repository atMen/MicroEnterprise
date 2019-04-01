package com.spring.chanba.presenter;

import com.spring.chanba.bean.DictionaryTypeEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.contract.BusinessHatchContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 创业孵化
 */
public class BusinessHatchPresenter implements BusinessHatchContract.Presenter {
    public BusinessHatchContract.View view;

    public BusinessHatchPresenter(BusinessHatchContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getHatchList(map).enqueue(new Callback<LawServiceListEntity>() {
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

    @Override
    public void getType(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getDictionaryList(map).enqueue(new Callback<DictionaryTypeEntity>() {
            @Override
            public void onResponse(Call<DictionaryTypeEntity> call, Response<DictionaryTypeEntity> response) {
                DictionaryTypeEntity entity = response.body();
                view.initType(entity);
            }

            @Override
            public void onFailure(Call<DictionaryTypeEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
