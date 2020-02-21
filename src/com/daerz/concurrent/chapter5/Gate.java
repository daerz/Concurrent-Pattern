package com.daerz.concurrent.chapter5;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/11
 * Single Threaded Execution design pattern
 * 共享资源
 */
public class Gate {

    private int counter = 0;
    private String name = "nobody";
    private String address = "nowhere";

    /**
     * 临界值
     * 解决资源共享,多线程访问同一资源引发的多线程问题:
     *      synchronized(串行化,效率较低,读的时候也会放入wait set)
     *      解决方案:  是否需要串行化(chapter6)
     *                +----------------------+
     *                +      ｜ READ ｜ WRITE ｜
     *                +-----------------------+
     *                + READ ｜  N   ｜  Y    ｜
     *                +-----------------------+
     *                + WRITE｜  Y   ｜  N    ｜
     *                +-----------------------+
     *                读写锁分离
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        /* 临界值 */
        this.name = name;
        this.address = address;
        verify();
    }

    /**
     * 校验身份
     */
    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("*********BROKEN***********" + toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "No." + counter + ":" + name + "," + address;
    }
}
