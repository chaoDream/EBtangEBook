package com.ebtang.ebtangebook.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.ebtang.ebtangebook.R;
import com.ebtang.ebtangebook.api.ParamsUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.decode.BaseImageDecoder;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.yunqing.core.db.DBExecutor;

import org.geometerplus.android.fbreader.config.ConfigShadow;
import org.geometerplus.zlibrary.ui.android.image.ZLAndroidImageManager;
import org.geometerplus.zlibrary.ui.android.library.ZLAndroidLibrary;

import java.io.File;

/**
 * 所有初始化的工作可在这里面进行
 * 
 * @author Xice
 * 
 */
public class AppContext extends MypAppContext{

    public DBExecutor dbExecutor;

    private static AppContext instance;

    private DisplayImageOptions default_options = new DisplayImageOptions.Builder()
            /**
             * 这只Uri为空、加载失败、加载时候的图片资源，可以接受Drawable 和 资源ID
             */
            .showImageForEmptyUri(R.mipmap.ic_launcher)
            .showImageOnFail(R.mipmap.ic_launcher)
            .showImageOnLoading(R.mipmap.ic_launcher)
            //是否设置在加载之前重置view
            .resetViewBeforeLoading(false)

            .delayBeforeLoading(1000)
            //是否缓存在内存中
            .cacheInMemory(true)
            //是否缓存在文件中
            .cacheOnDisk(true)
//                .preProcessor(...)
//                .postProcessor(...)
//                .extraForDownloader(...)
            .considerExifParams(false)
            //Image的缩放类型
            .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
            //bitmap 的config
            .bitmapConfig(Bitmap.Config.ARGB_8888)
            //decode参数
//                .decodingOptions()
            //设置显示，可以设置为圆角
            .displayer(new SimpleBitmapDisplayer())
            .handler(new Handler())
            .build();

    @Override
	public void onCreate() {
		super.onCreate();
        dbExecutor = DBExecutor.getInstance(this);
        initImageLoader(getApplicationContext());
        ParamsUtils.getInstance(this);
	}

    public void initImageLoader(Context context) {
        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions
                .diskCacheExtraOptions(480, 800, null)
//                .taskExecutor(...)
//                .taskExecutorForCachedImages(...)
//                .threadPoolSize(3) // default
                .threadPriority(Thread.NORM_PRIORITY - 1) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13) // default
                .diskCache(new UnlimitedDiskCache(cacheDir)) // default
                .defaultDisplayImageOptions(default_options)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(1000)
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(getApplicationContext())) // default
                .imageDecoder(new BaseImageDecoder(true)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();
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
