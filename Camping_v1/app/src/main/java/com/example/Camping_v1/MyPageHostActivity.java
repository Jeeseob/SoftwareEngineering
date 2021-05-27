package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPageHostActivity extends AppCompatActivity {
//캠핑장 관리자 마이페이지

    Button button_edit_userinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_host);

        button_edit_userinfo = findViewById(R.id.button_edit_userinfo);
        button_edit_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageHostActivity.this, MyPageEditActivity.class);
                startActivity(intent);
            }
        });
    }
}