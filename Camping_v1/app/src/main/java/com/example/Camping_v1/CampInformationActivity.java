package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CampInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_information);

    }

    public void onClick_edit_camp_button(View view){
        Intent intent = new Intent(CampInformationActivity.this, CampUploadActivity.class);
        startActivity(intent);
    }
}