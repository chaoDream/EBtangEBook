package com.ebtang.ebtangebook.view.setting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 16/7/23.
 */
public class SettingActivity extends BaseActivity{

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.settting_wifi_dongload_cb)
    CheckBox checkBox_download;
    @Bind(R.id.settting_wifi_gengxin_cb)
    CheckBox checkBox_gengxin;
    @Bind(R.id.settting_yinliangfanye_cb)
    CheckBox checkBox_fanye;
    @Bind(R.id.setting_dianzan)
    LinearLayout linearLayout_dianzan;
    @Bind(R.id.setting_download)
    LinearLayout linearLayout_download;
    @Bind(R.id.setting_gengxin)
    LinearLayout linearLayout_gengxin;
    @Bind(R.id.setting_fanye)
    LinearLayout linearLayout_fanye;
    @Bind(R.id.setting_clear)
    LinearLayout linearLayout_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("设置");

        checkBox_download.setChecked(SharedPrefHelper.getInstance(this).isWifiDownLoad());
        checkBox_gengxin.setChecked(SharedPrefHelper.getInstance(this).isWifiGengXin());
        checkBox_fanye.setChecked(SharedPrefHelper.getInstance(this).isYinLiangFanye());

        imageView_back.setOnClickListener(this);
        linearLayout_clear.setOnClickListener(this);
        linearLayout_dianzan.setOnClickListener(this);
        linearLayout_download.setOnClickListener(this);
        linearLayout_fanye.setOnClickListener(this);
        linearLayout_gengxin.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
            case R.id.setting_clear:
                Toast.makeText(this,"清理缓存",Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_dianzan:
                String str = "market://details?id=" + getPackageName();
                Intent dianzan = new Intent("android.intent.action.VIEW");
                dianzan.setData(Uri.parse(str));
                startActivity(dianzan);
                break;
            case R.id.setting_download:
                SharedPrefHelper.getInstance(this).setWifiDowanload
                        (!SharedPrefHelper.getInstance(this).isWifiDownLoad());
                if(SharedPrefHelper.getInstance(this).isWifiDownLoad()){
                    checkBox_download.setChecked(true);
                }else{
                    checkBox_download.setChecked(false);
                }
                break;
            case R.id.setting_fanye:
                SharedPrefHelper.getInstance(this).setYinliangFanye(
                        !SharedPrefHelper.getInstance(this).isYinLiangFanye());
                if(SharedPrefHelper.getInstance(this).isYinLiangFanye()){
                    checkBox_fanye.setChecked(true);
                }else{
                    checkBox_fanye.setChecked(false);
                }
                break;
            case R.id.setting_gengxin:
                SharedPrefHelper.getInstance(this).setWifiGengxin(
                        !SharedPrefHelper.getInstance(this).isWifiGengXin());
                if(SharedPrefHelper.getInstance(this).isWifiGengXin()){
                    checkBox_gengxin.setChecked(true);
                }else{
                    checkBox_gengxin.setChecked(false);
                }
                break;
        }
    }
}
