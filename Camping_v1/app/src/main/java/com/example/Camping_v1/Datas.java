package com.example.Camping_v1;

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


    public void putUserData(JSONObject jsonObject){
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
    public String getUserEmail(){
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
    String CampCost;
    String Imagepath;

    public CampUploadData putCampUploadData(JSONObject jsonObject){
        try {
            CampNum = jsonObject.getString("campNum");
            HostNum = jsonObject.getString("hostNum");
            CampName = jsonObject.getString("campName");
            CampAddress = jsonObject.getString("campAddress");
            CampPhone = jsonObject.getString("campPhone");
            CampKakao = jsonObject.getString("campKakao");
            AccountNum = jsonObject.getString("campAccount");
            CampTime = jsonObject.getString("campTime");
            CampExtra = jsonObject.getString("campExtra");
            CampCost = jsonObject.getString("campPrice");
            Imagepath = jsonObject.getString("imagepath");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return this;

    }

    public CampUploadData getCampUploadData(){
        return this;
    }
    public void putCampNum(String CNum){
        CampNum = CNum;
    }
    public void putHostNum(String HNum){
        HostNum = HNum;
    }
    public void putCampName(String CName){
        CampName = CName;
    }
    public void putCampAddress(String CAddress){
        CampAddress = CAddress;
    }
    public void putCampPhone(String CPhone){
        CampPhone = CPhone;
    }
    public void putCampKakao(String CKakao){
        CampKakao = CKakao;
    }
    public void putAccountNum(String ANum){AccountNum = ANum;}
    public void putCampTime(String CTime){CampTime = CTime;}
    public void putCampExtra(String CExtra){CampExtra = CExtra;}
    public void putCampCost(String CCost){CampCost = CCost;}

    //get
    public String getCampNum(){
        return CampNum;
    }

    public String getHostNum() {
        return HostNum;
    }

    public String getCampName() {
        return CampName;
    }

    public String getCampAddress() {
        return CampAddress;
    }

    public String getCampPhone() {
        return CampPhone;
    }

    public String getCampKakao() {
        return CampKakao;
    }

    public String getAccountNum() {
        return AccountNum;
    }

    public String getCampTime() {
        return CampTime;
    }

    public String getCampExtra() {
        return CampExtra;
    }

    public String getCampCost() {
        return CampCost;
    }
    public String getImagepath() {
        return Imagepath;
    }

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
    String CampExtraUse;
}
