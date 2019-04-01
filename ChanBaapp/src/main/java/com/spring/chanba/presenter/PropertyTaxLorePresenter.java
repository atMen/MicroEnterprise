package com.spring.chanba.presenter;

import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.contract.PropertyTaxLoreContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 财税：财税知识
 */
public class PropertyTaxLorePresenter implements PropertyTaxLoreContract.Presenter {
    private PropertyTaxLoreContract.View view;

    public PropertyTaxLorePresenter(PropertyTaxLoreContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getLore(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getFiscalInfoList(map).enqueue(new Callback<LawServiceListEntity>() {
            @Override
            public void onResponse(Call<LawServiceListEntity> call, Response<LawServiceListEntity> response) {
                LawServiceListEntity entity = response.body();
                view.initLore(entity);
            }

            @Override
            public void onFailure(Call<LawServiceListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}