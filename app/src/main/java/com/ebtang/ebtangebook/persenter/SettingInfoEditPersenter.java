package com.ebtang.ebtangebook.persenter;

import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.SettingInfoEditView;

import org.json.JSONObject;

/**
 * Created by fengzongwei on 2016/9/8 0008.
 * 信息设置页面persenter
 */
public class SettingInfoEditPersenter extends BasePersenter<SettingInfoEditView>{

    private boolean isCommitDataNow = false;

    @Override
    public void attachView(SettingInfoEditView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void getData() {
        if(!isCommitDataNow){
            isCommitDataNow = true;
            if(getMvpView().getInputType() == Constants.SETTING_EDIT_MODE_FEEDBACK){
                apiModel.getData(ApiClient.getClient().feedBack(ParamsUtils.feedBack("135",getMvpView().getInputContent())));
            }
        }
    }

    @Override
    public void setData(String obj) {
        isCommitDataNow = false;
        try{
            JSONObject object = new JSONObject(obj);
            if(object.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
                if(getMvpView().getInputType() == Constants.SETTING_EDIT_MODE_FEEDBACK){
                    getMvpView().showError("提交成功，感谢您的反馈！");
                }
            }else{
                getMvpView().showError("提交失败");
            }
        }catch (Exception e){
            getMvpView().showError("提交失败");
        }
    }

    @Override
    public void onError(Exception e) {
        super.onError(e);
        isCommitDataNow = false;
    }
}
