package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReserveCompleteActivity extends AppCompatActivity {
//사용자가 캠핑장 예약 완료 버튼을 클릭한 후 나오는 영수증

    Button button_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_complete);

        button_ok = findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveCompleteActivity.this, MainActivity.class);
            }
        });
    }
}