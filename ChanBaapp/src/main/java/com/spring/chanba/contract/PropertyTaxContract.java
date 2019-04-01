package com.spring.chanba.contract;

import com.spring.chanba.bean.BannerListEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.bean.ServiceMenuEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 财税服务
 */
public class PropertyTaxContract {
    public interface Presenter extends BasePresenter {
        void getBanner(HashMap<String, String> map);

        void getMenuList(HashMap<String, String> map);

        void getLore(HashMap<String, String> map);

        void getCase(HashMap<String, String> map);
    }

    public interface View extends BaseView<Presenter> {
        /**
         * 返回服务器失败信息
         *
         * @param message
         */
        void failedMessage(String message);

        /**
         * 轮播图
         *
         * @param entity
         */
        void initBanner(BannerListEntity entity);

        /**
         * 菜单
         *
         * @param entity
         */
        void initMenuList(ServiceMenuEntity entity);

        /**
         * 工商知识
         *
         * @param entity
         */
        void initLore(LawServiceListEntity entity);

        /**
         * 经典案例
         *
         * @param entity
         */
        void initCase(LawServiceListEntity entity);
    }
}
