package com.ebtang.ebtangebook.view.read;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.view.recomm.RecommAllActivity;

import org.geometerplus.android.fbreader.FBReader;
import org.geometerplus.android.fbreader.api.MenuNode;
import org.geometerplus.fbreader.fbreader.ActionCode;
import org.geometerplus.zlibrary.core.options.ZLIntegerRangeOption;

/**
 * Created by fengzongwei on 2016/8/18 0018.
 */
public class ReadSetColorFontPop implements View.OnClickListener{

    private FBReader context;
    private View contentView;

    private PopupWindow popupWindow;

    private TextView textView_fangda,textView_suoxiao,textView_set;

    private ImageView imageView_bg1,imageView_bg2,imageView_bg3,imageView_bg4,imageView_bg5;
    private RelativeLayout relativeLayout_bg1,relativeLayout_bg2,relativeLayout_bg3,relativeLayout_bg4,relativeLayout_bg5;

    private String bg1 = "wallpapers/hardpaper.jpg",bg2 = "wallpapers/leather.png",
            bg3 = "wallpapers/sand.png",bg4 = "wallpapers/sepia.png",bg5 = "wallpapers/wood.jpg";

    private ImageView imageView_paiban4,imageView_paiban3,imageView_paiban2;
    private RelativeLayout relativeLayout_paiban4,relativeLayout_paiban3,relativeLayout_paiban2;

    private SeekBar seekBar_liangdu;

    private CheckBox checkBox_liangdu;

    private float liangdu;

    public ReadSetColorFontPop(FBReader context){
        this.context = context;
        initView();
    }

