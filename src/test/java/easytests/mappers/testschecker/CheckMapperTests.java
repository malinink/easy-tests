package easytests.mappers.testschecker;

import easytests.config.DatabaseConfig;
import org.apache.ibatis.session.SqlSession;
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
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


/**
 * @author vkpankov
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
public class CheckMapperTests {

    static final String MAPPER_PACKAGE_NAME = "easytests.mappers";

    @Autowired
    private ApplicationContext applicationContext;

    public Set<BeanDefinition> getMappers() throws ClassNotFoundException {
        return new InterfaceComponentProvider().findCandidateComponents(MAPPER_PACKAGE_NAME);
    }

    public Set<BeanDefinition> getMapperTests() throws ClassNotFoundException {

        final ClassPathScanningCandidateComponentProvider provider =
                new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

        return provider.findCandidateComponents(MAPPER_PACKAGE_NAME);
    }

    public SqlSession getMapperSqlSession(Object mapper) throws Exception {

        final InvocationHandler mapperInvocationHandler = Proxy.getInvocationHandler(mapper);
        final Field sqlSessionField = mapperInvocationHandler.getClass().getDeclaredField("sqlSession");
        sqlSessionField.setAccessible(true);
        return (SqlSession) sqlSessionField.get(mapperInvocationHandler);

    }

    public void checkMapperTests(Class mapperClass, Class mapperTestClass, Field mapperField) throws Exception {

        final Object mapperTestInstance = mapperTestClass.newInstance();
        final Object originalMapper = applicationContext.getBean(mapperClass);
        final InterceptInvocationsProxy proxy = new InterceptInvocationsProxy(originalMapper);
        final Object mapperProxy = Proxy.newProxyInstance(
                mapperClass.getClassLoader(),
                new Class[]{mapperClass}, proxy);

        mapperField.setAccessible(true);
        mapperField.set(mapperTestInstance, mapperProxy);

        final SqlSession mapperSqlSession = getMapperSqlSession(originalMapper);

        final Method[] testMethods = mapperTestClass.getMethods();
        for (Method method : testMethods) {

            try {

                final Annotation methodAnnotation = method.getDeclaredAnnotations()[0];
                if (methodAnnotation.annotationType().equals(Test.class)) {

                    ScriptUtils.executeSqlScript(
                            mapperSqlSession.getConnection(),
                            new ClassPathResource("sql/mappersTestData.sql"));

                    mapperSqlSession.clearCache();

                    method.invoke(mapperTestInstance);

                }

            } catch (AssertionError anyAssertionException) {
                continue;
            } catch (Exception anyOtherException) {
                continue;
            }

        }
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

    @Transactional
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
                final Field[] mapperTestFields = mapperTest.getDeclaredFields();
                Field mapperField = null;

                for (Field field: mapperTestFields) {

                    final String fieldPackageName = field.getType().getPackage().getName();

                    if (MAPPER_PACKAGE_NAME.equals(fieldPackageName)) {
                        mapperField = field;
                        break;
                    }
                }

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
