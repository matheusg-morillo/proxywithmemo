public class Main {
    public static void main(String[] args) {
        Cacheable<Integer, Integer> fibonacci = Cache.withCache(new Fibonacci());
        System.out.println(fibonacci.of(5));
        System.out.println(fibonacci.of(5));
    }
}
