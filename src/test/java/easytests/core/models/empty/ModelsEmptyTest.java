package easytests.core.models.empty;

import easytests.core.models.ModelInterface;
import easytests.core.models.exceptions.CallMethodOnEmptyModelException;
import easytests.core.models.exceptions.CreateEmptyModelWithNullIdException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

/**
 * @author malinink
 */
@RunWith(Parameterized.class)
@SpringBootTest
public class ModelsEmptyTest extends AbstractEmptyTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    public ModelsEmptyTest(Class modelEmptyClass) {
        super(modelEmptyClass);
    }

    @Parameters
    public static List<Object[]> getModelsEmptyClassNames() throws ClassNotFoundException {
        final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
        final Set<BeanDefinition> classes = provider.findCandidateComponents("easytests.core.models.empty");
        final List<Object[]> parametersList = new ArrayList<>();

        for (BeanDefinition bean : classes) {
            final Class<?> emptyModelClass = Class.forName(bean.getBeanClassName());
            if (!AbstractModelEmpty.class.isAssignableFrom(emptyModelClass)) {
                continue;
            }
            parametersList.add(new Object[] {emptyModelClass});
        }
        return parametersList;
    }

    @Test
    public void testConstructorsPresent() throws Exception {
        Assert.assertEquals(2, this.modelEmpty.getConstructors().length);
        this.modelEmpty.getConstructor(Integer.class);
        this.modelEmpty.getConstructor();
    }

    @Test
    public void testConstructorFailsWithNullArgument() throws Throwable {
        exception.expect(CreateEmptyModelWithNullIdException.class);
        try {
            this.modelEmpty.getDeclaredConstructor(Integer.class).newInstance((Integer) null);
        } catch (InvocationTargetException ite) {
            throw ite.getCause();
        }
    }

    @Test
    public void testGetIdInModelEmptyWithIdWorksAsExpected() throws Exception {
        final Integer id = 5;
        final ModelInterface model = (ModelInterface) this.modelEmpty.getDeclaredConstructor(Integer.class).newInstance(id);

        Assert.assertEquals(id, model.getId());
    }

    @Test
    public void testGetIdInModelEmptyWithoutIdThrowsException() throws Exception {
        final ModelInterface model = (ModelInterface) this.modelEmpty.getDeclaredConstructor().newInstance();

        exception.expect(CallMethodOnEmptyModelException.class);
        model.getId();
    }

    @Test
    public void testAllOtherMethodsFailsInModelEmptyWithId() throws Exception {
        final Object model = this.modelEmpty.getDeclaredConstructor(Integer.class).newInstance(1);

        this.testModelTrowsExpectedExceptions(model, CallMethodOnEmptyModelException.class);
    }

    @Test
    public void testAllOtherMethodsFailsInModelEmptyWithoutId() throws Exception {
        final Object model = this.modelEmpty.getDeclaredConstructor().newInstance();

        this.testModelTrowsExpectedExceptions(model, CallMethodOnEmptyModelException.class);
    }

    @Test
    public void testEqualsOnSameClassSameObjects() throws Exception {
        final Object first = this.modelEmpty.getDeclaredConstructor(Integer.class).newInstance(1);
        final Object second = first;

        Assert.assertEquals(first, second);
    }

    @Test
    public void testEqualsOnSameClassEqualsObjects() throws Exception {
        final Object first = this.modelEmpty.getDeclaredConstructor(Integer.class).newInstance(1);
        final Object second = this.modelEmpty.getDeclaredConstructor(Integer.class).newInstance(1);

        Assert.assertEquals(first, second);
    }

    @Test
    public void testEqualsOnSameClassEqualsEmptyObjects() throws Exception {
        final Object first = this.modelEmpty.getDeclaredConstructor().newInstance();
        final Object second = this.modelEmpty.getDeclaredConstructor().newInstance();

        Assert.assertEquals(first, second);
    }

    @Test
    public void testEqualsOnDifferentClassObjects() throws Exception {
        final Object first = this.modelEmpty.getDeclaredConstructor(Integer.class).newInstance(1);
        final Object second = new AbstractModelEmpty(1) { };

        Assert.assertNotEquals(first, second);
    }

    @Test
    public void testEqualsOnDifferentClassEmptyObjects() throws Exception {
        final Object first = this.modelEmpty.getDeclaredConstructor().newInstance();
        final Object second = new AbstractModelEmpty() { };

        Assert.assertNotEquals(first, second);
    }

    @Test
    public void testEqualsOnSameClassDifferentObjects() throws Exception {
        final Object first = this.modelEmpty.getDeclaredConstructor(Integer.class).newInstance(1);
        final Object second = this.modelEmpty.getDeclaredConstructor(Integer.class).newInstance(3);

        Assert.assertNotEquals(first, second);
    }
}
