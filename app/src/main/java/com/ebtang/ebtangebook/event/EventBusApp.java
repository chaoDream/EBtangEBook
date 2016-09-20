package com.ebtang.ebtangebook.event;

import com.ebtang.ebtangebook.aquery.ResultData;

/**
 * Created by xunice on 3/4/15.
 */
public class EventBusApp {

   public class PostEvent {
        public ResultData rd;
        private static final String TAG = "PostEvent";
        public PostEvent(ResultData rd)
        {
            this.rd=rd;
        }
    }

    public class ErrorEvent {
        public  ResultData rd;
        private static final String TAG = "ErrorEvent";
        public ErrorEvent(ResultData rd)
        {
            this.rd=rd;
        }
    }


    public class MainEvent {
        private String msg;
        private static final String TAG = "MainEvent";
        public MainEvent(String msg)
        {
            this.msg=msg;
        }
    }

    public class BackEvent {
        private String msg;
        private static final String TAG = "BackEvent";
        public BackEvent(String msg)
        {
            this.msg=msg;
        }
    }




   /* public void methodPost()
    {
        Log.d("yzy", "PostThread-->" + Thread.currentThread().getId());
        EventBus.getDefault().post(eventBusApp.new PostEvent("PostEvent"));
    }

    public void methodMain()
    {
        Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
        EventBus.getDefault().post(eventBusApp.new MainEvent("MainEvent"));
    }

    public void methodBack()
    {
        Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
        EventBus.getDefault().post(eventBusApp.new BackEvent("BackEvent"));
    }

    public void methodAsync(Exam exam)
    {
        Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
        EventBus.getDefault().post(eventBusApp.new AsyncEvent(exam));
    }

    public void methodSubPost()
    {
        new Thread()
        {
            public void run() {
                Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
                EventBus.getDefault().post(eventBusApp.new PostEvent("PostEvent"));
            };
        }.start();

    }

    public void methodSubMain()
    {
        new Thread()
        {
            public void run() {
                Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
                EventBus.getDefault().post(eventBusApp.new MainEvent("MainEvent"));
            };
        }.start();

    }

    public void methodSubBack()
    {
        new Thread()
        {
            public void run() {
                Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
                EventBus.getDefault().post(eventBusApp.new BackEvent("BackEvent"));
            };
        }.start();

    }

    public void methodSubAsync(final Exam exam)
    {
        new Thread()
        {
            public void run() {
                Log.d("yzy", "PostThread-->"+Thread.currentThread().getId());
                EventBus.getDefault().post(eventBusApp.new AsyncEvent(exam));
            };
        }.start();

    }



    //Event-------------------------start-------------------------------
    *//**
     * 使用onEvent来接收事件，那么接收事件和分发事件在一个线程中执行
     * @param event
     *//*
    public void onEvent(PostEvent event)
    {
        Log.d("yzy", "OnEvent-->"+Thread.currentThread().getId());
        Log.d("yzy",event.msg);
    }

    *//**
     * 使用onEventMainThread来接收事件，那么不论分发事件在哪个线程运行，接收事件永远在UI线程执行，
     * 这对于android应用是非常有意义的
     * @param event
     *//*
    public void onEventMainThread(MainEvent event)
    {
        Log.d("yzy", "onEventMainThread-->" + Thread.currentThread().getId());
    }

    *//**
     * 使用onEventBackgroundThread来接收事件，如果分发事件在子线程运行，那么接收事件直接在同样线程
     * 运行，如果分发事件在UI线程，那么会启动一个子线程运行接收事件
     * @param event
     *//*
    public void onEventBackgroundThread(BackEvent event)
    {
        Log.d("yzy", "onEventBackgroundThread-->"+Thread.currentThread().getId());
    }
    *//**
     * 使用onEventAsync接收事件，无论分发事件在（UI或者子线程）哪个线程执行，接收都会在另外一个子线程执行
     * @param event
     *//*
    public void onEventAsync(AsyncEvent event)
    {
        Log.d("yzy", "onEventAsync-->"+Thread.currentThread().getId());
        Log.d("yzy", event.exam.getName());
    }
    //Event------------------------------end-------------------------------------*/
}
