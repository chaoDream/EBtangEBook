package com.ebtang.ebtangebook.view.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 16/7/30.
 */
public class AboutActivity extends BaseActivity{

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("关于雁北堂");
        imageView_back.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
