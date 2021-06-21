package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampInformationEditActivity extends AppCompatActivity {

    private static String IP_ADDRESS = "117.16.46.95:8080";
    private static String CampUpload = "/campDataInsert.php";

    private static final int REQUEST_CODE = 21;
    private ImageView image_addphoto;
    private Bitmap bitmapimg;
    ImageView imageView;

    CampUploadData campData = new CampUploadData();
    UserData userData = new UserData();
    private EditText editText_CampName;
    private EditText editText_CampAddress;
    private EditText editText_CampPhone;
    private EditText editText_CampKakao;
    private EditText editText_AccountNum;
    private EditText editText_CampTime;
    private EditText editText_CampExtra;
    private EditText editText_CampCost;
    private static String reservation = "/campEdit.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_information_edit);

        Intent intent = getIntent();
        campData.putImagePath(intent.getStringExtra("imagepath"));
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


        imageView = findViewById(R.id.image_addphoto);
        sendImageRequest(imageView, "http://117.16.46.95:8080/"+campData.getImagepath());
        editText_CampName = (EditText)findViewById(R.id.editText_CampName);
        editText_CampName.setText(campData.getCampName());
        editText_CampAddress = (EditText)findViewById(R.id.editText_CampAddress);
        editText_CampAddress.setText(campData.getCampAddress());
        editText_CampPhone = (EditText)findViewById(R.id.editText_CampPhone);
        editText_CampPhone.setText(campData.getCampPhone());
        editText_CampKakao = (EditText)findViewById(R.id.editText_CampKakao);
        editText_CampKakao.setText(campData.getCampKakao());
        editText_AccountNum = (EditText)findViewById(R.id.editText_AccountNum);
        editText_AccountNum.setText(campData.getAccountNum());
        editText_CampTime = (EditText)findViewById(R.id.editText_CampTime);
        editText_CampTime.setText(campData.getCampTime());
        editText_CampCost = (EditText)findViewById(R.id.editText_CampCost);
        editText_CampCost.setText(campData.getCampCost());
        editText_CampExtra = (EditText)findViewById(R.id.editText_CampExtra);
        editText_CampExtra.setText(campData.getCampExtra());

        image_addphoto = findViewById(R.id.image_addphoto);
        image_addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    public void sendImageRequest(ImageView imageView, String url) {
        ImageLoadControl task = new ImageLoadControl(url, imageView);
        task.execute();
    }

    public void onClick_edit_complete_camp(View view){

        String campname = editText_CampName.getText().toString();
        String campaddress = editText_CampAddress.getText().toString();
        String campphone = editText_CampPhone.getText().toString();
        String campkakao = editText_CampKakao.getText().toString();
        String accountnum = editText_AccountNum.getText().toString();
        String camptime = editText_CampTime.getText().toString();
        String campcost = editText_CampCost.getText().toString();
        String campextra = editText_CampExtra.getText().toString();
        CampInformationEditControl task = new CampInformationEditControl();
        task.execute("http://" + IP_ADDRESS + reservation, campData.getCampNum(),campname,campaddress, campphone,campkakao,accountnum,camptime,campcost,campextra);

        Intent intent = new Intent(CampInformationEditActivity.this, CampInformationHostActivity.class);
        intent.putExtra("CampNum", campData.getCampNum());
        intent.putExtra("HostNum", campData.getHostNum());
        intent.putExtra("CampName", campname);
        intent.putExtra("CampAddress", campaddress);
        intent.putExtra("CampPhone", campphone);
        intent.putExtra("CampKakao", campkakao);
        intent.putExtra("AccountNum", accountnum);
        intent.putExtra("CampTime", camptime);
        intent.putExtra("CampExtra", campextra);
        intent.putExtra("CampCost", campcost);
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