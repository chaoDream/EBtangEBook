package com.ebtang.ebtangebook.persenter;

import com.alibaba.fastjson.JSON;
import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.MyYinHaoView;
import com.ebtang.ebtangebook.view.setting.bean.MyYinHaoBean;

import org.json.JSONObject;

/**
 * Created by fengzongwei on 2016/9/5 0005.
 */
public class MyYinHaoPersenter extends BasePersenter<MyYinHaoView>{


    @Override
    public void attachView(MyYinHaoView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void getData() {
        apiModel.getData(ApiClient.getClient().myYinHao(ParamsUtils.myYinHao("135")));
    }

    @Override
    public void setData(String obj) {
        try{
            JSONObject object = new JSONObject(obj);
            if(object.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
                MyYinHaoBean myYinHaoBean = JSON.parseObject(object.getString("body"),MyYinHaoBean.class);
                getMvpView().showMyMoney(myYinHaoBean.getBalanceValue() + "豆");
            }else {
                getMvpView().showError(object.getString("msg"));
            }
        }catch (Exception e){
            e.printStackTrace();
            getMvpView().showError("获取数据失败");
        }
    }
}
