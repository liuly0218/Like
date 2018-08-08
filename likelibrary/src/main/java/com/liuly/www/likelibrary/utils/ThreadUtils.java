package com.liuly.www.likelibrary.utils;

import android.os.Handler;

/**
 * Created by liuly on 2016/12/19.
 * 线程管理工具
 */

public class ThreadUtils {
    /**
     * 子线程运行的方法
     *
     * @param runnable
     */
    public static void runOnBackground(Runnable runnable) {
        ThreadPoolManager.getInstance().createThreadPoolProxy().execute(runnable);
    }

    private static Handler handler = new Handler();

    /**
     * 主线程中运行的方法
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }
}
