package org.raksmey;

public class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
    }
}
