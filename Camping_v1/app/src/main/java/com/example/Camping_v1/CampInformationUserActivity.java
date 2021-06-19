package com.example.Camping_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CampInformationUserActivity extends AppCompatActivity {
    //사용자가 검색해서 나온 캠핑장 상세 정보
    Button button_reserve_camp;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {

                        CampUploadData campData = new CampUploadData();
                        campData.putCampUploadData(jsonObject);

                        imageView = findViewById(R.id.image_addphoto);
                        System.out.print(campData.getCampNum());
                        //이미지 업로드
                        sendImageRequest(imageView, "http://117.16.46.95:8080/campImage/"+campData.getImagepath());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_information_user);

        button_reserve_camp = findViewById(R.id.button_reserve_camp);
        button_reserve_camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CampInformationUserActivity.this, ReserveCampActivity.class);
                startActivity(intent);
            }
        });


        CampInformationControl camprequest = new CampInformationControl("2", responseListener);
        RequestQueue queue = Volley.newRequestQueue(CampInformationUserActivity.this);
        queue.add(camprequest);

    }


    public void sendImageRequest(ImageView imageView, String url) {
        ImageLoadControl task = new ImageLoadControl(url, imageView);
        task.execute();
    }
}