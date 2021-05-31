package com.example.Camping_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ReserveCampActivity extends AppCompatActivity {
//사용자가 캠핑장 상세정보에서 예약하기 버튼을 클릭한 뒤의 화면
    Button button_reserve_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_camp);

        button_reserve_complete = findViewById(R.id.button_reserve_complete);
        button_reserve_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReserveCampActivity.this, ReserveCompleteActivity.class);
                startActivity(intent);
            }
        });
    }
}