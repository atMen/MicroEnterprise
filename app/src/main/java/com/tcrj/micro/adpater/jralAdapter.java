package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.bankalInfo;
import com.tcrj.micro.entity.fpDataInfo;
import com.tcrj.micro.entity.qyzpListInfo;
import com.tcrj.micro.until.ShowImageUtils;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class jralAdapter extends BaseQuickAdapter<bankalInfo.ContentBean, BaseViewHolder> {


    private Context mContext;
    private int type;

    public jralAdapter(@Nullable List<bankalInfo.ContentBean> data, int type, Context context) {
        super(R.layout.jral_list_item, data);
        this.type = type;
        this.mContext = context;
    }

    @Override
    protected void convert(final BaseViewHolder helper, bankalInfo.ContentBean item) {

        ImageView imageView = helper.getView(R.id.jral_bg);
        ShowImageUtils.showjrcpImageView(mContext,
                ApiConstants.BASEIMAGE+item.getImg(),imageView);
        helper.setText(R.id.title, type == 0? item.getTitle() : item.getName());
        helper.setText(R.id.date, item.getIntroduction());

    }

}
