package com.spring.chanba.presenter;

import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.contract.BusinessServiceLoreContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 工商：工商知识
 */
public class BusinessServiceLorePresenter implements BusinessServiceLoreContract.Presenter {
    private BusinessServiceLoreContract.View view;

    public BusinessServiceLorePresenter(BusinessServiceLoreContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getLore(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getBusinessList(map).enqueue(new Callback<LawServiceListEntity>() {
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
