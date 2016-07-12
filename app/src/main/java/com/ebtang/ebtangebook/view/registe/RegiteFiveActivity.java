package com.ebtang.ebtangebook.view.registe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.event.RegisteSuccess;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 16/7/12.
 */
public class RegiteFiveActivity extends BaseActivity {

    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.top_title_left)
    ImageView imageView_left;
    @Bind(R.id.registe_five_bt)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registe_five);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        imageView_left.setImageResource(R.drawable.back);
        imageView_left.setVisibility(View.VISIBLE);
        textView_title.setText("账号注册");
        button.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
            case R.id.registe_five_bt:
                EventBus.getDefault().post(new RegisteSuccess());
                finish();
                break;
        }
    }
}
