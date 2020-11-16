package multithreads;

import multithreads.executors.MyThread;
import multithreads.util.Util;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Arrays;

public class MyThreadTest {
    private static final double DIFF = 1e-15;

    private static MyThread myThread;
    private static int sum;
    private static Util util;

    @BeforeClass
    public static void beforeClass() {
        myThread = new MyThread();
        util = new Util();
        sum = Arrays.stream(util.listGenerator()).sum();
    }

    @Test
    public void executorServicePositive() throws InterruptedException {
        int testSum = myThread.executorRun();
        Assert.assertEquals(sum, testSum, DIFF);
    }

    @Test
    public void forkJoinPositive() {
        int testSum = myThread.forkJoinRun();
        Assert.assertEquals(sum, testSum, DIFF);
    }
}
