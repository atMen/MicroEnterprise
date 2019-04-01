package com.newui.animation.ZoomEnter;

import android.animation.ObjectAnimator;
import android.view.View;

import com.newui.animation.BaseAnimatorSet;
public class ZoomInLeftEnter extends BaseAnimatorSet {
	public ZoomInLeftEnter() {
		duration = 750;
	}

	@Override
	public void setAnimation(View view) {
		view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		int w = view.getMeasuredWidth();

		animatorSet.playTogether(//
				ObjectAnimator.ofFloat(view, "scaleX", 0.1f, 0.475f, 1),//
				ObjectAnimator.ofFloat(view, "scaleY", 0.1f, 0.475f, 1),//
				ObjectAnimator.ofFloat(view, "translationX", -w, 48, 0),//
				ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1));
	}
}
