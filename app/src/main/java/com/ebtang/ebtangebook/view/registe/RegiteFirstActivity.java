package com.ebtang.ebtangebook.view.registe;

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
import com.ebtang.ebtangebook.event.RegisteSuccess;
import com.ebtang.ebtangebook.intent.IntentConfig;
import com.ebtang.ebtangebook.mvpView.RegistFirstView;
import com.ebtang.ebtangebook.persenter.RegisteFirstPersenter;
import com.ebtang.ebtangebook.utils.NetworkUtil;
import com.ebtang.ebtangebook.view.registe.utils.Code;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 2016/7/12 0012.
 */
public class RegiteFirstActivity extends BaseActivity implements RegistFirstView{

    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.top_title_left)
    ImageView imageView_left;
    @Bind(R.id.registe_first_phone_et)
    EditText editText_phone;
    @Bind(R.id.registe_first_code_et)
    EditText editText_code;
    @Bind(R.id.registe_first_valid_img)
    ImageView imageView_valid;
    @Bind(R.id.registe_first_bt)
    Button button;

    private String validStr;
    private final String PHONE = "^1[3|4|5|8|7][0-9]\\d{8}$";

    private RegisteFirstPersenter registeFirstPersenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regite_first);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        registeFirstPersenter = new RegisteFirstPersenter();
        registeFirstPersenter.attachView(this);
        initView();
        initData();
    }

    @Override
    public void initView() {
        imageView_left.setImageResource(R.drawable.back);
        imageView_left.setVisibility(View.VISIBLE);
        textView_title.setText("账号注册");
//        imageView_valid.setImageBitmap(Code.getInstance().createBitmap());
//        validStr = Code.getInstance().getCode().toLowerCase();

        imageView_left.setOnClickListener(this);
        imageView_valid.setOnClickListener(this);
        button.setOnClickListener(this);

    }

    @Override
    public void initData() {
        registeFirstPersenter.getCheckImg();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_title_left:
                finish();
                break;
            case R.id.registe_first_valid_img:
//                imageView_valid.setImageBitmap(Code.getInstance().createBitmap());
//                validStr = Code.getInstance().getCode().toLowerCase();
                registeFirstPersenter.getCheckImg();
                break;
            case R.id.registe_first_bt:
                if(NetworkUtil.isNetworkAvailable(this)){
                    if (editText_phone.getText().toString() == null || !editText_phone.getText().toString().matches(PHONE)) {
                        showError("请输入正确的手机号");
                        return;
                    }
                    registeFirstPersenter.getData();
                }else{
                    Toast.makeText(RegiteFirstActivity.this,"请先连接网络",Toast.LENGTH_SHORT).show();
                }
//                Intent intent = new Intent(this,RegiteSecendActivity.class);
//                startActivity(intent);
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
        Toast.makeText(RegiteFirstActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public ImageView getImageView() {
        return imageView_valid;
    }

    @Override
    public String getPhoneNum() {
        return editText_phone.getText().toString();
    }

    @Override
    public String getCheckCode() {
        return editText_code.getText().toString();
    }

    @Override
    public void gotoNext() {
        Intent intent = new Intent(this,RegisteSetPwActivity.class);
        intent.putExtra(IntentConfig.REGISTE_PHONE,editText_phone.getText().toString());
        startActivity(intent);
    }
}
