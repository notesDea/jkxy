package com.jkxy.movebuttom;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnViewXml, btnViewCode, btnObjectXml, btnObjectCode;
    private Context mContext;
    private ImageView imgA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        initView();
        initListener();
    }

    private void initListener() {
        btnViewXml.setOnClickListener(this);
        btnViewCode.setOnClickListener(this);
        btnObjectXml.setOnClickListener(this);
        btnObjectCode.setOnClickListener(this);
        imgA.setOnClickListener(this);
    }

    private void initView() {
        btnViewXml = (Button) findViewById(R.id.btn_main_viewxml);
        btnViewCode = (Button) findViewById(R.id.btn_main_viewcode);
        btnObjectXml = (Button) findViewById(R.id.btn_main_objectxml);
        btnObjectCode = (Button) findViewById(R.id.btn_main_objectcode);
        imgA = (ImageView) findViewById(R.id.iv_main_a);
    }

    //获取水平移动的 ObjectAnimator 对象
    private ObjectAnimator getTranslationX(Object target, long duration, float... values) {
        ObjectAnimator translationX = ObjectAnimator.ofFloat(target, "translationX", values);
        translationX.setDuration(duration);
        return translationX;
    }

    //获取垂直移动的 ObjectAnimator 对象
    private ObjectAnimator getTranslationY(Object target, long duration, float...values) {
        ObjectAnimator translationY = ObjectAnimator.ofFloat(target, "translationY", values);
        translationY.setDuration(duration);
        return translationY;

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //XML文件配置视图动画
            case R.id.btn_main_viewxml:
                AnimationSet viewXmlSet = (AnimationSet) AnimationUtils.loadAnimation(
                        mContext, R.anim.main_viewanimation);
                view.startAnimation(viewXmlSet);
                break;

            //代码配置视图动画
            case R.id.btn_main_viewcode:
                AnimationSet viewCodeSet = new AnimationSet(true);
                viewCodeSet.setDuration(1000);
                //设置移动动画
                TranslateAnimation translateX = new TranslateAnimation(0, 300, 0, 0);
                TranslateAnimation translateY = new TranslateAnimation(0, 0, 0, 300);
                translateY.setStartOffset(1000);

                viewCodeSet.addAnimation(translateX);
                viewCodeSet.addAnimation(translateY);

                view.startAnimation(viewCodeSet);
                break;

            //XML文件配置属性动画
            case R.id.btn_main_objectxml:
                AnimatorSet animator= (AnimatorSet) AnimatorInflater.loadAnimator(
                        mContext, R.animator.main_objectanimation);
                animator.setTarget(view);
                animator.start();
                break;

            //代码配置属性动画
            case R.id.btn_main_objectcode:
                //设置移动动画
                ObjectAnimator translationX = getTranslationX(view, 1000, 0, 300);
                ObjectAnimator translationY = getTranslationY(view, 1000, 0, 300);

                //设置倒退动画
                ObjectAnimator translationYReverse = getTranslationY(view, 1000, 300, 0);
                ObjectAnimator translationXReverse = getTranslationX(view, 1000, 300, 0);

                AnimatorSet objectCodeSet = new AnimatorSet();
                objectCodeSet.playSequentially(translationX, translationY,
                        translationYReverse, translationXReverse);
                objectCodeSet.start();
                break;

            //图片的翻转
            case R.id.iv_main_a:
                AnimatorSet imgSet = (AnimatorSet) AnimatorInflater.loadAnimator(
                        this, R.animator.main_imganim);
                imgSet.setTarget(view);
                imgSet.start();
                break;

        }
    }
}
