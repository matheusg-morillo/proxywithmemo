package config.cache;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Cache implements InvocationHandler {

    public final Object target;
    public final Map<Object, Object> memo;

    public Cache(Object target) {
        this.target = target;
        memo = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object arg = args[0];

        if (memo.containsKey(arg)) {
            return memo.get(arg);
        }

        Object result = method.invoke(target, arg);
        memo.put(arg, result);

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <I, O> Cacheable<I, O> withCache(Cacheable<I, O> target) {
        return (Cacheable<I, O>) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[] { Cacheable.class },
                new Cache(target)
        );
    }
}
