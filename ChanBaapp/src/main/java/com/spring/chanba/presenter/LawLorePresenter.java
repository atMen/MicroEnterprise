package com.spring.chanba.presenter;

import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.contract.LawLoreContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 法律知识
 */
public class LawLorePresenter implements LawLoreContract.Presenter {
    public LawLoreContract.View view;

    public LawLorePresenter(LawLoreContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    /**
     * 法律知识
     *
     * @param map
     */
    @Override
    public void getLawLore(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getLawInfoList(map).enqueue(new Callback<LawServiceListEntity>() {
            @Override
            public void onResponse(Call<LawServiceListEntity> call, Response<LawServiceListEntity> response) {
                LawServiceListEntity entity = response.body();
                view.initLawLore(entity);
            }

            @Override
            public void onFailure(Call<LawServiceListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}