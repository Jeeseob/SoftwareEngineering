package com.example.Camping_v1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReserveCampControl extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;
    private static String TAG = "test";
    @Override
    protected String doInBackground(String... params) {

        String reservationNum = (String) params[1];
        String userNum = (String) params[2];
        String userName = (String) params[3];
        String campNum = (String) params[4];
        String hostNum = (String) params[5];
        String hostPhoneNum = (String) params[6];
        String userPhoneNum = (String) params[7];
        String campName = (String) params[8];
        String Date = (String) params[9];
        String campAddress = (String) params[10];
        String accountNum = (String) params[11];
        String campExtraUse = (String) params[12];
        String campCost = (String) params[13];


        String serverURL = (String) params[0];

        //host num 추가해야함
        String postParameters = "usernum=" + userNum + "&username=" + userName + "&campnum=" + campNum  + "&hostnum=" + hostNum + "&hostphonenum=" + hostPhoneNum + "&userphonenum=" + userPhoneNum + "&campname=" + campName + "&date=" + Date+ "&campaddress=" + campAddress+ "&accountnum=" + accountNum+ "&campextrause=" + campExtraUse+ "&campcost="+ campCost;

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
            if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }


            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
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
