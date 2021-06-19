package com.example.Camping_v1;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.lakue.lakuepopupactivity.PopupActivity;
import com.lakue.lakuepopupactivity.PopupGravity;
import com.lakue.lakuepopupactivity.PopupType;

public class JoinActivity extends AppCompatActivity {
//회원가입 화면

    private static String IP_ADDRESS = "117.16.46.95:8080";
    private static String TAG = "test";
    private static String Insert = "/insert.php";

    private Button button_join_insert;

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

    }

    public void onClick_join_insert(View view){
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
            joinCheckBoxHost = "2";
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

            InsertDataControl task = new InsertDataControl();
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
}
