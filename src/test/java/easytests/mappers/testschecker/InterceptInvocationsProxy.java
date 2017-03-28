package easytests.mappers.testschecker;

import lombok.Getter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vkpankov
 */
public final class InterceptInvocationsProxy implements InvocationHandler {

    @Getter
    private final List<Method> methodList;

    private final Object mapperBean;

    public InterceptInvocationsProxy(Object originalMapper) {

        methodList = new ArrayList<>();
        mapperBean = originalMapper;

    }

    public Object invoke(Object proxy, Method m, Object[] args) {
        methodList.add(m);
        try {
            return m.invoke(mapperBean, args);
        } catch (Exception anyException) {
            return null;
        }
    }
}

