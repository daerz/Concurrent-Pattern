package com.daerz.concurrent.chapter4;

/**
 * @Author 刘小猛 liuxiaomeng@dcocd.cn
 * @Date 2020/2/11
 * 观察者线程
 */
public abstract class ObserverRunnable implements Runnable{

    final protected LifeCycleListener listener;

    public ObserverRunnable(final LifeCycleListener listener) {
        this.listener = listener;
    }

    protected void notifyChange(final RunnableEvent event) {
        listener.onEvent(event);
    }

    public enum RunnableState {
        RUNNING, ERROR, DONE;
    }

    public static class RunnableEvent {
        private final RunnableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableState state, Thread thread, Throwable cause) {
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }
}
