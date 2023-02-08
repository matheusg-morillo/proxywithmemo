package application;

import config.cache.Cacheable;

import static config.cache.Cache.withCache;

public class Main {
    public static void main(String[] args) {
        // Memoization
        Cacheable<Integer, Integer> fibonacci = withCache(new Fibonacci());
        System.out.println(fibonacci.of(5));
        System.out.println(fibonacci.of(5));
    }
}
