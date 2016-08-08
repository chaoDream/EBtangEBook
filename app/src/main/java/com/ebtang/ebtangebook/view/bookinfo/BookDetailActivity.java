package com.ebtang.ebtangebook.view.bookinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.view.bookcity.adapter.RecommGVAdapter;
import com.ebtang.ebtangebook.view.bookinfo.adapter.PingLunAdapter;
import com.ebtang.ebtangebook.view.recomm.RecommAllActivity;
import com.ebtang.ebtangebook.view.recomm.RecommDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/8/4 0004.
 */
public class BookDetailActivity extends BaseActivity{
    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.book_detail_pinglun_lv)
    ListView listView;
    @Bind(R.id.book_detail_bottom_gv)
    GridView gridView;
    @Bind(R.id.bookdetail_mulu_bt)
    RelativeLayout relativeLayout_mulu;
    @Bind(R.id.book_detail_shequ_bt)
    TextView textView_shequ;

    private PingLunAdapter pingLunAdapter;
    private RecommGVAdapter recommGVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookdeitail_activity);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        textView_title.setText("作品详情");

        imageView_back.setOnClickListener(this);
        relativeLayout_mulu.setOnClickListener(this);

        textView_shequ.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BookDetailActivity.this, RecommDetailActivity.class);
                startActivity(intent);
            }
        });

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
        pingLunAdapter = new PingLunAdapter(this,list);
        listView.setAdapter(pingLunAdapter);

        List<Object> list1 = new ArrayList<>();
        list1.add(new Object());
        list1.add(new Object());
        list1.add(new Object());
        recommGVAdapter = new RecommGVAdapter(this,list1);
        gridView.setAdapter(recommGVAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
            case R.id.bookdetail_mulu_bt:
                Intent intent = new Intent(this,BookLabelActivity.class);
                startActivity(intent);
                break;
            case R.id.book_detail_shequ_bt:
                Intent intent1 = new Intent(this,RecommAllActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
