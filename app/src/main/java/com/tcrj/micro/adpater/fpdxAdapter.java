package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.R;

import com.tcrj.micro.entity.fpjlListInfo;


import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class fpdxAdapter extends BaseQuickAdapter<fpjlListInfo.ContentBean, BaseViewHolder> {

    private Context mContext;

    public fpdxAdapter(@Nullable List<fpjlListInfo.ContentBean> data, Context context) {
        super(R.layout.item_fpjl, data);
        this.mContext = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final fpjlListInfo.ContentBean item) {

        TextView name = helper.getView(R.id.name);
        TextView address = helper.getView(R.id.address);
        TextView fptj = helper.getView(R.id.tv_fptj);

        TextView wyfp = helper.getView(R.id.wyfp);
        TextView yqtr = helper.getView(R.id.yqtr);

        helper.setText(R.id.jzfp, "捐助扶贫："+item.getAidPoorMoney());
        helper.setText(R.id.zlbf, "智力帮扶："+item.getAidPoorIntelligence());
        helper.setText(R.id.jyfp, "就业扶贫："+item.getAidPoorJob());
        helper.setText(R.id.cybf, "产业帮扶："+item.getAidPoorIndustry());
        helper.setText(R.id.smfp, "商贸扶贫："+item.getAidPoorBussiness());
        helper.setText(R.id.qtfs, "其他方式："+item.getAidPoorOtherType());


        name.setText(item.getHomeName());
        address.setText("地址："+item.getCityName()+"-"+item.getCountyName()+"-"+item.getTownName());
        fptj.setText(item.getPoorReason());

        wyfp.setText("修改信息");
        wyfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(onItemPlayClick != null) {
                    onItemPlayClick.onPlayItemClick(item,0);
                }

            }
        });
        yqtr.setText("取消扶贫");
        yqtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(onItemPlayClick != null) {
                    onItemPlayClick.onPlayItemClick(item,1);
                }

            }
        });
    }


    public interface OnPlayClickListener {
         void onPlayItemClick(fpjlListInfo.ContentBean id,int position);
    }

    OnPlayClickListener onItemPlayClick;
    public void setOnPlayClickListener(OnPlayClickListener onItemPlayClick) {
        this.onItemPlayClick = onItemPlayClick;
    }


}
