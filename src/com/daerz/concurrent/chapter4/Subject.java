package com.daerz.concurrent.chapter4;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/9
 */
public class Subject {

    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        if (state == this.state) {
            return;
        }
        this.state = state;
        notifyAllObserver();
    }

    public void notifyAllObserver() {
        observers.stream().forEach(Observer::update);
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }



}
