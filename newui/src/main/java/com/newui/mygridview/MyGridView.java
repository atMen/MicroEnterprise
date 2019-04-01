package com.newui.mygridview;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.newui.R;

public class MyGridView extends GridView {
	private boolean haveScrollbar = true;
	private boolean dispatchDraw = false;

	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyGridView(Context context) {
		super(context);
	}

	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 设置是否有ScrollBar，当要在ScollView中显示时，应当设置为false。 默认为 true
	 * 
	 * @param haveScrollbars
	 */
	public void setHaveScrollbar(boolean haveScrollbar) {
		this.haveScrollbar = haveScrollbar;
	}
	
	//设置是否有分隔线
	public void setDispatchDraw(boolean dispatchDraw) {
		this.dispatchDraw = dispatchDraw;
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		if (haveScrollbar == true) {
			int expandSpec = MeasureSpec.makeMeasureSpec(
					Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
			super.onMeasure(widthMeasureSpec, expandSpec);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (dispatchDraw == true) {
			int viewWidth = getWidth();
			View localView1 = getChildAt(0);
			if(localView1 != null){
				viewWidth = localView1.getWidth();
			}
			int column = getWidth() / viewWidth;
			int childCount = getChildCount();
			Paint localPaint;
			localPaint = new Paint();
			localPaint.setStyle(Paint.Style.STROKE);
			//设置颜色
			localPaint.setColor(999999);
			for (int i = 0; i < childCount; i++) {
				View cellView = getChildAt(i);
				if ((i + 1) % column == 0) {
					canvas.drawLine(cellView.getLeft(), cellView.getBottom(),
							cellView.getRight(), cellView.getBottom(),
							localPaint);
				} else if ((i + 1) > (childCount - (childCount % column))) {
					canvas.drawLine(cellView.getRight(), cellView.getTop(),
							cellView.getRight(), cellView.getBottom(),
							localPaint);
				} else {
					canvas.drawLine(cellView.getRight(), cellView.getTop(),
							cellView.getRight(), cellView.getBottom(),
							localPaint);
					canvas.drawLine(cellView.getLeft(), cellView.getBottom(),
							cellView.getRight(), cellView.getBottom(),
							localPaint);
				}
			}
			if (childCount % column != 0) {
				for (int j = 0; j < (column - childCount % column); j++) {
					View lastView = getChildAt(childCount - 1);
					canvas.drawLine(lastView.getRight() + lastView.getWidth()
							* j, lastView.getTop(), lastView.getRight()
							+ lastView.getWidth() * j, lastView.getBottom(),
							localPaint);
				}
			}
		}

	}
}
