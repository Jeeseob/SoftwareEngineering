package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CampInformationHostActivity extends AppCompatActivity {
//캠핑장 관리자가 업로드한 캠핑장 정보 볼 수 있는 곳

    private Button button_edit_camp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_information_host);

        button_edit_camp = findViewById(R.id.button_edit_camp);
        button_edit_camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CampInformationHostActivity.this, CampUploadActivity.class);
                startActivity(intent);
            }
        });

    }
}