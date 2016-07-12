package com.ebtang.ebtangebook.mvp;

/**
 * Created by xunice on 16/3/24.
 */
public interface ResultListener {
    public void onSuccess(String json);
    public void onError(Exception e);
}