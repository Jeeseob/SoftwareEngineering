package com.example.Camping_v1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
//로그인 화면
    private EditText editText_login_id, editText_login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("로그인");
        ab.setDisplayShowCustomEnabled(true);

        editText_login_id = findViewById( R.id.editText_login_id );
        editText_login_password = findViewById( R.id.editText_login_password );

    }

    public void onClick_id_find(View view){
        Intent intent = new Intent(LoginActivity.this, FindIdActivity.class);
        startActivity(intent);
    }

    public void onClick_pw_find(View view){
        Intent intent = new Intent(LoginActivity.this, FindPwActivity.class);
        startActivity(intent);
    }

    public void onClick_login(View view){
        String UserId = editText_login_id.getText().toString();
        String UserPwd = editText_login_password.getText().toString();

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject( response );
                    boolean success = jsonObject.getBoolean( "success" );

                    if(success) {//로그인 성공시

                        UserData userData = new UserData();
                        userData.putUserData(jsonObject);
                        userData.getUserName();
                        //userData.putUserName(jsonObject.getString( "UserName" ));
                        //String UserId = jsonObject.getString( "UserId" );

                        Toast.makeText( getApplicationContext(), String.format("%s님 환영합니다.", userData.UserName), Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent( LoginActivity.this, MainActivity.class );
                        intent.putExtra( "UserId", userData.getUserId());
                        intent.putExtra( "UserPwd", userData.getUserPassword());
                        intent.putExtra( "UserNum", userData.getUserNum());
                        intent.putExtra( "UserName", userData.getUserName());
                        intent.putExtra( "UserEmail", userData.getUserEmail());
                        intent.putExtra( "UserPhoneNum", userData.getUserPhoneNum());
                        intent.putExtra( "Host", userData.getHost());

                        startActivity( intent );

                    } else {//로그인 실패시
                        Toast.makeText( getApplicationContext(), "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT ).show();
                        return;
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        LoginControl loginRequest = new LoginControl( UserId, UserPwd, responseListener );
        RequestQueue queue = Volley.newRequestQueue( LoginActivity.this );
        queue.add( loginRequest );

    }

    public void onClick_join(View view){
        Intent intent = new Intent( LoginActivity.this, JoinActivity.class );
        startActivity( intent );
    }

}