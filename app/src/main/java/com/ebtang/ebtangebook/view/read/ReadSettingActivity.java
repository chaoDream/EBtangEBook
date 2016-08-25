package com.ebtang.ebtangebook.view.read;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.event.AnimStyle;
import com.ebtang.ebtangebook.event.LongClickDrawLine;
import com.ebtang.ebtangebook.event.VolumeChangePage;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;

import org.geometerplus.zlibrary.core.application.ZLApplication;
import org.geometerplus.zlibrary.core.view.ZLView;
import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 2016/8/24 0024.
 * 阅读设置
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
    @Bind(R.id.read_setting_yinliang_fanye_cb)
    CheckBox checkBox_yinliang;
    @Bind(R.id.read_setting_changan_huaxian_cb)
    CheckBox checkBox_changan;

    private Dialog dialog;
    private TextView textView_fangzhen,textView_fugai,textView_pinghua,textView_none;
    private LinearLayout linearLayout_empty;

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

        checkBox_changan.setChecked(SharedPrefHelper.getInstance(this).getIsLongClickDraw());
        checkBox_yinliang.setChecked(SharedPrefHelper.getInstance(this).getIsVolumeChangePage());

        checkBox_yinliang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPrefHelper.getInstance(ReadSettingActivity.this).setIsVolumeChangePage(isChecked);
                EventBus.getDefault().post(new VolumeChangePage(isChecked));
            }
        });
        checkBox_changan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPrefHelper.getInstance(ReadSettingActivity.this).setIsLongClickDraw(isChecked);
                EventBus.getDefault().post(new LongClickDrawLine(isChecked));
            }
        });

        initAnimDialog();
        setAnimNowName();

        textView_fugai.setOnClickListener(this);
        textView_fangzhen.setOnClickListener(this);
        textView_pinghua.setOnClickListener(this);
        textView_none.setOnClickListener(this);
        imageView_back.setOnClickListener(this);
        linearLayout_anim.setOnClickListener(this);
        linearLayout_changan.setOnClickListener(this);
        linearLayout_yinliang_fanye.setOnClickListener(this);
        linearLayout_empty.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read_setting_fanye_anim:
                dialog.show();
                break;
            case R.id.read_setting_changan_huaxian:
                break;
            case R.id.read_setting_yinliang_fanye:
                break;
            case R.id.top_title_left:
                finish();
                break;
            case R.id.select_anim_fangzhen:
                EventBus.getDefault().post(new AnimStyle(curl));
                setAnimNowName();
                dialog.dismiss();
                break;
            case R.id.select_anim_fugai:
                EventBus.getDefault().post(new AnimStyle(slide));
                setAnimNowName();
                dialog.dismiss();
                break;
            case R.id.select_anim_none:
                EventBus.getDefault().post(new AnimStyle(none));
                setAnimNowName();
                dialog.dismiss();
                break;
            case R.id.select_anim_pinghua:
                EventBus.getDefault().post(new AnimStyle(shift));
                setAnimNowName();
                dialog.dismiss();
                break;
            case R.id.select_change_page_anim_dialog_top:
                dialog.dismiss();
                break;
        }
    }

    /**
     * 设置当前翻页动画名称
     */
    private void setAnimNowName(){
        ZLView.Animation type = ZLApplication.Instance().getCurrentView().getAnimationType();
        switch (type){
            case none:
                textView_anim_now.setText("无");
                textView_none.setTextColor(getResources().getColor(R.color.orange));
                textView_fangzhen.setTextColor(Color.BLACK);
                textView_fugai.setTextColor(Color.BLACK);
                textView_pinghua.setTextColor(Color.BLACK);
                break;
            case curl:
                textView_anim_now.setText("仿真");
                textView_none.setTextColor(Color.BLACK);
                textView_fangzhen.setTextColor(getResources().getColor(R.color.orange));
                textView_fugai.setTextColor(Color.BLACK);
                textView_pinghua.setTextColor(Color.BLACK);
                break;
            case slide:
                textView_anim_now.setText("覆盖");
                textView_none.setTextColor(Color.BLACK);
                textView_fangzhen.setTextColor(Color.BLACK);
                textView_fugai.setTextColor(getResources().getColor(R.color.orange));
                textView_pinghua.setTextColor(Color.BLACK);
                break;
            case shift:
                textView_anim_now.setText("平滑");
                textView_none.setTextColor(Color.BLACK);
                textView_fangzhen.setTextColor(Color.BLACK);
                textView_fugai.setTextColor(Color.BLACK);
                textView_pinghua.setTextColor(getResources().getColor(R.color.orange));
                break;
        }
    }

    /**
     * 显示选择翻页动画的Dialog
     */
    private void initAnimDialog(){
            dialog = new Dialog(this, R.style.MyDialogStyle);
            View contentView = LayoutInflater.from(this).inflate(R.layout.select_change_page_anim_dialog,null);
            textView_fangzhen = (TextView)contentView.findViewById(R.id.select_anim_fangzhen);
            textView_pinghua = (TextView)contentView.findViewById(R.id.select_anim_pinghua);
            textView_fugai = (TextView)contentView.findViewById(R.id.select_anim_fugai);
            textView_none = (TextView)contentView.findViewById(R.id.select_anim_none);
            linearLayout_empty = (LinearLayout)contentView.findViewById(R.id.select_change_page_anim_dialog_top);
            dialog.setContentView(contentView);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            setDialog(dialog);
    }

    /**
     * 设置Dialog的大小和Dialog中ListView的自适应
     * @param dialog 对话框
     */
    public void setDialog(Dialog dialog) {
        if (dialog != null) {
            //得到当前dialog对应的窗体
            Window dialogWindow = dialog.getWindow();
            //管理器
            WindowManager m = getWindowManager();
            //屏幕分辨率，获取屏幕宽、高用
            Display d = m.getDefaultDisplay();
            //获取对话框当前的参数值
            WindowManager.LayoutParams p = dialogWindow.getAttributes();
            //宽度设置为屏幕的0.8
            p.width = (int) (d.getWidth() * 1);
            //获取ListView的高度和当前屏幕的0.6进行比较，如果高，就自适应改变
            p.height = (int)(d.getHeight() * 0.97);
            //设置Dialog的高度
            dialogWindow.setAttributes(p);
        }
    }

}
