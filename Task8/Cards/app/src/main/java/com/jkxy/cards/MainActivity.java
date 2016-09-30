package com.jkxy.cards;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "lifecycle";
    private LinearLayout rootView;
    private TextView tvNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        rootView = new LinearLayout(this);
        rootView.setOrientation(LinearLayout.VERTICAL);
        setContentView(rootView);

        //获取屏幕宽高
        Point point = getPoint();
        int screenWidth = point.x;
        int screenHeight = point.y;
        //控件间隔距离
        int widthMargin = screenWidth / 200;
        int heightMargin = screenHeight / 120;

        blankLayout(rootView);

        int num = 1;
        for (int row = 0; row < 5; row++) {
            LinearLayout childLayout = new LinearLayout(this);
            childLayout.setOrientation(LinearLayout.HORIZONTAL);

            for (int column = 0; column < 4; column++) {
                //设置文字属性
                tvNum = new TextView(this);
                tvNum.setText(num++ + "");
                tvNum.setGravity(Gravity.CENTER);
                tvNum.setBackgroundResource(R.color.red);
                tvNum.setTextSize(30);
                tvNum.setTextColor(getResources().getColor(R.color.white));
                //设置布局属性
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.MATCH_PARENT);
                params.weight = 1;
                params.setMargins(widthMargin,heightMargin ,widthMargin ,heightMargin );
                childLayout.addView(tvNum, params);
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, 0);
            params.weight = 1;
            rootView.addView(childLayout, params);
        }

        blankLayout(rootView);
    }

    //留白
    private void blankLayout(ViewGroup rootView) {
        Point point = getPoint();
        int screenHeight = point.y;
        int margin = screenHeight / 35;

        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, margin);
        rootView.addView(layout, params);
    }

    private Point getPoint() {
        Point point= new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        return point;
    }


}
