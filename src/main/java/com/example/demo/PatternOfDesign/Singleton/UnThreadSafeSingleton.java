package com.example.demo.PatternOfDesign.Singleton;

public class UnThreadSafeSingleton {
    private static UnThreadSafeSingleton instance = null;

    private UnThreadSafeSingleton(){

    }

    public static  UnThreadSafeSingleton getInstance(){
        if(instance == null){
            instance = new UnThreadSafeSingleton();
        }
        return instance;
    }

}
