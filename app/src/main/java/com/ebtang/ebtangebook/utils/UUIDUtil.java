package com.ebtang.ebtangebook.utils;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;


/**
 * 唯一ID工具类
 * @author dhw
 *
 */
public class UUIDUtil {

	/**
	 * 获得设备唯一ID
	 * @return
	 */
	public static String getDeviceUUID(Context context){
		String id = "";
		try {
			final String androidId = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
			if (!"9774d56d682e549c".equals(androidId)) {
				id = androidId;
			} else {
				id = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
			}
		}catch (Exception e){
			id = "";
		}

		return id;
	}
	
}
