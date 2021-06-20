package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CampListActivity extends AppCompatActivity {
//캠핑장 관리자가 캠핑장 업로드한 리스트를 볼 수 있는 목록
    private Button button_camp_add;
    UserData userData = new UserData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_list);
        Intent intent = getIntent();
        userData.putUserNum(intent.getStringExtra("UserNum"));
        userData.putUserName(intent.getStringExtra("UserName"));
        userData.putUserEmail(intent.getStringExtra("UserEmail"));
        userData.putUserPhoneNum(intent.getStringExtra("UserPhone"));
        userData.putAdmin(intent.getStringExtra("Host"));

    }

    public void onClick_camp_add(View view){
        Intent intent = new Intent(CampListActivity.this, CampUploadActivity.class);
        intent.putExtra( "UserNum", userData.getUserNum());
        intent.putExtra( "UserName", userData.getUserName());
        intent.putExtra( "UserEmail", userData.getUserEmail());
        intent.putExtra( "UserPhoneNum", userData.getUserPhoneNum());
        intent.putExtra( "Host", userData.getHost());
        startActivity(intent);
    }
}