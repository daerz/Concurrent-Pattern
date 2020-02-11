package com.daerz.concurrent.chapter4;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/9
 */
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary String:" + Integer.toBinaryString(subject.getState()));
    }
}
