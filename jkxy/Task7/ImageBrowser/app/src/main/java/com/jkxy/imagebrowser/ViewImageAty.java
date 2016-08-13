package com.jkxy.imagebrowser;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class ViewImageAty extends AppCompatActivity {
    public static final int WRITE_STORAGE_PERMISSION_CODE = 1;
    private ImageView imageView;
    //TODO 增大图片、缩小图片、旋转等功能
    private Button btnUpAlpha, btnDownAlpha, btnUp, btnDown;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        initView();

        if (Build.VERSION.SDK_INT >= 23) {
            int permission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission == PackageManager.PERMISSION_DENIED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        WRITE_STORAGE_PERMISSION_CODE);
            }
        }

        //获取图片
        try {
            Intent intent = getIntent();
            Uri uri = intent.getData();
            Log.d("uri", "Path: " + uri.getPath());
            imageView.setImageURI(uri);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_STORAGE_PERMISSION_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //获取图片
                    try {
                        Intent intent = getIntent();
                        Uri uri = intent.getData();
                        Log.d("uri", "Path: " + uri.getPath());
                        imageView.setImageURI(uri);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }
}
