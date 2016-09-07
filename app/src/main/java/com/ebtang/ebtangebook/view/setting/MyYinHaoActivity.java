package com.ebtang.ebtangebook.view.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.app.BaseFragmentActivity;
import com.ebtang.ebtangebook.mvpView.MyYinHaoView;
import com.ebtang.ebtangebook.persenter.MyYinHaoPersenter;
import com.ebtang.ebtangebook.view.setting.adapter.MessageFragmentAdapter;
import com.ebtang.ebtangebook.view.setting.adapter.YInHaoAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 2016/7/29 0029.
 * 我的银号
 */
public class MyYinHaoActivity extends BaseFragmentActivity implements MyYinHaoView{

    @Bind(R.id.top_title_text)
    TextView textView_top_title;
    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.setting_myyinhao_shengyu)
    TextView textView_shengyu;
    //    @Bind(R.id.persenal_message_center_title_left)
//    TextView textView_message;
//    @Bind(R.id.persenal_message_center_title_right)
//    TextView textView_zhanduan;
//    @Bind(R.id.persenal_message_center_vp)
    @Bind(R.id.persenal_yinhao_tab)
    SmartTabLayout smartTabLayout;
    @Bind(R.id.persenal_yinhao_vp)
    ViewPager viewPager;
    @Bind(R.id.persenal_yinhao_chongzhi_bt)
    TextView textView_chongzhi;

    private MyYinHaoPersenter myYinHaoPersenter;

    private YInHaoAdapter yInHaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persenal_yinhao);
        ButterKnife.bind(this);
        myYinHaoPersenter = new MyYinHaoPersenter();
        myYinHaoPersenter.attachView(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        textView_top_title.setText("我的银号");
        imageView_back.setVisibility(View.VISIBLE);

        imageView_back.setOnClickListener(this);
        textView_chongzhi.setOnClickListener(this);
    }

    @Override
    public void initData() {
        myYinHaoPersenter.getData();

        yInHaoAdapter = new YInHaoAdapter(getSupportFragmentManager());
        viewPager.setAdapter(yInHaoAdapter);
        smartTabLayout.setViewPager(viewPager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
            case R.id.persenal_yinhao_chongzhi_bt:
                Intent intent = new Intent(this,ChongzhiActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showMyMoney(String money) {
        textView_shengyu.setText(money);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return this;
    }
}
