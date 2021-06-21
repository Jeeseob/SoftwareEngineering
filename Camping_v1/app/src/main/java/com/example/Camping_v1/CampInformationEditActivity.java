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
    private Button button_edit_complete_camp;
    private Bitmap bitmapimg;
    ImageView imageView;

    CampUploadData campData = new CampUploadData();
    UserData userData = new UserData();
    private EditText CampName;
    private EditText CampAddress;
    private EditText CampPhone;
    private EditText CampKakao;
    private EditText AccountNum;
    private EditText CampTime;
    private EditText CampExtra;
    private EditText CampCost;
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
        CampName = (EditText)findViewById(R.id.editText_CampName);
        CampName.setText(campData.getCampName());
        CampAddress = (EditText)findViewById(R.id.editText_CampAddress);
        CampAddress.setText(campData.getCampAddress());
        CampPhone = (EditText)findViewById(R.id.editText_CampPhone);
        CampPhone.setText(campData.getCampPhone());
        CampKakao = (EditText)findViewById(R.id.editText_CampKakao);
        CampKakao.setText(campData.getCampKakao());
        AccountNum = (EditText)findViewById(R.id.editText_AccountNum);
        AccountNum.setText(campData.getAccountNum());
        CampTime = (EditText)findViewById(R.id.editText_CampTime);
        CampTime.setText(campData.getCampTime());
        CampCost = (EditText)findViewById(R.id.editText_CampCost);
        CampCost.setText(campData.getCampCost());
        CampExtra = (EditText)findViewById(R.id.editText_CampExtra);
        CampExtra.setText(campData.getCampExtra());

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

        String campname = CampName.getText().toString();
        String campaddress = CampAddress.getText().toString();
        String campphone = CampPhone.getText().toString();
        String campkakao = CampKakao.getText().toString();
        String accountnum = AccountNum.getText().toString();
        String camptime = CampTime.getText().toString();

        String campcost = CampCost.getText().toString();
        String campextra = CampExtra.getText().toString();
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