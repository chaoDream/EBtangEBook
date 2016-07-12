package com.ebtang.ebtangebook.app;


import android.app.Application;

/**
 * 
 * Application context
 * @author Xice
 *
 */
public class MypAppContext  extends Application{
	private static volatile BaseAppContext baseAppContext;

	@Override
	public void onCreate() {
		super.onCreate();
		init();
	}

	// 初始化程序
	private void init() {
		if (baseAppContext == null) {// double check
			synchronized (MypAppContext.class) {
				if (baseAppContext == null) {
					baseAppContext = new BaseAppContext(this);
				}
			}
		}
		
	}

	public static Application getApp() {
		return baseAppContext.getApp();
	}

	public static final class BaseAppContext {
		private Application app;

		private BaseAppContext(Application app) {
			this.app = app;
		}

		public Application getApp() {
			return app;


		}
	}
	


}
