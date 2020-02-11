package com.daerz.concurrent.chapter4;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/11
 * 用来监听线程生命周期
 */
public interface LifeCycleListener {

    /**
     * 回调处理
     * @param event
     */
    void onEvent(ObserverRunnable.RunnableEvent event);
}
