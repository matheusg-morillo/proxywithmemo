public interface Cacheable<I, O> {
    O of(I arg);
}
