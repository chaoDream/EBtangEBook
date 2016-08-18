package com.ebtang.ebtangebook.view.read;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.view.recomm.RecommAllActivity;

import org.geometerplus.android.fbreader.FBReader;
import org.geometerplus.android.fbreader.api.MenuNode;
import org.geometerplus.fbreader.fbreader.ActionCode;
import org.geometerplus.zlibrary.core.options.ZLIntegerRangeOption;

/**
 * Created by dell on 2016/8/18 0018.
 */
public class ReadSetColorFontPop implements View.OnClickListener{

    private FBReader context;
    private View contentView;

    private PopupWindow popupWindow;

    private TextView textView_fangda,textView_suoxiao;

    public ReadSetColorFontPop(FBReader context){
        this.context = context;
        initView();
    }

    private void initView(){
        if(contentView == null){
            contentView = LayoutInflater.from(context).inflate(R.layout.read_util_set_bottom,null);
            textView_fangda = (TextView)contentView.findViewById(R.id.read_set_fangda);
            textView_suoxiao = (TextView)contentView.findViewById(R.id.read_set_suoxiao);
            popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            // 需要设置一下此参数，点击外边可消失
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            // 设置点击窗口外边窗口消失
            popupWindow.setOutsideTouchable(true);
            // 设置此参数获得焦点，否则无法点击
            popupWindow.setFocusable(true);
            popupWindow.setAnimationStyle(R.style.popwin_anim_style);

            textView_suoxiao.setOnClickListener(this);
            textView_fangda.setOnClickListener(this);
        }
    }


    public void showPop(){
        popupWindow.showAtLocation(context.findViewById(R.id.root_view), Gravity.BOTTOM, 0, 0);
    }

    public void dissmissPop(){
        if(popupWindow!=null)
            popupWindow.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read_set_fangda:
                final ZLIntegerRangeOption option = context.myFBReaderApp.ViewOptions.getTextStyleCollection().getBaseStyle().FontSizeOption;
                option.setValue(option.getValue() + 2);
                context.myFBReaderApp.clearTextCaches();
                context.myFBReaderApp.getViewWidget().repaint();
                break;
            case R.id.read_set_suoxiao:
                final ZLIntegerRangeOption options = context.myFBReaderApp.ViewOptions.getTextStyleCollection().getBaseStyle().FontSizeOption;
                options.setValue(options.getValue() - 2);
                context.myFBReaderApp.clearTextCaches();
                context.myFBReaderApp.getViewWidget().repaint();
                break;
        }
    }
}