    private void initView(){
        if(contentView == null){
            contentView = LayoutInflater.from(context).inflate(R.layout.read_util_set_bottom,null);
            seekBar_liangdu = (SeekBar)contentView.findViewById(R.id.read_system_liangdu);
            textView_set = (TextView) contentView.findViewById(R.id.read_util_bottom_set);
            checkBox_liangdu = (CheckBox)contentView.findViewById(R.id.read_liangdu_cb);
            textView_fangda = (TextView)contentView.findViewById(R.id.read_set_fangda);
            textView_suoxiao = (TextView)contentView.findViewById(R.id.read_set_suoxiao);
            imageView_bg1 = (ImageView)contentView.findViewById(R.id.read_util_set_bottom_select1);
            imageView_bg2 = (ImageView)contentView.findViewById(R.id.read_util_set_bottom_select2);
            imageView_bg3 = (ImageView)contentView.findViewById(R.id.read_util_set_bottom_select3);
            imageView_bg4 = (ImageView)contentView.findViewById(R.id.read_util_set_bottom_select4);
            imageView_bg5 = (ImageView)contentView.findViewById(R.id.read_util_set_bottom_select5);
            relativeLayout_bg1 = (RelativeLayout)contentView.findViewById(R.id.resd_util_set_bottom_img1);
            relativeLayout_bg2 = (RelativeLayout)contentView.findViewById(R.id.resd_util_set_bottom_img2);
            relativeLayout_bg3 = (RelativeLayout)contentView.findViewById(R.id.resd_util_set_bottom_img3);
            relativeLayout_bg4 = (RelativeLayout)contentView.findViewById(R.id.resd_util_set_bottom_img4);
            relativeLayout_bg5 = (RelativeLayout)contentView.findViewById(R.id.resd_util_set_bottom_img5);
            relativeLayout_paiban4 = (RelativeLayout)contentView.findViewById(R.id.read_util_paiban_four);
            relativeLayout_paiban3 = (RelativeLayout)contentView.findViewById(R.id.read_util_paiban_three);
            relativeLayout_paiban2 = (RelativeLayout)contentView.findViewById(R.id.read_util_paiban_two);
            imageView_paiban4 = (ImageView)contentView.findViewById(R.id.read_setting_paiban_select4);
            imageView_paiban3 = (ImageView)contentView.findViewById(R.id.read_setting_paiban_select3);
            imageView_paiban2 = (ImageView)contentView.findViewById(R.id.read_setting_paiban_select2);
            popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            // 需要设置一下此参数，点击外边可消失
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            // 设置点击窗口外边窗口消失
            popupWindow.setOutsideTouchable(true);
            // 设置此参数获得焦点，否则无法点击
            popupWindow.setFocusable(true);
            popupWindow.setAnimationStyle(R.style.popwin_anim_style);
            checkBox_liangdu.setChecked(SharedPrefHelper.getInstance(context).getIsUseSystemLiangdu());
            checkBox_liangdu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    SharedPrefHelper.getInstance(context).setIsUseSystemLiangdu(isChecked);
                    if (isChecked) {
                        try {
                            liangdu = SharedPrefHelper.getInstance(context).getScreenLiangdu();
                            SharedPrefHelper.getInstance(context).setIsUseSystemLiangdu(true);
                            context.setScreenBrightnessSystem(Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS) / 255.0f);
                            seekBar_liangdu.setProgress(((int) (Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS) / 255.0f * 100)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        SharedPrefHelper.getInstance(context).setIsUseSystemLiangdu(false);
                        seekBar_liangdu.setProgress(((int)(liangdu * 100)));
//                        context.setScreenBrightnessSystem(SharedPrefHelper.getInstance(context).getScreenLiangdu());
                    }
                }
            });
            if(!SharedPrefHelper.getInstance(context).getIsUseSystemLiangdu())
                seekBar_liangdu.setProgress(((int) SharedPrefHelper.getInstance(context).getScreenLiangdu() * 100));
            else
            try{
                seekBar_liangdu.setProgress(((int) (Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS) / 255.0f * 100)));
            }catch (Exception e){

            }
            seekBar_liangdu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                    context.setScreenBrightnessSystem((float)seekBar.getProgress()/10);
                    if(SharedPrefHelper.getInstance(context).getIsUseSystemLiangdu())
                        return;
                    SharedPrefHelper.getInstance(context).setScreenLiangdu((float)seekBar.getProgress()/100);
                    context.setScreenBrightnessSystem((float)seekBar.getProgress()/100);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    String a = "";
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    String a = "";
                }
            });

            setPaibanShow(SharedPrefHelper.getInstance(context).getReadSettingPaiban());
            setImagShow(SharedPrefHelper.getInstance(context).getReadBackgroudPaper());
            textView_suoxiao.setOnClickListener(this);
            textView_fangda.setOnClickListener(this);
            relativeLayout_bg4.setOnClickListener(this);
            relativeLayout_bg5.setOnClickListener(this);
            relativeLayout_bg3.setOnClickListener(this);
            relativeLayout_bg2.setOnClickListener(this);
            relativeLayout_bg1.setOnClickListener(this);
            relativeLayout_paiban4.setOnClickListener(this);
            relativeLayout_paiban3.setOnClickListener(this);
            relativeLayout_paiban2.setOnClickListener(this);
            textView_set.setOnClickListener(this);
        }
    }


    public void showPop(){
        popupWindow.showAtLocation(context.findViewById(R.id.root_view), Gravity.BOTTOM, 0, 0);
    }

    public void dissmissPop(){
        if(popupWindow!=null)
            popupWindow.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read_set_fangda:
                final ZLIntegerRangeOption option = context.myFBReaderApp.ViewOptions.getTextStyleCollection().getBaseStyle().FontSizeOption;
                option.setValue(option.getValue() + 2);
                context.myFBReaderApp.clearTextCaches();
                context.myFBReaderApp.getViewWidget().repaint();
                break;
            case R.id.read_set_suoxiao:
                final ZLIntegerRangeOption options = context.myFBReaderApp.ViewOptions.getTextStyleCollection().getBaseStyle().FontSizeOption;
                options.setValue(options.getValue() - 2);
                context.myFBReaderApp.clearTextCaches();
                context.myFBReaderApp.getViewWidget().repaint();
                break;
            case R.id.resd_util_set_bottom_img1:
                setImagShow(1);
                SharedPrefHelper.getInstance(context).setReadBackgroudPaper(1);
                context.myFBReaderApp.ViewOptions.getColorProfile().WallpaperOption.setValue(bg1);
                context.myFBReaderApp.getViewWidget().reset();
                context.myFBReaderApp.getViewWidget().repaint();
                break;
            case R.id.resd_util_set_bottom_img2:
                setImagShow(2);
                SharedPrefHelper.getInstance(context).setReadBackgroudPaper(2);
                context.myFBReaderApp.ViewOptions.getColorProfile().WallpaperOption.setValue(bg2);
                context.myFBReaderApp.getViewWidget().reset();
                context.myFBReaderApp.getViewWidget().repaint();
                break;
            case R.id.resd_util_set_bottom_img3:
                setImagShow(3);
                SharedPrefHelper.getInstance(context).setReadBackgroudPaper(3);
                context.myFBReaderApp.ViewOptions.getColorProfile().WallpaperOption.setValue(bg3);
                context.myFBReaderApp.getViewWidget().reset();
                context.myFBReaderApp.getViewWidget().repaint();
                break;
            case R.id.resd_util_set_bottom_img4:
                setImagShow(4);
                SharedPrefHelper.getInstance(context).setReadBackgroudPaper(4);
                context.myFBReaderApp.ViewOptions.getColorProfile().WallpaperOption.setValue(bg4);
                context.myFBReaderApp.getViewWidget().reset();
                context.myFBReaderApp.getViewWidget().repaint();
                break;
            case R.id.resd_util_set_bottom_img5:
                setImagShow(5);
                SharedPrefHelper.getInstance(context).setReadBackgroudPaper(5);
                context.myFBReaderApp.ViewOptions.getColorProfile().WallpaperOption.setValue(bg5);
                context.myFBReaderApp.getViewWidget().reset();
                context.myFBReaderApp.getViewWidget().repaint();
                break;
            case R.id.read_util_paiban_four:
                SharedPrefHelper.getInstance(context).setReadSettingPaiban(4);
                context.myFBReaderApp.ViewOptions.TopMargin.setValue(35);
                context.myFBReaderApp.ViewOptions.BottomMargin.setValue(35);
                context.myFBReaderApp.ViewOptions.getTextStyleCollection().getBaseStyle().LineSpaceOption.setValue(13);
                context.myFBReaderApp.clearTextCaches();
                context.myFBReaderApp.getViewWidget().repaint();
                setPaibanShow(4);
                break;
            case R.id.read_util_paiban_three:
                SharedPrefHelper.getInstance(context).setReadSettingPaiban(3);
                context.myFBReaderApp.ViewOptions.TopMargin.setValue(35);
                context.myFBReaderApp.ViewOptions.BottomMargin.setValue(35);
                context.myFBReaderApp.ViewOptions.getTextStyleCollection().getBaseStyle().LineSpaceOption.setValue(17);
                context.myFBReaderApp.clearTextCaches();
                context.myFBReaderApp.getViewWidget().repaint();
                setPaibanShow(3);
                break;
            case R.id.read_util_paiban_two:
                SharedPrefHelper.getInstance(context).setReadSettingPaiban(2);
                context.myFBReaderApp.ViewOptions.TopMargin.setValue(35);
                context.myFBReaderApp.ViewOptions.BottomMargin.setValue(35);
                context.myFBReaderApp.ViewOptions.getTextStyleCollection().getBaseStyle().LineSpaceOption.setValue(20);
                context.myFBReaderApp.clearTextCaches();
                context.myFBReaderApp.getViewWidget().repaint();
                setPaibanShow(2);
                break;
            case R.id.read_util_bottom_set:
                Intent intent = new Intent(context,ReadSettingActivity.class);
                context.startActivity(intent);
                popupWindow.dismiss();
                break;
        }
    }

    /**
     * 设置背景选择按钮显示
     * @param type
     */
    private void setImagShow(int type){
        switch (type){
            case 1:
                imageView_bg1.setVisibility(View.VISIBLE);
                imageView_bg2.setVisibility(View.GONE);
                imageView_bg3.setVisibility(View.GONE);
                imageView_bg4.setVisibility(View.GONE);
                imageView_bg5.setVisibility(View.GONE);
                break;
            case 2:
                imageView_bg1.setVisibility(View.GONE);
                imageView_bg2.setVisibility(View.VISIBLE);
                imageView_bg3.setVisibility(View.GONE);
                imageView_bg4.setVisibility(View.GONE);
                imageView_bg5.setVisibility(View.GONE);
                break;
            case 3:
                imageView_bg1.setVisibility(View.GONE);
                imageView_bg2.setVisibility(View.GONE);
                imageView_bg3.setVisibility(View.VISIBLE);
                imageView_bg4.setVisibility(View.GONE);
                imageView_bg5.setVisibility(View.GONE);
                break;
            case 4:
                imageView_bg1.setVisibility(View.GONE);
                imageView_bg2.setVisibility(View.GONE);
                imageView_bg3.setVisibility(View.GONE);
                imageView_bg4.setVisibility(View.VISIBLE);
                imageView_bg5.setVisibility(View.GONE);
                break;
            case 5:
                imageView_bg1.setVisibility(View.GONE);
                imageView_bg2.setVisibility(View.GONE);
                imageView_bg3.setVisibility(View.GONE);
                imageView_bg4.setVisibility(View.GONE);
                imageView_bg5.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 设置排版按钮显示
     */
    private void setPaibanShow(int type){
        switch (type){
            case 4:
                imageView_paiban4.setImageResource(R.drawable.read_set_paiban_checked);
                imageView_paiban3.setImageResource(R.drawable.read_set_paiban_unchecked);
                imageView_paiban2.setImageResource(R.drawable.read_set_paiban_unchecked);
                break;
            case 3:
                imageView_paiban4.setImageResource(R.drawable.read_set_paiban_unchecked);
                imageView_paiban3.setImageResource(R.drawable.read_set_paiban_checked);
                imageView_paiban2.setImageResource(R.drawable.read_set_paiban_unchecked);
                break;
            case 2:
                imageView_paiban4.setImageResource(R.drawable.read_set_paiban_unchecked);
                imageView_paiban3.setImageResource(R.drawable.read_set_paiban_unchecked);
                imageView_paiban2.setImageResource(R.drawable.read_set_paiban_checked);
                break;
        }
    }


}
