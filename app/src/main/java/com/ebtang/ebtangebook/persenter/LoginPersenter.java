package com.ebtang.ebtangebook.persenter;

import com.alibaba.fastjson.JSON;
import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.LogInView;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.view.login.bean.UserBean;

import org.json.JSONObject;

/**
 * Created by fengzongwei on 2016/9/2 0002.
 */
public class LoginPersenter extends BasePersenter<LogInView>{

    private boolean isLoginNow = false;

    @Override
    public void attachView(LogInView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void getData() {
        if(!isLoginNow){
            isLoginNow = true;
            apiModel.getData(ApiClient.getClient().login(ParamsUtils.login(getMvpView().getLoginName(),getMvpView().getLoginPw())));
        }
    }

    @Override
    public void setData(String obj) {
        isLoginNow = false;
        try{
            JSONObject jsonObject = new JSONObject(obj);
            if(jsonObject.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
                UserBean userBean = JSON.parseObject(jsonObject.getString("body"), UserBean.class);
                SharedPrefHelper.getInstance(getMvpView().context()).setIsLogin(true);
                SharedPrefHelper.getInstance(getMvpView().context()).setUserTouxiangImg(userBean.getUserpic());
                SharedPrefHelper.getInstance(getMvpView().context()).setUserId(userBean.getId() + "");
                SharedPrefHelper.getInstance(getMvpView().context()).setLoginUsername(userBean.getName());
                getMvpView().loginSuccess();
            }else{
                getMvpView().showError(jsonObject.getString("msg"));
            }
        }catch (Exception e){
            getMvpView().showError("登录失败");
        }
    }

    @Override
    public void onError(Exception e) {
        super.onError(e);
        isLoginNow = false;
    }
}
