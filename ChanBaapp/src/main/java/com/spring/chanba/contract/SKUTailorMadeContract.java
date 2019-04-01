package com.spring.chanba.contract;

import com.spring.chanba.bean.DictionaryTypeEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

public class SKUTailorMadeContract {
    public interface Presenter extends BasePresenter {
        void getTradeData(HashMap<String, String> map);

        void getScaleData(HashMap<String, String> map);

        void getTypeData(HashMap<String, String> map);
    }

    public interface View extends BaseView<Presenter> {
        /**
         * 返回服务器失败信息
         *
         * @param message
         */
        void failedMessage(String message);

        /**
         * 行业数据
         *
         * @param entity
         */
        void initTradeData(DictionaryTypeEntity entity);

        /**
         * 规模数据
         *
         * @param entity
         */
        void initScaleData(DictionaryTypeEntity entity);

        /**
         * 企业类型
         *
         * @param entity
         */
        void initTypeData(DictionaryTypeEntity entity);
    }
}
