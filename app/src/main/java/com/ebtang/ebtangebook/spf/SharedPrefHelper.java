/*
 *
 *  * Copyright (C) 2015 by  xunice@qq.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  * http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *
 */

package com.ebtang.ebtangebook.spf;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences的工具类
 * 
 * @author xunice
 * 
 */
public class SharedPrefHelper {
	/**
	 * SharedPreferences的名字
	 */
	private static final String SP_FILE_NAME = "ebook_project";
	private static SharedPrefHelper sharedPrefHelper = null;
	private static SharedPreferences sharedPreferences;


	private static final String UNIQURE_ID = "Uniqure_Id";//设备唯一标示
	private static final String ISFIRSTIN_TAG = "isFirstIn";//第一次进入应用
	private static final String IS_LOGIN = "isLogin";// 是否登录
	private static final String LOGIN_USERNAME = "login_username";// 登录用户名
	private static final String LOGIN_PASSWORD = "login_password";// 登录密码
	private static final String USER_TOUXIANG_IMG = "user_touxiang_img";//用户头像地址
    private static final String ACCESSTOKEN = "AccessToken";
    private static final String LOGIN_USERID = "Login_Userid";

	private static final String WIFI_DOWANLOAD = "wifi_download";//wifi下可以下载/上传
	private static final String WIFI_GENGXIN = "wifi_gengxin";//wifi下更新

	private static final String YINLIANG_FANYE = "yinliang_fanye";//音量翻页

	private static final String SCREEN_LIANGDU = "screen_liangdu";//屏幕亮度

	private static final String IS_USE_SYSTEM_LIANGDU = "is_use_system_liangdu";//是否使用系统亮度

	private static final String IS_VOLUME_CHANGE_PAGE = "is_volume_change_page";//是否音量键翻页

	private static final String IS_LONG_CLICK_DRAW = "is_long_click_draw_line";//是否长按划线

	private static final String READ_BACKGROUD_PAPER = "read_background_paper";//阅读页面背景图样式
	private static final String READ_SETTING_PAIBAN = "read_setting_paiban";//阅读排版格式

	public static synchronized SharedPrefHelper getInstance(Context context) {
		if (null == sharedPrefHelper) {
			sharedPrefHelper = new SharedPrefHelper(context);
		}
		return sharedPrefHelper;
	}

