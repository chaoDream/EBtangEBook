package com.ebtang.ebtangebook.view.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.intent.IntentConfig;
import com.ebtang.ebtangebook.mvpView.UserInfoView;
import com.ebtang.ebtangebook.persenter.UserInfoPersenter;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.view.setting.bean.UserInfoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by fengzongwei on 16/9/19.
 */
public class UserInfoSettingActivity extends BaseActivity implements UserInfoView{
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.user_info_setting_id)
    TextView textView_id;
    @Bind(R.id.user_info_setting_nicheng)
    TextView textView_nicheng;
    @Bind(R.id.user_info_setting_qianming)
    TextView textView_qianming;
    @Bind(R.id.user_info_setting_phonenum)
    TextView textView_phoneNum;
    @Bind(R.id.user_info_setting_name)
    TextView textView_name;
    @Bind(R.id.user_info_setting_img)
    ImageView imageView;
    @Bind(R.id.user_info_setting_exit)
    Button button_exit;
    @Bind(R.id.user_info_setting_touxiang_body)
    LinearLayout linearLayout_touxiang;
    @Bind(R.id.user_info_setting_nicheng_body)
    LinearLayout linearLayout_nicheng;
    @Bind(R.id.user_info_setting_vip_body)
    LinearLayout linearLayout_vip;
    @Bind(R.id.user_info_setting_qianming_body)
    LinearLayout linearLayout_qianming;
    @Bind(R.id.user_info_setting_mobile_body)
    LinearLayout linearLayout_mobile;
    @Bind(R.id.user_info_setting_realname_body)
    LinearLayout linearLayout_realname;
    @Bind(R.id.user_info_setting_psw_body)
    LinearLayout linearLayout_psw;

    UserInfoPersenter userInfoPersenter;

    private UserInfoBean userInfoBean;

    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .displayer(new CircleBitmapDisplayer(70))
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_setting);
        ButterKnife.bind(this);
        userInfoPersenter = new UserInfoPersenter();
        userInfoPersenter.attachView(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void initView() {
        imageView_back = (ImageView) findViewById(R.id.top_title_left);
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        imageView_back.setOnClickListener(this);
        textView_title.setText("个人信息");

        linearLayout_touxiang.setOnClickListener(this);
        linearLayout_nicheng.setOnClickListener(this);
        linearLayout_vip.setOnClickListener(this);
        linearLayout_qianming.setOnClickListener(this);
        linearLayout_mobile.setOnClickListener(this);
        linearLayout_realname.setOnClickListener(this);
        button_exit.setOnClickListener(this);
    }

    @Override
    public void initData() {
        userInfoPersenter.getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
            case R.id.user_info_setting_touxiang_body:
                break;
            case R.id.user_info_setting_nicheng_body:
                Intent intent_nicheng = new Intent(this,SettingInfoEditActivity.class);
                intent_nicheng.putExtra(IntentConfig.SETTING_INFO_EDIT_TYPE, Constants.SETTING_EDIT_MODE_NICHENG);
                startActivity(intent_nicheng);
                break;
            case R.id.user_info_setting_vip_body:
                break;
            case R.id.user_info_setting_qianming_body:
                Intent intent_qianming = new Intent(this,SettingInfoEditActivity.class);
                intent_qianming.putExtra(IntentConfig.SETTING_INFO_EDIT_TYPE, Constants.SETTING_EDIT_MODE_QIANMING);
                startActivity(intent_qianming);
                break;
            case R.id.user_info_setting_mobile_body:
                break;
            case R.id.user_info_setting_realname_body:
                Intent intent_realname = new Intent(this,SettingInfoEditActivity.class);
                intent_realname.putExtra(IntentConfig.SETTING_INFO_EDIT_TYPE, Constants.SETTING_EDIT_MODE_XINGMING);
                startActivity(intent_realname);
                break;
            case R.id.user_info_setting_exit:
                final MaterialDialog materialDialog = new MaterialDialog(this);
                materialDialog.setTitle("提示");
                materialDialog.setMessage("您确定要退出当前账号吗?");
                materialDialog.setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialDialog.dismiss();
                    }
                });
                materialDialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPrefHelper.getInstance(UserInfoSettingActivity.this).setIsLogin(false);
                        materialDialog.dismiss();
                        finish();
                    }
                });
                materialDialog.show();
                break;
        }
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

    @Override
    public void showUserInfo(UserInfoBean userInfoBean) {
        this.userInfoBean = userInfoBean;
        ImageLoader.getInstance().displayImage(userInfoBean.getUserpic(),imageView,options);
        textView_id.setText(userInfoBean.getId()+"");
        textView_nicheng.setText(userInfoBean.getNick());
        if(!TextUtils.isEmpty(userInfoBean.getSignature()))
            textView_qianming.setText(userInfoBean.getSignature());
        else
            textView_qianming.setText("暂未设置");
        if(!TextUtils.isEmpty(userInfoBean.getMobile()))
            textView_phoneNum.setText(userInfoBean.getMobile());
        else
            textView_phoneNum.setText("暂未绑定手机");
        if(!TextUtils.isEmpty(userInfoBean.getRealName()))
            textView_name.setText(userInfoBean.getRealName());
        else
            textView_name.setText("暂未实名认证");
    }
}
