package com.ebtang.ebtangebook.view.scan.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.view.scan.AutoScanActivity;

/**
 * Created by dell on 2016/8/10 0010.
 */
public class AutoScanFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.auto_scan_fragment,container,false);
        TextView textView = (TextView)rootView.findViewById(R.id.auto_scan_bt);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AutoScanActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}
