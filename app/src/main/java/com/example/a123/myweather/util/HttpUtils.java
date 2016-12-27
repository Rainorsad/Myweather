package com.example.a123.myweather.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.a123.myweather.bean.InfoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by WB on 2016/12/8.
 */

public class HttpUtils {
    static HttpUtils httpUtils;
    private OkHttpClient okHttpClient;
    private Gson gson;

    public HttpUtils(){
        super();
        okHttpClient = new OkHttpClient();
        gson = new Gson();
    }

    public static HttpUtils getHttpUtils(){
        if (httpUtils == null){
            synchronized (HttpUtils.class){
                if (httpUtils == null){
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    public void getHttpJson(String url,final Handler handler, final int i, final Type infoBeanClass) {

        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String data = response.body().string();

                InfoBean infoBean = gson.fromJson(data, infoBeanClass);
                Log.d("测试",infoBean.getMsg());
                Log.d("测试",infoBean.getRetCode());
                Message message = handler.obtainMessage();
                message.what = i;
                message.obj = infoBean;
                handler.sendMessage(message);
            }
        });
    }
}
