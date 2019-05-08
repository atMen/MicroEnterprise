package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.grzpListInfo;
import com.tcrj.micro.entity.qyzpListInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class qyzpAdapter extends BaseQuickAdapter<grzpListInfo.DataBean.ContentBean, BaseViewHolder> {

    private Context mContext;
    private String state;
    private String suitable;

    public qyzpAdapter(@Nullable List<grzpListInfo.DataBean.ContentBean> data, Context context) {
        super(R.layout.item_qyyp, data);
        this.mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, grzpListInfo.DataBean.ContentBean item) {

        /**
         * applyDate : 2019-03-15 14:51:01
         * enterpriseId : ff8080816488a9e1016488ae7a450003
         * hasSend :
         * id : ff808081695aef420169801e47a70124
         * isSuitable : 1
         * jobId : ff808081690dce960169466b11070018
         * jobName : 2
         * optime : 2019-04-02 09:28:43
         * replyContent : 合适人选
         * replyDate : 2019-03-15 15:13:17
         * resumeId : ff808081695aef420169801ab342011e
         * sendName : 小曲
         * status : 1
         * userId : ff808081695aef42016976055d0400f8
         */

        String status = item.getStatus();
        if("0".equals(status)){
            state = "待处理";
        }else if("1".equals(status)){
            state = "待沟通";
        }else if("2".equals(status)){
            state = "已拒绝";
        }

        String table = item.getIsSuitable();
        if("0".equals(table)){
            suitable = "待处理";
        }else if("1".equals(table)){
            suitable = "合适";
        }else if("2".equals(table)){
            suitable = "不合适";
        }

        helper.setText(R.id.tv_zw, item.getJobName());
        helper.setText(R.id.name, item.getSendName());
        helper.setText(R.id.time, item.getApplyDate());
        helper.setText(R.id.state, state);
        helper.setText(R.id.sfyp, suitable);

//        helper.setText(R.id.zy, item.getSalarRange());
//        helper.setText(R.id.dz, item.getEducationBackgroundRequire());
//        helper.setText(R.id.xl, item.getAgeRequire());
//        helper.setText(R.id.gznx, item.getJobCityName());
//        helper.setText(R.id.xz, item.getJobCountyName());
//        helper.setText(R.id.time, item.getJobCountyName());
//        helper.setText(R.id.state, item.getJobCountyName());
//        helper.setText(R.id.sfyp, item.getJobCountyName());





    }

}
