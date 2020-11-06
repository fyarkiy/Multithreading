package multithreads;

import multithreads.executors.Threading;
import multithreads.util.Util;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Arrays;

public class ThreadingTest {
    private static final double DIFF = 1e-15;

    private static Threading threading;
    private static int sum;
    private static Util util;

    @BeforeClass
    public static void beforeClass() {
        threading = new Threading();
        util = new Util();
        sum = Arrays.stream(util.listGenerator()).mapToInt(Integer::intValue).sum();
    }

    @Test
    public void executorServicePositive() {
        int testSum = threading.executorRun();
        Assert.assertEquals(sum, testSum, DIFF);
    }

    @Test
    public void forkJoinPositive() {
        int testSum = threading.forkJoinRun();
        Assert.assertEquals(sum, testSum, DIFF);
    }
}
