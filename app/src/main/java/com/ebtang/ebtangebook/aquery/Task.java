package com.ebtang.ebtangebook.aquery;

import com.yunqing.core.db.utils.LogUtils;

import java.util.HashMap;

/*****
 * 任务类
 * 
 * @author xice
 * 
 */
@SuppressWarnings("unused")
public class Task {
	private int taskID;// 任务类型
	private HashMap<String, String> taskParam;// 任务参数
	private HashMap<String, Object> params;
	private String url;

	public Task(int taskID, String url) {
		super();
		this.taskID = taskID;
		this.url = url;
		LogUtils.d("url==" + url);
	}

	public Task(int taskID, HashMap<String, Object> params, String url) {
		super();
		this.taskID = taskID;
		this.params = params;
		this.url = url;
		LogUtils.d("url==" + url);
	}

	public HashMap<String, Object> getParams() {
		return params;
	}

	public void setParams(HashMap<String, Object> params) {
		this.params = params;
	}

	public Task(int taskID, String url, HashMap<String, String> taskParam) {
		super();
		this.taskID = taskID;
		this.taskParam = taskParam;
		this.url = url;
		LogUtils.d("url==" + url);
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public HashMap<String, String> getTaskParam() {
		return taskParam;
	}

	public void setTaskParam(HashMap<String, String> taskParam) {
		this.taskParam = taskParam;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
