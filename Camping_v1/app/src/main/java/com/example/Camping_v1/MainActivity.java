package com.example.Camping_v1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    ImageView imageView;
    String Test;
    Button go_host_camp;

    //userdata 선언
    UserData userData = new UserData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        //ab.setTitle("ActionBar Title by setTitle()");
        //ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true);

        go_host_camp = findViewById(R.id.go_host_camp);
        go_host_camp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CampInformationHostActivity.class);
                startActivity(intent);
            }
        });

        viewPager = (ViewPager)findViewById(R.id.viewPager);
        imageView = findViewById(R.id.image_addphoto);

        //이미지 업로드
        sendImageRequest(imageView, "http://117.16.46.95:8080/campImage/test.jpg");

        MainViewPageControl viewPagerAdapter = new MainViewPageControl(this);
        viewPager.setAdapter(viewPagerAdapter);

        //userdata 받아오기 Intenet에서
        Intent intent = getIntent();
        userData.putUserId(intent.getStringExtra("UserId"));
        userData.putUserPassword(intent.getStringExtra("UserPwd"));
        userData.putUserNum(intent.getStringExtra("UserNum"));
        userData.putUserName(intent.getStringExtra("UserName"));
        userData.putUserEmail(intent.getStringExtra("UserEmail"));
        userData.putUserPhoneNum(intent.getStringExtra("UserPhoneNum"));
        userData.putAdmin(intent.getStringExtra("Host"));
        Test = userData.getHost();
        System.out.println(Test);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        if(userData.getUserNum() == null || userData.getUserNum().equals("0")) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.main_menu, menu);
            //System.out.println("userNum is "+ intent.getStringExtra("UserNum"));
        }
        else if(Test.equals("1")){
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.login_host_menu, menu);
        }
        else{
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.login_menu, menu);
            //System.out.println("userNum is "+ intent.getStringExtra("UserNum"));
        }
        return super.onCreateOptionsMenu(menu);
    }
//이미지 업로드 (이걸 함수에 넣을 수 있을까??)
    public void sendImageRequest(ImageView imageView, String url){
        ImageLoadControl task = new ImageLoadControl(url, imageView);
        task.execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.btn_login:
                Toast.makeText(this, "login button click", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.btn_signin:
                Toast.makeText(this, "signin button click", Toast.LENGTH_SHORT).show();
                Intent signinIntent = new Intent(this, JoinActivity.class);
                startActivity(signinIntent);
                break;
            case R.id.btn_search:
                Toast.makeText(this, "search button click", Toast.LENGTH_SHORT).show();
                Intent searchIntent = new Intent(this, SearchActivity.class);
                searchIntent.putExtra( "UserId", userData.getUserId());
                searchIntent.putExtra( "UserPwd", userData.getUserPassword());
                searchIntent.putExtra( "UserName", userData.getUserName());
                searchIntent.putExtra( "UserNum", userData.getUserNum());
                searchIntent.putExtra( "UserEmail", userData.getUserEmail());
                searchIntent.putExtra( "UserPhoneNum", userData.getUserPhoneNum());
                searchIntent.putExtra( "Host", userData.getHost());
                startActivity(searchIntent);
                break;
            case R.id.btn_myPage:
                Toast.makeText(this, "myPage button click", Toast.LENGTH_SHORT).show();
                Intent myMenuIntent = new Intent(this, MyPageActivity.class);
                //Intent intent = getIntent();
                //String UserNum = intent.getExtras().getString("UserNum");
                myMenuIntent.putExtra( "UserId", userData.getUserId());
                myMenuIntent.putExtra( "UserPwd", userData.getUserPassword());
                myMenuIntent.putExtra( "UserName", userData.getUserName());
                myMenuIntent.putExtra( "UserNum", userData.getUserNum());
                myMenuIntent.putExtra( "UserEmail", userData.getUserEmail());
                myMenuIntent.putExtra( "UserPhoneNum", userData.getUserPhoneNum());
                myMenuIntent.putExtra( "Host", userData.getHost());

                startActivity(myMenuIntent);
                break;
            case R.id.btn_logout:
                Toast.makeText(this, "logout button click", Toast.LENGTH_SHORT).show();
                Intent LogoutIntent = new Intent(this, MainActivity.class);
                startActivity(LogoutIntent);
                break;
            case R.id.btn_list_host:
                Toast.makeText(this, "list button click", Toast.LENGTH_SHORT).show();
                Intent ListIntent = new Intent(this, CampListActivity.class);
                ListIntent.putExtra( "UserId", userData.getUserId());
                ListIntent.putExtra( "UserPwd", userData.getUserPassword());
                ListIntent.putExtra( "UserNum", userData.getUserNum());
                ListIntent.putExtra( "UserName", userData.getUserName());
                ListIntent.putExtra( "UserEmail", userData.getUserEmail());
                ListIntent.putExtra( "UserPhoneNum", userData.getUserPhoneNum());
                ListIntent.putExtra( "Host", userData.getHost());
                startActivity(ListIntent);
                break;
            case R.id.btn_myPage_host:
                Toast.makeText(this, "myPage button click", Toast.LENGTH_SHORT).show();
                Intent myPageHostIntent = new Intent(this, MyPageHostActivity.class);
                myPageHostIntent.putExtra( "UserId", userData.getUserId());
                myPageHostIntent.putExtra( "UserPwd", userData.getUserPassword());
                myPageHostIntent.putExtra( "UserNum", userData.getUserNum());
                myPageHostIntent.putExtra( "UserName", userData.getUserName());
                myPageHostIntent.putExtra( "UserEmail", userData.getUserEmail());
                myPageHostIntent.putExtra( "UserPhoneNum", userData.getUserPhoneNum());
                myPageHostIntent.putExtra( "Host", userData.getHost());
                startActivity(myPageHostIntent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}