package com.daerz.concurrent.chapter4;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/9
 */
public class ObserverClient {

    public static void main(String[] args) {

        final Subject subject = new Subject();
        new BinaryObserver(subject);
        new OctalObserver(subject);
        System.out.println("===================");
        subject.setState(10);
        System.out.println("--------------------");
        subject.setState(10);
        //state变化
        subject.setState(15);
        System.out.println("====================");
    }
}
