package easytests.models.empty;

import easytests.models.ModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractModelEmptyTest {

    /**
     * Must be class of testing empty model
     */
    protected static Class modelEmpty;

    /**
     * Determine all defaults methods that should be skipped from test
     */
    private static List<String> skipMethodsWithNames = new ArrayList<>(
            Arrays.asList("getId", "wait", "equals", "notify", "notifyAll", "toString", "hashCode", "getClass")
    );

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testRightConstructorPresent() throws NoSuchMethodException {
        Assert.assertEquals(1, modelEmpty.getConstructors().length);
        modelEmpty.getConstructor(Integer.class);
    }

    @Test
    public void testIdIsSet()
        throws
            NoSuchMethodException,
            IllegalAccessException,
            InstantiationException,
            InvocationTargetException {
        final Integer id = 5;
        final ModelInterface model = (ModelInterface) modelEmpty.getDeclaredConstructor(Integer.class).newInstance(id);

        Assert.assertEquals(id, model.getId());
    }

    @Test
    public void testAllOtherMethodsFails()
        throws
            NoSuchMethodException,
            IllegalAccessException,
            InstantiationException,
            InvocationTargetException {
        final ModelInterface model = (ModelInterface) modelEmpty.getDeclaredConstructor(Integer.class).newInstance(10);

        for (Method method: modelEmpty.getMethods()) {
            if (skipMethodsWithNames.contains(method.getName())) {
                continue;
            }
            System.out.println(method.getName());
            final Method userMethod = modelEmpty.getMethod(method.getName(), method.getParameterTypes());

            try {
                userMethod.invoke(model, this.getMethodParametersValues(method));
                /**
                 * Method should throw exception
                 */
                Assert.assertTrue(false);
            } catch (InvocationTargetException ite) {
                System.out.println(ite.getCause().toString());
                /**
                 * Exception should be instance of CallMethodOnEmptyModelException
                 */
                Assert.assertTrue(ite.getCause() instanceof CallMethodOnEmptyModelException);
            }
        }
    }

    /**
     * Get actual parameters for method
     *
     * @param method
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object[] getMethodParametersValues(Method method) throws IllegalAccessException, InstantiationException {
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
     *
     * @param type
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object getObjectByType(Class<?> type) throws IllegalAccessException, InstantiationException {
        final Object object;
        if (type == Integer.class) {
            object = 9;
        } else if (type == Double.class) {
            object = 9.5;
        } else if (Modifier.isFinal(type.getModifiers())) {
            object = type.newInstance();
        } else {
            object = Mockito.mock(type);
        }
        return object;
    }
}
