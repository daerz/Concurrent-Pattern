package com.daerz.concurrent.chapter4;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/9
 */
public class OctalObserver extends Observer{

    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String:" + Integer.toOctalString(subject.getState()));
    }
}
