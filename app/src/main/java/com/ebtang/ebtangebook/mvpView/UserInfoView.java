package com.ebtang.ebtangebook.mvpView;

import com.ebtang.ebtangebook.mvp.MvpView;
import com.ebtang.ebtangebook.view.setting.bean.UserInfoBean;

/**
 * Created by fengzongwei on 16/9/19.
 */
public interface UserInfoView extends MvpView{
    void showUserInfo(UserInfoBean userInfoBean);//显示个人信息
}
