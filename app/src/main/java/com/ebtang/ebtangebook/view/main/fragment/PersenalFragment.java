package com.ebtang.ebtangebook.view.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.view.login.LoginActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 2016/7/11 0011.
 */
public class PersenalFragment extends BaseFragment {
    @Bind(R.id.persenal_icon)
    ImageView imageView_persenal_icon;
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
        imageView_persenal_icon.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.persenal_icon:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                break;
        }
    }
}
