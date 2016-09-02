package com.ebtang.ebtangebook.mvpView;

import android.widget.ImageView;

import com.ebtang.ebtangebook.mvp.MvpView;

/**
 * Created by fengzongwei on 2016/8/31 0031.
 * 注册页面1的mvp
 */
public interface RegistFirstView extends MvpView{
    ImageView getImageView();
    String getPhoneNum();//手机号
    String getCheckCode();//验证码
    void gotoNext();//验证成功，进入下一页
}
