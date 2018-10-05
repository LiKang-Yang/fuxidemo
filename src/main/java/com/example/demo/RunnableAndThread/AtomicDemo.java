package com.example.demo.RunnableAndThread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicDemo {

    //使用java.util.concurrent.atomic可以保持原子性，不会出现线程不安全
    public static AtomicLong nextNumber = new AtomicLong();


    public static void main(String[] args) {
        Runnable task1 = () ->{
            for (int i=0;i<10;i++){
                long id = nextNumber.incrementAndGet();
                System.out.println(id);
            }

        };

        Runnable task2 = () ->{
            for (int i=0;i<10;i++){
                long id = nextNumber.incrementAndGet();
                System.out.println(id);
            }

        };

        Executor executor = Executors.newCachedThreadPool();
        executor.execute(task1);
        executor.execute(task2);
    }



}
