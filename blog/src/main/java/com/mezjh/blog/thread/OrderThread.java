package com.mezjh.blog.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 探究 ExecutorService 的 invokeAll()方法 是否具有执行顺序
 * 简单的记录一下...
 * @author ZJH
 * @date 2020/9/16 15:36
 */
public class OrderThread {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 0;i < 10;i++) {
            tasks.add(new Mytask(i));
        }
        List<Future<Integer>> futures = executorService.invokeAll(tasks);
        if (futures != null && futures.size() > 0) {
            futures.stream().forEach(res -> {
                try {
                    System.out.println(res.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    public static class Mytask implements Callable<Integer> {

        private int var;

        public Mytask(int var) {
            this.var = var;
        }

        @Override
        public Integer call() throws Exception {
            if (this.var == 5) {
                Thread.sleep(10000);
            }
            return this.var;
        }
    }
}

