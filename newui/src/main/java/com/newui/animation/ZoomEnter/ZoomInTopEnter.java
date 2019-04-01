package com.newui.animation.ZoomEnter;

import android.animation.ObjectAnimator;
import android.view.View;

import com.newui.animation.BaseAnimatorSet;

public class ZoomInTopEnter extends BaseAnimatorSet {
	public ZoomInTopEnter() {
		duration = 600;
	}

	@Override
	public void setAnimation(View view) {
		view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		int h = view.getMeasuredHeight();

		animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1),//
				ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1),//
				ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1),//
				ObjectAnimator.ofFloat(view, "translationY", -h, 60, 0));
	}
}
