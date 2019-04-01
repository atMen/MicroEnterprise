package com.spring.chanba.presenter;

import com.spring.chanba.bean.BannerListEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.bean.ServiceMenuEntity;
import com.spring.chanba.contract.FinanceServiceContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 金融服务
 */
public class FinanceServicePresenter implements FinanceServiceContract.Presenter {
    private FinanceServiceContract.View view;

    public FinanceServicePresenter(FinanceServiceContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    /**
     * 轮番图
     *
     * @param map
     */
    @Override
    public void getBanner(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getCmsList(map).enqueue(new Callback<BannerListEntity>() {
            @Override
            public void onResponse(Call<BannerListEntity> call, Response<BannerListEntity> response) {
                BannerListEntity entity = response.body();
                view.initBanner(entity);
            }

            @Override
            public void onFailure(Call<BannerListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }

    /**
     * 金融菜单
     *
     * @param map
     */
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

    /**
     * 金融知识
     *
     * @param map
     */
    @Override
    public void getLore(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getFinanceInfoList(map).enqueue(new Callback<LawServiceListEntity>() {
            @Override
            public void onResponse(Call<LawServiceListEntity> call, Response<LawServiceListEntity> response) {
                LawServiceListEntity entity = response.body();
                view.initLore(entity);
            }

            @Override
            public void onFailure(Call<LawServiceListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }

    /**
     * 金融案例
     *
     * @param map
     */
    @Override
    public void getCase(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getFinanceCaseList(map).enqueue(new Callback<LawServiceListEntity>() {
            @Override
            public void onResponse(Call<LawServiceListEntity> call, Response<LawServiceListEntity> response) {
                LawServiceListEntity entity = response.body();
                view.initCase(entity);
            }

            @Override
            public void onFailure(Call<LawServiceListEntity> call, Throwable t) {
                view.failedMessage(t.getMessage());
            }
        });
    }
}
