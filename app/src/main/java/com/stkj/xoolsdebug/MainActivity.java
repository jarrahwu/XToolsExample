package com.stkj.xoolsdebug;

import android.os.Bundle;
import android.view.View;

import com.android.volley.VolleyError;
import com.stkj.xtools.Bind;
import com.stkj.xtools.BindActivity;
import com.stkj.xtools.Http;
import com.stkj.xtools.HttpCallBack;
import com.stkj.xtools.Log;
import com.stkj.xtools.Util;
import com.stkj.xtools.XTCookieStore;

import org.json.JSONObject;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends BindActivity {

    String url = "http://192.168.2.122:8888/user/login";

    @Bind(id = R.id.btn0, onClick = "onLogin")
    View login;

    @Bind(id = R.id.btn1, onClick = "getUser")
    View getUser;

    @Override
    protected int onLoadViewResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewDidLoad(Bundle savedInstanceState) {

    }
    String token = "";
    public void onLogin(View v) {
        Http http = new Http();
        HashMap<String ,String> map = new HashMap<>();
        map.put("phone", "18682212241");
        map.put("password", "123456");
        JSONObject jo = new JSONObject(map);
        http.url(url).JSON(jo).post(new HttpCallBack(){
            @Override
            public void onResponse(JSONObject response) {
                super.onResponse(response);
                token = response.optJSONObject("extra").optString("token");
                Util.toast(token);
        }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
                Util.toast(error.toString());
            }
        });

        List<HttpCookie> cookies = new XTCookieStore(this).getCookies();

        for (HttpCookie cookie : cookies) {
            Log.from(this, "cookie \n" + cookie);
        }
    }


    public void getUser(View v) {
        Http http = new Http();
        String url = "http://192.168.2.122:8888/user";
        http.url(url).get(new HttpCallBack() {
            @Override
            public void onResponse(JSONObject response) {
                super.onResponse(response);
                token = response.optJSONObject("extra").optString("token");
                Util.toast(token);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                super.onErrorResponse(error);
                Util.toast(error.toString());
            }
        });

        List<HttpCookie> cookies = new XTCookieStore(this).getCookies();

        for (HttpCookie cookie : cookies) {
            Log.from(this, "cookie \n" + cookie);
        }
    }
}
