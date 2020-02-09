package com.daerz.concurrent.chapter3;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/8
 * 更新时,并不会导致INIT不一致
 */
public class VolatileTest2 {


    private static int INIT = 0;
    private static final int MAX = 50;

    private static void updater(String name) {
        new Thread(() -> {
            while (INIT < MAX) {
                System.out.printf(name + " update the value to [%d]...\n", ++INIT);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "WRITER").start();
    }
    public static void main(String[] args) throws InterruptedException {
        updater("first");
        updater("secondary");
    }
}
