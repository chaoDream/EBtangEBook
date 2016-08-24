package com.ebtang.ebtangebook.view.read;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.view.main.popwindow.DensityUtil;

import org.geometerplus.android.fbreader.FBReader;

/**
 * Created by fengzongwei on 2016/8/18 0018.
 */
public class ReadSettingPopWindow implements View.OnClickListener{

    private FBReader context;
    private View archr;

    private View contentView;

    private PopupWindow popupWindow;

    private int xoff;

    private LinearLayout linearLayout_money,linearLayout_command,linearLayout_biji,
            linearLayout_shuqian,linearLayout_detail,linearLayout_set,linearLayout_none;

    public ReadSettingPopWindow(FBReader context,View archr){
        this.context = context;
        this.archr = archr;
        initView();
    }

    private void initView(){
        if(popupWindow == null){
            contentView = LayoutInflater.from(context).inflate(R.layout.read_setting,null);
            linearLayout_money = (LinearLayout)contentView.findViewById(R.id.read_setting_money);
            linearLayout_command = (LinearLayout)contentView.findViewById(R.id.read_setting_command);
            linearLayout_biji = (LinearLayout)contentView.findViewById(R.id.read_setting_biji);
            linearLayout_shuqian = (LinearLayout)contentView.findViewById(R.id.read_setting_lebal);
            linearLayout_detail = (LinearLayout)contentView.findViewById(R.id.read_setting_detail);
            linearLayout_set = (LinearLayout)contentView.findViewById(R.id.read_setting_set);
            linearLayout_none = (LinearLayout)contentView.findViewById(R.id.read_util_none);
            popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            // 需要设置一下此参数，点击外边可消失
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            // 设置点击窗口外边窗口消失
            popupWindow.setOutsideTouchable(true);
            // 设置此参数获得焦点，否则无法点击
            popupWindow.setFocusable(true);
            xoff = (int) ((DensityUtil.getWidthInPx(context) - DensityUtil
                    .dip2px(context, 186)));

            linearLayout_money.setOnClickListener(this);
            linearLayout_command.setOnClickListener(this);
            linearLayout_biji.setOnClickListener(this);
            linearLayout_shuqian.setOnClickListener(this);
            linearLayout_detail.setOnClickListener(this);
            linearLayout_set.setOnClickListener(this);
            linearLayout_none.setOnClickListener(this);
        }
    }

    public void showPop(){
        popupWindow.showAsDropDown(archr, xoff, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read_setting_money:
                break;
            case R.id.read_setting_command:
                break;
            case R.id.read_setting_biji:
                break;
            case R.id.read_setting_lebal:
                break;
            case R.id.read_setting_detail:
                break;
            case R.id.read_setting_set:
                dissmissPop();
                context.showUtil();
                context.readSetColorFontPop.showPop();
                break;
            case R.id.read_util_none:
                dissmissPop();
                break;
        }
    }

    private void dissmissPop(){
        if(popupWindow!=null)
            popupWindow.dismiss();
    }

}
