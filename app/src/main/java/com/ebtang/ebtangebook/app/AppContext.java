package com.ebtang.ebtangebook.app;

import android.content.Context;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.yunqing.core.db.DBExecutor;

import org.geometerplus.android.fbreader.config.ConfigShadow;
import org.geometerplus.zlibrary.ui.android.image.ZLAndroidImageManager;
import org.geometerplus.zlibrary.ui.android.library.ZLAndroidLibrary;

/**
 * 所有初始化的工作可在这里面进行
 * 
 * @author Xice
 * 
 */
public class AppContext extends MypAppContext{

    public DBExecutor dbExecutor;

    private static AppContext instance;

    @Override
	public void onCreate() {
		super.onCreate();
        dbExecutor = DBExecutor.getInstance(this);
        initImageLoader(getApplicationContext());
	}

    public void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(context);
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }

    public static AppContext getInstance() {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

}
