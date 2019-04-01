package com.spring.chanba.presenter;

import com.spring.chanba.bean.DictionaryTypeEntity;
import com.spring.chanba.contract.MeasureListContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeasureListPresenter implements MeasureListContract.Presenter {
    private MeasureListContract.View view;

    public MeasureListPresenter(MeasureListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getDictionaryList(map).enqueue(new Callback<DictionaryTypeEntity>() {
            @Override
            public void onResponse(Call<DictionaryTypeEntity> call, Response<DictionaryTypeEntity> response) {
                DictionaryTypeEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<DictionaryTypeEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
