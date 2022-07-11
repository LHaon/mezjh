package com.mezjh.blog.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ZJH
 * @date 2021/7/2 14:14
 */
public class ThreadAndLockMethod {

    private static final ReentrantLock lock = new ReentrantLock();

    public void testExecutorService(Integer number) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程执行完毕" + number);
        });
        executorService.shutdown();
        if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
            executorService.shutdownNow();
        }
        System.out.println("执行完毕");
    }

    public void testLock(Integer number) throws InterruptedException {

        System.out.println("执行完毕");
    }
}
