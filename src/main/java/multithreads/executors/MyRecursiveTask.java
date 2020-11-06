package multithreads.executors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Integer> {
    private static final int LIMIT = 100;
    private Integer[] table;

    public MyRecursiveTask(Integer[] table) {
        this.table = table;
    }

    @Override
    protected Integer compute() {
        if (table.length > LIMIT) {
            return ForkJoinTask.invokeAll(createSubTasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        }
        return Arrays.stream(table).reduce((a, b) -> a + b).get();
    }

    private List<MyRecursiveTask> createSubTasks() {
        List<MyRecursiveTask> dividedTasks = new ArrayList<>();
        dividedTasks.add(new MyRecursiveTask(
                Arrays.copyOfRange(table, 0, table.length / 2)));
        dividedTasks.add(new MyRecursiveTask(
                Arrays.copyOfRange(table, table.length / 2, table.length)));
        return dividedTasks;
    }
}
