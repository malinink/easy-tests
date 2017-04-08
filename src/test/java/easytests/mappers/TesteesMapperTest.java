package easytests.mappers;

import easytests.config.DatabaseConfig;
import easytests.entities.TesteeEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        final List<TesteeEntity> testeesEntities = this.testeesMapper.findAll();
        Assert.assertEquals(3, testeesEntities.size());
    }

    @Test
    public void testFind() throws Exception {
        final TesteeEntity testee = this.testeesMapper.find(1);
        Assert.assertEquals((long) 1, (long) testee.getId());
        Assert.assertEquals("FirstName1", testee.getFirstName());
        Assert.assertEquals("LastName1", testee.getLastName());
        Assert.assertEquals("Surname1", testee.getSurname());
    }

    @Test
    public void testInsert() throws Exception{
        final Integer id = this.testeesMapper.findAll().size() + 1;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String surname = "Surname";
        final Integer groupNumber = 307;

        TesteeEntity testeeEntity = Mockito.mock(TesteeEntity.class);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surname);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn((groupNumber));

        this.testeesMapper.insert(testeeEntity);

        verify(testeeEntity, times(1)).setId(id);

        testeeEntity = this.testeesMapper.find(id);
        Assert.assertEquals(id, testeeEntity.getId());
        Assert.assertEquals(firstName, testeeEntity.getFirstName());
        Assert.assertEquals(lastName, testeeEntity.getLastName());
        Assert.assertEquals(surname, testeeEntity.getSurname());
        Assert.assertEquals(groupNumber, testeeEntity.getGroupNumber());
    }

    @Test
    public void testUpdate() throws Exception{
        final Integer id = 1;
        final String firstName = "NewFirstName";
        final String lastName = "NewLastName";
        final String surname = "NewSurname";
        final Integer groupNumber = 308;

        TesteeEntity testeeEntity = this.testeesMapper.find(id);
        Assert.assertNotNull(testeeEntity);
        Assert.assertEquals(id, testeeEntity.getId());
        Assert.assertNotEquals(firstName, testeeEntity.getFirstName());
        Assert.assertNotEquals(lastName, testeeEntity.getLastName());
        Assert.assertNotEquals(surname, testeeEntity.getSurname());
        Assert.assertNotEquals(groupNumber, testeeEntity.getGroupNumber());

        testeeEntity = Mockito.mock(TesteeEntity.class);
        Mockito.when(testeeEntity.getId()).thenReturn(id);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surname);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn(groupNumber);

        this.testeesMapper.update(testeeEntity);

        testeeEntity = this.testeesMapper.find(id);
        Assert.assertEquals(id, testeeEntity.getId());
        Assert.assertEquals(firstName, testeeEntity.getFirstName());
        Assert.assertEquals(lastName, testeeEntity.getLastName());
        Assert.assertEquals(surname, testeeEntity.getSurname());
        Assert.assertEquals(groupNumber,testeeEntity.getGroupNumber());
    }

    @Test
    public void testDelete() throws Exception{
        TesteeEntity testeeEntity= this.testeesMapper.find(1);
        Assert.assertNotNull(testeeEntity);

        this.testeesMapper.delete(testeeEntity);
        testeeEntity = this.testeesMapper.find(1);
        Assert.assertNull(testeeEntity);
    }

}
