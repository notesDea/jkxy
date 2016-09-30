package com.jkxy.card2d;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements Animator.AnimatorListener {

    private ImageView imgA, imgB;
    private Context mContext;
    private View rootView;
    private ObjectAnimator scaleReduce, scaleBelowup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();

        //点击 rootView 后开始动画
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isVisible(imgA)) {
                    startScaleReduce(imgA);
                } else {
                    startScaleReduce(imgB);
                }
            }
        });

    }

    private void initView() {
        imgA = (ImageView) findViewById(R.id.iv_main_a);
        imgB = (ImageView) findViewById(R.id.iv_main_b);
        rootView = findViewById(R.id.activity_main);
        //初始化动画
        scaleReduce = (ObjectAnimator) AnimatorInflater.loadAnimator(mContext,
                R.animator.main_scalereduce);
        scaleBelowup = (ObjectAnimator) AnimatorInflater.loadAnimator(mContext,
                R.animator.main_scalebelowup);

    }

    //设置 imgA 可见
    private void showImgA() {
        imgA.setVisibility(View.VISIBLE);
        imgB.setVisibility(View.GONE);
    }

    //设置 imgB 可见
    private void showImgB() {
        imgA.setVisibility(View.GONE);
        imgB.setVisibility(View.VISIBLE);
    }

    //判断 View 是否可见
    private boolean isVisible(View view) {
        return view.getVisibility() == View.VISIBLE;
    }

    //开始缩小动画
    private void startScaleReduce(Object target) {
        scaleReduce.setTarget(target);
        scaleReduce.start();
        scaleReduce.addListener(this);
    }

    //开始放大动画
    private void startScaleBelowup(Object target) {
        scaleBelowup.setTarget(target);
        scaleBelowup.start();
        scaleBelowup.addListener(this);
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (animation == scaleReduce) {
            if (((ObjectAnimator) animation).getTarget() == imgA) {
                showImgB();
                startScaleBelowup(imgB);
            } else {
                showImgA();
                startScaleBelowup(imgA);
            }
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
