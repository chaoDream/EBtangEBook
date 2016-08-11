package com.ebtang.ebtangebook.view.bookcity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.view.bookcity.adapter.DiscountGVAdapter;
import com.ebtang.ebtangebook.widget.MyScrollGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/11 0011.
 */
public class DiscountActivity extends BaseActivity{
    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.discount_gv)
    MyScrollGridView myScrollGridView;
    @Bind(R.id.discount_before_price)
    TextView textView_before_price;

    private DiscountGVAdapter discountGVAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discount_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("半价特惠");

        imageView_back.setOnClickListener(this);
        textView_before_price.getPaint().setAntiAlias(true);//抗锯齿
        textView_before_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//中划线
        textView_before_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void initData() {
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        discountGVAdapter = new DiscountGVAdapter(this,list);
        myScrollGridView.setAdapter(discountGVAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
        }
    }
}
