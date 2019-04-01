package com.spring.chanba.contract;

import com.spring.chanba.bean.DictionaryTypeEntity;
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 创业孵化：列表
 */
public class BusinessHatchContract {
    public interface Presenter extends BasePresenter {
        void getData(HashMap<String, String> map);

        void getType(HashMap<String, String> map);
    }

    public interface View extends BaseView<Presenter> {
        /**
         * 返回服务器失败信息
         *
         * @param message
         */
        void failedMessage(String message);

        /**
         * 返回成功数据
         *
         * @param entity
         */
        void initData(LawServiceListEntity entity);

        /**
         * 分类
         *
         * @param entity
         */
        void initType(DictionaryTypeEntity entity);
    }
}
