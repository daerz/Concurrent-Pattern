package com.daerz.concurrent.chapter1;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/6
 * 最棒(效率、安全、优雅)
 */
public class SingletonObject7Use2 {


    private SingletonObject7Use2() {

    }

    private enum Singleton {
        //补
        INSTANCE;

        private final SingletonObject7Use2 instance;

        /**
         * 初始化
         */
        Singleton() {
            instance = new SingletonObject7Use2();
        }

        private SingletonObject7Use2 getInstance() {
            return instance;
        }
    }

    public SingletonObject7Use2 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

}
