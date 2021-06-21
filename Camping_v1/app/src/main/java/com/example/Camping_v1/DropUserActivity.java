package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DropUserActivity extends AppCompatActivity {
//사용자가 회원정보 삭제
    EditText editText_check_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_user);
    }

    public void onClick_complete_dropuser(View view){

    }
}