package easytests.mappers.testschecker;

import easytests.config.DatabaseConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author vkpankov
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
public class CheckMappersTests {

    private static final String MAPPER_PACKAGE_NAME = "easytests.mappers";

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    private Set<BeanDefinition> getMappers() throws ClassNotFoundException {
        return new InterfaceComponentProvider().findCandidateComponents(MAPPER_PACKAGE_NAME);
    }

    private Set<BeanDefinition> getMapperTests() throws ClassNotFoundException {
        final ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

        return provider.findCandidateComponents(MAPPER_PACKAGE_NAME);
    }

    private void resetTestData() throws Exception {
        final SqlSession sqlSession = sqlSessionFactory.openSession();

        ScriptUtils.executeSqlScript(
                sqlSession.getConnection(),
                new ClassPathResource("sql/mappersTestData.sql"));

        sqlSession.close();
    }

    private Field findMapperFieldInTest(Class test) throws Exception {
        final Field[] mapperTestFields = test.getDeclaredFields();
        Field mapperField = null;

        for (Field field: mapperTestFields) {

            final Package fieldPackage = field.getType().getPackage();
            if (fieldPackage == null) {
                continue;
            }

            final String fieldPackageName = field.getType().getPackage().getName();

            if (MAPPER_PACKAGE_NAME.equals(fieldPackageName)) {
                mapperField = field;
                break;
            }
        }
        return mapperField;
    }

    private void invokeTestMethods(Object test) throws Exception {

        final Method[] testMethods = test.getClass().getMethods();
        for (Method method : testMethods) {

            final Annotation[] annotations = method.getDeclaredAnnotations();
            if (annotations.length == 0) {
                continue;
            }
            if (annotations[0].annotationType().equals(Test.class)) {

                resetTestData();
                method.invoke(test);

            }
        }
    }

    private void checkMapperTests(Class mapperClass, Class mapperTestClass, Field mapperField) throws Exception {
        final Object mapperTestInstance = mapperTestClass.newInstance();
        final Object originalMapper = applicationContext.getBean(mapperClass);
        final InterceptInvocationsProxy proxy = new InterceptInvocationsProxy(originalMapper);
        final Object mapperProxy = Proxy.newProxyInstance(
                mapperClass.getClassLoader(),
                new Class[]{mapperClass}, proxy);

        mapperField.setAccessible(true);
        mapperField.set(mapperTestInstance, mapperProxy);

        invokeTestMethods(mapperTestInstance);

        final Method[] mapperMethods = mapperClass.getMethods();
        final List<Method> calledMethodsList = proxy.getMethodList();
        for (Method method : mapperMethods) {

            boolean founded = false;

            for (Method calledMethod : calledMethodsList) {
                if (calledMethod.getName().equals(method.getName())) {
                    founded = true;
                    break;
                }
            }

            Assert.assertTrue(String.format("Method '%1$s' from mapper '%2$s' has not been invoked",
                    method.getName(), mapperClass.getName()), founded);
        }
    }

    @Test
    public void checkAllMappers() throws Exception {
        final Set<BeanDefinition> mappers = getMappers();
        final Set<BeanDefinition> mapperTests = getMapperTests();

        for (Object mapperBean: mappers) {

            Class currentMapperTest = null;
            Field currentMapperAutowiredField = null;

            final Class mapper = Class.forName(((BeanDefinition) mapperBean).getBeanClassName());

            for (Object mapperTestBean: mapperTests) {

                final Class mapperTest = Class.forName(((BeanDefinition) mapperTestBean).getBeanClassName());

                final Field mapperField = findMapperFieldInTest(mapperTest);

                if (mapperField == null) {
                    continue;
                }

                if (mapperField.getType() == mapper) {
                    currentMapperTest = mapperTest;
                    currentMapperAutowiredField = mapperField;
                    break;
                }

            }

            Assert.assertNotNull("Couldn't find test for mapper " + mapper.getName(), currentMapperTest);

            checkMapperTests(mapper, currentMapperTest, currentMapperAutowiredField);
        }
    }
}
