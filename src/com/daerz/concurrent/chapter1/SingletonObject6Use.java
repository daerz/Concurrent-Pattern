package com.daerz.concurrent.chapter1;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/6
 * 推荐使用(效率、安全、优雅)
 */
public class SingletonObject6Use {

    private SingletonObject6Use() {

    }

    /**
     * 主动加载
     */
    private static class InstanceHolder {
        private final static SingletonObject6Use INSTANCES = new SingletonObject6Use();
    }

    public SingletonObject6Use getInstance() {
        return InstanceHolder.INSTANCES;
    }
}
