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
import com.spring.chanba.bean.LawServiceListEntity;
import com.spring.chanba.utils.Utils;

import java.util.List;

/**
 * 财税： 知识、案例
 */
public class PropertyLoreCaseAdapter extends RecyclerView.Adapter<PropertyLoreCaseAdapter.ViewHolder> {
    private Context mContext;
    private OnItemCallBack callBack;
    private List<LawServiceListEntity.DataBean> dataList;

    public PropertyLoreCaseAdapter(Context mContext, List<LawServiceListEntity.DataBean> itemList) {
        this.mContext = mContext;
        this.dataList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LawServiceListEntity.DataBean entity = dataList.get(position);
        if (!Utils.isStringEmpty(entity.getPicPath())) {
            Glide.with(mContext).load(entity.getPicPath()).into(holder.imgServicePicture);
        }
        holder.tvServiceContent.setText(entity.getTitle());
        holder.tvServiceRelasetime.setText(entity.getAddTime());
        holder.tvServiceType.setText(entity.getAuthor());
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
        private TextView tvServiceContent;
        private TextView tvServiceType;
        private TextView tvServiceRelasetime;
        private ImageView imgServicePicture;

        public ViewHolder(View view) {
            super(view);
            tvServiceContent = (TextView) view.findViewById(R.id.tv_service_content);
            tvServiceType = (TextView) view.findViewById(R.id.tv_service_type);
            tvServiceRelasetime = (TextView) view.findViewById(R.id.tv_service_relasetime);
            imgServicePicture = (ImageView) view.findViewById(R.id.img_service_picture);
        }
    }

    public void setOnItemClickListener(OnItemCallBack callBack) {
        this.callBack = callBack;
    }

    public interface OnItemCallBack {
        void setOnClickListener(String id);
    }
}