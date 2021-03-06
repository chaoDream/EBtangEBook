package com.ebtang.ebtangebook.persenter;

import com.alibaba.fastjson.JSON;
import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.YinHaoView;
import com.ebtang.ebtangebook.view.setting.bean.MyYinHaoConsumeBean;
import com.ebtang.ebtangebook.view.setting.bean.MyYinHaoRechargeBean;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by fengzongwei on 2016/9/7 0007.
 * 消费记录
 */
public class YinHaoRechargePersenter extends BasePersenter<YinHaoView> {

    @Override
    public void attachView(YinHaoView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void getData() {
        apiModel.getData(ApiClient.getClient().myYinHaoRecharge(ParamsUtils.myYinHaoRecharge("135")));
    }

    @Override
    public void setData(String obj) {
        try{
            JSONObject jsonObject = new JSONObject(obj);
            if(jsonObject.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
                List<MyYinHaoRechargeBean> myYinHaoRechargeBeanList =
                        JSON.parseArray(jsonObject.getJSONObject("body").getString("list"),MyYinHaoRechargeBean.class);
                getMvpView().showRechargeData(myYinHaoRechargeBeanList);
            }else{
                getMvpView().showError("获取数据失败");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
