package com.example.demo.RunnableAndThread;

public class SynchronizedDemo {
    private int value;

    public SynchronizedDemo(int value){
        this.value = value;
    }

    public synchronized int increment(){
        value++;
        return  value;
    }
}
