package easytests.core.mappers;

import easytests.core.mappers.helpers.MapperTestHelper;
import easytests.core.mappers.providers.MapperComponentProvider;
import easytests.core.mappers.providers.MapperTestComponentProvider;
import java.lang.reflect.Field;
import java.util.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.config.BeanDefinition;
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
public class EachMapperHaveOwnTestTest {

    private static final String MAPPER_PACKAGE_NAME = "easytests.core.mappers";

    private Set<BeanDefinition> getMappers() throws ClassNotFoundException {
        final MapperComponentProvider provider = new MapperComponentProvider();
        return provider.findCandidateComponents(MAPPER_PACKAGE_NAME);
    }

    private Set<BeanDefinition> getMappersTests() throws ClassNotFoundException {
        final MapperTestComponentProvider provider = new MapperTestComponentProvider();
        return provider.findCandidateComponents(MAPPER_PACKAGE_NAME);
    }

    @Test
    public void checkEachMapperHaveOwnTest() throws Exception {
        final Set<BeanDefinition> mappers = getMappers();
        final Set<BeanDefinition> mappersTests = getMappersTests();
        final Set<Class> mappersClasses = new HashSet<>();
        final Set<Class> mappersWithTestsClasses = new HashSet<>();

        for (BeanDefinition mapper: mappers) {
            mappersClasses.add(Class.forName(mapper.getBeanClassName()));
        }

        for (BeanDefinition mapperTest: mappersTests) {
            final Class mapperTestClass = Class.forName(mapperTest.getBeanClassName());
            final Field mapperField = MapperTestHelper.findMapperFieldInTest(mapperTestClass);

            Assert.assertNotNull(
                    String.format(
                        "Mapper '%1$s' use unknown mapper, or mapper have no @Mapper annotation",
                            mapperTestClass.getName()
                    ),
                    mapperField
            );
            mappersWithTestsClasses.add(mapperField.getType());
        }

        for (Class mapperClass : mappersClasses) {
            Assert.assertTrue(
                    String.format(
                            "Mapper '%1$s' have no test",
                            mapperClass.getName()
                    ),
                    mappersWithTestsClasses.contains(mapperClass)
            );
        }
    }

}
