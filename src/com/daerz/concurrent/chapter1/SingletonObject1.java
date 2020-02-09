package com.daerz.concurrent.chapter1;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/6
 * 饿汉式(不能懒加载)
 */
public class SingletonObject1 {

    private static final SingletonObject1 singleton = new SingletonObject1();

    private SingletonObject1() {

    }

    private static SingletonObject1 getInstance() {
        return singleton;
    }
}
