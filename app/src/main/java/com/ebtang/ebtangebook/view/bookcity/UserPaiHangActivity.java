package com.ebtang.ebtangebook.view.bookcity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.view.bookcity.adapter.UserPaiHangAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/11 0011.
 */
public class UserPaiHangActivity extends BaseActivity{

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.user_paihang_lv)
    ListView listView;

    private UserPaiHangAdapter userPaiHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_paihang);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("粉丝排行榜");

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
        userPaiHangAdapter = new UserPaiHangAdapter(this,list);
        listView.setAdapter(userPaiHangAdapter);
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
