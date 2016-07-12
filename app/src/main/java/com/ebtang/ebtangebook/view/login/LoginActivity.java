package com.ebtang.ebtangebook.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.view.registe.RegiteFirstActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/7/12 0012.
 */
public class LoginActivity extends BaseActivity{
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.top_title_left)
    ImageView imageView_left;
    @Bind(R.id.login_registe_bt)
    Button button_registe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void initView() {
        textView_title.setText("登录");
        imageView_left.setVisibility(View.VISIBLE);
        imageView_left.setImageResource(R.drawable.back);

        imageView_left.setOnClickListener(this);
        button_registe.setOnClickListener(this);
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
            case R.id.login_registe_bt:
                Intent intent = new Intent(this, RegiteFirstActivity.class);
                startActivity(intent);
                break;
        }
    }
}
