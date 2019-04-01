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
public class fpdxAdapter extends BaseQuickAdapter<fpjlListInfo.DataBean, BaseViewHolder> {
    private Context mContext;

    public fpdxAdapter(@Nullable List<fpjlListInfo.DataBean> data, Context context) {
        super(R.layout.item_fpjl, data);
        this.mContext = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final fpjlListInfo.DataBean item) {

        TextView name = helper.getView(R.id.name);
        TextView address = helper.getView(R.id.address);
        TextView fptj = helper.getView(R.id.tv_fptj);

        TextView wyfp = helper.getView(R.id.wyfp);
        TextView yqtr = helper.getView(R.id.yqtr);


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

    public static interface OnPlayClickListener {
        // true add; false cancel
        public void onPlayItemClick(fpjlListInfo.DataBean id,int position);
    }

    // add click callback
    OnPlayClickListener onItemPlayClick;

    public void setOnPlayClickListener(OnPlayClickListener onItemPlayClick) {
        this.onItemPlayClick = onItemPlayClick;
    }



}
