package com.newui.animation.MyAnim;

import android.animation.ObjectAnimator;
import android.view.View;

import com.newui.animation.BaseAnimatorSet;


public class MyAnim extends BaseAnimatorSet {
	public MyAnim() {
		duration = 700;
	}

	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "alpha", 0.1f, 1));
	}
}
