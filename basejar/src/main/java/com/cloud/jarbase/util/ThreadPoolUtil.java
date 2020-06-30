package com.cloud.jarbase.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;


/**
 * 单例线程池，重复复用
 */
public class ThreadPoolUtil {

    private ThreadPoolUtil(){};

    public static final ThreadPoolUtil getInstance() {
        return ThreadPoolUtilHolder.INSTANCE;
    }

    private static class ThreadPoolUtilHolder {
        private static final ThreadPoolUtil INSTANCE = new ThreadPoolUtil();
    }

    private static final ExecutorService executorService;

    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("rate-pool-%d").build();
        executorService = new ThreadPoolExecutor(2, 5
                , 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(), threadFactory);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
