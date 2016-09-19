package com.ebtang.ebtangebook.api;

import java.io.File;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by xunice on 16/3/28.
 */
public interface ApiService {

    public final static String GET_CHECK_IMG = "validateCodeServlet";//获取验证码图片

    public final static String CHEECK_PHONE_NUM = "V1_0/validate_phone";//校验手机号是否注册过

    public final static String GET_CHECK_CODE = "V1_0/repeat_dynamic_code";//获取手机验证码

    public final static String REGISTE = "V1_0/mobile_register";//注册

    public final static String LOGIN = "V1_0/login";//登录

    public final static String MY_SUBSCRIVE = "V1_0/user/rss_list";//自动订阅

    public final static String MY_YINHAO = "V1_0/user/wallet";//我的银号

    public final static String CONSUME_HISTORY =  "V1_0/user/consumption_record";//消费记录

    public final static String RECHARGE_HISTORY = "V1_0/user/recharge_record";//充值记录

    public final static String FEED_BACK =  "V1_0/user/feedback";//意见反馈

    public final static String USER_INFO = "V1_0/user";//个人信息

    public final static String USER_MODIY_REALNAME = "V1_0/user/real_name";

    public final static String USER_MODIY_QIANMING = "V1_0/user/signature";

    public final static String USER_MODIY_NICHENG = "V1_0/user/nick";


    public final static String COURSE_LIST = "getUserSubjects";

    //提交图片
    public static final String POST_IMG = "mobile/uploadImage";//http://172.16.208.144/app/api/v1/

    @GET(CHEECK_PHONE_NUM)
    Call<String> checkPhone(@QueryMap Map<String, String> options);

    @GET(GET_CHECK_CODE)
    Call<String> getCheckCode(@QueryMap Map<String, String> options);

    @GET(REGISTE)
    Call<String> registe(@QueryMap Map<String, String> options);

    @GET(LOGIN)
    Call<String> login(@QueryMap Map<String, String> options);

    @GET(MY_SUBSCRIVE)
    Call<String> mySubscribe(@QueryMap Map<String, String> options);

    @GET(MY_YINHAO)
    Call<String> myYinHao(@QueryMap Map<String, String> options);

    @GET(CONSUME_HISTORY)
    Call<String> myYinHaoConsume(@QueryMap Map<String, String> options);

    @GET(RECHARGE_HISTORY)
    Call<String> myYinHaoRecharge(@QueryMap Map<String, String> options);

    @GET(FEED_BACK)
    Call<String> feedBack(@QueryMap Map<String, String> options);

    @GET(USER_INFO)
    Call<String> userInfo(@QueryMap Map<String, String> options);

    @GET(USER_MODIY_NICHENG)
    Call<String> modifyNIck(@QueryMap Map<String, String> options);

    @GET(USER_MODIY_QIANMING)
    Call<String> modifyQianming(@QueryMap Map<String, String> options);

    @GET(USER_MODIY_REALNAME)
    Call<String> modifyRealName(@QueryMap Map<String, String> options);


    /*******************************************/

    @Headers("Cache-Control: public, max-age=3600") //设置缓存
    @GET(COURSE_LIST)
    Call<String> courseList(@QueryMap Map<String, String> options);

    @FormUrlEncoded
    @POST(POST_IMG)
    Call<String> postImg(@FieldMap Map<String, Object> options);

    @Multipart
    @POST(POST_IMG)
    Call<String> postImg(@Part("file") RequestBody photo);

    /**
     * @param imgs
     * @return
     */
    @Multipart
    @POST(POST_IMG)
    Call<String> uploadImage(@PartMap Map<String, RequestBody> imgs);
}
