import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Cache implements InvocationHandler {

    public final Object target;
    public final Map<Object[], Object> memo;

    public Cache(Object target) {
        this.target = target;
        memo = new HashMap<>();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (memo.containsKey(args)) {
            return memo.get(args);
        }

        Object result = method.invoke(target, args);
        memo.put(args, result);

        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> T withCache(T target) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                new Class[] { Cacheable.class },
                new Cache(target)
        );
    }
}
