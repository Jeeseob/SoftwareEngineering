package com.example.Camping_v1;

import android.provider.ContactsContract;
import org.json.JSONException;
import org.json.JSONObject;

class UserData {
    String UserNum;
    String UserName;
    String UserId;
    String UserPassword;
    String UserEmail;
    String UserPhoneNum;
    String Host;


    //put

    public UserData putUserData(JSONObject jsonObject){
        try {
            UserName = jsonObject.getString( "UserName" );
            UserNum = jsonObject.getString( "UserNum" );
            UserId = jsonObject.getString( "UserId" );
            UserPassword = jsonObject.getString( "UserPwd" );
            UserEmail = jsonObject.getString( "UserEmail" );
            UserPhoneNum = jsonObject.getString( "UserPhone" );
            Host = jsonObject.getString( "host" );

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;

    }
    public UserData getUserData(){
        return this;
    }
    public void putUserNum(String Num){
        UserNum = Num;
    }
    public void putUserName(String Name){
        UserName=Name;
    }
    public void putUserId(String Id){
        UserId = Id;
    }
    public void putUserPassword(String Password){
        UserPassword = Password;
    }
    public  void putUserEmail(String Email){
        UserEmail = Email;
    }
    public  void putUserPhoneNum(String PhoneNumber){
        UserPhoneNum = PhoneNumber;
    }
    public void putAdmin(String host){Host = host;}

    //get
    public String getUserNum(){
        return UserNum;
    }
    public String getUserName(){
        return UserName;
    }
    public String getUserId(){
        return UserId;
    }
    public String getUserPassword(){
        return UserPassword;
    }
    public String getUSerEmail(){
        return UserEmail;
    }
    public String getUserPhoneNum() { return UserPhoneNum;}
    public String getHost(){return Host;}
}


class CampUploadData{
    String CampNum;
    String HostNum; //host의 usernum
    String CampName;
    String CampAddress;
    String CampPhone;
    String CampKakao;
    String AccountNum;
    String CampTime;
    String CampExtra;
}



class ReservationData {
    String ReservationNum;
    String UserNum;
    String UserName;
    String CampNum;
    String HostNum;
    String HostPhoneNum;
    String UserPhoneNum;
    String CampName;
    String date;
    String CampAddress;
    String AccountNum;
}
