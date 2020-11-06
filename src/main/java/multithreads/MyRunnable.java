package multithreads;

public class MyRunnable implements Runnable {
    private Counter counter;

    MyRunnable(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (counter.getCounter() < Counter.END_COUNT) {
            counter.sumUp();
        }
    }
}
