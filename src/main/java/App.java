import org.apache.log4j.Logger;

public class App {
    private static final int END_COUNT = 100;
    private static final Logger logger = Logger.getLogger(App.class);

    private static Counter counter = new Counter();

    static class MyInnerThread extends Thread {
        @Override
        public void run() {
            while (counter.getCounter() < END_COUNT) {
                counter.sumUp();
                logger.info(Thread.currentThread().getName() + " value = "
                        + counter.getCounter());
            }
            ;
        }
    }

    public static void main(String[] args) {
        Thread myRunnableThread = new Thread(new Runnable() {
            public void run() {
                while (counter.getCounter() < END_COUNT) {
                    counter.sumUp();
                    logger.info(Thread.currentThread().getName() + " (Runnable) value = "
                            + counter.getCounter());
                }
            }
        });
        MyInnerThread myInnerThread = new MyInnerThread();
        myRunnableThread.start();
        myInnerThread.start();
    }
}
