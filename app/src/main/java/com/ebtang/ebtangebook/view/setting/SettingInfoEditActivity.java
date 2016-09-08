package com.ebtang.ebtangebook.view.setting;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.app.BaseActivity;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.event.BookMarkModify;
import com.ebtang.ebtangebook.intent.IntentConfig;
import com.ebtang.ebtangebook.mvpView.SettingInfoEditView;
import com.ebtang.ebtangebook.persenter.SettingInfoEditPersenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fengzongwei on 16/7/23.
 */
public class SettingInfoEditActivity extends BaseActivity implements SettingInfoEditView{

    @Bind(R.id.top_title_left)
    ImageView imageView_back;
    @Bind(R.id.top_title_text)
    TextView textView_title;
    @Bind(R.id.setting_info_edit_et)
    EditText editText;
    @Bind(R.id.setting_info_bt)
    Button button;
    private int type;

    private SettingInfoEditPersenter settingInfoEditPersenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_info_edit);
        ButterKnife.bind(this);
        settingInfoEditPersenter = new SettingInfoEditPersenter();
        settingInfoEditPersenter.attachView(this);
        type = getIntent().getIntExtra(IntentConfig.SETTING_INFO_EDIT_TYPE,-1);
        initView();
    }

    @Override
    public void initView() {
        imageView_back.setImageResource(R.drawable.back);
        imageView_back.setVisibility(View.VISIBLE);
        setShowMsg();
        imageView_back.setOnClickListener(this);
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
            case R.id.setting_info_bt:
                btAction();
                break;
        }
    }

    /**
     * 根据不同的类型设置不同的title和et中提示、显示内容
     */
    private void setShowMsg(){
        switch (type){
            case Constants.SETTING_EDIT_MODE_BOOKMARK:
                textView_title.setText("笔记");
                button.setText("完成");
                if(getIntent().getStringExtra(IntentConfig.BOOK_MARK_CONTENT) == null ||
                        getIntent().getStringExtra(IntentConfig.BOOK_MARK_CONTENT).equals(""))
                    editText.setHint("添加您的阅读笔记吧~");
                else{
                    editText.setText(getIntent().getStringExtra(IntentConfig.BOOK_MARK_CONTENT));
                    editText.setSelection(getIntent().getStringExtra(IntentConfig.BOOK_MARK_CONTENT).length());
                }
                break;
            default:
                textView_title.setText("意见反馈");
                break;
        }
    }

    /**
     * 不同的类型，点击确定的功能不一样
     */
    private void btAction(){
        switch (type){
            case Constants.SETTING_EDIT_MODE_BOOKMARK:
                if(editText.getText().toString()==null || editText.getText().toString().equals(""))
                    Toast.makeText(SettingInfoEditActivity.this, "请填写笔记内容，然后再提交", Toast.LENGTH_SHORT).show();
                else{
                    EventBus.getDefault().post(new BookMarkModify(editText.getText().toString()));
                    finish();
                }
                break;
            default:
                if(editText.getText().toString() == null || editText.getText().toString().equals("")){
                    Toast.makeText(this,"请输入您的反馈信息，然后再提交",Toast.LENGTH_SHORT).show();
                    return;
                }
                settingInfoEditPersenter.getData();
                break;
        }
    }


    @Override
    public int getInputType() {
        return type;
    }

    @Override
    public String getInputContent() {
        return editText.getText().toString();
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
}
