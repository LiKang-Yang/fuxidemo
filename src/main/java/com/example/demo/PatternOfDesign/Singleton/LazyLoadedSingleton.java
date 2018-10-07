package com.example.demo.PatternOfDesign.Singleton;

public class LazyLoadedSingleton {
    private LazyLoadedSingleton(){}

    private static class LazyHolder{
        private static final LazyLoadedSingleton singletonInstance = new LazyLoadedSingleton();
    }

    public static LazyLoadedSingleton getInstance(){
        return LazyHolder.singletonInstance;
    }
}
