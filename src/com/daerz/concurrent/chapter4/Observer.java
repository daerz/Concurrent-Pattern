package com.daerz.concurrent.chapter4;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/9
 * 观察者模式,线程变动通知到观察者
 */
public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }

    public abstract void update();
}
