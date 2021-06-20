package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ReserveCompleteActivity extends AppCompatActivity {
//사용자가 캠핑장 예약 완료 버튼을 클릭한 후 나오는 영수증

    TextView date;
    TextView UserName;
    TextView CampExtraUse;
    TextView CampName;
    TextView CampAddress;
    TextView CampCost;
    TextView AccountNum;

    Button button_ok;
    CampUploadData campData = new CampUploadData();
    UserData userData = new UserData();
    ReservationData reservationData = new ReservationData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_complete);

        Intent intent = getIntent();
        campData.putCampName(intent.getStringExtra("CampName"));
        campData.putCampNum(intent.getStringExtra("CampNum"));
        campData.putHostNum(intent.getStringExtra("HostNum"));
        campData.putCampAddress(intent.getStringExtra("CampAddress"));
        campData.putCampPhone(intent.getStringExtra("CampPhone"));
        campData.putCampKakao(intent.getStringExtra("CampKakao"));
        campData.putAccountNum(intent.getStringExtra("AccountNum"));
        campData.putCampTime(intent.getStringExtra("CampTime"));
        campData.putCampExtra(intent.getStringExtra("CampExtra"));
        campData.putCampCost(intent.getStringExtra("CampCost"));
        userData.putUserId(intent.getStringExtra("UserId"));
        userData.putUserPassword(intent.getStringExtra("UserPwd"));
        userData.putUserNum(intent.getStringExtra("UserNum"));
        userData.putUserName(intent.getStringExtra("UserName"));
        userData.putUserEmail(intent.getStringExtra("UserEmail"));
        userData.putUserPhoneNum(intent.getStringExtra("UserPhoneNum"));
        userData.putAdmin(intent.getStringExtra("Host"));

        System.out.println("액티비티 보내짐 "+ userData.getUserNum() + userData.getUserName()+  campData.getCampNum()+  campData.getHostNum() +campData.getCampPhone()+  userData.getUserPhoneNum() + campData.getCampName()+  campData.getCampAddress()+  campData.getAccountNum()+  campData.getCampCost()+ campData.getCampKakao());


        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {

                        reservationData.putReservationData(jsonObject);

                        date = (TextView)findViewById(R.id.date);
                        date.setText(reservationData.getDate());
                        UserName = (TextView)findViewById(R.id.UserName);
                        UserName.setText(reservationData.getUserName());
                        CampExtraUse = (TextView)findViewById(R.id.CampExtraUse);
                        CampExtraUse.setText(reservationData.getCampExtraUse());
                        CampName = (TextView)findViewById(R.id.CampName);
                        CampName.setText(reservationData.getCampName());
                        CampAddress = (TextView)findViewById(R.id.CampAddress);
                        CampAddress.setText(reservationData.getCampAddress());
                        CampCost = (TextView)findViewById(R.id.CampCost);
                        CampCost.setText(reservationData.getCampCost());
                        AccountNum = (TextView)findViewById(R.id.AccountNum);
                        AccountNum.setText(reservationData.getAccountNum());


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        ReserveCompleteControl campRequest = new ReserveCompleteControl("2", responseListener);
        RequestQueue queue = Volley.newRequestQueue(ReserveCompleteActivity.this);
        queue.add(campRequest);

        button_ok = findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveCompleteActivity.this, MainActivity.class);
            }
        });
    }
}