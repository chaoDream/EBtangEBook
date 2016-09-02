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
