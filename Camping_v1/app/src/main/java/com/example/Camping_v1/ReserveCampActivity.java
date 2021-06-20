package com.example.Camping_v1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReserveCampActivity extends AppCompatActivity {
//사용자가 캠핑장 상세정보에서 예약하기 버튼을 클릭한 뒤의 화면
    private static String IP_ADDRESS = "117.16.46.95:8080";
    private static String reservation = "/reservationDataInsert.php";

    private static final int REQUEST_CODE = 21;
    Button button_reserve_complete;
    TextView CampName, CampCost;
    EditText Date, CampExtra;
    CampUploadData campData = new CampUploadData();
    UserData userData = new UserData();
    ReservationData reservationData = new ReservationData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_camp);

        Intent intent = getIntent();
        campData.putCampName(intent.getStringExtra("CampName"));
        campData.putCampNum(intent.getStringExtra("CampNum"));
        campData.putHostNum(intent.getStringExtra("HostNum"));
        campData.putCampAddress(intent.getStringExtra("CampAddress"));
        campData.putCampPhone(intent.getStringExtra("CampPhone"));
        campData.putCampKakao(intent.getStringExtra("CampKakao"));
        campData.putAccountNum(intent.getStringExtra("AccountNum"));
        campData.putCampTime(intent.getStringExtra("CampTime"));
        campData.putCampExtra(intent.getStringExtra("CampExtra"));
        campData.putCampCost(intent.getStringExtra("CampCost"));
        userData.putUserId(intent.getStringExtra("UserId"));
        userData.putUserPassword(intent.getStringExtra("UserPwd"));
        userData.putUserNum(intent.getStringExtra("UserNum"));
        userData.putUserName(intent.getStringExtra("UserName"));
        userData.putUserEmail(intent.getStringExtra("UserEmail"));
        userData.putUserPhoneNum(intent.getStringExtra("UserPhoneNum"));
        userData.putAdmin(intent.getStringExtra("Host"));

        Date = (EditText)findViewById(R.id.editText_date);
        CampExtra = (EditText)findViewById(R.id.editText_CampExtra);

        CampName = (TextView)findViewById(R.id.CampName);
        CampName.setText(campData.getCampName());
        CampCost = (TextView)findViewById(R.id.CampCost);
        CampCost.setText(campData.getCampCost());
    }

    public void onClick_reserve_complete(View view){
        String date = Date.getText().toString();
        String campExtra = CampExtra.getText().toString();

        ReserveCampControl task = new ReserveCampControl();
        //InsertDataControl task = new InsertDataControl();
        task.execute("http://" + IP_ADDRESS + reservation, userData.getUserNum(),userData.getUserName(), campData.getCampNum(), campData.getHostNum(),campData.getCampPhone(), userData.getUserPhoneNum(), campData.getCampName(), date, campData.getCampAddress(), campData.getAccountNum(), campExtra, campData.getCampCost(), campData.getCampKakao());
        Intent intent = new Intent(ReserveCampActivity.this, ReserveCompleteActivity.class);
        startActivity(intent);
    }
}