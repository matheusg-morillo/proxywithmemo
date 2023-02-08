package config.cache;

public interface Cacheable<I, O> {
    O of(I number);
}
