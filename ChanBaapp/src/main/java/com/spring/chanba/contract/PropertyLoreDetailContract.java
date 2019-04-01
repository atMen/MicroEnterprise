package com.spring.chanba.contract;

import com.spring.chanba.bean.FinanceServiceDetailEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 财税：财税知识详情
 */
public class PropertyLoreDetailContract {
    public interface Presenter extends BasePresenter {

        void getLore(HashMap<String, String> map);
    }

    public interface View extends BaseView<Presenter> {
        /**
         * 返回服务器失败信息
         *
         * @param message
         */
        void failedMessage(String message);

        /**
         * 工商知识
         *
         * @param entity
         */
        void initLore(FinanceServiceDetailEntity entity);
    }
}
