package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampInformationEditActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "117.16.46.95:8080";
    private static String CampUpload = "/campDataInsert.php";

    private static final int REQUEST_CODE = 21;
    private ImageView image_addphoto;
    private Button button_edit_complete_camp;
    private Bitmap bitmapimg;

    private EditText CampName;
    private EditText CampAddress;
    private EditText CampPhone;
    private EditText CampKakao;
    private EditText AccountNum;
    private EditText CampTime;
    private EditText CampExtra;
    private EditText CampCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_information_edit);

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
        com.android.volley.Response.Listener<String> responseListener = new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {

                        CampUploadData campData = new CampUploadData();
                        campData.putCampUploadData(jsonObject);


                        System.out.print(campData.getCampNum());
                        //이미지 로드
                        sendImageRequest(image_addphoto, "http://117.16.46.95:8080/"+campData.getImagepath());
                        CampName = (EditText)findViewById(R.id.editText_CampName);
                        CampName.setText(campData.getCampName());
                        CampAddress = (EditText)findViewById(R.id.editText_CampAddress);
                        CampAddress.setText(campData.getCampAddress());
                        CampPhone = (EditText)findViewById(R.id.editText_CampPhone);
                        CampPhone.setText(campData.getCampPhone());
                        CampKakao = (EditText)findViewById(R.id.editText_CampKakao);
                        CampKakao.setText(campData.getCampKakao());
                        AccountNum = (EditText)findViewById(R.id.editText_AccountNum);
                        AccountNum.setText(campData.getAccountNum());
                        CampTime = (EditText)findViewById(R.id.editText_CampTime);
                        CampTime.setText(campData.getCampTime());
                        CampCost = (EditText)findViewById(R.id.editText_CampCost);
                        CampCost.setText(campData.getCampCost());
                        CampExtra = (EditText)findViewById(R.id.editText_CampExtra);
                        CampExtra.setText(campData.getCampExtra());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        CampInformationControl campRequest = new CampInformationControl("2", responseListener);
        RequestQueue queue = Volley.newRequestQueue(CampInformationEditActivity.this);
        queue.add(campRequest);
    }
    public void sendImageRequest(ImageView imageView, String url) {
        ImageLoadControl task = new ImageLoadControl(url, imageView);
        task.execute();
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
                Toast.makeText(CampInformationEditActivity.this, response.body().getRemarks(), Toast.LENGTH_SHORT).show();

                if (response.body().isStatus()) {

                } else {

                }
            }

            @Override
            public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                Toast.makeText(CampInformationEditActivity.this, "Network Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClick_edit_complete_camp(View view){
        Intent intent = new Intent(CampInformationEditActivity.this, CampInformationHostActivity.class);
        startActivity(intent);
    }
}