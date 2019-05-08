package com.tcrj.micro.adpater;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tcrj.micro.ApiConstants;
import com.tcrj.micro.R;
import com.tcrj.micro.entity.fpjlListInfo;

import org.w3c.dom.Text;

import java.util.List;


/**
 * desc: .
 * author: Will .
 * date: 2017/9/27 .
 */
public class fpdtInfoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context mContext;

    public fpdtInfoAdapter(@Nullable List<String> data, Context context) {
        super(R.layout.item_fpdtinfo, data);
        this.mContext = context;
    }


    @Override
    protected void convert(final BaseViewHolder helper, final String item) {

        ImageView imageView = helper.getView(R.id.iv_dtfp);

        if(item != null){
            Glide.with(mContext).load(ApiConstants.BASEIMAGE+item).into(imageView);
        }else {
            Glide.with(mContext).load("").into(imageView);
        }

    }

}
