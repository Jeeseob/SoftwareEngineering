package com.example.Camping_v1;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


//joinActivity
class JoinControl extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;
    private static String TAG = "test";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //progressDialog = ProgressDialog.show(JoinActivity.this,
               // "Please Wait", null, true, true);
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        //progressDialog.dismiss();
        //Log.d(TAG, "POST response  - " + result);
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