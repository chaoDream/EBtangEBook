package com.ebtang.ebtangebook.mvpView;

import com.ebtang.ebtangebook.mvp.MvpView;
import com.ebtang.ebtangebook.view.setting.bean.MySubscribBean;

import java.util.List;

/**
 * Created by fengzongwei on 2016/9/5 0005.
 */
public interface MySubscribeView extends MvpView{
    void showData(List<MySubscribBean> mySubscribBeanList);//显示数据
}
