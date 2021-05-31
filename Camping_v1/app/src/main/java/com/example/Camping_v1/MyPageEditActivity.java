package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPageEditActivity extends AppCompatActivity {
//사용자 정보 수정 페이지

    Button button_complete_userinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_edit);

        button_complete_userinfo = findViewById(R.id.button_complete_userinfo);
        button_complete_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //관리자인지 유저인지 확인받아서 다른 화면으로 보내야함
                //유저
                Intent intent = new Intent(MyPageEditActivity.this, MyPageActivity.class);
                startActivity(intent);
                //관리자
                /*Intent intent = new Intent(MypageEditActivity.this, MyPageHostActivity.class);
                startActivity(intent);*/
            }
        });
    }
}