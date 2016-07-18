package com.ebtang.ebtangebook.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by fengzongwei on 16/7/18.
 */
public class MyScrollGridView extends GridView {

    public MyScrollGridView(Context context){
        super(context);
    }

    public MyScrollGridView(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if(isInEditMode())
            return;
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
