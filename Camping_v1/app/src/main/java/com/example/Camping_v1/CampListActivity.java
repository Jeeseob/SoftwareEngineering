package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CampListActivity extends AppCompatActivity {

    private Button camp_add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_list);

        camp_add_button = findViewById(R.id.camp_add_button);
        camp_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CampListActivity.this, CampUploadActivity.class);
                startActivity(intent);
            }
        });
    }
}