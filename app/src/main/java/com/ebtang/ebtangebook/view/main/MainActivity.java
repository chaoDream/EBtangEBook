package com.ebtang.ebtangebook.view.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.view.main.fragment.BookCityFragment;
import com.ebtang.ebtangebook.view.main.fragment.BookShelfFragment;
import com.ebtang.ebtangebook.view.main.fragment.FoundFragment;
import com.ebtang.ebtangebook.view.main.fragment.PersenalFragment;
import com.ebtang.ebtangebook.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity {

    private FragmentTabHost mTabHost;
//    private ViewPager mViewPager;
    private MyViewPager mViewPager;
    private List<Fragment> mFragmentList;

    private String mTitles[] = {"书架", "书城", "发现", "我的"};
    private int mImages[] = {
            R.drawable.tab_bookshelf,
            R.drawable.tab_bookcity,
            R.drawable.tab_found,
            R.drawable.tab_persenal
    };

    private static View rootView;

    private BookShelfFragment bookShelfFragment = new BookShelfFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootView = getWindow().getDecorView();
        initView();
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0 && bookShelfFragment.isShowDeleteBt())
            bookShelfFragment.hideDeleteBt();
        else
            super.onBackPressed();
    }

    @Override
    public void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
//        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager = (MyViewPager) findViewById(R.id.view_pager);
        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(bookShelfFragment);
        mFragmentList.add(new BookCityFragment());
        mFragmentList.add(new FoundFragment());
        mFragmentList.add(new PersenalFragment());
        initTabs();
    }

    private void initTabs(){
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mFragmentList.size(); i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTitles[i]).setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec, mFragmentList.get(i).getClass(), null);
//            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragmentList.get(position);
            }

            @Override
            public int getCount() {
                return mFragmentList.size();
            }
        });

        mViewPager.setOffscreenPageLimit(4);

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                bookShelfFragment.hideDeleteBt();

                showView(mTabHost.getCurrentTab());
            }
        });

    }

    private void showView(int position) {
        mViewPager.setCurrentItem(position);
    }

    private View getTabView(final int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.main_tab_item, null);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);
        image.setImageResource(mImages[index]);
        title.setText(mTitles[index]);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击" + mTitles[index], Toast.LENGTH_SHORT).show();
                return;
            }
        });
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }

    public static int getWindowWidth() {
        return rootView.getMeasuredWidth();
    }

    public static int getWindowHeight() {
        return rootView.getMeasuredHeight();
    }




}
