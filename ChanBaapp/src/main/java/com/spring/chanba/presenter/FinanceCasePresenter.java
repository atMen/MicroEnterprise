package com.spring.chanba.presenter;

import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.contract.FinanceCaseContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 金融：经典案例
 */
public class FinanceCasePresenter implements FinanceCaseContract.Presenter {
    private FinanceCaseContract.View view;

    public FinanceCasePresenter(FinanceCaseContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getCase(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getFinanceCaseList(map).enqueue(new Callback<LawServiceListEntity>() {
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
