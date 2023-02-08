public class Fibonacci implements Cacheable<Integer, Integer> {

    @Override
    public Integer of(Integer n) {
        if (n < 0) {
            throw new IllegalArgumentException("Index was negative. No such thing as a negative index in a series.");
        }

        if (n == 0 || n == 1) {
            return n;
        }

        System.out.printf("computing fib(%d)\n", n);
        return of(n - 1) + of(n - 2);
    }
}
