package com.daerz.concurrent.chapter1;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/6
 * 饱汉式2(线程安全,性能不好)
 */
public class SingletonObject3 {

    private static SingletonObject3 singleton;

    private SingletonObject3() {

    }

    private static synchronized SingletonObject3 getInstance() {
        if (singleton == null) {
            singleton = new SingletonObject3();
        }
        return singleton;
    }
}
