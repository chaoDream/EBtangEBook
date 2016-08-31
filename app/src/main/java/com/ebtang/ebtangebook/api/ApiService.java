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

    //登录
    public final static String LOGIN = "mobile/appLogin";

    //我的
    public final static String MY_USER_INFO = "mobile/getUserInfoByUserId";

    public final static String SUBJECT_LIST = "mobile/subjectInfoByUserId";

    public final static String COURSE_LIST = "getUserSubjects";

    public final static String COURSE_DETAIL_LIST = "getUserCourses.html";

    public final static String COURSE_CENTER_LIST = "shopping/exam/getExamList";

    //注册
    public final static String REGISTER = "mobile/registerAppUser";

    public final static String COURSE_SELECT_LIST = "shopping/goods/getGoodsList";

    public final static String GOODS_SELECT_DISCOUNT = "shopping/getCartInfo";
    
    public final static String COMMODITY_ORDER_INFO = "shopping/goods/getGoodsList";//获取商品订单支付信息


    // 获取验证码
    public final static String MOBILEVALID = "mobile/sendPhoneCode";

    //获取订单列表
    public final static String MY_ORDER_LIST = "shopping/order/getUserOrderInfo";

    //获取订单详情
    public final static String MY_ORDER_DETAIL = "shopping/order/getOrderDetail";
    /* wyc  */
    public final static String STUDY_BAR_FRAGMENT ="mobile/studyBarList";
    public final static String STUDY_BAR_FRAGMENT_PRIVATE ="mobile/noticeListByClassId";
    public final static String STUDY_BAR_FRAGMENT_QUESTIONSOLUTION ="qas/list";
    public final static String EXAM_PAPER = "examinations/examination_paper";
    public final static String EXAM_ORIGINAL_QUESTION = "examinations/origin_examquestion";
    public final static String EXAM_PAPER_LIST = "examinations/examination_paper_list";
    public final static String UPLOAD_EXAM_PAPER = "examinations/examination_submit";
    public final static String EXAM_PRACTICE_LIST = "http://jxjyapi.dongao.com/v1/courseware/listCourseware?app=com.dongao.app.dongaoacc&appName=da-jxjy-app&currentYear=2016&deviceType=1&mobileAccessToken=8e27051ca5c2478cb3a6350106716147&uniqueId=0A00270000000000&userID=cc3d8e9809864e94952ca2f23aa9f62f&version=1.0.0&sign=7ff7b2c46088f3ca68dd738d040895dd";

    //精华答疑
    public static final String RECOMMEND_QUESTION = "mobile/essenceQas";

    //我的答疑http://examapi.dongao.com/V1_1/qa/myqa.html
    public static final String MY_QUESTION_LIST = "mobile/myQas";

    //精准答疑
    public static final String QUESTION_DETAIL = "mobile/getAskDetailsById";

    //试题的推荐答疑
    public static final String EXAM_RECOMM_QUESTION = "mobile/recommQasForExaminationQuestion" ;

    //是否能提问
    public static final String IS_CAN_ASK = "mobile/isAskForExaminationQuestion";

    //追问我的答疑
    public static  final String CONTINUE_ASK_QUESTION = "mobile/queryAgainQas";

    //新增我的答疑(教材)
    public static final String ADD_QUESTION = "mobile/saveBookAsk";

    //新增我的答疑（非教材）
    public static final String ADD_QUESTION_NORMAL = "mobile/saveExamquestionAsk";

    //修改我的答疑
    public static final String MODIFY_QUESTION = "mobile/modifyMyQas";

    //获取科目下的可选章节列表（新增答疑页面选择的数据源）
    public static final String QUESTION_SUBJECT_SECTION_LIST = "mobile/getSectionsBySubjectId";

    //提交图片
    public static final String POST_IMG = "mobile/uploadImage";//http://172.16.208.144/app/api/v1/
    public final static String COMMODITY_ORDER_INFO_WX = "mobile/prepareWXPayParam";//获取商品订单支付信息


    public final static String COMMODITY_ORDER_INFO_ZFB = "mobile/prepareAlipayParam";//获取商品订单支付信息

    //图书激活
    public static final String BOOK_ACTIVE_CARD = "card/activeCard";

    //图书激活
    public static final String BOOK_ACTIVE_CARD_LIST = "card/listCard";

    //验证deviceToken
    public static  final String CHECK_DEVICETOKEN = "checkAccessToken.html";


    //首页数据
    //public static final String HOME_LIST = "getHomeList";

    //每日一练科目定制科目列表
    public static final String DAY_TEST_SUBJECT_LSIT = "examinations/custom_subject_list";

    //每日一练题目拉取
    public static  final String DAY_TEST_QUESTION = "examinations/daily_exercise";

    //获取试题列表
    public static  final String GET_PAPER_LIST = "mobile/examinationTypeList";

    //获取课程列表
    public static  final String GET_COURSEPLAY_LIST = "mobile/coursesBySubjectId";

    //获取扫一扫类型
    public static  final String GET_TYPE = "mobile/getQRCodeMetaInfo";

    //获取扫一扫类型1的数据
    public static  final String GETDATA_TYPE1= "mobile/getCourseByQrCodeId";

    //获取扫一扫类型2的数据
    public static  final String GETDATA_TYPE2 = "mobile/getQRCodeMetaInfo";

    //意见反馈
    public static  final String FEED_BACK = "mobile/feedBackForUser";

    //上传地理位置
    public static  final String UPLOAD_POSITION = "position/saveUserPosition";

    //课程详情
    public static final String PLAY_DETAIL = "mobile/getCourseWaresByCourseId";

    public static final String VERSION = "mobile/androidUpgrade";

    //检查用户绑定
    public static  final String CHECK_MACHINE = "mobile/checkMachineSign";

    //上传听课记录
    public static  final String UPLOAD_VIDEO = "courseware/uploadCourseWareLessionLog";

    //上传运维记录
    public static  final String UPLOAD_OPERA = "examinations/sync_records";

    //首页
    public static  final String HOME_LIST = "pushmsg/getUserPushmsgList";

    //首页
    public static  final String HOME_LIST_BY_ID = "pushmsg/getUserPushmsgListById";


    //选课
   // public static  final String SELECT_COURSE_URL = "http://p.m.test.com/appInfo/toApp.html";
    public static  final String ORDER_URL = "http://p.m.test.com/appInfo/to_order.html";
    public static  final String SELECT_COURSE_URL = "http://p.m.test.com/zsfa/to_swx.html";
   // public static  final String ORDER_URL = "http://p.m.test.com/appInfo/myorder.html?qtype=all";

    //获取课堂答疑数字
    public static  final String COURSE_ANSWER_NUM = "mobile/qasCount";

    @GET(GET_CHECK_IMG)
    Call<String> getCheckImg(@QueryMap Map<String, String> options);

    @Headers("Cache-Control: public, max-age=3600") //设置缓存
    @GET(COURSE_LIST)
    Call<String> courseList(@QueryMap Map<String, String> options);

    @GET(PLAY_DETAIL)
    Call<String> playDetail(@QueryMap Map<String, String> options);

    @GET(COURSE_DETAIL_LIST)
    Call<String> courseDetailList(@QueryMap Map<String, String> options);

    @FormUrlEncoded
    @POST(LOGIN)
    Call<String> login(@FieldMap Map<String, String> options);

    @GET(COURSE_CENTER_LIST)
    Call<String> getExamList(@QueryMap Map<String, String> options);

    @GET(COURSE_SELECT_LIST)
    Call<String> getGoodsList(@QueryMap Map<String, String> options);

    @GET(MY_USER_INFO)
    Call<String> myUserInfo(@QueryMap Map<String, String> options);

    @Headers("Cache-Control: public, max-age=3600") //设置缓存
    @GET(GOODS_SELECT_DISCOUNT)
    Call<String> getGoodsDiscount(@QueryMap Map<String, String> options);


    @FormUrlEncoded
    @POST(COMMODITY_ORDER_INFO)
    Call<String> getCommodityOrderInfo(@FieldMap Map<String, String> options);

    //微信支付获取订单信息
    @GET(COMMODITY_ORDER_INFO_WX)
    Call<String> getCommodityOrderInfoByWX(@QueryMap Map<String, String> options);

    //支付宝支付获取订单信息
    @GET(COMMODITY_ORDER_INFO_ZFB)
    Call<String> getCommodityOrderInfoByZFB(@QueryMap Map<String, String> options);

    @GET(REGISTER)
    Call<String> register(@QueryMap Map<String, String> options);

    @GET(MOBILEVALID)
    Call<String> mobilevalid(@QueryMap Map<String, String> options);

    @Headers("Cache-Control: public, max-age=3600") //设置缓存
    @GET(MY_ORDER_LIST)
    Call<String> orderList(@QueryMap Map<String, String> options);

    @Headers("Cache-Control: public, max-age=3600") //设置缓存
    @GET(MY_ORDER_DETAIL)
    Call<String> orderDetail(@QueryMap Map<String, String> options);

    @FormUrlEncoded
    @POST(RECOMMEND_QUESTION)
    Call<String> recommQuestion(@FieldMap Map<String, String> options);

    @FormUrlEncoded
    @POST(CONTINUE_ASK_QUESTION)
    Call<String> continueAskQuestion(@FieldMap Map<String, String> options);

    @FormUrlEncoded
    @POST(QUESTION_SUBJECT_SECTION_LIST)
    Call<String> questionSubjectSection(@FieldMap Map<String, String> options);

    @GET(QUESTION_DETAIL)
    Call<String> questionDetail(@QueryMap Map<String, String> options);

    @GET(EXAM_RECOMM_QUESTION)
    Call<String> examRecommQuestion(@QueryMap Map<String, String> options);

    @GET(IS_CAN_ASK)
    Call<String> isCanAsk(@QueryMap Map<String, String> options);

    @GET(MY_QUESTION_LIST)
    Call<String> myQuestion(@QueryMap Map<String, String> options);

    @FormUrlEncoded
    @POST(ADD_QUESTION)
    Call<String> addQuestion(@FieldMap Map<String, String> options);

    @FormUrlEncoded
    @POST(MODIFY_QUESTION)
    Call<String> modifyQuestion(@FieldMap Map<String, String> options);

    @FormUrlEncoded
    @POST(ADD_QUESTION_NORMAL)
    Call<String> addQuestionNormal(@FieldMap Map<String, String> options);

    @FormUrlEncoded
    @POST(POST_IMG)
    Call<String> postImg(@FieldMap Map<String, Object> options);

    @Multipart
    @POST(POST_IMG)
    Call<String> postImg(@Part("file") RequestBody photo);

    /**
     * wyc 获取试题
     */
    @GET(EXAM_PAPER)
    Call<String> getExamPaper(@QueryMap Map<String, String> options);

    /**
     * wyc 获取原题
     */
    @GET(EXAM_ORIGINAL_QUESTION)
    Call<String> getOriginalQuestion(@QueryMap Map<String, String> options);

    /**
     * wyc 获取试卷列表
     */
    @GET(EXAM_PAPER_LIST)
    Call<String> getExamPaperList(@QueryMap Map<String, String> options);

    /**
     * 上传做完的试卷
     * @param options
     * @return
     */
    @POST(UPLOAD_EXAM_PAPER)
    Call<String> postUploadExamPaper(@QueryMap Map<String, String> options);
    
    @GET(EXAM_PRACTICE_LIST)
    Call<String> getExamPracticeList(@QueryMap Map<String, String> options);

    @GET(VERSION)
    Call<String> version(@QueryMap Map<String, String> options);

    @GET(EXAM_PRACTICE_LIST)
    Call<String> getExamListData(@QueryMap Map<String, String> options);
    
    @GET(SUBJECT_LIST)
    Call<String> subjectList(@QueryMap Map<String, String> options);

    @GET(BOOK_ACTIVE_CARD)
    Call<String> bookActivate(@QueryMap Map<String, String> options);

    @GET(BOOK_ACTIVE_CARD_LIST)
    Call<String> bookActivateList(@QueryMap Map<String, String> options);


    @FormUrlEncoded
    @POST(CHECK_DEVICETOKEN)
    Call<String> checkToken(@FieldMap Map<String, String> options);

   // @GET("http://172.16.208.200/gaze/api/getUserPushmsgList")
    @GET("http://192.168.0.176:8080/app/api/v1/pushmsg/getUserPushmsgListById")
    Call<String> homeList(@QueryMap Map<String, String> options);

    @GET(STUDY_BAR_FRAGMENT)//获取学习中心的数据
    Call<String> getStudyBarFragment(@QueryMap Map<String, String> options);

    @GET(STUDY_BAR_FRAGMENT_PRIVATE)//获取私教班数据
    Call<String> getPrivateClass(@QueryMap Map<String, String> options);

    @GET(STUDY_BAR_FRAGMENT_QUESTIONSOLUTION)//获取答疑消息列表
    Call<String> getQuestionSolution(@QueryMap Map<String, String> options);


    @GET(DAY_TEST_SUBJECT_LSIT)
    Call<String> daytestsubjectlist(@QueryMap Map<String, String> options);

    @GET(CHECK_MACHINE)
    Call<String> checkMachine(@QueryMap Map<String, String> options);

    @GET(GET_PAPER_LIST)
    Call<String> getPaperList(@QueryMap Map<String, String> options);

    @GET(GET_COURSEPLAY_LIST)
    Call<String> getCoursePlayList(@QueryMap Map<String, String> options);

    @GET(GET_TYPE)
    Call<String> getype(@QueryMap Map<String, String> options);

    @GET(GETDATA_TYPE1)
    Call<String> getDataType1(@QueryMap Map<String, String> options);

    @GET(GETDATA_TYPE2)
    Call<String> getDataType2(@QueryMap Map<String, String> options);

    @POST(FEED_BACK)
    Call<String> feedBack(@QueryMap Map<String, String> options);

    @GET("http://192.168.0.176:8080/app/api/v1/position/saveUserPosition")
    Call<String> upLoadPositon(@QueryMap Map<String, String> options);

    @GET(DAY_TEST_QUESTION)
    Call<String> daytestQuestion(@QueryMap Map<String, String> options);

    //"http://120.55.160.19/yd_errlog.php"  @FieldMap(encoded=false)
    @FormUrlEncoded
    @POST(UPLOAD_OPERA)
    Call<String> postOperates(@FieldMap Map<String, String> options);

    @POST("http://192.168.0.176:8080/app/api/v1/courseware/uploadCourseWareLessionLog")
    Call<String> upLoadVideos(@QueryMap Map<String, String> options);

    @GET
    Call<String> CheckTimes(@Url String url);

    @GET(COURSE_ANSWER_NUM)
    Call<String> courseAnswerNum(@QueryMap Map<String, String> options);

    /**
     * @param imgs
     * @return
     */
    @Multipart
    @POST(POST_IMG)
    Call<String> uploadImage(@PartMap Map<String, RequestBody> imgs);
}
