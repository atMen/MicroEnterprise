package com.spring.chanba.presenter;

import com.spring.chanba.bean.BannerListEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.bean.ServiceMenuEntity;
import com.spring.chanba.contract.PropertyTaxContract;
import com.spring.chanba.utils.RetrofitUtil;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 财税服务
 */
public class PropertyTaxPresenter implements PropertyTaxContract.Presenter {
    private PropertyTaxContract.View view;

    public PropertyTaxPresenter(PropertyTaxContract.View view) {
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
     * 菜单
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
     * 财税知识
     *
     * @param map
     */
    @Override
    public void getLore(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getFiscalInfoList(map).enqueue(new Callback<LawServiceListEntity>() {
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
     * 知识案例
     *
     * @param map
     */
    @Override
    public void getCase(HashMap<String, String> map) {
        RetrofitUtil.initRequestURL().getCaseList(map).enqueue(new Callback<LawServiceListEntity>() {
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
