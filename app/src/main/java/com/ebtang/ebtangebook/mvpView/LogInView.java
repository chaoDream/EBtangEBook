package com.ebtang.ebtangebook.mvpView;

import com.ebtang.ebtangebook.mvp.MvpView;

/**
 * Created by dell on 2016/9/2 0002.
 */
public interface LogInView extends MvpView{
    String getLoginName();//用户名
    String getLoginPw();//密码
    void loginSuccess();//登录成功
}
