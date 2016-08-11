package com.ebtang.ebtangebook.view.bookcity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ebtang.ebtangebook.view.bookinfo.fragment.PaiHangListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengzongwei on 2016/8/11 0011.
 */
public class PaiHangAdapter extends FragmentPagerAdapter{

    private String[] strings = new String[]{"周榜","月榜","总榜"};
    private List<Fragment> list;
    public PaiHangAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        list = new ArrayList<>();
        list.add(new PaiHangListFragment());
        list.add(new PaiHangListFragment());
        list.add(new PaiHangListFragment());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }



}
