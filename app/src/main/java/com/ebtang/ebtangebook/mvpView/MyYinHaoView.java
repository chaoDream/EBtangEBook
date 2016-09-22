package com.ebtang.ebtangebook.mvpView;

import com.ebtang.ebtangebook.mvp.MvpView;
import com.ebtang.ebtangebook.view.setting.bean.MyMsgBean;

import java.util.List;

/**
 * Created by fengzongwei on 2016/9/5 0005.
 */
public interface MyYinHaoView extends MvpView{
    void showMyMoney(String money);//显示剩余糖豆
}
