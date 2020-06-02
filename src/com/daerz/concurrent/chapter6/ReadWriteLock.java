package com.daerz.concurrent.chapter6;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/11
 * @Description 线程读写锁分离设计模式
 *
 * 解决资源共享,多线程访问同一资源引发的多线程问题:
 *      synchronized(串行化,效率较低,读的时候也会放入wait set)
 *      解决方案:  读写锁分离
 *                +----------------------+
 *                +      ｜ READ ｜ WRITE ｜
 *                +-----------------------+
 *                + READ ｜  N   ｜  Y    ｜
 *                +-----------------------+
 *                + WRITE｜  Y   ｜  N    ｜
 *                +-----------------------+
 */
public class ReadWriteLock {
    /**
     * 读
     */
    private int readingReaders = 0;
    /**
     * 等待读
     */
    private int waitingReaders = 0;
    /**
     * 写
     */
    private int writingWriters = 0;
    /**
     * 等待写
     */
    private int waitingWriters = 0;

    private boolean preferWriter;

    public ReadWriteLock() {
        this(true);
    }

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    /**
     * 读锁
     *      正在写,进入等待
     *      当有写存在时,等待
     * @throws InterruptedException
     */
    public synchronized void readLock() throws InterruptedException {
        this.waitingReaders++;
        try {
            while (writingWriters > 0 /*|| (preferWriter && waitingWriters > 0)*/) {
                this.wait();
            }
            this.readingReaders++;
        }finally {
            this.waitingReaders--;
        }
    }

    public synchronized void readUnlock() {
        readingReaders--;
        notifyAll();
    }

    /**
     * 写锁
     *      正在读或写,进入等待
     * @throws InterruptedException
     */
    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                this.wait();
            }
            this.writingWriters++;
        }finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnlock() {
        this.writingWriters--;
        notifyAll();
    }
}
