package com.spring.chanba.presenter;

import com.spring.chanba.bean.LawServiceGridEntity;
import com.spring.chanba.contract.LawCaseContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 法律案例
 */
public class LawCasePresenter implements LawCaseContract.Presenter {
    public LawCaseContract.View view;

    public LawCasePresenter(LawCaseContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    /**
     * 法律案例
     *
     * @param map
     */
    @Override
    public void getLawCase(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getLawCaseList(map).enqueue(new Callback<LawServiceGridEntity>() {
            @Override
            public void onResponse(Call<LawServiceGridEntity> call, Response<LawServiceGridEntity> response) {
                LawServiceGridEntity entity = response.body();
                view.initLawCase(entity);
            }

            @Override
            public void onFailure(Call<LawServiceGridEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}