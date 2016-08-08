package com.ebtang.ebtangebook.view.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.view.search.adapter.SearchOfflineAdapter;
import com.ebtang.ebtangebook.view.search.adapter.SearchOnlineAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/8 0008.
 */
public class SearchActivity extends BaseActivity{

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_right)
    ImageView imageView_search;
    @Bind(R.id.top_title_text)
    EditText editText;
    @Bind(R.id.search_online_lv)
    ListView listView_online;
    @Bind(R.id.search_offline_lv)
    ListView listView_offline;

    private SearchOnlineAdapter searchOnlineAdapter;
    private SearchOfflineAdapter searchOfflineAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setOnClickListener(this);
        imageView_search.setOnClickListener(this);
    }

    @Override
    public void initData() {
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        searchOfflineAdapter = new SearchOfflineAdapter(this,list);
        searchOnlineAdapter = new SearchOnlineAdapter(this,list);
        listView_offline.setAdapter(searchOfflineAdapter);
        listView_online.setAdapter(searchOnlineAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
            case R.id.top_title_right:
                Intent intent = new Intent(this,SearchResultActivity.class);
                startActivity(intent);
                break;
        }
    }
}
