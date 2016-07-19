package com.ebtang.ebtangebook.view.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ebtang.ebtangebook.view.bookcity.fragment.ManFragment;
import com.ebtang.ebtangebook.view.bookcity.fragment.ShizishanFragment;
import com.ebtang.ebtangebook.view.bookcity.fragment.WomenFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/7/19 0019.
 */
public class BookCityFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    public BookCityFragmentAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        fragmentList = new ArrayList<>();
        fragmentList.add(new ShizishanFragment());
        fragmentList.add(new ManFragment());
        fragmentList.add(new WomenFragment());
        fragmentList.add(new ShizishanFragment());
        fragmentList.add(new ShizishanFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
