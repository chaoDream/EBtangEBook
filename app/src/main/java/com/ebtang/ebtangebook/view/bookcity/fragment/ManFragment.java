package com.ebtang.ebtangebook.view.bookcity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.view.bookcity.adapter.RecommGVAdapter;
import com.ebtang.ebtangebook.view.bookcity.adapter.RecommLVAdapter;
import com.ebtang.ebtangebook.widget.MyScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/7/19 0019.
 */
public class ManFragment extends BaseFragment{
    @Bind(R.id.bookcity_man_lv)
    ListView listView;
    @Bind(R.id.bookcity_man_tuijian_body)
    LinearLayout linearLayout_tuijian;
    MyScrollGridView gridView;
    private View rootView;
    private RecommGVAdapter recommGVAdapter;
    private RecommLVAdapter recommLVAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(R.layout.bookcity_man_women,container,false);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        ButterKnife.bind(this,rootView);
        initView();
        initData();
        return  rootView;
    }

    @Override
    public void initView() {
        gridView = (MyScrollGridView)linearLayout_tuijian.findViewById(R.id.book_tuijian_gv);
    }

    @Override
    public void initData() {
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        recommGVAdapter = new RecommGVAdapter(getActivity(),list);
        recommLVAdapter = new RecommLVAdapter(getActivity(),list);
        gridView.setAdapter(recommGVAdapter);
        listView.setAdapter(recommLVAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
