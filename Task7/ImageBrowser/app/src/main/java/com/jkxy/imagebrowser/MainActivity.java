package com.jkxy.imagebrowser;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private Button btnOpenImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnOpenImage = (Button) findViewById(R.id.btnOpenImage);

        btnOpenImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取本地图片的Uri
                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),
                        BitmapFactory.decodeResource(getResources(), R.drawable.dd_launcher),
                        null, null));
                Intent intent = openImageFile(uri);
                startActivity(intent);
            }
        });
    }

    //获取一个打开图片的Intent
    private Intent openImageFile(Uri uri) {
        Intent openImageIntent = new Intent(Intent.ACTION_VIEW);
        openImageIntent.addCategory(Intent.CATEGORY_DEFAULT);
        openImageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        openImageIntent.setDataAndType(uri, "image/*");

        return openImageIntent;
    }


}
