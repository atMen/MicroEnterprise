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
import com.spring.chanba.bean.TrainEnrolmentEntity;
import com.spring.chanba.utils.Utils;

import java.util.List;

/**
 * 培训报名
 */
public class TrainEnrolmentAdapter extends RecyclerView.Adapter<TrainEnrolmentAdapter.ViewHolder> {
    public Context mContext;
    private List<TrainEnrolmentEntity.DataBean> dataList;

    public TrainEnrolmentAdapter(Context mContext, List<TrainEnrolmentEntity.DataBean> itemList) {
        this.mContext = mContext;
        this.dataList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_listview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TrainEnrolmentEntity.DataBean entity = dataList.get(position);
        holder.tvTrainName.setText(entity.getTitle());
        holder.tvTrainTime.setText(entity.getPubTime());
        holder.tvTrainState.setText(entity.getStateName());
        if (!Utils.isStringEmpty(entity.getPicPath())) {
            Glide.with(mContext).load(entity.getPicPath()).into(holder.imgTrainImage);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgTrainImage;
        private TextView tvTrainName;
        private TextView tvTrainTime;
        private TextView tvTrainState;

        public ViewHolder(View view) {
            super(view);
            imgTrainImage = (ImageView) view.findViewById(R.id.img_train_image);
            tvTrainName = (TextView) view.findViewById(R.id.tv_train_name);
            tvTrainTime = (TextView) view.findViewById(R.id.tv_train_time);
            tvTrainState = (TextView) view.findViewById(R.id.tv_train_state);
        }
    }
}