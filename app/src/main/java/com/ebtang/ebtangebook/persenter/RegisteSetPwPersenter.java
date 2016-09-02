package com.ebtang.ebtangebook.persenter;

import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.RegisteSetPwView;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fengzongwei on 2016/9/2 0002.
 */
public class RegisteSetPwPersenter extends BasePersenter<RegisteSetPwView>{

    private boolean isRegisteNow = false;

    @Override
    public void attachView(RegisteSetPwView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void getData() {
        if(!isRegisteNow){
            isRegisteNow = true;
            apiModel.getData(ApiClient.getClient().registe
                    (ParamsUtils.registe(getMvpView().phoneNumber(),getMvpView().getPw(),getMvpView().getCode())));
        }
    }

    @Override
    public void setData(String obj) {
        isRegisteNow = false;
        try{
            JSONObject object = new JSONObject(obj);
            if(object.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
                getMvpView().registeSuccess();
            }else{
                getMvpView().showError(object.getString("msg"));
            }
        }catch (Exception e){
            getMvpView().showError("获取验证码失败");
        }
    }

    @Override
    public void onError(Exception e) {
        super.onError(e);
        isRegisteNow = false;
        getMvpView().showError("网络异常注册失败");
    }

    public void getValidCode(){
        Call<String> call = ApiClient.getClient().getCheckCode(ParamsUtils.getCheckCode(getMvpView().phoneNumber()));
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String str = response.body();
                    try{
                        JSONObject object = new JSONObject(str);
                        if(object.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
                            getMvpView().switchBtStatus();
                            getMvpView().showError("验证码已发送");
                        }else{
                            getMvpView().showError(object.getString("msg"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                        getMvpView().showError("获取验证码失败");
                    }
                } else {
                    getMvpView().showError("获取验证码失败");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                getMvpView().showError("发送验证码失败");
            }
        });
    }

}
