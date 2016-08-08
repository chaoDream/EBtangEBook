package com.ebtang.ebtangebook.view.recomm;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.view.recomm.adapter.RecommAllAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/8 0008.
 */
public class RecommAllActivity extends BaseActivity{
    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.recomm_all_lv)
    ListView listView;

    private RecommAllAdapter recommAllAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recomm_all_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("全部评论");

        imageView_back.setOnClickListener(this);
    }

    @Override
    public void initData() {
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        list.add(new Object());
        recommAllAdapter = new RecommAllAdapter(this,list);
        listView.setAdapter(recommAllAdapter);
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
