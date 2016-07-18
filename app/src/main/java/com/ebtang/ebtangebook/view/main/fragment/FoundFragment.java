package com.ebtang.ebtangebook.view.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.view.found.adapter.FoundAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/7/11 0011.
 */
public class FoundFragment extends BaseFragment{
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.main_found_lv)
    ListView listView;
    @Bind(R.id.top_title_right)
    ImageView imageView_right;
    private View rootView;

    private FoundAdapter foundAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(R.layout.main_found, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        ButterKnife.bind(this,rootView);
        initView();
        return rootView;
    }

    @Override
    public void initView() {
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        foundAdapter = new FoundAdapter(getActivity(),list);
        listView.setAdapter(foundAdapter);
        imageView_right.setImageResource(R.drawable.main_icon_search);
        imageView_right.setVisibility(View.VISIBLE);
        textView_title.setText("发现");
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
