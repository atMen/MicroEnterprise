package com.spring.chanba.contract;

import com.spring.chanba.bean.FinanceServiceDetailEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 金融：案例
 */
public class FinanceCaseDetailContract {
    public interface Presenter extends BasePresenter {
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
         * 工商知识
         *
         * @param entity
         */
        void initCase(FinanceServiceDetailEntity entity);
    }
}
