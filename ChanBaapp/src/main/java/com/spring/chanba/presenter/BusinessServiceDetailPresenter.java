package com.spring.chanba.presenter;

import com.spring.chanba.bean.FinanceServiceDetailEntity;
import com.spring.chanba.contract.BusinessServiceDetailContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 工商服务：详情
 */
public class BusinessServiceDetailPresenter implements BusinessServiceDetailContract.Presenter {
    private BusinessServiceDetailContract.View view;

    public BusinessServiceDetailPresenter(BusinessServiceDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getCase(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getBusinessDetail(map).enqueue(new Callback<FinanceServiceDetailEntity>() {
            @Override
            public void onResponse(Call<FinanceServiceDetailEntity> call, Response<FinanceServiceDetailEntity> response) {
                FinanceServiceDetailEntity entity = response.body();
                view.initCase(entity);
            }

            @Override
            public void onFailure(Call<FinanceServiceDetailEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}