package com.ebtang.ebtangebook.view.registe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.event.RegisteSuccess;
import com.ebtang.ebtangebook.mvpView.RegistFirstView;
import com.ebtang.ebtangebook.persenter.RegisteFirstPersenter;
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

    @Bind(R.id.registe_first_valid_img)
    ImageView imageView_valid;
    @Bind(R.id.registe_first_bt)
    Button button;

    private String validStr;

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
        validStr = Code.getInstance().getCode().toLowerCase();

        imageView_left.setOnClickListener(this);
        imageView_valid.setOnClickListener(this);
        button.setOnClickListener(this);

    }

    @Override
    public void initData() {
        registeFirstPersenter.getData();
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
                registeFirstPersenter.getData();
                break;
            case R.id.registe_first_bt:
                Intent intent = new Intent(this,RegiteSecendActivity.class);
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

    }

    @Override
    public Context context() {
        return this;
    }

    @Override
    public ImageView getImageView() {
        return imageView_valid;
    }
}