	private SharedPrefHelper(Context context) {
		sharedPreferences =context.getSharedPreferences(
                SP_FILE_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * wifi下,下载/上穿图书
	 * @param flag
     */
	public void setWifiDowanload(boolean flag){
		sharedPreferences.edit().putBoolean(WIFI_DOWANLOAD, flag).commit();
	}
	public boolean isWifiDownLoad(){
		return sharedPreferences.getBoolean(WIFI_DOWANLOAD, true);
	}

	/**
	 * wifi下,更新
	 * @param flag
	 */
	public void setWifiGengxin(boolean flag){
		sharedPreferences.edit().putBoolean(WIFI_GENGXIN, flag).commit();
	}
	public boolean isWifiGengXin(){
		return sharedPreferences.getBoolean(WIFI_GENGXIN, true);
	}

	/**
	 * 音量翻页
	 * @param flag
	 */
	public void setYinliangFanye(boolean flag){
		sharedPreferences.edit().putBoolean(YINLIANG_FANYE, flag).commit();
	}
	public boolean isYinLiangFanye(){
		return sharedPreferences.getBoolean(YINLIANG_FANYE, true);
	}


	/**
	 * 设置设备唯一标示
	 * @param uniqureId
	 */
	public void setUniqureId(String uniqureId){
		sharedPreferences.edit().putString(UNIQURE_ID+getUserId(), uniqureId).commit();
	}
	public String getUniqureId(){
		return sharedPreferences.getString(UNIQURE_ID+getUserId(), "");
	}

	/**
	 * 是否为第一次进入
	 * 
	 * @param flag
	 */
	public void setFirstIn(boolean flag) {
		sharedPreferences.edit().putBoolean(ISFIRSTIN_TAG, flag).commit();
	}

	public boolean isFirstIn() {
		return sharedPreferences.getBoolean(ISFIRSTIN_TAG, true);
	}

	/**
	 * 是否登录
	 * 
	 * @param flag
	 */
	public void setIsLogin(boolean flag) {
		sharedPreferences.edit().putBoolean(IS_LOGIN, flag).commit();
	}

	public boolean isLogin() {
		return sharedPreferences.getBoolean(IS_LOGIN, false);
	}
	/**
	 * 登录用户名
	 * @param username
	 */
	public void setLoginUsername(String username){
		sharedPreferences.edit().putString(LOGIN_USERNAME, username).commit();
	}
	public String getLoginUsername(){
		return sharedPreferences.getString(LOGIN_USERNAME, "");
	}
	/**
	 * 登录密码
	 * @param password
	 */
	public void setLoginPassword(String password){
		sharedPreferences.edit().putString(LOGIN_PASSWORD, password).commit();
	}
	public String getLoginPassword(){
		return sharedPreferences.getString(LOGIN_PASSWORD, "");
	}

    /**
     * 登陆 ACCESSTOKEN
     * @param accessToken
     */
    public void setAccessToken(String accessToken){
        sharedPreferences.edit().putString(ACCESSTOKEN, accessToken).commit();
    }
    public String getAccessToken(){
        return sharedPreferences.getString(ACCESSTOKEN, "");
    }

    /**
     * 登陆 UserId
     * @param userId
     */
    public void setUserId(String userId){
        sharedPreferences.edit().putString(LOGIN_USERID, userId).commit();
    }
    public String getUserId(){
        return sharedPreferences.getString(LOGIN_USERID,"0");
    }

	/**
	 * 答疑数字缓存
	 * @param json
	 */
	public void setAnswerCache(String subjectId,String json){
		sharedPreferences.edit().putString(subjectId+getUserId(), json).commit();
	}
	public String getAnswerCache(String subjectId){
		return sharedPreferences.getString(subjectId+getUserId(),"");
	}

	/**
	 * 设置亮度
	 * @param liangdu
	 */
	public void setScreenLiangdu(float liangdu){
		sharedPreferences.edit().putFloat(SCREEN_LIANGDU,liangdu).commit();
	}
	public float getScreenLiangdu(){
		return sharedPreferences.getFloat(SCREEN_LIANGDU,1.0f);
	}

	/**
	 * 是否使用屏幕亮度
	 * @param flag
	 */
	public void setIsUseSystemLiangdu(boolean flag){
		sharedPreferences.edit().putBoolean(IS_USE_SYSTEM_LIANGDU,flag).commit();
	}
	public boolean getIsUseSystemLiangdu(){
		return sharedPreferences.getBoolean(IS_USE_SYSTEM_LIANGDU,false);
	}

	/**
	 * 是否音量键进行翻页
	 * @param flag
	 */
	public void setIsVolumeChangePage(boolean flag){
		sharedPreferences.edit().putBoolean(IS_VOLUME_CHANGE_PAGE,flag).commit();
	}
	public boolean getIsVolumeChangePage(){
		return sharedPreferences.getBoolean(IS_VOLUME_CHANGE_PAGE,true);
	}

	/**
	 * 是否长按进行画线
	 * @param flag
	 */
	public void setIsLongClickDraw(boolean flag){
		sharedPreferences.edit().putBoolean(IS_LONG_CLICK_DRAW,flag).commit();
	}
	public boolean getIsLongClickDraw(){
		return sharedPreferences.getBoolean(IS_LONG_CLICK_DRAW,true);
	}

	/**
	 * 阅读背景图样式
	 * @param type
	 */
	public void setReadBackgroudPaper(int type){
		sharedPreferences.edit().putInt(READ_BACKGROUD_PAPER,type).commit();
	}
	public int getReadBackgroudPaper(){
		return sharedPreferences.getInt(READ_BACKGROUD_PAPER,1);
	}

	/**
	 * 阅读页面排版样式
	 * @param type
	 */
	public void setReadSettingPaiban(int type){
		sharedPreferences.edit().putInt(READ_SETTING_PAIBAN,type).commit();
	}
	public int getReadSettingPaiban(){
		return sharedPreferences.getInt(READ_SETTING_PAIBAN,4);
	}

	/**
	 * 设置用户的头像地址
	 * @param imgUrl
	 */
	public void setUserTouxiangImg(String imgUrl){
		sharedPreferences.edit().putString(USER_TOUXIANG_IMG,imgUrl).commit();
	}
	public String getUserTouxiangImg(){
		return  sharedPreferences.getString(USER_TOUXIANG_IMG,"");
	}
}
