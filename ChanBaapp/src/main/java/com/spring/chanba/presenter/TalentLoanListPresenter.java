package com.spring.chanba.presenter;

import com.spring.chanba.bean.TalentLoanListEntity;
import com.spring.chanba.contract.TalentLoanListContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TalentLoanListPresenter implements TalentLoanListContract.Presenter {
    private TalentLoanListContract.View view;

    public TalentLoanListPresenter(TalentLoanListContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getApplyBankProductList(map).enqueue(new Callback<TalentLoanListEntity>() {
            @Override
            public void onResponse(Call<TalentLoanListEntity> call, Response<TalentLoanListEntity> response) {
                TalentLoanListEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<TalentLoanListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
