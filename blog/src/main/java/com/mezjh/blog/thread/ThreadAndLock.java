package com.mezjh.blog.thread;

/**
 * @author ZJH
 * @date 2021/7/2 14:12
 */
public class ThreadAndLock {

    public static void main(String[] args) throws InterruptedException {
        ThreadAndLockMethod method = new ThreadAndLockMethod();
        //method.testExecutorService(1);
        method.testLock(2);
    }
}
