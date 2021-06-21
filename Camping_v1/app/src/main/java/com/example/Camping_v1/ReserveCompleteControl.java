package com.example.Camping_v1;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class ReserveCompleteControl extends StringRequest {

    //서버 URL 설정(php 파일 연동)
    final static private String URL = "http://117.16.46.95:8080/getReservationData.php";
    private Map<String, String> map;

    public ReserveCompleteControl(String num, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("reservationNum", num);
    }

    @Override
    protected Map<String, String>getParams() throws AuthFailureError {
        return map;
    }
}
