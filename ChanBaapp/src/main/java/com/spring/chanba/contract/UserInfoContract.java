package com.spring.chanba.contract;

import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.PersonInfoEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 编辑用户信息
 */
public class UserInfoContract {
    public interface Presenter extends BasePresenter {
        void getData(HashMap<String, String> map);

        void getMember(HashMap<String, String> map);
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
        void initData(HandleEntity entity);

        /**
         * 检查是否有用户信息
         *
         * @param entity
         */
        void initMember(PersonInfoEntity entity);
    }
}
