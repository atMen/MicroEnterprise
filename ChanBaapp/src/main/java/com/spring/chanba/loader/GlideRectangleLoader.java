package com.spring.chanba.loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class GlideRectangleLoader extends ImageLoader {
    @Override
    public void displayImage(Context mContext, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        RequestOptions options = new RequestOptions().transform(new GlideRectangleTransform(mContext, 0));
        Glide.with(mContext.getApplicationContext())
                .load(path)
                .apply(options)
                .into(imageView);
    }
}
