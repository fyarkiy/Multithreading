package multithreads.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import multithreads.util.PoolUtil;
import multithreads.util.Util;
import org.apache.log4j.Logger;

public class MyThread {
    private static final Logger logger = Logger.getLogger(MyThread.class);
    private static final int THREADS_NUMBER = 4;
    private static final long TERMINATION_TIME = 800;

    public int executorRun() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUMBER);
        Callable<Integer> callableTask = new MyCallable(new Util().listGenerator());
        List<Callable<Integer>> callableTasks = new ArrayList<>();
        for (int i = 0; i < THREADS_NUMBER; i++) {
            callableTasks.add(new MyCallable(new Util()
                    .listGenerator(i * Util.MILLION / THREADS_NUMBER + 1,
                    (i + 1) * Util.MILLION / THREADS_NUMBER)));
        }
        List<Future<Integer>> futures = executorService.invokeAll(callableTasks);
        executorService.shutdown();
        int result = 0;
        try {
            if (!executorService.awaitTermination(TERMINATION_TIME, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
            for (Future<Integer> f : futures) {
                result = result + f.get();
            }
            logger.info("Executor Service result " + result);
        } catch (InterruptedException | ExecutionException e) {
            executorService.shutdownNow();
        }
        return result;
    }

    public int forkJoinRun() {
        ForkJoinPool forkJoinPool = PoolUtil.forkJoinPool;
        int result = forkJoinPool.invoke(new MyRecursiveTask(new Util().listGenerator()));
        logger.info("ForkJoin result = " + result);
        return result;
    }
}
