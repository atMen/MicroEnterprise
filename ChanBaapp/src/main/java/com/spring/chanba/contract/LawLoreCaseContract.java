package com.spring.chanba.contract;

import com.spring.chanba.bean.BannerListEntity;
import com.spring.chanba.bean.LawServiceGridEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 法律知识/法律案例
 */
public class LawLoreCaseContract {
    public interface Presenter extends BasePresenter {
        void getLawLore(HashMap<String, String> map);

        void getLawCase(HashMap<String, String> map);

        void getLBanner(HashMap<String, String> map);
    }

    public interface View extends BaseView<Presenter> {
        /**
         * 返回服务器失败信息
         *
         * @param message
         */
        void failedMessage(String message);

        /**
         * 法律知识
         *
         * @param entity
         */
        void initLawLore(LawServiceListEntity entity);

        /**
         * 法律案例
         *
         * @param entity
         */
        void initLawCase(LawServiceGridEntity entity);

        /**
         * 轮播图
         *
         * @param entity
         */
        void initLBanner(BannerListEntity entity);
    }
}
