package com.ebtang.ebtangebook.view.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvpView.MyVipView;
import com.ebtang.ebtangebook.persenter.MyVipPersenter;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.view.setting.bean.MyVipBean;
import com.ebtang.ebtangebook.widget.myWebView.WebViewActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 16/9/20.
 */
public class MyVipLevelActivity extends BaseActivity implements MyVipView{
    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.my_vip_level_img)
    ImageView imageView_touxiang;
    @Bind(R.id.my_vip_level_nick)
    TextView textView_nick;
    @Bind(R.id.my_vip_level_pb)
    ProgressBar progressBar;
    @Bind(R.id.my_vip_level_progress_tv)
    TextView textView_progress;
    @Bind(R.id.my_vip_level_fansvalue)
    TextView textView_fansValue;
    @Bind(R.id.my_vip_level_gold_count)
    TextView textView_gold;
    @Bind(R.id.my_vip_level_discount)
    TextView textView_discount;
    @Bind(R.id.my_vip_Level_viprule)
    LinearLayout linearLayout_vip_rule;
    @Bind(R.id.my_vip_Level_fansrule)
    LinearLayout linearLayout_fansRule;

    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .displayer(new CircleBitmapDisplayer(70))
            .build();

    private MyVipPersenter myVipPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_vip_level);
        ButterKnife.bind(this);
        myVipPersenter = new MyVipPersenter();
        myVipPersenter.attachView(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        linearLayout_fansRule.setOnClickListener(this);
        linearLayout_vip_rule.setOnClickListener(this);
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        imageView_back.setOnClickListener(this);
        textView_title.setText("VIP等级");
        ImageLoader.getInstance().displayImage(SharedPrefHelper.getInstance(this).getUserTouxiangImg(),imageView_touxiang,options);
    }

    @Override
    public void initData() {
        myVipPersenter.getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
            case R.id.my_vip_Level_viprule:
                Intent intent_vip_rule = new Intent(this, WebViewActivity.class);
                intent_vip_rule.putExtra(Constants.APP_WEBVIEW_URL,"http://test.ebtang.com/api/V1_0/user/vip_rule");
                intent_vip_rule.putExtra(Constants.APP_WEBVIEW_TITLE,"VIP等级规则");
                startActivity(intent_vip_rule);
                break;
            case R.id.my_vip_Level_fansrule:
                Intent intent_fans_rule = new Intent(this, WebViewActivity.class);
                intent_fans_rule.putExtra(Constants.APP_WEBVIEW_URL,"http://test.ebtang.com/api/V1_0/user/fans_rule");
                intent_fans_rule.putExtra(Constants.APP_WEBVIEW_TITLE,"详细粉丝值");
                startActivity(intent_fans_rule);
                break;
        }
    }

    @Override
    public void showData(MyVipBean myVipBean) {
        textView_nick.setText(myVipBean.getNick());
        textView_fansValue.setText(myVipBean.getFansValue()+"");
        textView_gold.setText(myVipBean.getGoldenValue()+"");
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
