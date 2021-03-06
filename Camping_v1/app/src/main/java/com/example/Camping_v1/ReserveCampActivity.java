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
    TextView CampName, CampCost;
    EditText editText_date, editText_campExtraUse;
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

        editText_date = (EditText)findViewById(R.id.editText_date);
        editText_campExtraUse = (EditText)findViewById(R.id.editText_CampExtra);

        CampName = (TextView)findViewById(R.id.CampName);
        CampName.setText(campData.getCampName());
        CampCost = (TextView)findViewById(R.id.CampCost);
        CampCost.setText(campData.getCampCost());
    }

    public void onClick_reserve_complete(View view){
        String date = editText_date.getText().toString();
        String campExtra = editText_campExtraUse.getText().toString();

        System.out.println("유저 네임: "+userData.getUserName());
        System.out.println("액티비티 보내짐 "+ userData.getUserNum() + userData.getUserName()+  campData.getCampNum()+  campData.getHostNum() +campData.getCampPhone()+  userData.getUserPhoneNum() + campData.getCampName()+  date+ campData.getCampAddress()+  campData.getAccountNum()+  campExtra+campData.getCampCost()+ campData.getCampKakao());
        ReserveCampControl task = new ReserveCampControl();
        //InsertDataControl task = new InsertDataControl();
        task.execute("http://" + IP_ADDRESS + reservation, userData.getUserNum(),userData.getUserName(), campData.getCampNum(), campData.getHostNum(),campData.getCampPhone(), userData.getUserPhoneNum(), campData.getCampName(), date, campData.getCampAddress(), campData.getAccountNum(), campExtra, campData.getCampCost(), campData.getCampKakao());
        Intent intent = new Intent(ReserveCampActivity.this, ReserveCompleteActivity.class);
        intent.putExtra("CampNum", campData.getCampNum());
        intent.putExtra("HostNum", campData.getHostNum());
        intent.putExtra("CampName", campData.getCampName());
        intent.putExtra("CampAddress", campData.getCampAddress());
        intent.putExtra("CampPhone", campData.getCampPhone());
        intent.putExtra("CampKakao", campData.getCampKakao());
        intent.putExtra("AccountNum", campData.getAccountNum());
        intent.putExtra("CampTime", campData.getCampTime());
        intent.putExtra("CampExtra", campData.getCampExtra());
        intent.putExtra("CampCost", campData.getCampCost());
        intent.putExtra( "UserNum", userData.getUserNum());
        intent.putExtra( "UserName", userData.getUserName());
        intent.putExtra( "UserId", userData.getUserId());
        intent.putExtra( "UserPwd", userData.getUserPassword());
        intent.putExtra( "UserEmail", userData.getUserEmail());
        intent.putExtra( "UserPhoneNum", userData.getUserPhoneNum());
        intent.putExtra( "Host", userData.getHost());
        startActivity(intent);
    }
}