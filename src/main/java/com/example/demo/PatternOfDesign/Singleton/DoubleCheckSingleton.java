package com.example.demo.PatternOfDesign.Singleton;

public class DoubleCheckSingleton {
    private volatile static DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton(){}

    public static DoubleCheckSingleton getInstance(){
        if(instance == null){
            synchronized (DoubleCheckSingleton.class){
                if (instance == null){
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
