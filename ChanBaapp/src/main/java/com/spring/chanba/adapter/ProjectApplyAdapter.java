package com.spring.chanba.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.bean.ProjectApplyEntity;

import java.util.List;

public class ProjectApplyAdapter extends RecyclerView.Adapter<ProjectApplyAdapter.ViewHolder> {
    public Context mContext;
    private List<ProjectApplyEntity.DataBean> dataList;

    public ProjectApplyAdapter(Context mContext, List<ProjectApplyEntity.DataBean> itemList) {
        this.mContext = mContext;
        this.dataList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apply_listview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ProjectApplyEntity.DataBean entity = dataList.get(position);
        holder.tvApplyTitle.setText(entity.getTitle());
        holder.tvApplyPerson.setText(entity.getAuthor());
        holder.tvApplyTime.setText(entity.getApplyTime());
        if (entity.getState().equals("0")) {
            holder.imgApplyPicture.setImageResource(R.mipmap.img_service_apply);
        } else {
            holder.imgApplyPicture.setImageResource(R.mipmap.img_service_handle);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvApplyPerson;
        private TextView tvApplyTime;
        private TextView tvApplyTitle;
        private ImageView imgApplyPicture;

        public ViewHolder(View view) {
            super(view);
            tvApplyPerson = (TextView) view.findViewById(R.id.tv_apply_person);
            tvApplyTime = (TextView) view.findViewById(R.id.tv_apply_time);
            tvApplyTitle = (TextView) view.findViewById(R.id.tv_apply_title);
            imgApplyPicture = (ImageView) view.findViewById(R.id.img_apply_picture);
        }
    }
}