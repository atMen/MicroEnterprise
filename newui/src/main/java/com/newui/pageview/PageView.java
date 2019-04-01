package com.newui.pageview;

import android.os.Bundle;

public interface PageView {
	public void onCreate(Bundle savedInstanceState);

	public void onSaveInstanceState(Bundle outState);

	public boolean isCreated();

	public void visible();

	public void disVisible();

	public void onResume();

	public void onPause();

	public void onDestroy();

	public boolean isVisible();
}