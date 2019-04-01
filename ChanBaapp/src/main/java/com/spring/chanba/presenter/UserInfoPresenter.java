package com.spring.chanba.presenter;

import com.google.gson.Gson;
import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.PersonInfoEntity;
import com.spring.chanba.contract.UserInfoContract;
import com.spring.chanba.utils.RetrofitUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 编辑用户信息
 */
public class UserInfoPresenter implements UserInfoContract.Presenter {
    public UserInfoContract.View view;

    public UserInfoPresenter(UserInfoContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getEditMemberBasicInfo(map).enqueue(new Callback<HandleEntity>() {
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

    @Override
    public void getMember(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().isMemBasicInfoCompleted(map).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String strResponse = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(strResponse);
                        String strValue = jsonObject.getString("data");
                        if (jsonObject.getInt("state") == 1) {
                            if (strValue.length() > 1) {
                                Gson gs = new Gson();
                                PersonInfoEntity entity = gs.fromJson(strResponse, PersonInfoEntity.class);
                                view.initMember(entity);
                            } else {
                                view.initMember(null);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
