package com.spring.chanba.dialog.basedialog.effects;


import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * 创建时间：2018/2/26
 * 创建人：
 * 功能描述：
 */

public class FadeIn extends BaseEffects {

    @Override
    protected void setupAnimation(View view) {
        getAnimatorSet().playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration));
    }
}
