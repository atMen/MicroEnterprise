package com.spring.chanba.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spring.chanba.R;
import com.spring.chanba.bean.LawServiceGridEntity;

import com.spring.chanba.utils.Utils;

import java.util.List;

public class LawCaseListAdapter extends RecyclerView.Adapter<LawCaseListAdapter.ViewHolder> {
    public Context mContext;
    private OnItemCallBack callBack;
    private List<LawServiceGridEntity.DataBean> dataList;

    public LawCaseListAdapter(Context mContext, List<LawServiceGridEntity.DataBean> itemList) {
        this.mContext = mContext;
        this.dataList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lawcase_listview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LawServiceGridEntity.DataBean entity = dataList.get(position);
        holder.tvLawcaseTitle.setText(entity.getTitle());
        holder.tvLawcaseAuthor.setText(entity.getAuthor());
        holder.tvLawcaseType.setText(entity.getFtitle());
        holder.tvLawcaseRelasetime.setText(entity.getAddTime());
        if (!Utils.isStringEmpty(entity.getPicPath())) {
            Glide.with(mContext).load(entity.getPicPath()).into(holder.imgLawcasePicture);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.setOnClickListener(entity.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvLawcaseTitle;
        private TextView tvLawcaseType;
        private TextView tvLawcaseAuthor;
        private TextView tvLawcaseRelasetime;
        private ImageView imgLawcasePicture;

        public ViewHolder(View view) {
            super(view);
            tvLawcaseTitle = (TextView) view.findViewById(R.id.tv_lawcase_title);
            tvLawcaseType = (TextView) view.findViewById(R.id.tv_lawcase_type);
            tvLawcaseAuthor = (TextView) view.findViewById(R.id.tv_lawcase_author);
            tvLawcaseRelasetime = (TextView) view.findViewById(R.id.tv_lawcase_relasetime);
            imgLawcasePicture = (ImageView) view.findViewById(R.id.img_lawcase_picture);
        }
    }

    public void setOnItemClickListener(OnItemCallBack callBack) {
        this.callBack = callBack;
    }

    public interface OnItemCallBack {
        void setOnClickListener(String id);
    }
}