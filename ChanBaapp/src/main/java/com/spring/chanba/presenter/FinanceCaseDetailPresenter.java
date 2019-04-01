package com.spring.chanba.presenter;

import com.spring.chanba.bean.FinanceServiceDetailEntity;
import com.spring.chanba.contract.FinanceCaseDetailContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 金融：金融案例
 */
public class FinanceCaseDetailPresenter implements FinanceCaseDetailContract.Presenter {
    private FinanceCaseDetailContract.View view;

    public FinanceCaseDetailPresenter(FinanceCaseDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getCase(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getFinanceCaseDetail(map).enqueue(new Callback<FinanceServiceDetailEntity>() {
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
