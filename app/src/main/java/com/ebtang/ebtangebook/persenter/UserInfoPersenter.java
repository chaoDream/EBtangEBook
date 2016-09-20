package com.ebtang.ebtangebook.persenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ebtang.ebtangebook.api.ApiClient;
import com.ebtang.ebtangebook.api.ApiService;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.ebtang.ebtangebook.constants.Constants;
import com.ebtang.ebtangebook.mvp.BasePersenter;
import com.ebtang.ebtangebook.mvpView.UserInfoView;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.view.setting.bean.UserInfoBean;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import group.pals.android.lib.ui.filechooser.utils.E;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import okhttp3.Callback;
import retrofit2.Response;

/**
 * Created by fengzongwei on 16/9/19.
 */
public class UserInfoPersenter extends BasePersenter<UserInfoView>{

    @Override
    public void attachView(UserInfoView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void getData() {
        apiModel.getData(ApiClient.getClient().userInfo(
                ParamsUtils.userInfo(SharedPrefHelper.getInstance(getMvpView().context()).getUserId())));
    }

    @Override
    public void setData(String obj) {
        try{
            JSONObject object = new JSONObject(obj);
            if(object.getString("code").equals("0")){
                UserInfoBean userInfoBean = JSON.parseObject(object.getString("body"),UserInfoBean.class);
                getMvpView().showUserInfo(userInfoBean);
            }
        }catch (Exception e){
            getMvpView().showError("");
        }
    }

    public void commitImg(String path){
        String userId =  SharedPrefHelper.getInstance(getMvpView().context()).getUserId();
        File file = new File(path);
        byte[] imageData = Bitmap2Bytes(BitmapFactory.decodeFile(path));
        Map<String,RequestBody> photos = new HashMap<>();
//        Map<String,String> photos = new HashMap<>();
        String filename = path.substring(path.lastIndexOf("/")+1);
        photos.put("headImage\"; filename=\"" + filename,RequestBody.create(MediaType.parse("image/png"), file));
        photos.put("userId",toRequestBody(userId));
//        photos.put("userId",userId);
//        photos.put("headImage","");
        Call<String> call = ApiClient.getClient().postUserImg(photos);
        call.enqueue(new retrofit2.Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String obj = response.body();
                try {
                    JSONObject object = new JSONObject(obj);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
//        RequestBody body = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("userId", userId)
//                .addPart(Headers.of(
//                                "Content-Disposition",
//                                "form-data; name=\"userId\""),
//                        RequestBody.create(null,userId))
//                .addPart(Headers.of("Content-Disposition", "form-data; name=\"headImage\";filename=\"file.jpg\""),
//                        RequestBody.create(MediaType.parse("image/png"), file))
//                .addFormDataPart("headImage", file.getName(), RequestBody.create(MediaType.parse("image/png"), file))
//                .build();
//        Request request = new Request.Builder().url(ApiService.POST_USER_IMG).post(body).build();
//        client.newCall(request).enqueue(new Callback(){
//            @Override
//            public void onFailure(okhttp3.Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
//                if(response.isSuccessful()){
//                    String obj = response.body().string();
//                    try{
//                        JSONObject object = new JSONObject(obj);
//                        if(object.getString("code").equals(Constants.NET_RESULT_CODE_SUCCESS)){
//                        }else {
//                        }
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });

    }

    /**
     * bitmap转化成byt
     */
    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public RequestBody toRequestBody(String value){
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }

    @Override
    public void onError(Exception e) {
        super.onError(e);
        getMvpView().showError("");
    }
}
