package com.example.Camping_v1;

class FTPServerData {

    private String fServerIp = "117.16.46.95";
    private String fServerId = "user1";
    private String fSserverPassword = "2017";
    private String fServerPath = "campImage";

    public FTPServerData getFTPServerData(){
        return this;
    }
    public String getfServerIp(){
        return fServerIp;
    }
    public String getfServerId(){
        return fServerId;
    }
    public String getfSserverPassword(){
        return fSserverPassword;
    }
    public String getfServerPath(){
        return fServerPath;
    }
}

class PHPServerData{
    private String dbServerIp = "117.16.46.95";
    private String dbJoinPath = "/inert.php";
    private String PHPLoginPath="/testlogin.php";

    public PHPServerData getPHPServerData(){
        return this;
    }
}

