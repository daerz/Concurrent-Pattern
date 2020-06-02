package com.daerz.concurrent.chapter3;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/8
 * 读时线程会从CPU的高速缓存拿数据,若主内存数据发生变化,高速缓存无法获取
 * 三个重要概念:
 *      1.原子性(一个操作,要么都成功,要么都失败)
 *          对基本数据类型的变量读取和赋值是保证了原子性的,要么都成功,要么都失败,这些操作不可被中断
 *          i = 10;
 *          cache 10, memory 10;
 *          eg:
 *              a = 10; 原子性
 *              b = a;  非原子性, 1. read a; 2. assign b;
 *              c++;    非原子性, 1. read c; 2. add; 3. assign to c;
 *              c = c+1;非原子性, 1. read c; 2. add; 3. assign to c;
 *      2.可见性
 *          volatile:
 *              保证了数据的可见性(从主存里获取数据)
 *      3.有序性(顺序性)
 *          happens-before relationship(Java线程有序性的处理规则):
 *              1. 代码的执行顺序,编写在前面的发生在编写的后面的(单线程)
 *              2. unlock必须发生在lock之后
 *              3. volatile修饰一个变量,对变量的写操作先于对变量的读操作
 *              4. 传递规则,操作A先于B,操作B先于C,则操作A肯定先于C
 *              5. 线程启动规则,start方法执行肯定优先于线程run
 *              6. 线程中断规则,interrupt动作,必须发生在捕获该动作之前
 *              7. 对象销毁规则,初始化必须发生在finalize之前
 *              8. 线程终结规则,所有的操作都发生在线程死亡之前
 *      volatile关键字
 *          1.保证了不同线程间的可见性
 *          2.禁止对其进行重排序(即保证了有序性)
 *          3.并未保证原子性
 *
 *          1.保证重排序的有序性不会把后面的指令放到屏障前面,也不会把前面的放到后面
 *          2.强制对缓存的修改操作立刻写入主存
 *          3.如果是写操作,他会导致其他CPU中的缓存失效
 *          使用场景
 *              1.状态量标记
 *              2.屏障前后一致性
 *      1.CPU结构
 *      2.JMM大致结构  ++INIT(volatile): read -> main memory -> 10、INIT = 10+1 -> add -> write
 *      3.缓存一致性协议
 *      4.指令重排序
 *      5.happens-before
 *      6.并发编程三要素
 *      7.volatile关键字作用
 */
public class VolatileTest {

    /**
     * volatile:保证内存可见性、有序性
     */
    private static /*volatile*/ int INIT = 0;
    private static final int MAX = 5;

    private static void reader() {
        new Thread(() -> {
            int reader = INIT;
            while (reader < MAX) {
                if (reader != INIT){
                    System.out.printf("The value updated to [%d]\n", INIT);
                    reader = INIT;
                }
            }
        }, "RAEDER").start();
    }

    /**
     * 缓存不一致问题: 主存中数据发生变化,读时数据为cache中数据,并未做更新,导致缓存不一致
     * i = 1; i = i+1;
     * CPU1 -> main memory -> cache i+1 -> cache(2) -> main memory(2)
     * CPU1 -> main memory -> cache i+1 -> cache(2) -> main memory(2)
     * 1.给数据总线加锁
     *      总线(数据总线,地址总线,控制总线)
     *      LOCK#
     * 2.CPU高速缓存一致性协议
     *      Intel MESI(保证cache中数据的一致性)
     *      核心思想:
     *          1.当CPU写入数据时,如果发现变量被共享(也就是其他CPU中也存在该变量副本),
     *          会发出一个信号,通知其他CPU该变量的缓存无效
     *          2.当其他CPU访问该变量的时候,重新到主内存获取
     */
    private static void updater() {
        new Thread(() -> {
            int updater = INIT;
            while (INIT < MAX) {
                System.out.printf("update the value to [%d]...\n", ++updater);
                INIT = updater;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "WRITER").start();
    }
    public static void main(String[] args) throws InterruptedException {
        reader();
        updater();
    }
}
