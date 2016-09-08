package com.ebtang.ebtangebook.mvpView;

import com.ebtang.ebtangebook.mvp.MvpView;

/**
 * Created by dell on 2016/9/8 0008.
 */
public interface SettingInfoEditView extends MvpView{

    int getInputType();//获取本页的作用类型
    String getInputContent();//输入的内容
}
