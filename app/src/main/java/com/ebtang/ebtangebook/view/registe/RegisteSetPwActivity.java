package com.ebtang.ebtangebook.view.registe;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.event.RegisteSuccess;
import com.ebtang.ebtangebook.intent.IntentConfig;
import com.ebtang.ebtangebook.mvpView.RegisteSetPwView;
import com.ebtang.ebtangebook.persenter.RegisteSetPwPersenter;
import com.ebtang.ebtangebook.view.read.ReadSettingActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 2016/9/2 0002.
 * 注册账号页面
 */
public class RegisteSetPwActivity extends BaseActivity implements RegisteSetPwView{

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.registe_set_pw_phone)
    TextView textView_phone;
    @Bind(R.id.registe_set_pw_code)
    EditText editText_code;
    @Bind(R.id.registe_set_pw_get_code)
    TextView textView_getCode;
    @Bind(R.id.registe_set_pw_pw)
    EditText editText_pw1;
    @Bind(R.id.registe_set_pw_pw2)
    EditText editText_pw2;
    @Bind(R.id.registe_set_pw_bt)
    Button button;

    private String phone;
    private String resend = "倒计时";

    private RegisteSetPwPersenter registeSetPwPersenter;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0){
                textView_getCode.setText("重新获取");
                textView_getCode.setBackgroundDrawable(getResources().getDrawable(R.drawable.registe_check_bt));
                textView_getCode.setTextColor(Color.WHITE);
                textView_getCode.setEnabled(true);
                return;
            }
            textView_getCode.setText(resend+"("+msg.what+"s"+")");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registe_set_pw);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        registeSetPwPersenter = new RegisteSetPwPersenter();
        registeSetPwPersenter.attachView(this);
        phone = getIntent().getStringExtra(IntentConfig.REGISTE_PHONE);
        initView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        textView_title.setText("注册账号");
        textView_phone.setText(phone);
        imageView_back.setVisibility(View.VISIBLE);

        imageView_back.setOnClickListener(this);
        button.setOnClickListener(this);
        textView_getCode.setOnClickListener(this);
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
            case R.id.registe_set_pw_bt:
                if(editText_code.getText().toString() == null || editText_code.getText().toString().equals("")){
                    Toast.makeText(RegisteSetPwActivity.this,"请输入验证码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editText_pw1.getText().toString() == null || editText_pw1.getText().toString().equals("")){
                    Toast.makeText(RegisteSetPwActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(editText_pw2.getText().toString() == null || editText_pw2.getText().toString().equals("")){
                    Toast.makeText(RegisteSetPwActivity.this,"请确认密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!editText_pw1.getText().toString().equals(editText_pw2.getText().toString())){
                    Toast.makeText(RegisteSetPwActivity.this,"两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                registeSetPwPersenter.getData();
                break;
            case R.id.registe_set_pw_get_code:
                if (textView_getCode.getText().toString().contains("重新获取") || textView_getCode.getText().toString().equals("获取验证码")) {
                    registeSetPwPersenter.getValidCode();
                }
                break;
        }
    }

    @Subscribe
    public void onEventMainThread(RegisteSuccess event){
        finish();
    }

    @Override
    public void switchBtStatus() {
        textView_getCode.setText(60 + "s");
        textView_getCode.setTextColor(Color.parseColor("#999999"));
        new Thread() {
            @Override
            public void run() {
                try {
                    int i = 59;
                    while (i >= 0) {
                        handler.sendEmptyMessage(i);
                        i--;
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    @Override
    public String getCode() {
        return editText_code.getText().toString();
    }

    @Override
    public String getPw() {
        return editText_pw1.getText().toString();
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
        Toast.makeText(RegisteSetPwActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public String phoneNumber() {
        return phone;
    }

    @Override
    public void registeSuccess() {
        Intent intent = new Intent(this,RegiteFiveActivity.class);
        startActivity(intent);
    }
}
