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
 * 培训服务
 */
public class BusinessHatchAdapter extends RecyclerView.Adapter<BusinessHatchAdapter.ViewHolder> {
    public Context mContext;
    private OnItemCallBack callBack;
    private List<LawServiceListEntity.DataBean> dataList;

    public BusinessHatchAdapter(Context mContext, List<LawServiceListEntity.DataBean> itemList) {
        this.mContext = mContext;
        this.dataList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hatch_listview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final LawServiceListEntity.DataBean entity = dataList.get(position);
        holder.tvHatchState.setText(entity.getAuthor());
        holder.tvHatchName.setText(entity.getTitle());
        holder.tvHatchTime.setText(entity.getAddTime());
        if (!Utils.isStringEmpty(entity.getPicPath())) {
            Glide.with(mContext).load(entity.getPicPath()).into(holder.imgHatchImage);
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
        private ImageView imgHatchImage;
        private TextView tvHatchName;
        private TextView tvHatchTime;
        private TextView tvHatchState;

        public ViewHolder(View view) {
            super(view);
            imgHatchImage = (ImageView) view.findViewById(R.id.img_hatch_image);
            tvHatchName = (TextView) view.findViewById(R.id.tv_hatch_name);
            tvHatchTime = (TextView) view.findViewById(R.id.tv_hatch_time);
            tvHatchState = (TextView) view.findViewById(R.id.tv_hatch_state);
        }
    }

    public void setOnItemClickListener(OnItemCallBack callBack) {
        this.callBack = callBack;
    }

    public interface OnItemCallBack {
        void setOnClickListener(String hatchId);
    }
}
