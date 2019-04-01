package com.spring.chanba.presenter;

import com.spring.chanba.bean.BannerListEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.bean.ServiceMenuEntity;
import com.spring.chanba.contract.BusinessServiceContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 工商服务
 */
public class BusinessServicePresenter implements BusinessServiceContract.Presenter {
    private BusinessServiceContract.View view;

    public BusinessServicePresenter(BusinessServiceContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    /**
     * 轮播图
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
     * 获取菜单数据
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
     * 工商知识
     *
     * @param map
     */
    @Override
    public void getLore(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getBusinessList(map).enqueue(new Callback<LawServiceListEntity>() {
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
     * 经典案例
     *
     * @param map
     */
    @Override
    public void getCase(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getBusinessList(map).enqueue(new Callback<LawServiceListEntity>() {
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
