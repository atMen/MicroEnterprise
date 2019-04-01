package com.newui.pageview;

import java.util.ArrayList;
import java.util.List;


import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class PageViewAdapter extends PagerAdapter {
	private final static String TAG = PageViewAdapter.class.getSimpleName();
	private List<PageView> mListViews = null;

	public PageViewAdapter(List<PageView> views) {
		if (views != null) {
			this.mListViews = views;
		} else {
			mListViews = new ArrayList<PageView>();
		}
	}

	@Override
	public void destroyItem(View view, int index, Object arg2) {
		if (mListViews == null) {
			return;
		}
		int size = mListViews.size();
		if (index >= size) {
			return;
		}
		PageView pageView = mListViews.get(index);
		if (pageView == null) {
			return;
		}
		View childView = (View) pageView;
		if (childView.getParent() != null) {
			ViewPager viewPage = (ViewPager) view;
			viewPage.removeView(childView);
		}
	}

	@Override
	public void finishUpdate(View arg0) {
	}

	@Override
	public int getCount() {
		return mListViews.size();
	}

	@Override
	public Object instantiateItem(View view, int index) {
		View pageView = (View) mListViews.get(index);
		if (pageView.getParent() == null) {
			((ViewPager) view).addView((View) mListViews.get(index),
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		}
		return mListViews.get(index);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {

	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View arg0) {

	}
}
