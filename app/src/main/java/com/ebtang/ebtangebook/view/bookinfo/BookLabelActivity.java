package com.ebtang.ebtangebook.view.bookinfo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookLabelAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/5 0005.
 */
public class BookLabelActivity extends BaseFragmentActivity{
    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.book_label_tab)
    SmartTabLayout smartTabLayout;
    @Bind(R.id.book_label_vp)
    ViewPager viewPager;

    private BookLabelAdapter bookLabelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_label_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("无尽尸路");

        imageView_back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        bookLabelAdapter = new BookLabelAdapter(getSupportFragmentManager());
        viewPager.setAdapter(bookLabelAdapter);
        viewPager.setOffscreenPageLimit(3);
        smartTabLayout.setViewPager(viewPager);
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
