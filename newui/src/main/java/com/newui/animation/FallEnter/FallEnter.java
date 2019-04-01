package com.newui.animation.FallEnter;

import android.animation.ObjectAnimator;
import android.view.View;

import com.newui.animation.BaseAnimatorSet;
public class FallEnter extends BaseAnimatorSet {
	@Override
	public void setAnimation(View view) {
		animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 2f, 1.5f, 1f).setDuration(duration),//
				ObjectAnimator.ofFloat(view, "scaleY", 2f, 1.5f, 1f).setDuration(duration),//
				ObjectAnimator.ofFloat(view, "alpha", 0, 1f).setDuration(duration));
	}
}
