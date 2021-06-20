package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MyPageEditActivity extends AppCompatActivity {
//사용자 정보 수정 페이지
    private static String IP_ADDRESS = "117.16.46.95:8080";
    private static String reservation = "/userDataUpdate.php";
    UserData userData = new UserData();
    Button button_complete_userinfo;
    TextView UserId;
    private EditText editText_UserName;
    private EditText editText_UserPassword;
    private EditText editText_UserPhoneNum;
    private EditText editText_UserEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_edit);

        Intent intent = getIntent();
        userData.putUserId(intent.getStringExtra("UserId"));
        userData.putUserPassword(intent.getStringExtra("UserPwd"));
        userData.putUserNum(intent.getStringExtra("UserNum"));
        userData.putUserName(intent.getStringExtra("UserName"));
        userData.putUserEmail(intent.getStringExtra("UserEmail"));
        userData.putUserPhoneNum(intent.getStringExtra("UserPhoneNum"));
        userData.putAdmin(intent.getStringExtra("Host"));

        UserId = (TextView)findViewById(R.id.UserId);
        UserId.setText(userData.getUserId());


        editText_UserName = (EditText)findViewById(R.id.editText_UserName);
        editText_UserName.setText(userData.getUserName());
        editText_UserPassword = (EditText)findViewById(R.id.editText_UserPassword);
        editText_UserPassword.setText(userData.getUserPassword());
        editText_UserPhoneNum = (EditText)findViewById(R.id.editText_UserPhoneNum);
        editText_UserPhoneNum.setText(userData.getUserPhoneNum());
        editText_UserEmail = (EditText)findViewById(R.id.editText_UserEmail);
        editText_UserEmail.setText(userData.getUserEmail());


        button_complete_userinfo = findViewById(R.id.button_complete_userinfo);
        button_complete_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //관리자인지 유저인지 확인받아서 다른 화면으로 보내야함
                //유저
                if(userData.getHost().equals("2")) {

                    String UserName = editText_UserName.getText().toString();
                    String UserPassword = editText_UserPassword.getText().toString();
                    String UserPhoneNum = editText_UserPhoneNum.getText().toString();
                    String UserEmail = editText_UserEmail.getText().toString();


                    //중요내용~~!! 수정해야됨!!!
                    ReserveCampControl task = new ReserveCampControl();
                    //InsertDataControl task = new InsertDataControl();
                    task.execute("http://" + IP_ADDRESS + reservation, userData.getUserNum(),UserName, UserPassword,UserPhoneNum,UserEmail);


                    Intent intent = new Intent(MyPageEditActivity.this, MyPageActivity.class);
                    startActivity(intent);
                }
                //관리자
                else{
                    String UserName = editText_UserName.getText().toString();
                    String UserPassword = editText_UserPassword.getText().toString();
                    String UserPhoneNum = editText_UserPhoneNum.getText().toString();
                    String UserEmail = editText_UserEmail.getText().toString();
                    Intent intent = new Intent(MyPageEditActivity.this, MyPageHostActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}