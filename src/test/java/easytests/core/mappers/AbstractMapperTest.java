package easytests.core.mappers;

import easytests.core.mappers.helpers.MapperTestHelper;
import easytests.core.mappers.proxy.InterceptInvocationsProxy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractMapperTest {

    private static Boolean initComplete = false;

    private static Object mapperProxy;

    private static Field mapperField;

    @Before
    public final void setup() throws Exception {
        final Field currentMapperField = MapperTestHelper.findMapperFieldInTest(this.getClass());

        if (initComplete && currentMapperField.equals(mapperField)) {
            mapperField.set(this, mapperProxy);
            return;
        }

        mapperField = currentMapperField;
        mapperField.setAccessible(true);

        mapperProxy = Proxy.newProxyInstance(
                mapperField.getType().getClassLoader(),
                new Class[]{mapperField.getType()},
                new InterceptInvocationsProxy(mapperField.get(this))
        );

        mapperField.set(this, mapperProxy);
        initComplete = true;
    }

    @Test
    public final void testZ() throws Exception {
        Assert.assertTrue(initComplete);

        final InterceptInvocationsProxy proxy = (InterceptInvocationsProxy) Proxy.getInvocationHandler(mapperField.get(this));

        for (Method method : mapperField.getType().getMethods()) {
            Assert.assertTrue(
                    String.format(
                            "Method '%1$s' from mapper '%2$s' has not been invoked",
                            method.getName(),
                            mapperField.getType().getName()
                    ),
                    proxy.getMethods().contains(method)
            );
        }
    }

}
