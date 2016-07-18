package com.ebtang.ebtangebook.view.bookcity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;

/**
 * Created by fengzongwei on 16/7/18.
 */
public class ShizishanFragment extends BaseFragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.bookcity_shizishan,container,false);
        return rootView;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {

    }
}
