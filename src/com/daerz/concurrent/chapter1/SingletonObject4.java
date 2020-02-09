package com.daerz.concurrent.chapter1;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/6
 * 饱汉式3(double check,解决单例、懒加载和性能问题,但可能引起空指针异常)
 * NPE原因：第一个刚开始new是,可能尚未初始化对线中属性,第二个获取单例对象时
 * 用到属性时可能会尚未初始化
 * happens-before,保证读的时候,写已经全部完成
 */
public class SingletonObject4 {

    private static SingletonObject4 singleton;

    private SingletonObject4() {

    }

    private static SingletonObject4 getInstance() {
        if (singleton == null) {
            synchronized (SingletonObject4.class) {
                if (singleton == null) {
                    singleton = new SingletonObject4();
                }
            }
        }
        return singleton;
    }
}
