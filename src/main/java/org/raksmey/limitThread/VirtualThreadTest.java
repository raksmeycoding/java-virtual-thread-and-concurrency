package org.raksmey.limitThread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class VirtualThreadTest {
    static Logger logger = LoggerFactory.getLogger(VirtualThreadTest.class);
    public static void main(String[] args) throws InterruptedException {
        // Only 1 platform thread
        ForkJoinPool onePlatformThread = new ForkJoinPool(1);

        // Virtual thread factory that uses the limited platform thread pool
        ThreadFactory virtualFactory = task -> Thread.ofVirtual().unstarted(() -> onePlatformThread.execute(task));

        // Executor for virtual threads
        ExecutorService virtualExecutor = Executors.newThreadPerTaskExecutor(virtualFactory);

        // ✅ Proper virtual-thread-per-task executor (JVM manages scheduling)
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        // 6 long-running virtual threads
        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            executor.submit(() -> {
                logger.info("Task {} started on ", Thread.currentThread());
                try {
                    Thread.sleep(3000); // Simulate long I/O
                    logger.info("Task {} finished", taskId);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        executor.shutdown();
        virtualExecutor.awaitTermination(100, TimeUnit.SECONDS);
    }
}
