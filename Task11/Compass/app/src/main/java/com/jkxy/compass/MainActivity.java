package com.jkxy.compass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private float[] accelerometerValues = new float[3];
    private float[] magneticValues = new float[3];
    private ImageView ivCompass;
    private float lastDegree = 0;
    private TextView tvDegreeText, tvDegreeDigit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ivCompass = (ImageView) findViewById(R.id.iv_main_compass);
        tvDegreeText = (TextView) findViewById(R.id.tv_degreeText);
        tvDegreeDigit = (TextView) findViewById(R.id.tv_degreeDigit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //加速度传感器
        registerSensor(sensorManager, Sensor.TYPE_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_GAME);
        //磁场传感器
        registerSensor(sensorManager, Sensor.TYPE_MAGNETIC_FIELD,
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    //注册传感器
    private Sensor registerSensor(SensorManager manager, int type, int samplingPeriodUs) {
        Sensor sensor = manager.getDefaultSensor(type);
        manager.registerListener(this, sensor, samplingPeriodUs);
        return sensor;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        //判断传感器类型
        int type = sensorEvent.sensor.getType();
        if (type == Sensor.TYPE_ACCELEROMETER) {
            accelerometerValues = sensorEvent.values.clone();
        } else if (type == Sensor.TYPE_MAGNETIC_FIELD) {
            magneticValues = sensorEvent.values.clone();
        }

        //计算方向
        float[] R = new float[9];
        float[] values = new float[3];
        SensorManager.getRotationMatrix(R, null, accelerometerValues, magneticValues);
        SensorManager.getOrientation(R, values);
        float currentDegree = -transformDegree((float) Math.toDegrees(values[0]));

        //旋转
        if (Math.abs(currentDegree - lastDegree) > 1) {
            RotateAnimation rotate = new RotateAnimation(lastDegree, currentDegree,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(200);
            rotate.setFillAfter(true);
            ivCompass.startAnimation(rotate);
            Log.d("SensorChanged", currentDegree + "");
            lastDegree = currentDegree;

            //设置方向的文字和数字
            tvDegreeText.setText(getOrientationName(-currentDegree));
            tvDegreeDigit.setText(getString(com.jkxy.compass.R.string.degree, -(int) currentDegree));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    //把度数从 -180~180 转换成 0~360度
    private float transformDegree(float degree) {
        if (degree < 0) {
            return 360 + degree;
        }
        return degree;
    }

    //获取方向对应的方向名
    private String getOrientationName(float degree) {
        if (isInRange(degree, 25, 74)) {
            return "东北";
        } else if (isInRange(degree, 75, 111)) {
            return "东";
        } else if (isInRange(degree, 112, 156)) {
            return "东南";
        } else if (isInRange(degree, 157, 201)) {
            return "南";
        } else if (isInRange(degree, 202, 246)) {
            return "西南";
        } else if (isInRange(degree, 247, 292)) {
            return "西";
        } else if (isInRange(degree, 293, 338)) {
            return "西北";
        } else {
            return "北";
        }
    }

    //是否在两个浮点数范围内
    private boolean isInRange(float src, float lowDest, float heightDest) {
        return src >= lowDest && src <= heightDest;
    }
}
