package com.example.Camping_v1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
//로그인 화면
    private EditText login_id, login_password;
    private Button button_login, button_join, button_id_find, button_pw_find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("로그인");
        ab.setDisplayShowCustomEnabled(true);

        login_id = findViewById( R.id.editText_login_id );
        login_password = findViewById( R.id.editText_login_password );

        button_join = findViewById( R.id.button_join);
        button_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( LoginActivity.this, JoinActivity.class );
                startActivity( intent );
            }
        });

        button_login = findViewById( R.id.button_login );
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserId = login_id.getText().toString();
                String UserPwd = login_password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            if(success) {//로그인 성공시

                                UserData userData = new UserData();
                                userData.putUserData(jsonObject);

                                //userData.putUserName(jsonObject.getString( "UserName" ));
                                //String UserId = jsonObject.getString( "UserId" );

                                Toast.makeText( getApplicationContext(), String.format("%s님 환영합니다.", userData.UserName), Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( LoginActivity.this, MainActivity.class );
                                intent.putExtra( "UserNum", userData.getUserNum());
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
        });

        button_id_find = findViewById(R.id.button_id_find);
        button_id_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindIdActivity.class);
                startActivity(intent);
            }
        });

        button_pw_find = findViewById(R.id.button_pw_find);
        button_pw_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, FindPwActivity.class);
                startActivity(intent);
            }
        });

    }
}