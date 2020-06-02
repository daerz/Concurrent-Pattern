package com.daerz.concurrent.chapter6;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/11
 * 共享的数据
 */
public class SharedData {

    private final char[] buffer;

    private final ReadWriteLock LOCK = new ReadWriteLock();

    public SharedData(int size) {
        this.buffer = new char[size];
        //初始化
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }

    /**
     * 读
     * @return
     * @throws InterruptedException
     */
    public char[] read() throws InterruptedException {
        try {
            LOCK.readLock();
            return this.doRead();
        }finally {
            LOCK.readUnlock();
        }
    }

    private char[] doRead() {
        char[] newBuffer = new char[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            newBuffer[i] = buffer[i];
        }
        slowly(50);
        return newBuffer;
    }

    /**
     * 写
     * @param c
     */
    public void write(char c) throws InterruptedException {
        try{
            LOCK.writeLock();
            this.doWrite(c);
        }finally {
            LOCK.writeUnlock();
        }
    }

    private void doWrite(char c) {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = c;
            slowly(10);
        }
    }

    private void slowly(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
