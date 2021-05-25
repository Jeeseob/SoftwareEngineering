package com.example.Camping_v1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Camping_v1.R;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.HttpURLConnection;
import java.net.URL;

import com.lakue.lakuepopupactivity.PopupActivity;
import com.lakue.lakuepopupactivity.PopupGravity;
import com.lakue.lakuepopupactivity.PopupResult;
import com.lakue.lakuepopupactivity.PopupType;

public class JoinActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "117.16.46.95";
    private static String TAG = "test";
    private static String Insert = "/insert.php";

    private Button join_delete, join_button;

    private EditText mEditTextName;
    private EditText mEditTextId;
    private EditText mEditTextPassword;
    private EditText mEditTextPasswordCheck;
    private EditText mEditTextEmail;
    private EditText mEditTextPhone;
    private CheckBox mEditCheckBoxHost;


    //private TextView mTextViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("회원가입");
        ab.setDisplayShowCustomEnabled(true);

        mEditTextName = (EditText)findViewById(R.id.editText_join_name);
        mEditTextId = (EditText)findViewById(R.id.editText_join_id);
        mEditTextPassword = (EditText)findViewById(R.id.editText_join_password);
        mEditTextPasswordCheck = (EditText)findViewById(R.id.editText_join_passwordChek);
        mEditTextEmail = (EditText)findViewById(R.id.editText_join_email);
        mEditTextPhone = (EditText)findViewById(R.id.editText_join_phone);
        mEditCheckBoxHost = (CheckBox) findViewById(R.id.checkbox_host);


        //mTextViewResult = (TextView)findViewById(R.id.textView_main_result);

        //mTextViewResult.setMovementMethod(new ScrollingMovementMethod());





        join_button = findViewById(R.id.button_join_insert);
        join_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                String joinName = mEditTextName.getText().toString();
                String joinId = mEditTextId.getText().toString();
                String joinPassword = mEditTextPassword.getText().toString();
                String joinPasswordCheck = mEditTextPasswordCheck.getText().toString();
                String joinEmail = mEditTextEmail.getText().toString();
                String joinPhone = mEditTextPhone.getText().toString();
                Boolean joinCheckBox = mEditCheckBoxHost.isChecked();

                String joinCheckBoxHost;
                if(joinCheckBox == Boolean.TRUE) {
                    joinCheckBoxHost = "1";
                }
                else{
                    joinCheckBoxHost = "0";
                }


                joinName = joinName.trim();

                if(joinName.getBytes().length <=0 || joinId.getBytes().length <=0 || joinPassword.getBytes().length <=0 || joinEmail.getBytes().length <=0){
                    Intent intent = new Intent(getBaseContext(), PopupActivity.class);
                    intent.putExtra("type", PopupType.ERROR);
                    intent.putExtra("gravity", PopupGravity.RIGHT);
                    intent.putExtra("title", "입력 오류");
                    intent.putExtra("content", "입력되지 않은 값이 있습니다.");
                    intent.putExtra("buttonRight", "확인");
                    startActivityForResult(intent, 3);
                }
                else if(joinPassword.equals(joinPasswordCheck)) {
                    Toast.makeText(JoinActivity.this, "회원가입 완료", Toast.LENGTH_SHORT).show();

                    InsertData task = new InsertData();
                    task.execute("http://" + IP_ADDRESS + Insert, joinName, joinId, joinPassword, joinEmail, joinPhone, joinCheckBoxHost);
                    Intent intentMain = new Intent(JoinActivity.this, LoginActivity.class);
                    startActivity(intentMain);

                    //mEditTextName.setText("");
                    //mEditTextId.setText("");
                    //mEditTextPassword.setText("");
                    //mEditTextEmail.setText("");
                }
                else{
                    Intent intent = new Intent(getBaseContext(), PopupActivity.class);
                    intent.putExtra("type", PopupType.ERROR);
                    intent.putExtra("gravity", PopupGravity.RIGHT);
                    intent.putExtra("title", "비밀번호 오류");
                    intent.putExtra("content", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                    intent.putExtra("buttonRight", "확인");
                    startActivityForResult(intent, 3);
                }
            }
        });

        join_delete = findViewById(R.id.button_join_delete);
        join_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain = new Intent(JoinActivity.this, MainActivity.class);
                startActivity(intentMain);
            }
        });

    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(JoinActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String name = (String)params[1];
            String id= (String)params[2];
            String password= (String)params[3];
            String email= (String)params[4];
            String phone= (String)params[5];
            String host = (String)params[6];

            String serverURL = (String)params[0];

            String postParameters = "name=" + name + "&id=" + id + "&password=" + password + "&email=" + email + "&phone=" + phone + "&host=" + host;

            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));

                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                //reponseStatusCode == 200
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }

}
