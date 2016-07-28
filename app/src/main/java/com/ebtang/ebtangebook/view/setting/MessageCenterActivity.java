package com.ebtang.ebtangebook.view.setting;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.view.setting.adapter.MessageFragmentAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 2016/7/25 0025.
 */
public class MessageCenterActivity extends BaseFragmentActivity {
    @Bind(R.id.persenal_message_center_title_left)
    TextView textView_message;
    @Bind(R.id.persenal_message_center_title_right)
    TextView textView_zhanduan;
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
        textView_message.setOnClickListener(this);
        textView_zhanduan.setOnClickListener(this);
    }

    @Override
    public void initData() {
        messageFragmentAdapter = new MessageFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(messageFragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if(i == 0){
                    textView_message.setTextColor(getResources().getColor(R.color.primary_red));
                    textView_zhanduan.setTextColor(getResources().getColor(R.color.text_color_primary));
                }else {
                    textView_message.setTextColor(getResources().getColor(R.color.primary_red));
                    textView_zhanduan.setTextColor(getResources().getColor(R.color.text_color_primary));
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.persenal_message_center_title_right:
                viewPager.setCurrentItem(0);
                break;
            case R.id.persenal_message_center_title_left:
                viewPager.setCurrentItem(1);
                break;
        }
    }
}
