package multithreads;

import org.apache.log4j.Logger;

public class App {
    private static final Logger logger = Logger.getLogger(App.class);

    private static Counter counter = new Counter();

    public static void main(String[] args) {
        MyThread myThread = new MyThread(counter);
        MyRunnable myRunnable = new MyRunnable(counter);
        Thread runnableThread = new Thread(myRunnable);
        runnableThread.start();
        myThread.start();
    }
}
