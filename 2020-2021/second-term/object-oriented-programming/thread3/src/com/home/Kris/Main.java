package com.home.Kris;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static class MyRunnable implements Runnable, Callable<Integer> {

        @Override
        public void run() {
            try {
                for (int i = 0; i < 5; ++i) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                        Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " finished");
        }

        @Override
        public Integer call() throws Exception {
            return 1;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(6);

        executorService.submit((Runnable) new MyRunnable());
        executorService.submit((Runnable) new MyRunnable());
        executorService.submit((Runnable) new MyRunnable());
        executorService.submit((Runnable) new MyRunnable());
        executorService.submit((Runnable) new MyRunnable());
        executorService.submit((Runnable) new MyRunnable());
        executorService.submit((Runnable) new MyRunnable());
        executorService.submit((Runnable) new MyRunnable());

        Linked

        executorService.shutdown();
//        executorService.shutdownNow();
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        if(!executorService.isShutdown()) {
            executorService.shutdownNow();
        }
        executorService.
    }
}
