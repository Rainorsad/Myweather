package com.example.a123.myweather.acitvity;

import android.content.Context;
import android.widget.TextView;

import com.example.a123.myweather.R;
import com.example.a123.myweather.util.HttpUtils;
import com.example.a123.myweather.util.KJHttpUtils;

import org.kymjs.kjframe.http.HttpCallBack;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {

    private final static String TAG = "MainActivity";

    @InjectView(R.id.tv_day)
    TextView tvDay;
    @InjectView(R.id.tv_time)
    TextView tvTime;

    private HttpUtils httpUtils;
    private static String url = "http://apicloud.mob.com/v1/weather/query?key=197e8fe14585b";

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
        KJHttpUtils.getHttpWeather(context,"通州","北京",callBack);
    }

    HttpCallBack callBack = new HttpCallBack() {
        @Override
        public void onSuccess(String t) {
            super.onSuccess(t);
            ToaS(context,t);
        }
    };

}
