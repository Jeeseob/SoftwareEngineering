package com.example.Camping_v1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Client {

    private static final String BASE_URL = "http://117.16.46.95:8080";
    private static Client myClient;
    private Retrofit retrofit;

    private Client(){
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static synchronized Client getInstancce(){

        if(myClient == null){
            myClient = new Client();
        }
        return myClient;
    }

    public Api getApi(){
        return  retrofit.create(Api.class);
    }
}
