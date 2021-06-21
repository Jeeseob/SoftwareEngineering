package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CampListActivity extends AppCompatActivity {
    //캠핑장 관리자가 캠핑장 업로드한 리스트를 볼 수 있는 목록
    private Button button_camp_add;
    TextView CampName, CampAddress;
    ImageView imageView;
    CampUploadData campData = new CampUploadData();
    UserData userData = new UserData();
    ListView listView = null;          // 검색을 보여줄 리스트변수
    private ListViewControl adapter;      // 리스트뷰에 연결할 아답터
/*    private static final String TAG_JSON="Camping_v1";
    private static final String TAG_IMAGE = "imagepath";
    private static final String TAG_NAME = "campName";
    private static final String TAG_ADDRESS ="campAddress";*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_list);

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.

        // 리스트에 연동될 아답터를 생성한다.

        Intent intent = getIntent();
        userData.putUserId(intent.getStringExtra("UserId"));
        userData.putUserPassword(intent.getStringExtra("UserPwd"));
        userData.putUserNum(intent.getStringExtra("UserNum"));
        userData.putUserName(intent.getStringExtra("UserName"));
        userData.putUserEmail(intent.getStringExtra("UserEmail"));
        userData.putUserPhoneNum(intent.getStringExtra("UserPhoneNum"));
        userData.putAdmin(intent.getStringExtra("Host"));

        adapter = new ListViewControl();
        listView = (ListView) findViewById(R.id.listView);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //get TextView's Text
                //String strText = (String)parent.getItemAtPosition(position);

                //데이터 가지고 캠핑장 세부 내용 보여주기
                Intent intent = new Intent(CampListActivity.this, CampInformationHostActivity.class);
                intent.putExtra( "UserId", userData.getUserId());
                intent.putExtra( "UserPwd", userData.getUserPassword());
                intent.putExtra( "UserName", userData.getUserName());
                intent.putExtra( "UserNum", userData.getUserNum());
                intent.putExtra( "UserEmail", userData.getUserEmail());
                intent.putExtra( "UserPhoneNum", userData.getUserPhoneNum());
                intent.putExtra( "Host", userData.getHost());
                startActivity(intent);
            }
        });

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println("안녕");
                    System.out.println(response);
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");

                    if (success) {
                        System.out.println("하세요");
                        campData.putCampUploadData(jsonObject);


                        imageView = findViewById(R.id.image_search);
                        System.out.print(campData.getCampNum());
                        //이미지 로드
                        sendImageRequest(imageView, "http://117.16.46.95:8080/"+campData.getImagepath());
                        CampName = (TextView)findViewById(R.id.CampName);
                        CampName.setText(campData.getCampName());
                        CampAddress = (TextView)findViewById(R.id.CampAddress);
                        CampAddress.setText(campData.getCampAddress());

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        CampListControl campRequest = new CampListControl("2", responseListener);
        RequestQueue queue = Volley.newRequestQueue(CampListActivity.this);
        queue.add(campRequest);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.camping8),
                "testCamp", "sang");

    }
    public void sendImageRequest(ImageView imageView, String url) {
        ImageLoadControl task = new ImageLoadControl(url, imageView);
        task.execute();
    }
        /*System.out.println("리스트 액티비티 보내짐 "+ userData.getUserNum() + userData.getUserName()+ userData.getUserPhoneNum());

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    System.out.println(response);

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray(TAG_JSON);
                    boolean success = jsonObject.getBoolean("success");

                    System.out.println("성공?"+success);

                    if (success) {
                        campData.putCampUploadData(jsonObject);

                        for(int i=0;i<jsonArray.length();i++){

                            JSONObject item = jsonArray.getJSONObject(i);

                            String image = item.getString(TAG_IMAGE);
                            String name = item.getString(TAG_NAME);
                            String address = item.getString(TAG_ADDRESS);

                            HashMap<String,String> hashMap = new HashMap<>();

                            hashMap.put(TAG_IMAGE, image);
                            hashMap.put(TAG_NAME, name);
                            hashMap.put(TAG_ADDRESS, address);

                            mArrayList.add(hashMap);
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        CampListControl campRequest = new CampListControl(userData.getUserNum(), responseListener);
        RequestQueue queue = Volley.newRequestQueue(CampListActivity.this);
        queue.add(campRequest);
        }*/

    public void onClick_camp_add(View view){
        Intent intent = new Intent(CampListActivity.this, CampUploadActivity.class);
        intent.putExtra( "UserNum", userData.getUserNum());
        intent.putExtra( "UserName", userData.getUserName());
        intent.putExtra( "UserEmail", userData.getUserEmail());
        intent.putExtra( "UserPhoneNum", userData.getUserPhoneNum());
        intent.putExtra( "Host", userData.getHost());
        startActivity(intent);
    }
}