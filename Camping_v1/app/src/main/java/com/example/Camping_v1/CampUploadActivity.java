package com.example.Camping_v1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampUploadActivity extends AppCompatActivity {
    //캠핑장 관리자가 캠핑장을 업로드하는 화면
    private static String IP_ADDRESS = "117.16.46.95:8080";
    private static String CampUpload = "/campDataInsert.php";

    private static final int REQUEST_CODE = 21;
    private ImageView image_addphoto;
    private Bitmap bitmapimg;

    private EditText editText_CampName;
    private EditText editText_CampAddress;
    private EditText editText_CampPhone;
    private EditText editText_CampKakao;
    private EditText editText_AccountNum;
    private EditText editText_CampTime;
    private EditText editText_CampExtra;
    private EditText editText_CampCost;
    UserData userData = new UserData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_upload);

        Intent intent = getIntent();
        userData.putUserNum(intent.getStringExtra("UserNum"));
        userData.putUserName(intent.getStringExtra("UserName"));
        userData.putUserEmail(intent.getStringExtra("UserEmail"));
        userData.putUserPhoneNum(intent.getStringExtra("UserPhone"));
        userData.putAdmin(intent.getStringExtra("Host"));

        editText_CampName = (EditText)findViewById(R.id.editText_CampName);
        editText_CampAddress = (EditText)findViewById(R.id.editText_CampAddress);
        editText_CampPhone = (EditText)findViewById(R.id.editText_CampPhone);
        editText_CampKakao = (EditText)findViewById(R.id.editText_CampKakao);
        editText_AccountNum = (EditText)findViewById(R.id.editText_AccountNum);
        editText_CampTime = (EditText)findViewById(R.id.editText_CampTime);
        editText_CampCost = (EditText)findViewById(R.id.editText_CampCost);
        editText_CampExtra = (EditText)findViewById(R.id.editText_CampExtra);

        image_addphoto = findViewById(R.id.image_addphoto);
        image_addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data.getData()!= null) {
                Uri path = data.getData();
                try {
                    //InputStream in = getContentResolver().openInputStream(path);
                    bitmapimg = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                    image_addphoto.setImageBitmap(bitmapimg);
                    //in.close();

                    //addphoto_image.setImageBitmap(bitmapimg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }
    protected void uploadImage() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapimg.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);
        //Toast.makeText(this, encodedImgae,Toast.LENGTH_SHORT).show();
        Call<ResponsePOJO> call = Client.getInstancce().getApi().uploadImage(encodedImage);
        call.enqueue(new Callback<ResponsePOJO>() {
            @Override
            public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                Toast.makeText(CampUploadActivity.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();

                if (response.body().isStatus()) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(CampUploadActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    protected void uploadCampData(){

    }

    public void onClick_upload_camp(View view){
        String campName = editText_CampName.getText().toString();
        String campAddress = editText_CampAddress.getText().toString();
        String campPhone = editText_CampPhone.getText().toString();
        String campKakao = editText_CampKakao.getText().toString();
        String accountNum = editText_AccountNum.getText().toString();
        String campTime = editText_CampTime.getText().toString();
        String campCost = editText_CampCost.getText().toString();
        String campExtra = editText_CampExtra.getText().toString();

        CampUploadControl task = new CampUploadControl();
        //InsertDataControl task = new InsertDataControl();
        System.out.println(userData.getUserNum());
        task.execute("http://" + IP_ADDRESS + CampUpload, userData.getUserNum(),campName, campAddress, campPhone,campKakao, accountNum, campTime, campExtra, campCost);
        uploadImage();
        Intent intent = new Intent(CampUploadActivity.this, CampInformationHostActivity.class);
        startActivity(intent);
    }
}