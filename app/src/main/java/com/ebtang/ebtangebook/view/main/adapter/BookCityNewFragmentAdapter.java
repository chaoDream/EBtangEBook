package com.ebtang.ebtangebook.view.main.adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by dell on 2016/8/9 0009.
 */
public class BookCityNewFragmentAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private final ViewPager mViewPager;
    public ArrayList<ViewPageInfo> mTabs = new ArrayList<ViewPageInfo>();
    private Map<String, Fragment> mFragments = new ArrayMap<>();

    public BookCityNewFragmentAdapter(FragmentManager fm, ViewPager pager) {
        super(fm);
        mContext = pager.getContext();
        mViewPager = pager;
        mViewPager.setAdapter(this);
    }

    public void addTab(String title, String tag, Class<?> clss, Bundle args) {
        ViewPageInfo viewPageInfo = new ViewPageInfo(title, tag, clss, args);
        addFragment(viewPageInfo);
    }

    private void addFragment(ViewPageInfo info) {
        if (info == null) {
            return;
        }
        mTabs.add(info);
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        ViewPageInfo info = mTabs.get(position);

        Fragment fragment = mFragments.get(info.tag);
        if (fragment == null) {
            fragment = Fragment.instantiate(mContext, info.clss.getName(), info.args);
            // 避免重复创建而进行缓存
            mFragments.put(info.tag, fragment);
        }
        return fragment;
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mTabs.size();
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
