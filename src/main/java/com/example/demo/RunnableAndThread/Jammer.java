package com.example.demo.RunnableAndThread;

public class Jammer implements Runnable {
    private ToastQueue butteredQueue,finishedQueue;

    public Jammer(ToastQueue butteredQueue, ToastQueue finishedQueue) {
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }


    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                Toast t = butteredQueue.take();
                t.jammed();
                System.out.println(t);
                finishedQueue.put(t);
            }
        }catch (InterruptedException e){
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}
