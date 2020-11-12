package multithreads.util;

import java.util.stream.IntStream;

public class Util {
    public static final int MILLION = 1_000_000;

    public int[] listGenerator() {
        return IntStream.rangeClosed(1, 1_000_000).toArray();
    }

    public int[] listGenerator(int start, int finish) {
        return IntStream.rangeClosed(start, finish).toArray();
    }
}
