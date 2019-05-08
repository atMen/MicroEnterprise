package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.qyzpListInfo;
import com.tcrj.micro.entity.tdjlInfo;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class tdjlAdapter extends BaseQuickAdapter<tdjlInfo.ContentBean, BaseViewHolder> {

    private Context mContext;

    private String state;
    private String suitable;

    public tdjlAdapter(@Nullable List<tdjlInfo.ContentBean> data, Context context) {
        super(R.layout.item_tdjl, data);
        this.mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, tdjlInfo.ContentBean item) {

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
        helper.setText(R.id.state, "状态："+state);
        helper.setText(R.id.time, item.getOptime());
        helper.setText(R.id.sfhs, suitable);


    }

}
