package com.ebtang.ebtangebook.view.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.view.login.LoginActivity;
import com.ebtang.ebtangebook.view.setting.MessageCenterActivity;
import com.ebtang.ebtangebook.widget.myWebView.WebViewActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private View rootView;
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
        return rootView;
    }

    @Override
    public void initView() {
        relativeLayout_message.setOnClickListener(this);
        linearLayout_myYinHao.setOnClickListener(this);
        relativeLayout_qiandao.setOnClickListener(this);
        imageView_persenal_icon.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.persenal_message_item:
                Intent intent = new Intent(getActivity(), MessageCenterActivity.class);
                getActivity().startActivity(intent);
                break;
            case R.id.persenal_icon:
                Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.persenal_qiandao_item:
                Intent intent1 = new Intent(getActivity(), WebViewActivity.class);
                intent1.putExtra(Constants.APP_WEBVIEW_TITLE,"签到");
                intent1.putExtra(Constants.APP_WEBVIEW_URL,"http://www.baidu.com");
                break;
        }
    }
}
