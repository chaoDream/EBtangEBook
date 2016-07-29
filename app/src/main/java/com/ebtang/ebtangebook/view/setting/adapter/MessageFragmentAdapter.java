package com.ebtang.ebtangebook.view.setting.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ebtang.ebtangebook.widget.MyListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/7/28 0028.
 */
public class MessageFragmentAdapter extends FragmentPagerAdapter{

    private String[] titles = new String[]{"消息","站短"};

    private List<MyListFragment> listFragments = new ArrayList<>();

    public MessageFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        listFragments.add(new MyListFragment());
        listFragments.add(new MyListFragment());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = listFragments.get(i);
        Bundle bundle = new Bundle();
        bundle.putInt("type",i+1);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
