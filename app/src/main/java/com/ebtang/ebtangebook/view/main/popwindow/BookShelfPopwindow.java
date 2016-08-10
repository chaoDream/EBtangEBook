package com.ebtang.ebtangebook.view.main.popwindow;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.view.scan.ScanFileActivity;

import java.lang.annotation.Target;

/**
 * Created by dell on 2016/8/9 0009.
 */
public class BookShelfPopwindow implements View.OnClickListener{

    private Context context;
    private View archr;

    private View contentView;

    private PopupWindow popupWindow;

    private LinearLayout linearLayout_local,linearLayout_buyed,linearLayout_refresh,linearLayout_all;

    private int xoff;

    public BookShelfPopwindow(Context context,View archr){
        this.context = context;
        this.archr = archr;
        initView();
    }


    private void initView(){
        if(popupWindow == null){
            contentView = LayoutInflater.from(context).inflate(R.layout.bookshelf_pop,null);
            linearLayout_all = (LinearLayout)contentView.findViewById(R.id.book_shelf_menu_all);
            linearLayout_buyed = (LinearLayout)contentView.findViewById(R.id.book_shelf_menu_buyed);
            linearLayout_local = (LinearLayout)contentView.findViewById(R.id.book_shelf_menu_local);
            linearLayout_refresh = (LinearLayout)contentView.findViewById(R.id.book_shelf_menu_refresh);
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

            linearLayout_buyed.setOnClickListener(this);
            linearLayout_refresh.setOnClickListener(this);
            linearLayout_local.setOnClickListener(this);
            linearLayout_all.setOnClickListener(this);
        }
    }

    public void showPop(){
        popupWindow.showAsDropDown(archr, xoff, 1);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.book_shelf_menu_all:
                Toast.makeText(context,"查看全部", Toast.LENGTH_SHORT).show();
                dissmissPop();
                break;
            case R.id.book_shelf_menu_buyed:
                Toast.makeText(context,"查看已购", Toast.LENGTH_SHORT).show();
                dissmissPop();
                break;
            case R.id.book_shelf_menu_local:
                Intent intent = new Intent(context, ScanFileActivity.class);
                context.startActivity(intent);
                dissmissPop();
                break;
            case R.id.book_shelf_menu_refresh:
                Toast.makeText(context,"查看更新", Toast.LENGTH_SHORT).show();
                dissmissPop();
                break;
        }
    }

    private void dissmissPop(){
        if(popupWindow!=null)
            popupWindow.dismiss();
    }

}
