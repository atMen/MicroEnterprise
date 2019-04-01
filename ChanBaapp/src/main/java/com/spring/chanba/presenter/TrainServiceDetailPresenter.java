package com.spring.chanba.presenter;

import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.TrainServiceDetailEntity;
import com.spring.chanba.contract.TrainServiceDetailContract;
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
 * 培训详情
 */
public class TrainServiceDetailPresenter implements TrainServiceDetailContract.Presenter {
    public TrainServiceDetailContract.View view;

    public TrainServiceDetailPresenter(TrainServiceDetailContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getTrainingDetail(map).enqueue(new Callback<TrainServiceDetailEntity>() {
            @Override
            public void onResponse(Call<TrainServiceDetailEntity> call, Response<TrainServiceDetailEntity> response) {
                TrainServiceDetailEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<TrainServiceDetailEntity> call, Throwable t) {
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
     * 报名申请
     *
     * @param map
     */
    @Override
    public void getApplyTrain(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getApplyTraining(map).enqueue(new Callback<HandleEntity>() {
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
