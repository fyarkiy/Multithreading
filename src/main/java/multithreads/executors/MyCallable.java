package multithreads.executors;

import java.util.Arrays;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private int[] tableOfMil;

    MyCallable(int[] tableOfMil) {
        this.tableOfMil = tableOfMil;
    }

    @Override
    public Integer call() throws Exception {
        return Arrays.stream(tableOfMil).reduce((a, b) -> a + b).getAsInt();
    }
}
