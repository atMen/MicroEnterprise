package com.spring.chanba.presenter;

import com.spring.chanba.bean.InformationEntity;
import com.spring.chanba.contract.InformationContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 资讯
 */
public class InformationPresenter implements InformationContract.Presenter {
    public InformationContract.View view;

    public InformationPresenter(InformationContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getInfomationList(map).enqueue(new Callback<InformationEntity>() {
            @Override
            public void onResponse(Call<InformationEntity> call, Response<InformationEntity> response) {
                InformationEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<InformationEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
