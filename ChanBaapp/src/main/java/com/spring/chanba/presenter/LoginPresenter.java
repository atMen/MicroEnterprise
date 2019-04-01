package com.spring.chanba.presenter;

import com.spring.chanba.bean.UserInfoEntity;
import com.spring.chanba.contract.LoginContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {
    public LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getLogin(map).enqueue(new Callback<UserInfoEntity>() {
            @Override
            public void onResponse(Call<UserInfoEntity> call, Response<UserInfoEntity> response) {
                UserInfoEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<UserInfoEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
