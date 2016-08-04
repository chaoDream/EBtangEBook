package com.ebtang.ebtangebook.view.bookcity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.view.bookcity.adapter.RecommGVAdapter;
import com.ebtang.ebtangebook.view.bookcity.adapter.RecommLVAdapter;
import com.ebtang.ebtangebook.view.bookinfo.BookDetailActivity;
import com.ebtang.ebtangebook.view.bookinfo.FenleiActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 16/7/18.
 */
public class ShizishanFragment extends BaseFragment {
    @Bind(R.id.book_city_shizicheng_tuijian_man)
    LinearLayout linearLayout_man;
    @Bind(R.id.book_city_shizicheng_tuijian_women)
    LinearLayout linearLayout_women;
    @Bind(R.id.book_city_shizishan_gv1)
    GridView  gridView_top;
    @Bind(R.id.bookcity_shizishan_lv)
    ListView listView;
    @Bind(R.id.book_city_shizishan_mianfei)
    LinearLayout linearLayout_mianfei;
    @Bind(R.id.book_city_shizishan_banjia)
    LinearLayout linearLayout_banjia;
    @Bind(R.id.book_city_shizishan_retui)
    LinearLayout linearLayout_retui;

    private View rootView;
    private GridView gridView_man,gridView_women;
    private RecommGVAdapter recommGVAdapter_top,recommGVAdapter_man,recommGVAdapter_women;
    private RecommLVAdapter recommLVAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView == null)
            rootView = inflater.inflate(R.layout.bookcity_shizishan,container,false);
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
    public void initData() {
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        recommGVAdapter_top = new RecommGVAdapter(getActivity(),list);
        recommGVAdapter_man = new RecommGVAdapter(getActivity(),list);
        recommGVAdapter_women = new RecommGVAdapter(getActivity(),list);
        recommLVAdapter = new RecommLVAdapter(getActivity(),list);
        gridView_top.setAdapter(recommGVAdapter_top);
        gridView_man.setAdapter(recommGVAdapter_man);
        gridView_women.setAdapter(recommGVAdapter_women);
        listView.setAdapter(recommLVAdapter);
    }

    @Override
    public void initView() {
        gridView_man = (GridView)linearLayout_man.findViewById(R.id.book_tuijian_gv);
        gridView_women = (GridView)linearLayout_women.findViewById(R.id.book_tuijian_gv);

        linearLayout_banjia.setOnClickListener(this);
        linearLayout_mianfei.setOnClickListener(this);
        linearLayout_retui.setOnClickListener(this);

        gridView_man.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });
        gridView_women.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });
        gridView_top.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), BookDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.book_city_shizishan_mianfei:
                Intent intent1 = new Intent(getActivity(), FenleiActivity.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.book_city_shizishan_banjia:
                Intent intent2 = new Intent(getActivity(), FenleiActivity.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.book_city_shizishan_retui:
                Intent intent3 = new Intent(getActivity(), FenleiActivity.class);
                getActivity().startActivity(intent3);
                break;
        }
    }
}
