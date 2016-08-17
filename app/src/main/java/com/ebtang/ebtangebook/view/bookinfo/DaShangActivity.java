package com.ebtang.ebtangebook.view.bookinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.view.bookinfo.adapter.DaShangFansAdapter;
import com.ebtang.ebtangebook.view.bookinfo.adapter.DaShangGiftAdapter;
import com.ebtang.ebtangebook.view.bookinfo.adapter.DaShangJiLuAdapter;
import com.ebtang.ebtangebook.widget.MyScrollGridView;
import com.ebtang.ebtangebook.widget.MyScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/17 0017.
 */
public class DaShangActivity extends BaseActivity{

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.dashang_gv)
    MyScrollGridView myScrollGridView;
    @Bind(R.id.dashang_discount)
    ImageView imageView_discount;
    @Bind(R.id.dashang_plus)
    ImageView imageView_plus;
    @Bind(R.id.dashang_count)
    TextView textView_gift_count;
    @Bind(R.id.dashang_open_jilu)
    TextView textView_open_jilu;
    @Bind(R.id.dashang_lv)
    MyScrollListView myScrollListView_fans;
    @Bind(R.id.dashang_lv_bottom)
    MyScrollListView myScrollListView_jilu;

    private DaShangJiLuAdapter daShangJiLuAdapter;
    private DaShangFansAdapter daShangFansAdapter;
    private DaShangGiftAdapter daShangGiftAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashang_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("打赏");

        imageView_back.setOnClickListener(this);
        textView_open_jilu.setOnClickListener(this);
        imageView_discount.setOnClickListener(this);
        imageView_plus.setOnClickListener(this);
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
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        daShangFansAdapter = new DaShangFansAdapter(this,list);
        daShangGiftAdapter = new DaShangGiftAdapter(this,list);
        daShangJiLuAdapter = new DaShangJiLuAdapter(this,list);
        myScrollGridView.setAdapter(daShangGiftAdapter);
        myScrollListView_fans.setAdapter(daShangFansAdapter);
        myScrollListView_jilu.setAdapter(daShangJiLuAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
        }
    }
}
