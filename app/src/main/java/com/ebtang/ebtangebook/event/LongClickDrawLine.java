package com.ebtang.ebtangebook.event;

/**
 * Created by fengzongwei on 2016/8/25 0025.
 * 长按是否可以划线
 */
public class LongClickDrawLine {

    private boolean isCanDraw = true;

    public LongClickDrawLine(boolean isCanDraw){
        this.isCanDraw = isCanDraw;
    }

    public boolean isCanDraw(){
        return isCanDraw;
    }

}
