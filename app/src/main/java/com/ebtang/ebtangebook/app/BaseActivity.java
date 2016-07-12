package com.ebtang.ebtangebook.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

public abstract class BaseActivity extends Activity implements View.OnClickListener {

	protected AppContext appContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	// 初始化
	private void init() {
		// 添加Activity到堆栈
		AppManager.getAppManager().addActivity(this);
		appContext = (AppContext) AppContext.getApp().getApplicationContext();
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
        //EventBus.getDefault().unregister(this);
		super.onDestroy();
	}
	


}
