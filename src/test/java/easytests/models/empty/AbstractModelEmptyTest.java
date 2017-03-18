package easytests.models.empty;

import easytests.models.ModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import easytests.models.exceptions.CreateEmptyModelWithNullIdException;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
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
    public void testDefaultConstructorPresent() throws Exception {
        Assert.assertEquals(2, modelEmpty.getConstructors().length);
        modelEmpty.getConstructor();
    }

    @Test
    public void testIntegerConstructorPresent() throws Exception {
        Assert.assertEquals(2, modelEmpty.getConstructors().length);
        modelEmpty.getConstructor(Integer.class);
    }

    @Test
    public void testConstructorFailsWithNullArgument() throws Throwable {
        exception.expect(CreateEmptyModelWithNullIdException.class);
        try {
            modelEmpty.getDeclaredConstructor(Integer.class).newInstance((Integer) null);
        } catch (InvocationTargetException ite) {
            throw ite.getCause();
        }
    }

    @Test
    public void testIdIsSet() throws Exception {
        final Integer id = 5;
        final ModelInterface model = (ModelInterface) modelEmpty.getDeclaredConstructor(Integer.class).newInstance(id);

        Assert.assertEquals(id, model.getId());
    }

    @Test
    public void testGetIdOnModelWithoutIdThrowsException() throws Exception {
        final ModelInterface model = (ModelInterface) modelEmpty.getDeclaredConstructor().newInstance();

        exception.expect(CallMethodOnEmptyModelException.class);
        model.getId();
    }

    @Test
    public void testAllOtherMethodsFailsOnEmptyModelWithId() throws Exception {
        final ModelInterface model = (ModelInterface) modelEmpty.getDeclaredConstructor(Integer.class).newInstance(10);

        this.testModelTrowsExpectedExceptions(model);
    }

    @Test
    public void testAllOtherMethodsFailsOnEmptyModelWithoutId() throws Exception {
        final ModelInterface model = (ModelInterface) modelEmpty.getDeclaredConstructor().newInstance();

        this.testModelTrowsExpectedExceptions(model);
    }

    /**
     * Test that each method which are not in skipList throw expected Exception
     * @param model
     */
    private void testModelTrowsExpectedExceptions(ModelInterface model) throws Exception {
        for (Method method: modelEmpty.getMethods()) {
            if (skipMethodsWithNames.contains(method.getName())) {
                continue;
            }
            final Method userMethod = modelEmpty.getMethod(method.getName(), method.getParameterTypes());

            try {
                userMethod.invoke(model, this.getMethodParametersValues(method));
                throw new Exception("Method should throw exception");
            } catch (InvocationTargetException ite) {
                if (!(ite.getCause() instanceof CallMethodOnEmptyModelException)) {
                    throw new Exception("Exception should be instance of CallMethodOnEmptyModelException");
                }
            }
        }
    }

    /**
     * Get actual parameters for method
     *
     * @param method
     * @return
     */
    private Object[] getMethodParametersValues(Method method) throws Exception {
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
     */
    private Object getObjectByType(Class<?> type) throws Exception {
        final Object object;
        if (type == Integer.class) {
            object = 9;
        } else if (type == Double.class) {
            object = 9.5;
        } else if (type == Boolean.class) {
            object = true;
        } else if (Modifier.isFinal(type.getModifiers())) {
            object = type.newInstance();
        } else {
            object = Mockito.mock(type);
        }
        return object;
    }
}
