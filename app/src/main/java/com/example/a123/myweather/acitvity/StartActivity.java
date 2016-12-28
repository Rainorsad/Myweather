package com.example.a123.myweather.acitvity;

import android.content.Context;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.example.a123.myweather.R;
import com.example.a123.myweather.wowsplash.WowSplashView;
import com.example.a123.myweather.wowsplash.WowView;

import org.kymjs.kjframe.utils.PreferenceHelper;

import static android.view.View.GONE;

/**
 * Created by WB on 2016/12/28.
 */

public class StartActivity extends BaseActivity{
    private ViewFlipper flipper;
    private GestureDetector detector;
    private WowSplashView mWowSplashView;
    private WowView mWowView;
    private Context context;
    @Override
    protected void setView() {
        setContentView(R.layout.activity_start);
        context = this;
    }

    @Override
    protected void setData() {
        mWowSplashView = (WowSplashView) findViewById(R.id.wowSplash);
        mWowView = (WowView) findViewById(R.id.wowView);
        flipper = (ViewFlipper) findViewById(R.id.vf);

        mWowSplashView.startAnimate();

        mWowSplashView.setOnEndListener(new WowSplashView.OnEndListener() {
            @Override public void onEnd(WowSplashView wowSplashView) {
                mWowSplashView.setVisibility(GONE);
                mWowView.setVisibility(View.VISIBLE);
                mWowView.startAnimate(wowSplashView.getDrawingCache(),context);

                mWowView.setOnEndListener(new WowView.OnEndListener() {
                    @Override
                    public void onEnd(WowView w) {
                        mWowView.setVisibility(GONE);
                        beginData();
                    }
                });
            }
        });
    }

    private void beginData() {
        //读取是否是首次使用本软件
        boolean isGuide = PreferenceHelper.readBoolean(context, "UserInfo", "guide", true);
        if (isGuide){
            detector = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
                /**
                 * 按下（onDown）： 刚刚手指接触到触摸屏的那一刹那，就是触的那一下。
                 抛掷（onFling）： 手指在触摸屏上迅速移动，并松开的动作。
                 长按（onLongPress）： 手指按在持续一段时间，并且没有松开。
                 滚动（onScroll）： 手指在触摸屏上滑动。
                 按住（onShowPress）： 手指按在触摸屏上，它的时间范围在按下起效，在长按之前。
                 抬起（onSingleTapUp）：手指离开触摸屏的那一刹那。
                 * @param e1
                 * @param e2
                 * @param velocityX
                 * @param velocityY
                 * @return
                 */
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    if (e1.getX() - e2.getX() > 120.0F) {//
                        flipper.setInAnimation(context, R.anim.tip_left_in); //view进入时使用的动画
                        flipper.setOutAnimation(context, R.anim.tip_left_out); //view退出时使用的动画
                        if (flipper.getDisplayedChild() < 2) {
                            flipper.showNext(); //用于显示fragment下一个view
                        }
                    } else if (e1.getX() - e2.getX() < -120.0F) {
                        flipper.setInAnimation(context, R.anim.tip_right_in);
                        flipper.setOutAnimation(context, R.anim.tip_right_out);
                        if (flipper.getDisplayedChild() > 0) {
                            flipper.showPrevious(); //用于显示fragment上一个view
                        }
                    }
                    return false;
                }
            });
            PreferenceHelper.write(context, "UserInfo", "guide", false);
        }else {
            Intent i = new Intent(context,MainActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (detector != null) {
            return detector.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }
}
