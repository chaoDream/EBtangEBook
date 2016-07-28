package com.ebtang.ebtangebook.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.view.setting.adapter.MessageAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/7/28 0028.
 */
public class MyListFragment extends BaseFragment{

    private ListView listView;

    private List<Object> list = new ArrayList<>();

    private MessageAdapter messageAdapter;

    private int type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        listView = new ListView(getActivity());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        type = getArguments().getInt("type");
        messageAdapter = new MessageAdapter(getActivity(),list,type);
        return listView;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
