package com.ebtang.ebtangebook.view.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseFragment;
import com.ebtang.ebtangebook.view.main.adapter.BookCityFragmentAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/7/11 0011.
 */
public class BookCityFragment extends BaseFragment{
    @Bind(R.id.main_bookcity_vp)
    ViewPager viewPager;
    @Bind(R.id.main_bookcity_title_shizishan)
    TextView textView_shizishan;
    @Bind(R.id.main_bookcity_title_man)
    TextView textView_man;
    @Bind(R.id.main_bookcity_title_women)
    TextView textView_women;
    @Bind(R.id.main_bookcity_title_paihang)
    TextView textView_paiahng;
    @Bind(R.id.main_bookcity_title_free)
    TextView textView_free;
    private View rootView;
    private BookCityFragmentAdapter bookCityFragmentAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView==null){
            rootView=inflater.inflate(R.layout.main_bookcity, null);
        }
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        ButterKnife.bind(this,rootView);
        initData();
        initView();
        return rootView;
    }

    @Override
    public void initView() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeTitleTextBg(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        textView_shizishan.setOnClickListener(this);
        textView_man.setOnClickListener(this);
        textView_women.setOnClickListener(this);
        textView_paiahng.setOnClickListener(this);
        textView_free.setOnClickListener(this);

    }

    @Override
    public void initData() {
        bookCityFragmentAdapter = new BookCityFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(bookCityFragmentAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_bookcity_title_shizishan:
                viewPager.setCurrentItem(0);
                changeTitleTextBg(0);
                break;
            case R.id.main_bookcity_title_man:
                viewPager.setCurrentItem(1);
                changeTitleTextBg(1);
                break;
            case R.id.main_bookcity_title_women:
                viewPager.setCurrentItem(2);
                changeTitleTextBg(2);
                break;
            case R.id.main_bookcity_title_paihang:
                viewPager.setCurrentItem(3);
                changeTitleTextBg(3);
                break;
            case R.id.main_bookcity_title_free:
                viewPager.setCurrentItem(4);
                changeTitleTextBg(4);
                break;
        }
    }

    private void changeTitleTextBg(int position){
        switch (position){
            case 0:
                textView_shizishan.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_checked));
                textView_man.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_women.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_paiahng.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_free.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                break;
            case 1:
                textView_shizishan.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_man.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_checked));
                textView_women.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_paiahng.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_free.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                break;
            case 2:
                textView_shizishan.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_man.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_women.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_checked));
                textView_paiahng.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_free.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                break;
            case 3:
                textView_shizishan.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_man.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_women.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_paiahng.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_checked));
                textView_free.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                break;
            case 4:
                textView_shizishan.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_man.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_women.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_paiahng.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_unchecked));
                textView_free.setBackgroundDrawable(getResources().getDrawable(R.drawable.bookcity_title_channle_checked));
                break;
        }
    }

}
