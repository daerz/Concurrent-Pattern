package com.daerz.concurrent.chapter4;

import java.util.Arrays;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/11
 */
public class ThreadLifeCycleClient {

    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1", "2"));
    }
}
