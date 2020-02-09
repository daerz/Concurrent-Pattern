package com.daerz.concurrent.chapter2;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/7
 */
public class WaitSet {

    private final static Object LOCK = new Object();

    private static void work() {
        synchronized (LOCK) {
            System.out.println("Begin...");
            try {
                System.out.println("Thread is coming....");
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread is out...");
        }
    }

    /**
     * 1.所有对象都有一个wait set,用来存放调用了该对象wait方法之后进入block的状态的线程
     * 2.线程被notify之后,不一定立即执行
     * 3.线程从wait set中被唤醒不一定时FIFO
     * 4.线程被唤醒后,必须重新获取锁
     * @param args
     */
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            new Thread(String.valueOf(i)) {
                @Override
                public void run() {
                    synchronized (LOCK) {
                        try {
                            Optional.of(Thread.currentThread().getName() + " will come to wait set").ifPresent(System.out::println);
                            LOCK.wait();
                            Optional.of(Thread.currentThread().getName() + " will leave to wait set").ifPresent(System.out::println);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });
    }
}
