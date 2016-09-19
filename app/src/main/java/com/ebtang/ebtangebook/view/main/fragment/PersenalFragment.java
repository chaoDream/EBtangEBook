package com.ebtang.ebtangebook.view.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.intent.IntentConfig;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.view.login.LoginActivity;
import com.ebtang.ebtangebook.view.setting.AboutActivity;
import com.ebtang.ebtangebook.view.setting.ChongzhiActivity;
import com.ebtang.ebtangebook.view.setting.MessageCenterActivity;
import com.ebtang.ebtangebook.view.setting.MissionActivity;
import com.ebtang.ebtangebook.view.setting.MySubscribeBookActivity;
import com.ebtang.ebtangebook.view.setting.MyYinHaoActivity;
import com.ebtang.ebtangebook.view.setting.ReadHistoryActivity;
import com.ebtang.ebtangebook.view.setting.SettingActivity;
import com.ebtang.ebtangebook.view.setting.SettingInfoEditActivity;
import com.ebtang.ebtangebook.view.setting.UserInfoSettingActivity;
import com.ebtang.ebtangebook.widget.myWebView.WebViewActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.http.GET;

/**
 * Created by fengzongwei on 2016/7/11 0011.
 */
public class PersenalFragment extends BaseFragment {
    @Bind(R.id.persenal_icon)
    ImageView imageView_persenal_icon;
    @Bind(R.id.persenal_message_item)
    RelativeLayout relativeLayout_message;
    @Bind(R.id.persenal_wodeyinhao_item)
    LinearLayout linearLayout_myYinHao;
    @Bind(R.id.persenal_qiandao_item)
    RelativeLayout relativeLayout_qiandao;
    @Bind(R.id.persenal_chongzhi_bt)
    TextView textView_chongzhi;
    @Bind(R.id.persenal_setting)
    RelativeLayout relativeLayout_setting;
    @Bind(R.id.persenal_about)
    RelativeLayout relativeLayout_about;
    @Bind(R.id.persenal_subscrib_item)
    RelativeLayout relativeLayout_dingyue;
    @Bind(R.id.persenal_mission_item)
    RelativeLayout relativeLayout_mission;
    @Bind(R.id.persenal_readhistory_item)
    RelativeLayout relativeLayout_read_history;
    @Bind(R.id.persenal_feedback_item)
    RelativeLayout relativeLayout_feedback;
    @Bind(R.id.persenal_name)
    TextView textView_name;

    private View rootView;
    private ImageLoader imageLoader;

    private DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .cacheOnDisk(true)
            .displayer(new CircleBitmapDisplayer(70))
            .build();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(R.layout.main_persenal, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        ButterKnife.bind(this,rootView);
        initView();
        imageLoader = ImageLoader.getInstance();
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(SharedPrefHelper.getInstance(getActivity()).isLogin()){
            imageLoader.displayImage(
                    SharedPrefHelper.getInstance(getActivity()).getUserTouxiangImg(),
                    imageView_persenal_icon,
                    options);
            textView_name.setText(SharedPrefHelper.getInstance(getActivity()).getLoginUsername());
        }else{
            imageView_persenal_icon.setImageResource(R.drawable.persenal_unlogin);
            textView_name.setText("登录/注册");
        }
    }

    @Override
    public void initView() {
        relativeLayout_feedback.setOnClickListener(this);
        relativeLayout_message.setOnClickListener(this);
        linearLayout_myYinHao.setOnClickListener(this);
        relativeLayout_qiandao.setOnClickListener(this);
        imageView_persenal_icon.setOnClickListener(this);
        textView_chongzhi.setOnClickListener(this);
        relativeLayout_setting.setOnClickListener(this);
        relativeLayout_about.setOnClickListener(this);
        relativeLayout_dingyue.setOnClickListener(this);
        relativeLayout_mission.setOnClickListener(this);
        relativeLayout_read_history.setOnClickListener(this);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.persenal_chongzhi_bt:
                Intent intent4 = new Intent(getActivity(), ChongzhiActivity.class);
                getActivity().startActivity(intent4);
                break;
            case R.id.persenal_message_item:
                Intent intent = new Intent(getActivity(), MessageCenterActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.persenal_icon:
                if(SharedPrefHelper.getInstance(getActivity()).isLogin()){
                    Intent intent_userInfo = new Intent(getActivity(), UserInfoSettingActivity.class);
                    getActivity().startActivity(intent_userInfo);
                }else{
                    Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(intent2);
                }
                break;
//            case R.id.persenal_qiandao_item:
//                Intent intent1 = new Intent(getActivity(), WebViewActivity.class);
//                intent1.putExtra(Constants.APP_WEBVIEW_TITLE,"签到");
//                intent1.putExtra(Constants.APP_WEBVIEW_URL, "http://www.baidu.com");
//                break;
            case R.id.persenal_wodeyinhao_item:
                Intent intent3 = new Intent(getActivity(), MyYinHaoActivity.class);
                getActivity().startActivity(intent3);
                break;
            case R.id.persenal_setting:
                Intent intent5 = new Intent(getActivity(), SettingActivity.class);
                getActivity().startActivity(intent5);
                break;
            case R.id.persenal_about:
                Intent intent6 = new Intent(getActivity(), AboutActivity.class);
                getActivity().startActivity(intent6);
                break;
            case R.id.persenal_subscrib_item:
                Intent intent7 = new Intent(getActivity(), MySubscribeBookActivity.class);
                getActivity().startActivity(intent7);
                break;
            case R.id.persenal_mission_item:
                Intent intent8 = new Intent(getActivity(), MissionActivity.class);
                getActivity().startActivity(intent8);
                break;
            case R.id.persenal_readhistory_item:
                Intent intent9 = new Intent(getActivity(), ReadHistoryActivity.class);
                getActivity().startActivity(intent9);
                break;
            case R.id.persenal_feedback_item:
                Intent intent10 = new Intent(getActivity(), SettingInfoEditActivity.class);
                intent10.putExtra(IntentConfig.SETTING_INFO_EDIT_TYPE,Constants.SETTING_EDIT_MODE_FEEDBACK);
                getActivity().startActivity(intent10);
                break;
        }
    }
}
