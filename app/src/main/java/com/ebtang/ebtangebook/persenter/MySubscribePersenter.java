package com.ebtang.ebtangebook.persenter;

import com.alibaba.fastjson.JSON;
import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.MySubscribeView;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.view.setting.bean.MySubscribBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by fengzongwei on 2016/9/5 0005.
 * 自动订阅persenter
 */
public class MySubscribePersenter extends BasePersenter<MySubscribeView>{

    @Override
    public void attachView(MySubscribeView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void getData() {
        //SharedPrefHelper.getInstance(getMvpView().context()).getUserId()
        apiModel.getData(ApiClient.getClient().mySubscribe
                (ParamsUtils.mySubscribe("25370")));
    }

    @Override
    public void setData(String obj) {
        try{
            JSONObject object = new JSONObject(obj);
            if(object.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
                List<MySubscribBean> mySubscribBeanList = JSON.parseArray(object.getJSONObject("body").getString("list"),MySubscribBean.class);
                getMvpView().showData(mySubscribBeanList);
            }else{
                getMvpView().showError(object.getString("msg"));
            }
        }catch (Exception e){
            getMvpView().showError("获取数据失败");
        }
    }
}
