package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.Testee;
import easytests.entities.TesteeInterface;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import java.util.List;

/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class TesteesMapperTest {
    @Autowired
    private TesteesMapper testeesMapper;

    @Test
    public void testFindAll() throws Exception {
        List<Testee> testees = this.testeesMapper.findAll();
        Assert.assertNotNull(testees);
        Assert.assertEquals(3, testees.size());
    }

    @Test
    public void testFind() throws Exception {
        final TesteeInterface testee = this.testeesMapper.find(1);

        Assert.assertEquals((long) 1, (long) testee.getId());
        Assert.assertEquals("FirstName1", testee.getFirstName());
        Assert.assertEquals("LastName1", testee.getLastName());
        Assert.assertEquals("Surname1", testee.getSurname());
    }

    @Test
    public void testInsert() throws Exception{
        final Testee testee = new Testee();
        testee.setFirstName("FirstName");
        testee.setLastName("LastName");
        testee.setSurname("Surname");
        testee.setGroupNumber(307);
        testeesMapper.insert(testee);

        final Testee readTestee = testeesMapper.find(testee.getId());
        Assert.assertNotNull(readTestee);
    }

    @Test
    public void testUpdate() throws Exception{
        final TesteeInterface testee = this.testeesMapper.find(2);
        this.testeesMapper.update(testee);

        final Testee readTestee = testeesMapper.find(testee.getId());
        Assert.assertEquals((Integer)2,readTestee.getId());
    }

    @Test
    public void testDelete() throws Exception{
        Testee testee = this.testeesMapper.find(1);
        Assert.assertNotNull(testee);
        this.testeesMapper.delete(testee);
        testee = this.testeesMapper.find(1);
        Assert.assertNull(testee);
    }

}
