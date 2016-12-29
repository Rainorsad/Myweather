package com.example.a123.myweather.acitvity;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.a123.myweather.R;
import com.example.a123.myweather.bean.WeatherBean;
import com.example.a123.myweather.util.KJHttpUtils;

import org.kymjs.kjframe.http.HttpCallBack;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.alibaba.fastjson.JSON.parseObject;

public class MainActivity extends BaseActivity {

    private final static String TAG = "MainActivity";

    @InjectView(R.id.tv_day)
    TextView tvDay;
    @InjectView(R.id.tv_time)
    TextView tvTime;

    private Context context;

    @Override
    protected void setView() {
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        context = this;

    }

    @Override
    protected void setData() {
        setYearTime(); //设置年月时间
        startGetData(); //开始访问网络请求数据
    }

    /**
     * 设置年月时间
     */
    private void setYearTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        tvDay.setText(str);

        SimpleDateFormat f = new SimpleDateFormat("HH:mm");
        Date da = new Date(System.currentTimeMillis());
        String time = f.format(da);
        tvTime.setText(time);
    }

    /**
     * 开始访问网络请求数据
     */
    private void startGetData() {
        String city = toUtf("通州");
        String privince = toUtf("北京");
        KJHttpUtils.getHttpWeather(context,city,privince,callBack);
    }

    HttpCallBack callBack = new HttpCallBack() {
        @Override
        public void onSuccess(String t) {
            if (JSON.parseObject(t).getInteger("retCode") == 200){
                String s = String.valueOf(JSON.parseObject(t).getJSONObject("result"));
                WeatherBean weatherBean = parseObject(s, WeatherBean.class);
                Log.i(TAG, "onSuccess: "+weatherBean.toString());
            }else {
                String msg = parseObject(t).getString("msg");
                ToaS(context,msg);
            }
        }
    };

}
