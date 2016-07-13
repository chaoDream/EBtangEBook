package com.ebtang.ebtangebook.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by fengzongwei on 16/7/13.
 */
public class MyViewPager extends ViewPager {

    private boolean scollble = false;

    public MyViewPager(Context context){
        super(context);
    }


    public MyViewPager(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(!scollble){
            return  true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(scollble)
            return  false;
        return super.onInterceptTouchEvent(ev);
    }

    public void setScollble(boolean scollble){
        this.scollble = scollble;
    }

    public boolean getScollBle(){
        return scollble;
    }

}
