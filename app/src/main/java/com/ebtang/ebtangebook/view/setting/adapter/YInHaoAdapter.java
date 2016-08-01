package com.ebtang.ebtangebook.view.setting.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ebtang.ebtangebook.widget.MyListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengzongwei on 16/7/30.
 */
public class YInHaoAdapter extends FragmentPagerAdapter {

    private String[] titles = new String[]{"充值记录","消费记录"};

    private List<MyListFragment> listFragments = new ArrayList<>();

    public YInHaoAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        listFragments.add(new MyListFragment());
        listFragments.add(new MyListFragment());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = listFragments.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("type",position+3);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
