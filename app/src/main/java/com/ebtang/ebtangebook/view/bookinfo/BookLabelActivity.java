package com.ebtang.ebtangebook.view.bookinfo;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.intent.IntentConfig;
import com.ebtang.ebtangebook.view.bookinfo.adapter.BookLabelAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import org.geometerplus.fbreader.fbreader.FBReaderApp;
import org.geometerplus.zlibrary.core.application.ZLApplication;

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

    private int openAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_label_activity);
        ButterKnife.bind(this);
        openAction = getIntent().getIntExtra(IntentConfig.BOOK_LABEL_SHQIAN,-1);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText(((FBReaderApp) ZLApplication.Instance()).getCurrentBook().getTitle());

        imageView_back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        bookLabelAdapter = new BookLabelAdapter(getSupportFragmentManager());
        viewPager.setAdapter(bookLabelAdapter);
        viewPager.setOffscreenPageLimit(3);
        smartTabLayout.setViewPager(viewPager);
        if(openAction == 1){
            viewPager.setCurrentItem(2);
        }
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
