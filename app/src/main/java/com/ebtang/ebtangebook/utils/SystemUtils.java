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

package com.ebtang.ebtangebook.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 获取手机相关信息的工具类
 * 
 */
public class SystemUtils {

	/**
	 * 设备标识号
	 */
	private static String deviceId = null;

	/**
	 * 获取手机号码(支持部分制式)
	 * 
	 * @param context
	 * @return
	 */
	public static String getPhoneNumber(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getLine1Number();
	}

	/**
	 * 获取当前系统版本（如 11）
	 * 
	 * @return
	 */
	public static int getSystemVersionCode() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 * 获取手机串号 首先获取 imei 为空 获取 android_id 再为空 获取一个随机数
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		if (deviceId == null) {
			TelephonyManager telephonyManager = (TelephonyManager) context
					.getSystemService(Context.TELEPHONY_SERVICE);
			deviceId = telephonyManager.getDeviceId();
		}
		if (TextUtils.isEmpty(deviceId)) {
			deviceId = Secure.getString(context.getContentResolver(),
                    Secure.ANDROID_ID);
			if ("9774d56d682e549c".equals(deviceId)) {
				// 某些主流设备 android_id是固定的
				deviceId = "";
			}
		}
		if (TextUtils.isEmpty(deviceId)) {
			deviceId = "";
		}
		return deviceId;
	}

	/**
	 * 获取机器总内存
	 * 可能和手机上显示的不一样。 如手机显示1G内存，这里获取到的可能就800M左右
	 * @param context
	 * @return
	 */
	public static long getTotalMemory(Context context) {
		String str1 = "/proc/meminfo";// 系统内存信息文件
		String str2;
		String[] arrayOfString;
		long initial_memory = 0;

		try {
			FileReader localFileReader = new FileReader(str1);
			BufferedReader localBufferedReader = new BufferedReader(
					localFileReader, 8192);
			str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

			arrayOfString = str2.split("\\s+");

			initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
			localBufferedReader.close();

		} catch (IOException e) {
		}
		return initial_memory;
	}

	/**
	 * 获取分配的内存大小
	 * 
	 * @param context
	 * @return
	 */
	public static int getMemoryClass(Context context) {
		return ((ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
	}

	/**
	 * 获取本地客户端版本号(纯显示)
	 * 
	 * @return
	 */
	public static String getVersion(Context context) {
		String versionName = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			int versionCode = pi.versionCode;
			if (versionName == null || versionName.length() <= 0) {
				return "1.0";
			}

			// 追加显示versionCode
			StringBuilder buffer = new StringBuilder();
			// TODO 目前要求是只传版本号 eg:1.0
			// buffer.append(versionName).append("-").append(versionCode);
			buffer.append(versionName);
			versionName = buffer.toString();
		} catch (Exception e) {

		}
		return versionName;
	}

	/**
	 * 获取本地客户端版本号(CODE)
	 * 
	 * @return
	 */
	public static String getVersionCode(Context context) {
		int versionName = -1;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionCode;
		} catch (Exception e) {
		}
		return String.valueOf(versionName);
	}

	/**
	 * 客户端关闭
	 */
	public static void shutdown() {
		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
		System.exit(0);
	}

	/**
	 * 手机型号
	 * 
	 * @return
	 */
	public static String getDeviceType() {
		return Build.MODEL;// model
	}

	/**
	 * 系统内核版本如：2.3.7
	 * 
	 * @return
	 */
	public static String getSystemVersion() {
		return Build.VERSION.RELEASE;// firmware version
	}

	/**
	 * 获取本应用的包名
	 * 
	 * @param context
	 * @return
	 */
	public static String getPackageName(Context context) {
		String packageName = null;
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			packageName = pi.packageName;
		} catch (Exception e) {
		}
		return packageName;
	}

	/**
	 * 读取<meta-data>元素的数据 
	 * <activity...>
     *  <meta-data android:name="key" android:value="value"></meta-data>
     * </activity>
	 * @param context
	 * @return
	 */
	public static String getAppMetaData(Context context, String key) {
		String data = null;
		try {
			ApplicationInfo appInfo = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			data = appInfo.metaData.getString(key);
		} catch (Exception e) {
		}
		return data;
	}

	/**
	 * 秒数转time
	 * @param time
	 * @return
	 */
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00:00";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = unitFormat(minute) + ":" + unitFormat(second);
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
			}
		}
		return timeStr;
	}

	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}

	/**
	 * 得到两个日期相差的天数
	 */
	public static int getBetweenDay(Date date1, Date date2) {
		Calendar d1 = new GregorianCalendar();
		d1.setTime(date1);
		Calendar d2 = new GregorianCalendar();
		d2.setTime(date2);
		int days = d2.get(Calendar.DAY_OF_YEAR)- d1.get(Calendar.DAY_OF_YEAR);
		System.out.println("days="+days);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
//      d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}
}
