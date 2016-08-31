package com.ebtang.ebtangebook.api;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.ebtang.ebtangebook.spf.SharedPrefHelper;
import com.ebtang.ebtangebook.utils.CryptoUtil;
import com.ebtang.ebtangebook.utils.SystemUtils;
import com.ebtang.ebtangebook.utils.UUIDUtil;

import java.util.HashMap;
import java.util.Random;
/**
 * Created by xunice on 16/5/5.
 * 组拼所有的参数
 */
public class ParamsUtils {

    private static ParamsUtils paramsUtils;
    private static Context context;

    public static synchronized ParamsUtils getInstance(Context context) {
        if (null == paramsUtils) {
            paramsUtils = new ParamsUtils();
        }
        ParamsUtils.context = context;
        return paramsUtils;
    }

    public static HashMap<String, String> commonURL() {

        String appName = "ebtang-app-ebook"; //app名字，应该不会变得
        String deviceType = "1"; //标示为android phone
        String app = SystemUtils.getPackageName(context);
        String uniqueId = UUIDUtil.getDeviceUUID(context);
        String version = SystemUtils.getVersion(context);
        //添加mobileAccessToken
        String timeStamp = System.currentTimeMillis()+"";

        if (app == null) {
            app = "com.ebtang.ebtangebook";
        }
        if (uniqueId == null || uniqueId.equals("")) {
            uniqueId = "ebtang_123456789";
        }
        if (version == null) {
            version = "1.0.0";
        }
        //TODO 测试需要
//        if(mobileAccessToken == null){
//            mobileAccessToken = "mobiletoken";
//        }
        HashMap<String, String> params = new HashMap<>();
        params.put("deviceType", deviceType);
        params.put("app", app);
        params.put("appName", appName);
        params.put("version", version);
        params.put("uniqueId", uniqueId);
        params.put("phoneOsType","android");
        params.put("timeStamp", timeStamp);
        params.put("appId","com.ebtang.ebook");
        //params.put("sign", SignUtils.sign(params));
        return  params;
    }

    public static String commonURLStr(){
        String appName = "ebtang-app-ebook"; //app名字，应该不会变得
        String deviceType = "1"; //标示为android phone
        String app = SystemUtils.getPackageName(context);
        String uniqueId = SharedPrefHelper.getInstance(context).getUniqureId();
        String version = SystemUtils.getVersion(context);
        //添加mobileAccessToken
        String mobileAccessToken = SharedPrefHelper.getInstance(context).getAccessToken();
        String timeStamp = System.currentTimeMillis()+"";
        String random = System.currentTimeMillis() + new Random().nextInt(100)+"";
        if (app == null) {
            app = "com.ebtang.ebtangebook";
        }
        if (uniqueId == null || uniqueId.equals("")) {
            uniqueId = "ebtang_123456789";
        }
        if (version == null) {
            version = "1.0.0";
        }
        if(mobileAccessToken!=null && !mobileAccessToken.equals(""))
            return "?appName=" + appName + "&deviceType=" + deviceType + "&app=" + app + "&uniqueId=" + uniqueId + "&version=" + version + "&mobileAccessToken=" + mobileAccessToken
                + "&timeStamp=" + timeStamp + "&random=" + CryptoUtil.md5HexDigest(random, null);
        else
            return "?appName=" + appName + "&deviceType=" + deviceType + "&app=" + app + "&uniqueId=" + uniqueId + "&version=" + version + "&timeStamp=" + timeStamp + "&random=" + CryptoUtil.md5HexDigest(random, null);
    }

    /**
     * 无需提交参数的get请求
     * @return
     */
    public static HashMap<String,String> commonSignPramas(){
        HashMap<String,String> parmas = commonURL();
        parmas.put("sign", SignUtils.sign(parmas));
        return parmas;
    }


    /**
     * 获取验证码图片
     * @return
     */
    public static HashMap<String,String> getCheckImg(){
        HashMap<String,String> parmas = commonURL();
        return parmas;
    }

}
