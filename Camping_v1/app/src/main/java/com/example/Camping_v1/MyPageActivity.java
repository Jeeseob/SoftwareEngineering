package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MyPageActivity extends AppCompatActivity {
//사용자의 마이페이지
    Button button_edit_userinfo,button_reserve_lookup, button_deleteuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        button_edit_userinfo = findViewById(R.id.button_edit_userinfo);
        button_edit_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, MyPageEditActivity.class);
                startActivity(intent);
            }
        });

        button_reserve_lookup = findViewById(R.id.button_reserve_lookup);
        button_reserve_lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, ReserveLookupActivity.class);
                startActivity(intent);
            }
        });

        button_deleteuser = findViewById(R.id.button_drop_user);
        button_deleteuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyPageActivity.this, DropUserActivity.class);
                startActivity(intent);
            }
        });
    }
}