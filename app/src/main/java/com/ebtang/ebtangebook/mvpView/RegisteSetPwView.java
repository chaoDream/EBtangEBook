package com.ebtang.ebtangebook.mvpView;

import com.ebtang.ebtangebook.mvp.MvpView;

/**
 * Created by dell on 2016/9/2 0002.
 */
public interface RegisteSetPwView extends MvpView{

    void switchBtStatus();//修改获取验证码按钮状态
    String getCode();//验证码
    String getPw();//密码
    String phoneNumber();//手机号
    void registeSuccess();//注册成功
}
