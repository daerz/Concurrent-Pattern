package com.daerz.concurrent.chapter1;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/6
 * 饱汉式4(double check,解决单例、懒加载和性能问题,以及可能引起空指针异常)
 * happens-before,保证读的时候,写已经全部完成
 * double check add volatile
 */
public class SingletonObject5 {

    /**
     * 告诉编译器不需要指令重排
     */
    private static volatile SingletonObject5 singleton;

    private SingletonObject5() {

    }

    private static SingletonObject5 getInstance() {
        if (singleton == null) {
            synchronized (SingletonObject5.class) {
                if (singleton == null) {
                    singleton = new SingletonObject5();
                }
            }
        }
        return singleton;
    }
}
