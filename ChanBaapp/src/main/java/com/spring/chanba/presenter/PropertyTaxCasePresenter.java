package com.spring.chanba.presenter;

import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.contract.PropertyTaxCaseContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 财税：经典案例
 */
public class PropertyTaxCasePresenter implements PropertyTaxCaseContract.Presenter {
    private PropertyTaxCaseContract.View view;

    public PropertyTaxCasePresenter(PropertyTaxCaseContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getCase(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getCaseList(map).enqueue(new Callback<LawServiceListEntity>() {
            @Override
            public void onResponse(Call<LawServiceListEntity> call, Response<LawServiceListEntity> response) {
                LawServiceListEntity entity = response.body();
                view.initCase(entity);
            }

            @Override
            public void onFailure(Call<LawServiceListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
