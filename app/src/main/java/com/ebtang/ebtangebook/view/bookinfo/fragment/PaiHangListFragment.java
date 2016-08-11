package com.ebtang.ebtangebook.view.bookinfo.fragment;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.view.bookcity.adapter.RecommLVAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/8/11 0011.
 */
public class PaiHangListFragment extends BaseFragment{

    private ListView listView;

    private RecommLVAdapter recommLVAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listView = new ListView(getActivity());
        listView.setDividerHeight(10);
        initData();
        return listView;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        List<Object> list  = new ArrayList<>();
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        recommLVAdapter = new RecommLVAdapter(getActivity(),list);
        listView.setAdapter(recommLVAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
