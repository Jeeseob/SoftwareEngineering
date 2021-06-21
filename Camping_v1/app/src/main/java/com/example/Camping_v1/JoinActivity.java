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

    private EditText editText_join_name;
    private EditText editText_join_id;
    private EditText editText_join_password;
    private EditText editText_join_passwordChek;
    private EditText editText_join_email;
    private EditText editText_join_phone;
    private CheckBox checkbox_host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("회원가입");
        ab.setDisplayShowCustomEnabled(true);

        editText_join_name = (EditText)findViewById(R.id.editText_join_name);
        editText_join_id = (EditText)findViewById(R.id.editText_join_id);
        editText_join_password = (EditText)findViewById(R.id.editText_join_password);
        editText_join_passwordChek = (EditText)findViewById(R.id.editText_join_passwordChek);
        editText_join_email = (EditText)findViewById(R.id.editText_join_email);
        editText_join_phone = (EditText)findViewById(R.id.editText_join_phone);
        checkbox_host = (CheckBox) findViewById(R.id.checkbox_host);

        //mTextViewResult = (TextView)findViewById(R.id.textView_main_result);
        //mTextViewResult.setMovementMethod(new ScrollingMovementMethod());

    }

    public void onClick_join_insert(View view){
        String joinName = editText_join_name.getText().toString();
        String joinId = editText_join_id.getText().toString();
        String joinPassword = editText_join_password.getText().toString();
        String joinPasswordCheck = editText_join_passwordChek.getText().toString();
        String joinEmail = editText_join_email.getText().toString();
        String joinPhone = editText_join_phone.getText().toString();
        Boolean joinCheckBox = checkbox_host.isChecked();

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

            JoinControl task = new JoinControl();
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

    public void onClick_join_id_check(View view){

    }
}
