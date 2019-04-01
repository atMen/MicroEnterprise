package com.spring.chanba.contract;

import com.spring.chanba.bean.LawServiceDetailEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 法律知识
 */
public class WebViewLoreContract {
    public interface Presenter extends BasePresenter {
        void getData(HashMap<String, String> map);
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
        void initData(LawServiceDetailEntity entity);
    }
}
