package easytests.models.empty;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractEmptyTest {
    /**
     * Determine all defaults methods that should be skipped from test
     */
    protected static List<String> skipMethodsWithNames = new ArrayList<>(
            Arrays.asList("getId", "wait", "equals", "notify", "notifyAll", "toString", "hashCode", "getClass")
    );

    /**
     * Must be class of testing empty model
     */
    protected Class modelEmpty;

    public AbstractEmptyTest(Class modelEmptyClass) {
        this.modelEmpty = modelEmptyClass;
    }

    /**
     * Test that each method which are not in skipList throw expected Exception
     */
    protected void testModelTrowsExpectedExceptions(Object model, Class expectedException) throws Exception {
        for (Method method: this.modelEmpty.getMethods()) {
            if (skipMethodsWithNames.contains(method.getName())) {
                continue;
            }
            final Method userMethod = this.modelEmpty.getMethod(method.getName(), method.getParameterTypes());
            try {
                userMethod.invoke(model, this.getMethodParametersValues(method));
                throw new Exception("Method should throw exception");
            } catch (InvocationTargetException ite) {
                if (ite.getCause().getClass() != expectedException) {
                    throw new Exception("Exception should be instance of " + expectedException.getName());
                }
            }
        }
    }

    /**
     * Get actual parameters for method
     */
    private Object[] getMethodParametersValues(Executable method) throws Exception {
        final Object[] parameters = new Object[method.getParameterCount()];
        Integer i = 0;
        for (Class<?> type: method.getParameterTypes()) {
            parameters[i] = this.getObjectByType(type);
            i++;
        }
        return parameters;
    }

    /**
     * Get Object for Type
     * Create specific types manually cause of absent default constructor or final class
     * Create Mock object otherwise
     */
    private Object getObjectByType(Class<?> type) throws Exception {
        Object object;
        if ((type == Integer.class) || (type == int.class)) {
            object = 9;
        } else if (type == Double.class) {
            object = 9.5;
        } else if (type == Boolean.class) {
            object = true;
        } else if (Modifier.isFinal(type.getModifiers())) {
            try {
                object = type.newInstance();
            } catch (InstantiationException e) {
                object = null;
                for (Constructor constructor : type.getConstructors()) {
                    object = constructor.newInstance(this.getMethodParametersValues(constructor));
                    break;
                }
            }
        } else {
            object = Mockito.mock(type);
        }
        return object;
    }
}
