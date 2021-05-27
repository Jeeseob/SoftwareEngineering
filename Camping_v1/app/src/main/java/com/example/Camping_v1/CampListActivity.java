package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CampListActivity extends AppCompatActivity {
//캠핑장 관리자가 캠핑장 업로드한 리스트를 볼 수 있는 목록
    private Button button_camp_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_list);

        button_camp_add = findViewById(R.id.button_camp_add);
        button_camp_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CampListActivity.this, CampUploadActivity.class);
                startActivity(intent);
            }
        });
    }
}