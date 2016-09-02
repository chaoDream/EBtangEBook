package com.ebtang.ebtangebook.view.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.mvpView.LogInView;
import com.ebtang.ebtangebook.persenter.LoginPersenter;
import com.ebtang.ebtangebook.view.registe.RegiteFirstActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dell on 2016/7/12 0012.
 */
public class LoginActivity extends BaseActivity implements LogInView{
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.top_title_left)
    ImageView imageView_left;
    @Bind(R.id.login_registe_bt)
    Button button_registe;
    @Bind(R.id.login_name_et)
    EditText editText_name;
    @Bind(R.id.login_pw_et)
    EditText editText_pw;
    @Bind(R.id.login_bt)
    Button button_login;

    private LoginPersenter loginPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        loginPersenter = new LoginPersenter();
        loginPersenter.attachView(this);
        initView();
    }

    @Override
    public void initView() {
        textView_title.setText("登录");
        imageView_left.setVisibility(View.VISIBLE);
        imageView_left.setImageResource(R.drawable.back);

        imageView_left.setOnClickListener(this);
        button_registe.setOnClickListener(this);
        button_login.setOnClickListener(this);
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
            case R.id.login_bt:
                if(editText_name.getText().toString() == null || editText_name.getText().toString().equals("")){
                    showError("请输入用户名");
                    return;
                }
                if(editText_pw.getText().toString() == null || editText_pw.getText().toString().equals("")){
                    showError("请输入用户名");
                    return;
                }
                loginPersenter.getData();
                break;
        }
    }

    @Override
    public String getLoginName() {
        return editText_name.getText().toString();
    }

    @Override
    public String getLoginPw() {
        return editText_pw.getText().toString();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public void loginSuccess() {
        finish();
    }
}
