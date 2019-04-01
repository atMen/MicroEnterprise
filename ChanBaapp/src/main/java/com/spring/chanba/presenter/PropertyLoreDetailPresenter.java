package com.spring.chanba.presenter;

import com.spring.chanba.bean.FinanceServiceDetailEntity;
import com.spring.chanba.contract.PropertyLoreDetailContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 财税知识：详情
 */
public class PropertyLoreDetailPresenter implements PropertyLoreDetailContract.Presenter {
    private PropertyLoreDetailContract.View view;

    public PropertyLoreDetailPresenter(PropertyLoreDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getLore(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getFiscalInfoDetail(map).enqueue(new Callback<FinanceServiceDetailEntity>() {
            @Override
            public void onResponse(Call<FinanceServiceDetailEntity> call, Response<FinanceServiceDetailEntity> response) {
                FinanceServiceDetailEntity entity = response.body();
                view.initLore(entity);
            }

            @Override
            public void onFailure(Call<FinanceServiceDetailEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
