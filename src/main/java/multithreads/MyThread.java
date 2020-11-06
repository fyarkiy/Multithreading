package multithreads;

import org.apache.log4j.Logger;

public class MyThread extends Thread {
    private static final Logger logger = Logger.getLogger(App.class);
    private Counter counter;

    MyThread(Counter counter) {
        this.counter = counter;
    }

    public Counter getCounter() {
        return counter;
    }

    @Override
    public void run() {
        while (counter.getCounter() < Counter.END_COUNT) {
            counter.sumUp();
        }
    }
}
