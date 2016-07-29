package com.ebtang.ebtangebook.view.setting;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.view.setting.adapter.MessageFragmentAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 2016/7/25 0025.
 */
public class MessageCenterActivity extends BaseFragmentActivity {
    @Bind(R.id.top_title_text)
    TextView textView_top_title;
    @Bind(R.id.top_title_left)
    ImageView imageView_back;
//    @Bind(R.id.persenal_message_center_title_left)
//    TextView textView_message;
//    @Bind(R.id.persenal_message_center_title_right)
//    TextView textView_zhanduan;
//    @Bind(R.id.persenal_message_center_vp)
    @Bind(R.id.persenal_message_center_tab)
    SmartTabLayout smartTabLayout;
    @Bind(R.id.persenal_message_center_vp)
    ViewPager viewPager;

    private MessageFragmentAdapter messageFragmentAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persenal_message_center);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        textView_top_title.setText("消息中心");
        imageView_back.setVisibility(View.VISIBLE);

        imageView_back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        messageFragmentAdapter = new MessageFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(messageFragmentAdapter);
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
