package com.ebtang.ebtangebook.view.bookcity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.view.bookcity.adapter.PaiHangAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 2016/8/11 0011.
 */
public class PaiHangActivity extends BaseFragmentActivity{
    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.paihang_tab)
    SmartTabLayout smartTabLayout;
    @Bind(R.id.paihang_vp)
    ViewPager viewPager;

    private PaiHangAdapter paiHangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paihang_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("排行榜");

        imageView_back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        paiHangAdapter = new PaiHangAdapter(getSupportFragmentManager());
        viewPager.setAdapter(paiHangAdapter);
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
