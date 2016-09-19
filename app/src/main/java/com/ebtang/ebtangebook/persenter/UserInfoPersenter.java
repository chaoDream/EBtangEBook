package com.ebtang.ebtangebook.persenter;

import com.alibaba.fastjson.JSON;
import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.UserInfoView;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.view.setting.bean.UserInfoBean;

import org.json.JSONObject;

import group.pals.android.lib.ui.filechooser.utils.E;

/**
 * Created by fengzongwei on 16/9/19.
 */
public class UserInfoPersenter extends BasePersenter<UserInfoView>{

    @Override
    public void attachView(UserInfoView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void getData() {
        apiModel.getData(ApiClient.getClient().userInfo(
                ParamsUtils.userInfo(SharedPrefHelper.getInstance(getMvpView().context()).getUserId())));
    }

    @Override
    public void setData(String obj) {
        try{
            JSONObject object = new JSONObject(obj);
            if(object.getString("code").equals("0")){
                UserInfoBean userInfoBean = JSON.parseObject(object.getString("body"),UserInfoBean.class);
                getMvpView().showUserInfo(userInfoBean);
            }
        }catch (Exception e){
            getMvpView().showError("");
        }
    }

    @Override
    public void onError(Exception e) {
        super.onError(e);
        getMvpView().showError("");
    }
}
