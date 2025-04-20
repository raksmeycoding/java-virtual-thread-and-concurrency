package org.raksmey;

import java.util.concurrent.*;

public class TestingThreads {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(1);

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ": " + "Index: " + finalI);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown(); // allows submitted tasks to finish, doesn't block
        System.out.println("Main thread doing other things...");
        // after shutdown()
        while (!executor.isTerminated()) {
            System.out.println("Main thread still working...");
            try {
                Thread.sleep(1000); // simulate doing something else
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All tasks completed.");

    }
}
