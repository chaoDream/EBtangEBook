package com.ebtang.ebtangebook.view.scan;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.view.scan.adapter.ScanFileAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/10 0010.
 */
public class ScanFileActivity extends BaseFragmentActivity{

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.scan_file_tab)
    SmartTabLayout smartTabLayout;
    @Bind(R.id.scan_file_vp)
    ViewPager viewPager;

    private ScanFileAdapter scanFileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_file_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("导入本地书籍");

        imageView_back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        scanFileAdapter = new ScanFileAdapter(getSupportFragmentManager());
        viewPager.setAdapter(scanFileAdapter);
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
