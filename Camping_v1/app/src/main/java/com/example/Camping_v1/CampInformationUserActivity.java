package com.example.Camping_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CampInformationUserActivity extends AppCompatActivity {
//사용자가 검색해서 나온 캠핑장 상세 정보
    Button button_reserve_camp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


    }
}