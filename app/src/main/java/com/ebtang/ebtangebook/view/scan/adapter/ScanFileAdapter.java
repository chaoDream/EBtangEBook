package com.ebtang.ebtangebook.view.scan.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ebtang.ebtangebook.view.scan.fragment.AutoScanFragment;
import com.ebtang.ebtangebook.view.scan.fragment.LocalFileListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/8/10 0010.
 */
public class ScanFileAdapter extends FragmentPagerAdapter{

    private String[] strings = new String[]{"智能导入","导入本机"};
    private AutoScanFragment autoScanFragment = new AutoScanFragment();
    private LocalFileListFragment localFileListFragment = new LocalFileListFragment();
    private List<Fragment> list = new ArrayList<>();

    public ScanFileAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
        list.add(autoScanFragment);
        list.add(localFileListFragment);
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
        return 2;
    }
}
