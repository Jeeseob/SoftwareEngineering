package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DropUserActivity extends AppCompatActivity {
//사용자가 회원정보 삭제
    Button button_complete_dropuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_user);

        button_complete_dropuser = findViewById(R.id.button_complete_dropuser);
        button_complete_dropuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //회원정보 삭제
                Intent intent = new Intent(DropUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}