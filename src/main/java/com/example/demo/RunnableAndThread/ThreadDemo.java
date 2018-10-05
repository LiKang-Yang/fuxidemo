package com.example.demo.RunnableAndThread;

public class ThreadDemo extends Thread{

    /**
     * 重写（Override）run()方法 JVM会自动调用该方法
     */
    public void run() {
        System.out.println("I'm running!");
    }


    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.start();
    }
}
