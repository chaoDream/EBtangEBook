package com.ebtang.ebtangebook.view.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.view.bookcity.adapter.RecommLVAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 2016/8/2 0002.
 * 自动订阅
 */
public class MySubscribeBookActivity extends BaseActivity{
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.persenal_subscrib_lv)
    ListView listView;

    RecommLVAdapter recommLVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persenal_subscribe);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        textView_title.setText("自动订阅");
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);

        imageView_back.setOnClickListener(this);

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
        recommLVAdapter = new RecommLVAdapter(this,list);
        listView.setAdapter(recommLVAdapter);
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
