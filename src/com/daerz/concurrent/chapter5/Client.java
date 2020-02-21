package com.daerz.concurrent.chapter5;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/11
 * 多个线程访问同一个资源
 * vertify
 */
public class Client {

    public static void main(String[] args) {
        Gate gate = new Gate();
        User g = new User("GuangGuan", "GuangZhou", gate);
        User b = new User("Beibei", "BeiJing", gate);
        User n = new User("NanNan", "NanJing", gate);
        g.start();
        b.start();
        n.start();
    }
}
