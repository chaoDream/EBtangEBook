package com.ebtang.ebtangebook.view.read.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ebtang.ebtangebook.event.TimeChange;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by dell on 2016/8/24 0024.
 */
public class DataChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        EventBus.getDefault().post(new TimeChange());
    }
}