package com.example.Camping_v1;

import android.graphics.Bitmap;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;

public class CampUploadControl {

    private static Bitmap bitmap;
    private static final int REQUEST_CODE = 21;


    public static void viewImage(int resultCode, ImageView addphoto_image, Bitmap bitmapimg) {
            if (resultCode == RESULT_CANCELED) {
                bitmap = bitmapimg;
                //Toast.makeText(CampUploadControl.this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
            else{
                addphoto_image.setImageBitmap(bitmapimg);
            }
    }

    public static void uploadImage() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
        //Toast.makeText(this, encodedImgae,Toast.LENGTH_SHORT).show();
        Call<ResponsePOJO> call = Client.getInstancce().getApi().uploadImage(encodedImage);
        call.enqueue(new Callback<ResponsePOJO>() {
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                Toast.makeText(CampUploadControl.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();

                if (response.body().isStatus()) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(CampUploadControl.this, "Network Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
