package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CampInformationHostActivity extends AppCompatActivity {

    private Button edit_camp_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_information_host);

        edit_camp_button = findViewById(R.id.edit_camp_button);
        edit_camp_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CampInformationHostActivity.this, CampUploadActivity.class);
                startActivity(intent);
            }
        });

    }
}