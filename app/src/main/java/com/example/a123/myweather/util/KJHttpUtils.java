package com.example.a123.myweather.util;

import android.content.Context;

import com.example.a123.myweather.configer.Configer;

import org.kymjs.kjframe.KJHttp;
import org.kymjs.kjframe.http.HttpCallBack;
import org.kymjs.kjframe.http.HttpParams;
import org.kymjs.kjframe.ui.ViewInject;
import org.kymjs.kjframe.utils.SystemTool;

/**
 * Created by WB on 2016/12/28.
 */

public class KJHttpUtils {
    private static final String TAG = "KJHttpUtil";

    private static KJHttp kjh;

    private static KJHttp getKjHttp() {
        if (kjh == null) {
            kjh = new KJHttp();
        }
        return kjh;
    }

    /**
     * 框架中的get请求
     * @param context 上下文
     * @param url 接口
     * @param httpParams 参数
     * @param isCache 是否缓存
     * @param CallBack 回调
     */
    public static void getHttp(Context context, String url, HttpParams httpParams, boolean isCache,
                               HttpCallBack CallBack) {
        if (SystemTool.checkNet(context)) {//检测网络
            getKjHttp().get(url, httpParams, isCache, CallBack);
        }else{
            ViewInject.toast("网络不可用，请检查网络设置");
        }
    }

    public static void postHttp(Context context, String url, HttpParams httpParams, boolean isCache,
                                HttpCallBack CallBack) {
        if (SystemTool.checkNet(context)) {//检测网络
            getKjHttp().post(url, httpParams, isCache, CallBack);
        }else{
            ViewInject.toast("网络不可用，请检查网络设置");
        }
    }

    //访问接口请求天气数据
    public static void getHttpWeather(Context context, String city, String provice, HttpCallBack callBack){
        HttpParams params = new HttpParams();
        params.put("key", Configer.SHARESDK_KEY);
        params.put("city",city);
        params.put("province",provice);
        getHttp(context,Configer.HTTP_HOST,params,false,callBack);
    }

}
