package multithreads;

import org.apache.log4j.Logger;

public class Counter {
    static final int END_COUNT = 100;
    private static final Logger logger = Logger.getLogger(Counter.class);
    private int counter = 0;

    int getCounter() {
        return counter;
    }

    void sumUp() {
        counter = counter + 1;
        logger.info(Thread.currentThread().getName() + " value = " + counter);
    }
}
