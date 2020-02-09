package com.daerz.concurrent.chapter1;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/6
 * 饱汉式1(线程不安全)
 */
public class SingletonObject2 {

    private static SingletonObject2 singleton;

    private SingletonObject2() {

    }

    private static SingletonObject2 getInstance() {
        if (singleton == null) {
            singleton = new SingletonObject2();
        }
        return singleton;
    }
}
