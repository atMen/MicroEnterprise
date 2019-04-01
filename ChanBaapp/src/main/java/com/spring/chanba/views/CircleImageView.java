package com.spring.chanba.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.spring.chanba.R;

public class CircleImageView extends AppCompatImageView {
    private int outCiecleColor = Color.WHITE;
    private int outCiecleWidth;
    private Paint paintBorder;

    private int viewWidth;
    private int viewHeight;
    private Bitmap bitmap;

    public CircleImageView(Context context) {
        super(context);
        initAttrs(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CiecleImageView);
            int len = array.length();
            for (int i = 0; i < len; i++) {
                int attr = array.getIndex(i);
                if (attr == R.styleable.CiecleImageView_outCiecleColor) {
                    this.outCiecleColor = array.getColor(attr, Color.WHITE);

                } else if (attr == R.styleable.CiecleImageView_outCiecleWidth) {
                    this.outCiecleWidth = (int) array.getDimension(attr, 5);

                } else {
                }
            }
        }
        paintBorder = new Paint();
        paintBorder.setColor(outCiecleColor);
        paintBorder.setAntiAlias(true);
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measuredWidth(widthMeasureSpec);
        int height = measuredHeight(heightMeasureSpec);
        viewWidth = width - (outCiecleWidth * 2);
        viewHeight = height - (outCiecleWidth * 2);
        setMeasuredDimension(width, height);
    }

    /**
     * 加载图片
     */
    private void loadImage() {
        BitmapDrawable bitmapDrawable = (BitmapDrawable) this.getDrawable();
        if (bitmapDrawable != null) {
            bitmap = bitmapDrawable.getBitmap();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        loadImage();
        if (bitmap != null) {
            int min = Math.min(viewHeight, viewWidth);
            int ciecleCenter = min / 2;
            bitmap = Bitmap.createScaledBitmap(bitmap, min, min, false);
            canvas.drawCircle(ciecleCenter + outCiecleWidth, ciecleCenter + outCiecleWidth, ciecleCenter + outCiecleWidth, paintBorder);
            canvas.drawBitmap(createCiecleBitmap(bitmap, min), outCiecleWidth, outCiecleWidth, null);
        }
    }

    /**
     * 创建一个圆形的Bitmap
     *
     * @param image
     * @param min
     * @return
     */
    private Bitmap createCiecleBitmap(Bitmap image, int min) {
        Bitmap mBitmap = null;
        Paint mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mBitmap = Bitmap.createBitmap(min, min, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(mBitmap);
        canvas.drawCircle(min / 2, min / 2, min / 2, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(image, 0, 0, mPaint);
        return mBitmap;
    }

    /**
     * 测量宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    private int measuredWidth(int widthMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = viewWidth;
        }
        return result;
    }

    /**
     * 测量高度
     *
     * @param heightMeasureSpec
     * @return
     */
    private int measuredHeight(int heightMeasureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = viewHeight;
        }
        return result;
    }

    /**
     * 动态设置颜色
     *
     * @param color
     */
    public void setBorderColor(int color) {
        if (paintBorder != null) {
            paintBorder.setColor(color);
        }
        this.invalidate();
    }

    /**
     * 动态设置宽度
     *
     * @param width
     */
    public void setBorderWidth(int width) {
        this.outCiecleWidth = width;
        this.invalidate();
    }
}