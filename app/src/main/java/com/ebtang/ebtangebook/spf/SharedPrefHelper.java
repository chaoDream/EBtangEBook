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
    private static final String ACCESSTOKEN = "AccessToken";
    private static final String LOGIN_USERID = "Login_Userid";

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

}
