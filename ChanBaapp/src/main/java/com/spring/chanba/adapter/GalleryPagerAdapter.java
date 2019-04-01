package com.spring.chanba.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.spring.chanba.R;
import com.spring.chanba.bean.LawServiceGridEntity;
import com.spring.chanba.loader.GlideRectangleTransform;
import com.spring.chanba.ui.home.WebViewCaseActivity;
import com.spring.chanba.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 画廊
 */
public class GalleryPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<LawServiceGridEntity.DataBean> dataList;

    public GalleryPagerAdapter(Context context) {
        this.mContext = context;
        this.dataList = new ArrayList<>();
    }

    public void setData(List<LawServiceGridEntity.DataBean> list) {
        this.dataList.clear();
        this.dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hatch_viewpager, null);
        ImageView image = (ImageView) view.findViewById(R.id.img_hatch_picture);
        TextView tvName = (TextView) view.findViewById(R.id.tv_hatch_title);
        if (!Utils.isStringEmpty(dataList.get(position).getPicPath())) {
            RequestOptions options = new RequestOptions().transform(new GlideRectangleTransform(mContext, 5));
            Glide.with(mContext).load(dataList.get(position).getPicPath()).apply(options).into(image);
            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, WebViewCaseActivity.class);
                    intent.putExtra("id", dataList.get(position).getId());
                    mContext.startActivity(intent);
                }
            });
        }
        tvName.setText(dataList.get(position).getTitle());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public float getPageWidth(int position) {
        return (float) 0.46;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
