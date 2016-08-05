package com.ebtang.ebtangebook.view.bookinfo.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ebtang.ebtangebook.widget.MyListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/8/5 0005.
 */
public class BookLabelAdapter extends FragmentPagerAdapter{

    private String[] strings = new String[]{"目录","书签","笔记"};

    private List<MyListFragment> listFragments;

    public BookLabelAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        listFragments = new ArrayList<>();
        listFragments.add(new MyListFragment());
        listFragments.add(new MyListFragment());
        listFragments.add(new MyListFragment());
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = listFragments.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("type",position+5);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return strings[position];
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}
