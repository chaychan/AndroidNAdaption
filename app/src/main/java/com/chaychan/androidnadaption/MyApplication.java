package com.chaychan.androidnadaption;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;



public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName() ;
    private static Context mContext;
    private static Thread mMainThread;
    private static long mMainThreadId;
    private static Looper mMainLooper;
    private static Handler mHandler;

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static Handler getHandler() {
        return mHandler;
    }


    @Override
    public void onCreate() {// 程序的入口
//        Thread.setDefaultUncaughtExceptionHandler(new MyHandler());//设置全局异常捕获器

        // 初始化一些，常用的属性，然后放到盒子里面来
        // 上下文
        mContext = getApplicationContext();

        // 主线程
        mMainThread = Thread.currentThread();

        // 主线程id
        mMainThreadId = Process.myTid();

        // tid thread
        // uid user
        // pid process

        mMainLooper = getMainLooper();

        mHandler = new Handler();

        super.onCreate();
    }


    class MyHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            ex.printStackTrace();
            Process.killProcess(Process.myPid());//关闭当前进程
        }

    }
}
