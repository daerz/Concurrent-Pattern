package com.daerz.concurrent.chapter4;

import java.util.List;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/11
 */
public class ThreadLifeCycleObserver implements LifeCycleListener{

    private final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        ids.stream().forEach(id -> new Thread(new ObserverRunnable(this) {
            @Override
            public void run() {
                try {
                    //回调通知其状态
                    notifyChange(new RunnableEvent(RunnableState.RUNNING, Thread.currentThread(), null));
                    System.out.println("query for the id " + id);
                    Thread.sleep(1000L);
                    int a = 1 / 0;
                    notifyChange(new RunnableEvent(RunnableState.DONE, Thread.currentThread(), null));
                } catch (Exception e) {
                    notifyChange(new RunnableEvent(RunnableState.ERROR, Thread.currentThread(), e));
                }
            }
        }, id).start());
    }

    @Override
    public void onEvent(ObserverRunnable.RunnableEvent event) {
        synchronized (LOCK) {
            System.out.println("The runnable [" + event.getThread().getName() + "] data changed and state is [" + event.getState() + "]");
            //失败
            if (event.getCause() != null) {
                System.out.println("The runnable [" + event.getThread().getName() + "] process failed.");
                event.getCause().printStackTrace();
            }
        }
    }
}
