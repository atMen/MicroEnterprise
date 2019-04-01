package com.spring.chanba.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spring.chanba.R;
import com.spring.chanba.application.BaseApplication;
import com.spring.chanba.bean.PromptlyConsultEntity;
import com.spring.chanba.bean.WeChatUserInfoEntity;
import com.spring.chanba.utils.Utils;
import com.spring.chanba.views.CircleImageView;

import java.util.List;

/**
 * 立即咨询
 */
public class PromptlyConsultAdapter extends RecyclerView.Adapter<PromptlyConsultAdapter.ViewHolder> {
    public Context mContext;
    private List<PromptlyConsultEntity.DataBean> dataList;

    public PromptlyConsultAdapter(Context mContext, List<PromptlyConsultEntity.DataBean> itemList) {
        this.mContext = mContext;
        this.dataList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_promptly_listview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final PromptlyConsultEntity.DataBean entity = dataList.get(position);
        WeChatUserInfoEntity bean = BaseApplication.getUserInfoEntity();
        holder.tvPromptlyName.setText(bean.getNickname());
        holder.tvPromptlyTime.setText(entity.getConsultTime());
        holder.tvPromptlyContent.setText(entity.getConsultContent());
        holder.tvPromptlyReply.setText(entity.getAnswerTime());
        if (!Utils.isStringEmpty(bean.getHeadimgurl())) {
            Glide.with(mContext).load(bean.getHeadimgurl()).into(holder.imgPromptlyPhoto);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgPromptlyPhoto;
        private TextView tvPromptlyName;
        private TextView tvPromptlyTime;
        private TextView tvPromptlyContent;
        private TextView tvPromptlyReply;

        public ViewHolder(View view) {
            super(view);
            imgPromptlyPhoto = (CircleImageView) view.findViewById(R.id.img_promptly_photo);
            tvPromptlyName = (TextView) view.findViewById(R.id.tv_promptly_name);
            tvPromptlyTime = (TextView) view.findViewById(R.id.tv_promptly_time);
            tvPromptlyContent = (TextView) view.findViewById(R.id.tv_promptly_content);
            tvPromptlyReply = (TextView) view.findViewById(R.id.tv_promptly_reply);
        }
    }
}