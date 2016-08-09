package com.ebtang.ebtangebook.view.main;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.view.main.fragment.BookShelfFragment;
import com.ebtang.ebtangebook.view.main.widget.MainTab;
import com.ebtang.ebtangebook.view.main.widget.MyFragmentTabHost;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/9 0009.
 */
public class NewMainActivity extends BaseFragmentActivity{

    @Bind(android.R.id.tabhost)
    MyFragmentTabHost mTabHost;

    private static View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }
        rootView = getWindow().getDecorView();
        initTabs();
    }

    @Override
    public void onBackPressed() {
        if (getCurrentFragment() instanceof BookShelfFragment){
            BookShelfFragment bookShelfFragment = (BookShelfFragment)getCurrentFragment();
            if(bookShelfFragment.isShowDeleteBt())
                bookShelfFragment.hideDeleteBt();
            else
                super.onBackPressed();
        }
        else
            super.onBackPressed();
    }

    @SuppressWarnings("deprecation")
    private void initTabs() {
        MainTab[] tabs = MainTab.values();
        int size = tabs.length;
        for (int i = 0; i < size; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()));
            View indicator = View.inflate(this, R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            ImageView icon = (ImageView) indicator.findViewById(R.id.iv_icon);

            Drawable drawable = this.getResources().getDrawable(mainTab.getResIcon());
            icon.setImageDrawable(drawable);
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            tab.setContent(new TabHost.TabContentFactory() {

                @Override
                public View createTabContent(String tag) {
                    return new View(NewMainActivity.this);
                }
            });
            mTabHost.addTab(tab, mainTab.getClz(), null);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 获取当前正在显示的fragment
     * @return
     */
    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(
                mTabHost.getCurrentTabTag());
    }

    public static int getWindowWidth() {
        return rootView.getMeasuredWidth();
    }

    public static int getWindowHeight() {
        return rootView.getMeasuredHeight();
    }

}
