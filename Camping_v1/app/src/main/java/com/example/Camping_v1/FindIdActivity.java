package com.example.Camping_v1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FindIdActivity extends AppCompatActivity {
//아이디 찾는 화면

    EditText editText_find_id_name, editText_find_id_phone;
    Button button_pw_find;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        editText_find_id_name = findViewById(R.id.editText_find_id_name);

        editText_find_id_phone = findViewById(R.id.editText_find_id_phone);

/*        send_find_id = findViewById(R.id.button_id_find);
        send_find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap LargeIconForNoti = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
                PendingIntent pendingIntent = PendingIntent.getActivity(FindIdActivity.this,0,new Intent(getApplication))

                NotificationCompat.Builder builder = new NotificationCompat.Builder(FindIdActivity.this)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("아이디 찾기")
                        .setContentText("당신의 아이디는 입니다.")
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setLargeIcon(LargeIconForNoti)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);
            }
        });*/

        button_pw_find = findViewById(R.id.button_pw_find);
        button_pw_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( FindIdActivity.this, FindPwActivity.class );
                startActivity( intent );
            }
        });
    }

    public void send_find_idF(View view) {

            NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder= null;

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

                String channelID="channel_01"; //알림채널 식별자
                String channelName="MyChannel01"; //알림채널의 이름(별명)

                //알림채널 객체 만들기
                NotificationChannel channel= new NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_DEFAULT);

                //알림매니저에게 채널 객체의 생성을 요청
                notificationManager.createNotificationChannel(channel);

                //알림건축가 객체 생성
                builder=new NotificationCompat.Builder(this, channelID);


            }else{
                //알림 건축가 객체 생성
                builder= new NotificationCompat.Builder(this, null);
            }

            builder.setSmallIcon(android.R.drawable.ic_menu_view);

            builder.setContentTitle("아이디 찾기");//알림창 제목
            builder.setContentText("당신의 아이디는 test 입니다.");//알림창 내용
            //알림창의 큰 이미지
            Bitmap bm= BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher_background);
            builder.setLargeIcon(bm);

            //건축가에게 알림 객체 생성하도록
            Notification notification=builder.build();

            //알림매니저에게 알림(Notify) 요청
            notificationManager.notify(1, notification);

        }


}