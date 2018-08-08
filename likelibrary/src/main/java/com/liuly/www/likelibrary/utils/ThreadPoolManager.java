package com.liuly.www.likelibrary.utils;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by liuly on 2016/12/19.
 * 线程池管理器
 */

public class ThreadPoolManager {

    private ThreadPoolProxy threadPoolProxy;

    private ThreadPoolManager() {
    }

    private static ThreadPoolManager instance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return instance;
    }

    public ThreadPoolProxy createThreadPoolProxy() {
        if (threadPoolProxy == null) {
            threadPoolProxy = new ThreadPoolProxy(5, 5, 5000);
        }
        return threadPoolProxy;
    }

    class ThreadPoolProxy {
        //        CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
//        sPoolWorkQueue, sThreadFactory
        private int corePoolSize;
        private int maximumPoolSize;
        private int keepAlive;
        private ThreadPoolExecutor threadPoolExecutor;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, int keepAlive) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAlive = keepAlive;
        }

        public void execute(Runnable runnable) {
            if (threadPoolExecutor == null) {
                threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAlive, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<Runnable>());
            }
            threadPoolExecutor.execute(runnable);
        }

        public void cancel(Runnable runnable) {
            if (threadPoolExecutor != null && !threadPoolExecutor.isShutdown() && !threadPoolExecutor.isTerminated()) {
                threadPoolExecutor.remove(runnable);
            }
        }
    }
}
