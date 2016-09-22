package com.ebtang.ebtangebook.persenter;

import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.PersenalView;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;

import org.json.JSONObject;

/**
 * Created by dell on 2016/9/21 0021.
 */
public class PersenalPersenter extends BasePersenter<PersenalView>{

    @Override
    public void getData() {
        apiModel.getData(ApiClient.getClient().myUnReadMsg(ParamsUtils.myUnreadMsg(SharedPrefHelper.getInstance(getMvpView().context()).getUserId())));
    }

    @Override
    public void setData(String obj) {
        try{
            JSONObject object = new JSONObject(obj);
            if(object.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
                getMvpView().showUnReadCount(object.getJSONObject("body").getInt("letterNum") + object.getJSONObject("body").getInt("messageNum"));
            }else {
                getMvpView().showError(object.getString("msg"));
            }
        }catch (Exception e){
            e.printStackTrace();
            getMvpView().showError("获取数据失败");
        }
    }
}
