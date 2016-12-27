package com.example.a123.myweather.acitvity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by WB on 2016/12/8.
 */

public abstract class BaseActivity extends AppCompatActivity{

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;

        setView(); //绑定布局
        setData(); //逻辑
    }


    protected abstract void setView();

    protected abstract void setData();

    public void ToaS(Context c,String s){
        Toast.makeText(c,s,Toast.LENGTH_SHORT).show();
    }

    public void ToaL(Context c,String s){
        Toast.makeText(c,s,Toast.LENGTH_LONG).show();
    }

}
