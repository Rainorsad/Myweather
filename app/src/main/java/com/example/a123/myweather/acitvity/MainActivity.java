package com.example.a123.myweather.acitvity;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.a123.myweather.R;
import com.example.a123.myweather.application.AppLica;
import com.example.a123.myweather.bean.InfoBean;
import com.example.a123.myweather.util.HttpUtils;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.FormEncodingBuilder;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.Map;

public class MainActivity extends BaseActivity {

    private HttpUtils httpUtils;
    private static String url = "http://apicloud.mob.com/v1/weather/query?key=197e8fe14585b";

    private Context context;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_main);
        context = this;

    }

    @Override
    protected void setData() {
        startGetData(); //开始访问网络请求数据
    }

    /**
     * 开始访问网络请求数据
     */
    private void startGetData() {
        httpUtils = HttpUtils.getHttpUtils();
        String city = "南京";
        String province = "江苏";
        Type type = new TypeToken<InfoBean>() {}.getType();
//        String s = url+"&city="+city+"&province="+province;
        String s = "http://apicloud.mob.com/v1/weather/query?key=appkey&city=通州&province=北京";
        Log.d("测试url",s);
        httpUtils.getHttpJson(url,handler,1, type);
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    InfoBean  infoBean = (InfoBean) msg.obj;
                    ToaS(context,infoBean.getMsg());
                    break;
            }
        }
    };
}
