package com.mezjh.blog.reflect;

import lombok.SneakyThrows;

/**
 * @author ZJH
 * @date 2021/7/22 14:45
 */
public class Main {

    public static void main(String[] args) {
        MyThread2 mt=new MyThread2();
        MyThread2 mt2=new MyThread2();
        new Thread(mt).start();
        new Thread(mt2).start();
    }

    static class MyThread2 implements Runnable{

        public static int ticket = 30;
        private ThreadLocal<String> threadLocal = new ThreadLocal<>();
        @Override
        @SneakyThrows
        public void run(){
            threadLocal.set("30");
            int ticket = Integer.parseInt(threadLocal.get());
            while(true){
                System.out.println("Runnable ticket = " + ticket--);
                Thread.sleep(100);
                threadLocal.set(String.valueOf(ticket));
                if(ticket < 0){
                    break;
                }
            }
        }
    }
}
