package com.notesdea.scalingimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

/**
 * Created by notes on 2016/9/19.
 */

public class CustomImage extends View {

    private static final String TAG = "CustomImage";
    //缩放比例
    private float mScaleFactor = 1f;
    //缩放的图片
    private Bitmap mBitmap = null;
    //绘制图片的开始位置
    private float mLeft, mTop;

    //手势识别对象
    private GestureDetector mGestureDetector;
    //缩放手势对象
    private ScaleGestureDetector mScaleGestureDetector;

    public CustomImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBitmap = Bitmap.createBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.img));

        mGestureDetector = new GestureDetector(context, new MyGestureListener());
        mScaleGestureDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        computeDrawnStartPosition();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //计算绘制图片的左边距离和顶部距离
    private void computeDrawnStartPosition() {
        int bitmapHalfWidth = mBitmap.getWidth() / 2;
        int bitmapHalfHeight = mBitmap.getHeight() / 2;
        mLeft = getHalfOfViewWidth() - bitmapHalfWidth;
        mTop = getHalfOfViewHeight() - bitmapHalfHeight;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean retVal = mScaleGestureDetector.onTouchEvent(event);
        retVal = mGestureDetector.onTouchEvent(event) || retVal;
        return retVal || super.onTouchEvent(event);
    }

    //手势监听类
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }

        //e1 为第一次按下时的事件
        //e2为按下后移动的事件
        //distanceX/Y 为上一次调用 onScroll() 到当前的距离。
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return true;

        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

//            fling((int) -velocityX, (int) -velocityY);
            return true;
        }
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            computeScaleFactor(detector);
            invalidate();
            return true;
        }

        //计算缩放比例
        private void computeScaleFactor(ScaleGestureDetector detector) {
            mScaleFactor *= detector.getScaleFactor();
            //控制图片缩放大小
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        scalingImage(canvas);

    }

    //执行缩放图片操作
    private void scalingImage(Canvas canvas) {
        canvas.save();
        canvas.scale(mScaleFactor, mScaleFactor, getHalfOfViewWidth(), getHalfOfViewHeight());
        canvas.drawBitmap(mBitmap, mLeft, mTop, null);
        Rect rect = canvas.getClipBounds();
        canvas.restore();


    }

    //获取 View 视图宽的一半
    private float getHalfOfViewWidth() {
        return getWidth() / 2;
    }

    //获取 View 视图高度的一半
    private float getHalfOfViewHeight() {
        return getHeight() / 2;
    }
}
