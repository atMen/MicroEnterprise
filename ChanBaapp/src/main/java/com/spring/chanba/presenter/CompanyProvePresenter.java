package com.spring.chanba.presenter;

import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.contract.CompanyProveContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 企业认证
 */
public class CompanyProvePresenter implements CompanyProveContract.Presenter {
    public CompanyProveContract.View view;

    public CompanyProvePresenter(CompanyProveContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getSaveCompanyInfo(map).enqueue(new Callback<HandleEntity>() {
            @Override
            public void onResponse(Call<HandleEntity> call, Response<HandleEntity> response) {
                HandleEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<HandleEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
