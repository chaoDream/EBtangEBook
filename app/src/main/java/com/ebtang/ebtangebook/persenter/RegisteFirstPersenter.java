package com.ebtang.ebtangebook.persenter;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ApiService;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.RegistFirstView;
import com.ebtang.ebtangebook.utils.NetworkUtil;
import com.ebtang.ebtangebook.utils.UUIDUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dell on 2016/8/31 0031.
 */
public class RegisteFirstPersenter extends BasePersenter<RegistFirstView>{

    private boolean isGetDataNow = false;//防止多次点击调用接口

    @Override
    public void attachView(RegistFirstView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void getData() {
        if(!isGetDataNow){
            isGetDataNow = true;
            apiModel.getData(ApiClient.getClient().checkPhone
                    (ParamsUtils.checkPhoneNum(getMvpView().getCheckCode(), getMvpView().getPhoneNum())));
        }
    }

    public void getCheckImg(){
        String imgUrl = ApiClient.API_HOST + ApiService.GET_CHECK_IMG + "?uniqueId=" + UUIDUtil.getDeviceUUID(getMvpView().context());
        OkHttpUtils
                .get()//
                .url(imgUrl)//
                .tag(this)//
                .build()//
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(new BitmapCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id)
                    {
                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int id)
                    {
                        Log.e("TAG", "onResponse：complete");
                        getMvpView().getImageView().setImageBitmap(bitmap);
                    }
                });
    }

    @Override
    public void setData(String obj) {
        isGetDataNow = false;
        try{
            JSONObject object = new JSONObject(obj);
            if(object.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
                JSONObject object1 = object.getJSONObject("body");
                if(!object1.getBoolean("isRegister")){
                    getMvpView().gotoNext();
                }else{
                    getMvpView().showError(object.getString("手机号已注册"));
                }
            }else{
                getMvpView().showError(object.getString("msg"));
            }
        }catch (Exception e){
            e.printStackTrace();
            getMvpView().showError("加载数据失败");
        }
    }

    @Override
    public void onError(Exception e) {
        super.onError(e);
        isGetDataNow = false;
    }
}
