package com.ebtang.ebtangebook.mvpView;

import com.ebtang.ebtangebook.mvp.MvpView;
import com.ebtang.ebtangebook.view.setting.bean.MyVipBean;

/**
 * Created by fengzongwei on 16/9/20.
 */
public interface MyVipView extends MvpView{
    void showData(MyVipBean myVipBean);
}
