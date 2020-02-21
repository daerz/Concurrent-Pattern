package com.daerz.concurrent.chapter5;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/11
 * 线程
 */
public class User extends Thread{

    private final String myName;

    private final String myAddress;

    private final Gate gate;

    public User(String myName, String myAddress, Gate gate) {
        this.myName = myName;
        this.myAddress = myAddress;
        this.gate = gate;
    }

    @Override
    public void run() {
        System.out.println(myName + " BEGIN");
        while (true) {
            this.gate.pass(myName, myAddress);
        }
    }
}
