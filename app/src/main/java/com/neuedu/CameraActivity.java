package com.neuedu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.neuedu.businessproject.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


//调用相机的实现类
public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_camera;
    ImageView iv_camera;
    public static final int CAMERA = 1;
    public static final int PHOTO_REQUEST_CUT = 2;
    public File file;

    public Uri imageUri;

    public void startCamera(){

        //java.lang.SecurityException: Permission Denial: starting Intent { act=androi
        //这个权限拒绝是手机自带的安全检测，在手机设置里面打开对该应用是哟个摄像头的权限即可

        //MediaStore.ACTION_IMAGE_CAPTURE
        //android.media.action.IMAGE_CAPTURE
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,getImageUri(getFile()));

        startActivityForResult(intent,CAMERA);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameralayout);

        btn_camera = findViewById(R.id.btn_camera);
        iv_camera = findViewById(R.id.iv_camera);

        btn_camera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_camera:
                //点击了调用相机的按钮之后的响应
                startCamera();
                break;
        }
    }

    //构建uri
    public Uri getImageUri(File file){
        if (Build.VERSION.SDK_INT>=24){
            //sdk>=24或者Android7以上的版本
            imageUri = FileProvider.getUriForFile(this,"com.neuedu.fileprovider",file);
        }else {
            imageUri = Uri.fromFile(file);
        }

        return imageUri;
    }

    public File getFile(){
        file = new File(getExternalCacheDir(),"temp_photo.png");

        if (file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }



    //重写onActivityResult方法


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA) {
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    iv_camera.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //裁剪照片的方法
    public void crop(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop","true");

        //设置裁剪框的比例
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);


        //设置裁剪后图片的尺寸
        intent.putExtra("outputX",250);
        intent.putExtra("outputY",250);

        intent.putExtra("outputFormat","JPEG");
        intent.putExtra("noFaceDetection",true);
        intent.putExtra("return-data",true);

        startActivityForResult(intent,PHOTO_REQUEST_CUT);

    }
}
