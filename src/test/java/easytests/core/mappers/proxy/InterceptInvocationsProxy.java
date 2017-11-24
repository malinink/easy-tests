package easytests.core.mappers.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import lombok.Getter;


/**
 * @author malinink
 */
public final class InterceptInvocationsProxy implements InvocationHandler {

    @Getter
    private final Set<Method> methods;

    private final Object mapperBean;

    public InterceptInvocationsProxy(Object originalMapper) {
        methods = new HashSet<>();
        mapperBean = originalMapper;
    }

    public Object invoke(Object proxy, Method method, Object[] args) {
        methods.add(method);
        try {
            return method.invoke(mapperBean, args);
        } catch (Exception exception) {
            return null;
        }
    }
}

