package com.ebtang.ebtangebook.view.bookcity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.view.bookcity.adapter.FreeImgListAdapter;
import com.ebtang.ebtangebook.view.bookcity.adapter.FreeListAdapter;
import com.ebtang.ebtangebook.widget.MyScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 16/7/20.
 */
public class FreeFragment extends BaseFragment{
    @Bind(R.id.bookcity_free_paihang_lv)
    MyScrollListView listView;
    @Bind(R.id.bookcity_free_item_bottom_new)
    LinearLayout linearLayout_new;
    @Bind(R.id.bookcity_free_item_bottom_fans)
    LinearLayout linearLayout_fans;

    private MyScrollListView listView_new,listView_fans;

    private FreeImgListAdapter freeImgListAdapter;
    private FreeListAdapter freeListAdapter1,freeListAdapter2;

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(R.layout.bookcity_free_paihang,container,false);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        ButterKnife.bind(this,rootView);
        initView();
        initData();
        return rootView;
    }

    @Override
    public void initView() {
        listView_new   = (MyScrollListView) linearLayout_new.findViewById(R.id.bookcity_free_item_bottom_lv);
        listView_fans   = (MyScrollListView) linearLayout_fans.findViewById(R.id.bookcity_free_item_bottom_lv);
    }

    @Override
    public void initData() {
        List<Object> list1 = new ArrayList<>();
        list1.add(new Object());
        list1.add(new Object());
        list1.add(new Object());
        list1.add(new Object());
        list1.add(new Object());
        list1.add(new Object());
        freeImgListAdapter = new FreeImgListAdapter(getActivity(),list1);
        listView.setAdapter(freeImgListAdapter);

        List<Object> list2 = new ArrayList<>();
        list2.add(new Object());
        list2.add(new Object());
        list2.add(new Object());
        freeListAdapter1 = new FreeListAdapter(getActivity(),list2);
        listView_new.setAdapter(freeListAdapter1);

        List<Object> list3 = new ArrayList<>();
        list3.add(new Object());
        list3.add(new Object());
        list3.add(new Object());
        freeListAdapter2 = new FreeListAdapter(getActivity(),list3);
        listView_fans.setAdapter(freeListAdapter2);
    }

    @Override
    public void onClick(View v) {

    }
}
