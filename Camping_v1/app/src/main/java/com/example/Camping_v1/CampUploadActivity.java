package com.example.Camping_v1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class CampUploadActivity extends AppCompatActivity {
    //캠핑장 관리자가 캠핑장을 업로드하는 화면
    private static final int REQUEST_CODE = 21;
    private ImageView addphoto_image;
    private Button button_upload_camp;
    private Bitmap bitmapimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_upload);

        addphoto_image = findViewById(R.id.image_addphoto);
        addphoto_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        button_upload_camp = findViewById(R.id.button_upload_camp);
        button_upload_camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CampUploadControl.uploadImage();
                Intent intent = new Intent(CampUploadActivity.this, CampInformationHostActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data.getData()!= null) {
            Uri path = data.getData();
            try {
                bitmapimg = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
            } catch (IOException e) {
                e.printStackTrace();
            }

            CampUploadControl.viewImage(resultCode, addphoto_image, bitmapimg);
        }
    }
}