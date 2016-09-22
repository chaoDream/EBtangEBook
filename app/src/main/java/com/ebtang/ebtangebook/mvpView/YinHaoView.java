package com.ebtang.ebtangebook.mvpView;

import com.ebtang.ebtangebook.mvp.MvpView;
import com.ebtang.ebtangebook.view.setting.bean.MyMsgBean;
import com.ebtang.ebtangebook.view.setting.bean.MyMsgListBean;
import com.ebtang.ebtangebook.view.setting.bean.MyYinHaoConsumeBean;
import com.ebtang.ebtangebook.view.setting.bean.MyYinHaoRechargeBean;

import java.util.List;

/**
 * Created by fengzongwei on 2016/9/7 0007.
 */
public interface YinHaoView extends MvpView{
    void showConsumeData(List<MyYinHaoConsumeBean> myYinHaoConsumeBeanList);//显示消费数据
    void showRechargeData(List<MyYinHaoRechargeBean> myYinHaoRechargeBeanList);//显示充值数据
    void showMsgList(MyMsgListBean myMsgListBean);//显示消息中心数据
}
