package multithreads.util;

import java.util.stream.Stream;

public class Util {
    private static final int MILLION = 1_000_000;

    public Integer[] listGenerator() {
        return Stream.iterate(0, i -> i + 1)
                .limit(MILLION)
                .toArray(Integer[]::new);
    }
}
