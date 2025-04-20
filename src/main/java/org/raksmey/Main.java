package org.raksmey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.TemporalUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {


        for(int i = 0 ; i < 1000 ; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> {
                try {
                    log.info("Task: {}, Thread Info: {}", finalI, Thread.currentThread());
                    Thread.sleep(Duration.ofSeconds(60));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            thread.start();
        }




    }
}