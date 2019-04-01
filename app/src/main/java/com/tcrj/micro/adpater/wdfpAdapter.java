package com.tcrj.micro.adpater;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spring.chanba.ui.home.FinanceServiceActivity;
import com.tcrj.micro.R;
import com.tcrj.micro.activity.LoginActivity;
import com.tcrj.micro.entity.fpDataInfo;
import com.tcrj.micro.entity.fpdxInfo;
import com.tcrj.micro.entity.qyListInfo;
import com.tcrj.micro.until.ACache;

import java.util.List;



/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class wdfpAdapter extends BaseQuickAdapter<fpDataInfo.ContentBean, BaseViewHolder> {
    private Context mContext;

    public wdfpAdapter(@Nullable List<fpDataInfo.ContentBean> data, Context context) {
        super(R.layout.item_wdfp, data);
        this.mContext = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final fpDataInfo.ContentBean item) {

        String logintype = ACache.get(mContext).getAsString("logintype");

        LinearLayout ll_btn = helper.getView(R.id.ll_btn);
        TextView name = helper.getView(R.id.name);
        TextView address = helper.getView(R.id.address);
        TextView fptj = helper.getView(R.id.tv_fptj);

        TextView wyfp = helper.getView(R.id.wyfp);
        TextView yqtr = helper.getView(R.id.yqtr);


        if("1".equals(logintype)){
            ll_btn.setVisibility(View.GONE);
        }else {
            ll_btn.setVisibility(View.VISIBLE);
        }


        name.setText(item.getHomeName());
        address.setText("地址："+item.getCityName()+"-"+item.getCountyName()+"-"+item.getTownName());
        fptj.setText(item.getPoorReason());

        wyfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(onItemPlayClick != null) {
                    onItemPlayClick.onPlayItemClick(item.getId(),0);
                }

            }
        });

        yqtr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(onItemPlayClick != null) {
                    onItemPlayClick.onPlayItemClick(item.getId(),1);
                }
            }
        });

    }

    public static interface OnPlayClickListener {
        // true add; false cancel
        public void onPlayItemClick(String id,int position);
    }

    // add click callback
    OnPlayClickListener onItemPlayClick;

    public void setOnPlayClickListener(OnPlayClickListener onItemPlayClick) {
        this.onItemPlayClick = onItemPlayClick;
    }


}
