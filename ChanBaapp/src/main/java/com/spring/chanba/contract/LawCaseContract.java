package com.spring.chanba.contract;

import com.spring.chanba.bean.LawServiceGridEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 法律案例
 */
public class LawCaseContract {
    public interface Presenter extends BasePresenter {
        void getLawCase(HashMap<String, String> map);
    }

    public interface View extends BaseView<Presenter> {
        /**
         * 返回服务器失败信息
         *
         * @param message
         */
        void failedMessage(String message);

        /**
         * 法律案例
         *
         * @param entity
         */
        void initLawCase(LawServiceGridEntity entity);
    }
}
