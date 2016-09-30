package com.notesdea.scalingimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //自定义的缩放图片
    private CustomImage customImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customImage = (CustomImage) findViewById(R.id.customimage_main_image);
    }
}
