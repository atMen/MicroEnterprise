package com.spring.chanba.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spring.chanba.R;
import com.spring.chanba.bean.ServiceApplyListEntity;

import java.util.List;

public class ServiceApplyAdapter extends RecyclerView.Adapter<ServiceApplyAdapter.ViewHolder> {
    private Context mContext;
    private OnItemCallBack callBack;
    private List<ServiceApplyListEntity.DataBean> dataList;

    public ServiceApplyAdapter(Context mContext, List<ServiceApplyListEntity.DataBean> itemList) {
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
        final ServiceApplyListEntity.DataBean entity = dataList.get(position);
        holder.tvApplyTitle.setText(entity.getTitle());
        holder.tvApplyPerson.setText(entity.getAuthor());
        holder.tvApplyTime.setText(entity.getApplyTime());
        if (entity.getState().equals("0")) {
            holder.imgApplyPicture.setImageResource(R.mipmap.img_service_apply);
        } else {
            holder.imgApplyPicture.setImageResource(R.mipmap.img_service_handle);
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
        private TextView tvApplyTitle;
        private TextView tvApplyPerson;
        private TextView tvApplyTime;
        private ImageView imgApplyPicture;

        public ViewHolder(View view) {
            super(view);
            tvApplyTitle = (TextView) view.findViewById(R.id.tv_apply_title);
            tvApplyPerson = (TextView) view.findViewById(R.id.tv_apply_person);
            tvApplyTime = (TextView) view.findViewById(R.id.tv_apply_time);
            imgApplyPicture = (ImageView) view.findViewById(R.id.img_apply_picture);
        }
    }

    public void setOnItemClickListener(OnItemCallBack callBack) {
        this.callBack = callBack;
    }

    public interface OnItemCallBack {
        void setOnClickListener(String id);
    }
}