package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CampInformationHostActivity extends AppCompatActivity {
//캠핑장 관리자가 업로드한 캠핑장 정보 볼 수 있는 곳

    private Button button_edit_camp;
    //사용자가 검색해서 나온 캠핑장 상세 정보
    ImageView imageView;
    TextView CampName;
    TextView CampAddress;
    TextView CampPhone;
    TextView CampKakao;
    TextView AccountNum;
    TextView CampTime;
    TextView CampExtra;
    TextView CampCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_information_host);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {

                        CampUploadData campData = new CampUploadData();
                        campData.putCampUploadData(jsonObject);


                        imageView = findViewById(R.id.image_addphoto);
                        System.out.print(campData.getCampNum());
                        //이미지 로드
                        sendImageRequest(imageView, "http://117.16.46.95:8080/"+campData.getImagepath());
                        CampName = (TextView)findViewById(R.id.CampName);
                        CampName.setText(campData.getCampName());
                        CampAddress = (TextView)findViewById(R.id.CampAddress);
                        CampAddress.setText(campData.getCampAddress());
                        CampPhone = (TextView)findViewById(R.id.CampPhone);
                        CampPhone.setText(campData.getCampPhone());
                        CampKakao = (TextView)findViewById(R.id.CampKakao);
                        CampKakao.setText(campData.getCampKakao());
                        AccountNum = (TextView)findViewById(R.id.AccountNum);
                        AccountNum.setText(campData.getAccountNum());
                        CampTime = (TextView)findViewById(R.id.CampTime);
                        CampTime.setText(campData.getCampTime());
                        CampExtra = (TextView)findViewById(R.id.CampExtra);
                        CampExtra.setText(campData.getCampExtra());
                        CampCost = (TextView)findViewById(R.id.CampCost);
                        CampCost.setText(campData.getCampCost());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        CampInformationControl campRequest = new CampInformationControl("2", responseListener);
        RequestQueue queue = Volley.newRequestQueue(CampInformationHostActivity.this);
        queue.add(campRequest);


        button_edit_camp = findViewById(R.id.button_edit_camp);
        button_edit_camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CampInformationHostActivity.this, CampUploadActivity.class);
                startActivity(intent);
            }
        });

    }
    public void sendImageRequest(ImageView imageView, String url) {
        ImageLoadControl task = new ImageLoadControl(url, imageView);
        task.execute();
    }
}