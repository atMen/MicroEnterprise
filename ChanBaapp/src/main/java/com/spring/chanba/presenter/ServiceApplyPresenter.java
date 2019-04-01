package com.spring.chanba.presenter;

import com.spring.chanba.bean.ServiceApplyListEntity;
import com.spring.chanba.contract.ServiceApplyContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 服务申请
 */
public class ServiceApplyPresenter implements ServiceApplyContract.Presenter {
    private ServiceApplyContract.View view;

    public ServiceApplyPresenter(ServiceApplyContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getApplyServerList(map).enqueue(new Callback<ServiceApplyListEntity>() {
            @Override
            public void onResponse(Call<ServiceApplyListEntity> call, Response<ServiceApplyListEntity> response) {
                ServiceApplyListEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<ServiceApplyListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}