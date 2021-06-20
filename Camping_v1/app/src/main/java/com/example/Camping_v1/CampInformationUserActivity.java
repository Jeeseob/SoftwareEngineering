package com.example.Camping_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CampInformationUserActivity extends AppCompatActivity {
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
    CampUploadData campData = new CampUploadData();
    UserData userData = new UserData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_information_user);

        Intent intent = getIntent();
        userData.putUserId(intent.getStringExtra("UserId"));
        userData.putUserPassword(intent.getStringExtra("UserPwd"));
        userData.putUserNum(intent.getStringExtra("UserNum"));
        userData.putUserName(intent.getStringExtra("UserName"));
        userData.putUserEmail(intent.getStringExtra("UserEmail"));
        userData.putUserPhoneNum(intent.getStringExtra("UserPhoneNum"));
        userData.putAdmin(intent.getStringExtra("Host"));

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        campData.putCampUploadData(jsonObject);


                        imageView = findViewById(R.id.image_addphoto);
                        System.out.print(campData.getCampNum());
                        //이미지 업로드
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
        RequestQueue queue = Volley.newRequestQueue(CampInformationUserActivity.this);
        queue.add(campRequest);

    }

    public void sendImageRequest(ImageView imageView, String url) {
        ImageLoadControl task = new ImageLoadControl(url, imageView);
        task.execute();
    }

    public void onClick_reserve_camp(View view){
        Intent intent = new Intent(CampInformationUserActivity.this, ReserveCampActivity.class);
        intent.putExtra("CampNum", campData.getCampNum());
        intent.putExtra("HostNum", campData.getHostNum());
        intent.putExtra("CampName", campData.getCampName());
        intent.putExtra("CampAddress", campData.getCampAddress());
        intent.putExtra("CampPhone", campData.getCampPhone());
        intent.putExtra("CampKakao", campData.getCampKakao());
        intent.putExtra("AccountNum", campData.getAccountNum());
        intent.putExtra("CampTime", campData.getCampTime());
        intent.putExtra("CampExtra", campData.getCampExtra());
        intent.putExtra("CampCost", campData.getCampCost());
        intent.putExtra( "UserNum", userData.getUserNum());
        intent.putExtra( "UserName", userData.getUserName());
        intent.putExtra( "UserId", userData.getUserId());
        intent.putExtra( "UserPwd", userData.getUserPassword());
        intent.putExtra( "UserEmail", userData.getUserEmail());
        intent.putExtra( "UserPhoneNum", userData.getUserPhoneNum());
        intent.putExtra( "Host", userData.getHost());
        startActivity(intent);
    }

}