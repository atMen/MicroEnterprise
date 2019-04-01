package com.spring.chanba.presenter;

import com.spring.chanba.bean.ServiceMenuEntity;
import com.spring.chanba.contract.ServiceMenuContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 菜单
 */
public class ServiceMenuPresenter implements ServiceMenuContract.Presenter {
    private ServiceMenuContract.View view;

    public ServiceMenuPresenter(ServiceMenuContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getMenuList(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getServerList(map).enqueue(new Callback<ServiceMenuEntity>() {
            @Override
            public void onResponse(Call<ServiceMenuEntity> call, Response<ServiceMenuEntity> response) {
                ServiceMenuEntity entity = response.body();
                view.initMenuList(entity);
            }

            @Override
            public void onFailure(Call<ServiceMenuEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
