package com.ebtang.ebtangebook.view.read;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;

import org.geometerplus.zlibrary.core.application.ZLApplication;
import org.geometerplus.zlibrary.core.view.ZLView;
import org.geometerplus.zlibrary.ui.android.view.animation.CurlAnimationProvider;
import org.geometerplus.zlibrary.ui.android.view.animation.NoneAnimationProvider;
import org.geometerplus.zlibrary.ui.android.view.animation.ShiftAnimationProvider;
import org.geometerplus.zlibrary.ui.android.view.animation.SlideAnimationProvider;
import org.geometerplus.zlibrary.ui.android.view.animation.SlideOldStyleAnimationProvider;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/24 0024.
 */
public class ReadSettingActivity extends BaseActivity{

    private String none = "none",
                   curl = "curl",
                   slide = "slide",
                   shift = "shift";

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.read_setting_fanye_anim)
    LinearLayout linearLayout_anim;
    @Bind(R.id.read_setting_yinliang_fanye)
    LinearLayout linearLayout_yinliang_fanye;
    @Bind(R.id.read_setting_changan_huaxian)
    LinearLayout linearLayout_changan;
    @Bind(R.id.read_setting_fanye_anim_now)
    TextView textView_anim_now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_setting_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("阅读设置");
        setAnimNowName();

        imageView_back.setOnClickListener(this);
        linearLayout_anim.setOnClickListener(this);
        linearLayout_changan.setOnClickListener(this);
        linearLayout_yinliang_fanye.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read_setting_fanye_anim:
                break;
            case R.id.read_setting_changan_huaxian:
                break;
            case R.id.read_setting_yinliang_fanye:
                break;
            case R.id.top_title_left:
                finish();
                break;
        }
    }

    private void setAnimNowName(){
        ZLView.Animation type = ZLApplication.Instance().getCurrentView().getAnimationType();
        switch (type){
            case none:
                textView_anim_now.setText("无");
                break;
            case curl:
                textView_anim_now.setText("仿真");
                break;
            case slide:
                textView_anim_now.setText("覆盖");
                break;
            case shift:
                textView_anim_now.setText("平滑");
                break;
        }
    }

}
