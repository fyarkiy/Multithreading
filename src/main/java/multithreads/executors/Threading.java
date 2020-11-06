package multithreads.executors;

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

public class Threading {
    private static final Logger logger = Logger.getLogger(Threading.class);
    private static final int THREADS_NUMBER = 10;
    private static final long TERMINATION_TIME = 800;

    public int executorRun() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NUMBER);
        Callable<Integer> callableTask = new MyCallable(new Util().listGenerator());
        Future<Integer> future = executorService.submit(callableTask);
        executorService.shutdown();
        int result = 0;
        try {
            if (!executorService.awaitTermination(TERMINATION_TIME, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
            result = future.get();
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
