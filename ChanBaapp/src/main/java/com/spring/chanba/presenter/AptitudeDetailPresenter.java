package com.spring.chanba.presenter;

import com.spring.chanba.bean.AptitudeDetailEntity;
import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.contract.AptitudeDetailContract;
import com.spring.chanba.utils.RetrofitUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AptitudeDetailPresenter implements AptitudeDetailContract.Presenter {
    private AptitudeDetailContract.View view;

    public AptitudeDetailPresenter(AptitudeDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getServerDetail(map).enqueue(new Callback<AptitudeDetailEntity>() {
            @Override
            public void onResponse(Call<AptitudeDetailEntity> call, Response<AptitudeDetailEntity> response) {
                AptitudeDetailEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<AptitudeDetailEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }

    /**
     * 判断用户信息是否完善
     *
     * @param map
     */
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
                                view.initMember(1);
                            } else {
                                view.initMember(0);
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

    /**
     * 服务申请
     *
     * @param map
     */
    @Override
    public void getApply(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getApplyServer(map).enqueue(new Callback<HandleEntity>() {
            @Override
            public void onResponse(Call<HandleEntity> call, Response<HandleEntity> response) {
                HandleEntity entity = response.body();
                view.initApply(entity);
            }

            @Override
            public void onFailure(Call<HandleEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
