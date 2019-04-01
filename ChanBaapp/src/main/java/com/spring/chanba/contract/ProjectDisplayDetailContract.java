package com.spring.chanba.contract;

import com.spring.chanba.bean.HandleEntity;
import com.spring.chanba.bean.LawServiceDetailEntity;
import com.spring.chanba.interfaces.BasePresenter;
import com.spring.chanba.interfaces.BaseView;

import java.util.HashMap;

/**
 * 项目详情
 */
public class ProjectDisplayDetailContract {
    public interface Presenter extends BasePresenter {
        void getData(HashMap<String, String> map);

        void getHave(HashMap<String, String> map);

        void getApply(HashMap<String, String> map);
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

        /**
         * 查询企业
         *
         * @param flag
         */
        void initHaven(int flag);

        /**
         * 申请企业
         *
         * @param entity
         */
        void initApply(HandleEntity entity);
    }
}
