package com.spring.chanba.contract;

import com.spring.chanba.bean.ServiceMenuEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 菜单
 */
public class ServiceMenuContract {
    public interface Presenter extends BasePresenter {
        void getMenuList(HashMap<String, String> map);
    }

    public interface View extends BaseView<Presenter> {
        /**
         * 返回服务器失败信息
         *
         * @param message
         */
        void failedMessage(String message);

        /**
         * 菜单
         *
         * @param entity
         */
        void initMenuList(ServiceMenuEntity entity);
    }
}
