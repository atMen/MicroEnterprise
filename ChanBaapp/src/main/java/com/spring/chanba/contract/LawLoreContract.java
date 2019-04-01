package com.spring.chanba.contract;

import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 法律知识
 */
public class LawLoreContract {

    public interface Presenter extends BasePresenter {
        void getLawLore(HashMap<String, String> map);
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
    }
}
