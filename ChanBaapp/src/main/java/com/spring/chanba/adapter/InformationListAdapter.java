package com.spring.chanba.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spring.chanba.R;
import com.spring.chanba.bean.InformationEntity;
import com.spring.chanba.loader.GlideRectangleTransform;
import com.spring.chanba.ui.WebViewActivity;
import com.spring.chanba.utils.Utils;

import java.util.List;

public class InformationListAdapter extends RecyclerView.Adapter<InformationListAdapter.ViewHolder> {
    public Context mContext;
    private List<InformationEntity.DataBean> dataList;

    public InformationListAdapter(Context mContext, List<InformationEntity.DataBean> itemList) {
        this.mContext = mContext;
        this.dataList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final InformationEntity.DataBean entity = dataList.get(position);
        holder.tvInfoTitle.setText(entity.getTitle());
        holder.tvInfoContent.setText(entity.getAstitle());
        holder.tvInfoType.setText(entity.getAuthor());
        holder.tvInfoRelasetime.setText(entity.getPubTime());
        if (!Utils.isStringEmpty(entity.getFilePath())) {
            RequestOptions options = new RequestOptions().transform(new GlideRectangleTransform(mContext, 5));
            Glide.with(mContext).load(entity.getFilePath()).apply(options).into(holder.imgInfoLogo);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("id", entity.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvInfoTitle;
        private TextView tvInfoContent;
        private TextView tvInfoType;
        private TextView tvInfoRelasetime;
        private ImageView imgInfoLogo;

        public ViewHolder(View view) {
            super(view);
            tvInfoTitle = (TextView) view.findViewById(R.id.tv_info_title);
            tvInfoContent = (TextView) view.findViewById(R.id.tv_info_content);
            tvInfoType = (TextView) view.findViewById(R.id.tv_info_type);
            tvInfoRelasetime = (TextView) view.findViewById(R.id.tv_info_relasetime);
            imgInfoLogo = (ImageView) view.findViewById(R.id.img_info_logo);
        }
    }
}
