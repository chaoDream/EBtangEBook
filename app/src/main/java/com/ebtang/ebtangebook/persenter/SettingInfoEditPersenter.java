package com.ebtang.ebtangebook.persenter;

import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.SettingInfoEditView;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;

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
                apiModel.getData(ApiClient.getClient().feedBack(ParamsUtils.feedBack(
                        SharedPrefHelper.getInstance(getMvpView().context()).getUserId(),getMvpView().getInputContent())));
            }else if(getMvpView().getInputType() == Constants.SETTING_EDIT_MODE_NICHENG){
                apiModel.getData(ApiClient.getClient().modifyNIck(ParamsUtils.modifyNick(
                        SharedPrefHelper.getInstance(getMvpView().context()).getUserId(),getMvpView().getInputContent())));
            }else if(getMvpView().getInputType() == Constants.SETTING_EDIT_MODE_QIANMING){
                apiModel.getData(ApiClient.getClient().modifyQianming(ParamsUtils.modifySignature(
                        SharedPrefHelper.getInstance(getMvpView().context()).getUserId(),getMvpView().getInputContent())));
            }else if(getMvpView().getInputType() == Constants.SETTING_EDIT_MODE_XINGMING){
                apiModel.getData(ApiClient.getClient().modifyRealName(ParamsUtils.modifyRealName(
                        SharedPrefHelper.getInstance(getMvpView().context()).getUserId(),getMvpView().getInputContent())));
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
                }else if(getMvpView().getInputType() == Constants.SETTING_EDIT_MODE_NICHENG){
                    getMvpView().commitSuccess();
                    getMvpView().showError("昵称修改成功");
                }else if(getMvpView().getInputType() == Constants.SETTING_EDIT_MODE_QIANMING){
                    getMvpView().commitSuccess();
                    getMvpView().showError("签名修改成功");
                }else if(getMvpView().getInputType() == Constants.SETTING_EDIT_MODE_XINGMING){
                    getMvpView().commitSuccess();
                    getMvpView().showError("实名认证成功");
                }
            }else{
                getMvpView().showError(object.getString("msg"));
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
