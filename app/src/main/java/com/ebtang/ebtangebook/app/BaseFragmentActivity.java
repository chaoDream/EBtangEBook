package com.ebtang.ebtangebook.app;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ebtang.ebtangebook.widget.statusbar.SystemBarTintManager;


public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {

    protected AppContext appContext;
    protected Dialog progressDialog;


    protected SystemBarTintManager tintManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @TargetApi(19)
    public void setTranslucentStatus() {

        tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(android.R.color.transparent);  //设置上方状态栏透明
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                winParams.flags |= bits;
        }else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }



    // 初始化
    private void init() {
        // 添加Activity到堆栈
        AppManager.getAppManager().addActivity(this);
        appContext = (AppContext) AppContext.getApp().getApplicationContext();
        //EventBus.getDefault().register(this);
    }

    public abstract void initView();

    public abstract void initData();

    //
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        // 结束Activity&从堆栈中移除
        AppManager.getAppManager().finishActivity(this);
       // EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

}
