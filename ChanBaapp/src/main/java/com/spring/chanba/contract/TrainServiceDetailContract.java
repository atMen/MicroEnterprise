package com.spring.chanba.contract;

import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.TrainServiceDetailEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 培训服务：详情
 */
public class TrainServiceDetailContract {
    public interface Presenter extends BasePresenter {
        void getData(HashMap<String, String> map);

        void getMember(HashMap<String, String> map);

        void getApplyTrain(HashMap<String, String> map);
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
        void initData(TrainServiceDetailEntity entity);

        /**
         * 会员基本信息
         *
         * @param isFlag
         */
        void initMember(int isFlag);

        /**
         * 申请
         *
         * @param entity
         */
        void initApply(HandleEntity entity);
    }
}
