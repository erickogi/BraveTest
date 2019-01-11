package com.kogicodes.bravetest.utils;

import android.os.Process;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author kogi
 */
public class DefaultExecutorSupplier {

    public static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
    private static DefaultExecutorSupplier sInstance;
    private ThreadPoolExecutor mForBackgroundTasks;
    private ThreadPoolExecutor mForLightWeightBackgroundTasks;
    private Executor mMainThreadExecutor;


    public DefaultExecutorSupplier() {

        ThreadFactory backgroundPriorityThreadFactory = new
                PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND);

        mForBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

        mForLightWeightBackgroundTasks = new ThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                backgroundPriorityThreadFactory
        );

        mMainThreadExecutor = new MainThreadExecutor();
    }

    public static DefaultExecutorSupplier getInstance() {
        if (sInstance == null) {
            synchronized (DefaultExecutorSupplier.class) {
                sInstance = new DefaultExecutorSupplier();
            }
            return sInstance;
        }
        return sInstance;
    }

    public ThreadPoolExecutor forBackgroundTasks() {
        return mForBackgroundTasks;
    }


    public ThreadPoolExecutor forLightWeightBackgroundTasks() {
        return mForLightWeightBackgroundTasks;
    }


    public Executor forMainThreadTasks() {
        return mMainThreadExecutor;
    }
}
