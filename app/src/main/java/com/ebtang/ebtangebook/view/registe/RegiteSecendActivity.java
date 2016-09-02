package com.ebtang.ebtangebook.view.registe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.event.RegisteSuccess;
import com.ebtang.ebtangebook.intent.IntentConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 16/7/12.
 */
public class RegiteSecendActivity extends BaseActivity {

    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.top_title_left)
    ImageView imageView_left;
    @Bind(R.id.registe_secend_bt)
    Button button;
    @Bind(R.id.registe_secend_show_phone)
    TextView textView_showPhone;

    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registe_secend);
        phone = getIntent().getStringExtra(IntentConfig.REGISTE_PHONE);
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        String textShow = phone.substring(0,3) + "******" + phone.substring(phone.length()-3);
        textView_showPhone.setText(textShow);
        imageView_left.setImageResource(R.drawable.back);
        imageView_left.setVisibility(View.VISIBLE);
        textView_title.setText("账号注册");
        button.setOnClickListener(this);
        imageView_left.setOnClickListener(this);
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
            case R.id.registe_secend_bt:
                Intent intent = new Intent(this,RegiteThreeActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Subscribe
    public void onEventMainThread(RegisteSuccess event){
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
