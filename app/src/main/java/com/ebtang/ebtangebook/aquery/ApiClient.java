package com.ebtang.ebtangebook.aquery;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import com.ebtang.ebtangebook.app.AppContext;
import com.ebtang.ebtangebook.aquery.callback.AjaxCallback;
import com.ebtang.ebtangebook.aquery.callback.AjaxStatus;
import com.ebtang.ebtangebook.event.EventBusApp;

import org.greenrobot.eventbus.EventBus;

/****
 * 主要的访问API接口的类
 * 
 * @author xice
 * 
 */
public class ApiClient implements TaskType {

	private static Context context = AppContext.getApp()
			.getApplicationContext();
	private final static AQuery aq = new AQuery(context);
    private final static EventBusApp eventBusApp = new EventBusApp();
	
	/**
	 * 请求数据不加缓存
	 * @param ts
	 * @param handler
	 */
	public static void getData(final Task ts, final Handler handler) {
		final Message msg = new Message();
		aq.ajax(ts.getUrl(), String.class,new AjaxCallback<String>() {

					@Override
					public void callback(String url, String str,
							AjaxStatus status) {
						sendMessage(handler, msg, ts, str, status);
					}
				});
	}

	/**
	 * 请求数据加缓存
	 * @param ts
	 * @param handler
	 */
	public static void getDataCache(final Task ts, final Handler handler) {
		final Message msg = new Message();
		aq.ajax(ts.getUrl(), String.class,
				Constants.CACHE_TIME, new AjaxCallback<String>() {

					@Override
					public void callback(String url, String str,
							AjaxStatus status) {
						sendMessage(handler, msg, ts, str, status);
					}
				});
	}


	/**
	 * 请求数据带Dialog进度条不加缓存
	 * @param ts
	 * @param handler
	 */
	public static void getDataProgressDialog(
			ProgressDialog progressDialog, final Task ts, final Handler handler) {
		progressDialog.setCanceledOnTouchOutside(false);
		final Message msg = new Message();
		aq.progress(progressDialog).ajax(ts.getUrl(), String.class, new AjaxCallback<String>() {

			@Override
			public void callback(String url, String str,
								 AjaxStatus status) {
				sendMessage(handler, msg, ts, str, status);
			}
		});
	}
	
	/**
	 * 请求数据带进度条不加缓存
	 * @param ts
	 * @param handler
	 */
	public static void getDataProgressBar(
			ProgressBar progressBar, final Task ts, final Handler handler) {
		final Message msg = new Message();
		aq.progress(progressBar).ajax(ts.getUrl(), String.class, new AjaxCallback<String>() {

					@Override
					public void callback(String url, String str,
							AjaxStatus status) {
						sendMessage(handler, msg, ts, str, status);
					}
				});
	}
	
	/**
	 * 请求数据带Dialog进度条加缓存
	 * @param ts
	 * @param handler
	 */
	public static void getDataProgressDialogCache(
			ProgressDialog progressDialog, final Task ts, final Handler handler) {
		progressDialog.setCanceledOnTouchOutside(false);
		final Message msg = new Message();
		aq.progress(progressDialog).ajax(ts.getUrl(), String.class,
				Constants.CACHE_TIME, new AjaxCallback<String>() {

					@Override
					public void callback(String url, String str,
							AjaxStatus status) {
						sendMessage(handler, msg, ts, str, status);
					}
				});
	}
	
	/**
	 * 请求数据带进度条加缓存
	 * @param ts
	 * @param handler
	 */
	public static void getDataProgressBarCache(
			ProgressBar progressBar, final Task ts, final Handler handler) {
		final Message msg = new Message();
		aq.progress(progressBar).ajax(ts.getUrl(), String.class,
				Constants.CACHE_TIME, new AjaxCallback<String>() {

					@Override
					public void callback(String url, String str,
							AjaxStatus status) {
						sendMessage(handler, msg, ts, str, status);
					}
				});
	}
	
	/**
	 * 保存方法
	 * @param progressDialog
	 * @param ts
	 * @param handler
	 */
	public static void saveData(ProgressDialog progressDialog,
			final Task ts, final Handler handler) {
		progressDialog.setCanceledOnTouchOutside(false);
		final Message msg = new Message();
		aq.progress(progressDialog).ajax(ts.getUrl(), ts.getTaskParam(),
				String.class, new AjaxCallback<String>() {

					@Override
					public void callback(String url, String str,
							AjaxStatus status) {
						// TODO Auto-generated method stub
						sendMessage(handler, msg, ts, str, status);
					}

				});
	}
	
	/**
	 * 保存方法
	 * @param ts
	 * @param handler
	 */
	public static void saveData(final Task ts, final Handler handler) {
		final Message msg = new Message();
		aq.ajax(ts.getUrl(), ts.getTaskParam(),
				String.class, new AjaxCallback<String>() {

					@Override
					public void callback(String url, String str,
										 AjaxStatus status) {
						// TODO Auto-generated method stub
						sendMessage(handler, msg, ts, str, status);
					}

				});
	}


	/**
	 * 保存方法
	 * @param ts
	 * @param handler
	 */
	public static void saveFile(final Task ts, final Handler handler) {
		final Message msg = new Message();
		aq.ajax(ts.getUrl(), ts.getParams(),
				String.class, new AjaxCallback<String>() {
					@Override
					public void callback(String url, String str,
										 AjaxStatus status) {
						// TODO Auto-generated method stub
						sendMessage(handler, msg, ts, str, status);
					}

				});
	}

	/**
	 * 发送message
	 * 
	 * @param handler
	 * @param msg
	 * @param ts
	 * @param str
	 * @param status
	 */
	private static void sendMessage(Handler handler, Message msg, Task ts,
			String str, AjaxStatus status) {
		if (status.getCode() == 200) {
			msg.what = ts.getTaskID();
			msg.obj = str;
		} else {
			msg.what = TaskType.TS_ERROR;
			msg.obj = "服务暂时不可用，请稍后再试";
		}

		handler.sendMessage(msg);
	}


    /**
     * 请求数据加缓存
     * @param ts
     */
    public static void getDataCache(final Task ts) {
        aq.ajax(ts.getUrl(), String.class,
                Constants.CACHE_TIME, new AjaxCallback<String>() {

                    @Override
                    public void callback(String url, String str,
                                         AjaxStatus status) {
                        sendMessage(ts, str, status);
                    }
                });
    }

    /**
     * 发送message
     *
     * @param ts
     * @param str
     * @param status
     */
    private static void sendMessage(Task ts,String str, AjaxStatus status) {
        if (status.getCode() == 200) {
            ResultData rd = new ResultData(ts,str);
            EventBus.getDefault().post(eventBusApp.new PostEvent(rd));
        } else {
            ResultData rd = new ResultData(ts,"数据请求失败");
            EventBus.getDefault().post(eventBusApp.new ErrorEvent(rd));
        }
    }

}
