package com.spring.chanba.presenter;

import com.spring.chanba.bean.TrainEnrolmentEntity;
import com.spring.chanba.contract.TrainEnrolmentContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 培训报名
 */
public class TrainEnrolmentPresenter implements TrainEnrolmentContract.Presenter {
    private TrainEnrolmentContract.View view;

    public TrainEnrolmentPresenter(TrainEnrolmentContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void getData(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getApplyTrainingList(map).enqueue(new Callback<TrainEnrolmentEntity>() {
            @Override
            public void onResponse(Call<TrainEnrolmentEntity> call, Response<TrainEnrolmentEntity> response) {
                TrainEnrolmentEntity entity = response.body();
                view.initData(entity);
            }

            @Override
            public void onFailure(Call<TrainEnrolmentEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
