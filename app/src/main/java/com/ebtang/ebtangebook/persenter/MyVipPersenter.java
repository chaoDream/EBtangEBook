package com.ebtang.ebtangebook.persenter;

import com.alibaba.fastjson.JSON;
import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.MyVipView;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.view.setting.bean.MyVipBean;

import org.json.JSONObject;

/**
 * Created by fengzongwei on 16/9/20.
 */
public class MyVipPersenter extends BasePersenter<MyVipView>{


    @Override
    public void attachView(MyVipView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void getData() {
        apiModel.getData(ApiClient.getClient().myVipLevel(
                ParamsUtils.myVipLevel(SharedPrefHelper.getInstance(getMvpView().context()).getUserId())));
    }

    @Override
    public void setData(String obj) {
        try{
            JSONObject object = new JSONObject(obj);
            if(object.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
                MyVipBean myVipBean = JSON.parseObject(object.getString("body"),MyVipBean.class);
                getMvpView().showData(myVipBean);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
