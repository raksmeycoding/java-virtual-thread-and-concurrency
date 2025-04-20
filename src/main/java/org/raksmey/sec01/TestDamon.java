package org.raksmey.sec01;

import java.time.Duration;

public class TestDamon {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                    System.out.println("Daemon Thread running.");
//
            }
        });

        thread.setDaemon(true);
        thread.start();

        try {

            System.out.println("Main thread starting.");
        } catch (Exception e ) {
            throw new RuntimeException(e);
        }
    }
}
