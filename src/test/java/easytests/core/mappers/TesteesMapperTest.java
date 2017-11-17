package easytests.core.mappers;

import easytests.core.entities.TesteeEntity;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author DoZor-80
 */
public class TesteesMapperTest extends AbstractMapperTest {

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
        Assert.assertEquals((long) 1, (long) testee.getQuizId());
    }

    @Test
    public void testFindByQuizId() throws Exception {
        final TesteeEntity testee = this.testeesMapper.findByQuizId(3);

        Assert.assertEquals((long) 3, (long) testee.getId());
        Assert.assertEquals("FirstName3", testee.getFirstName());
        Assert.assertEquals("LastName3", testee.getLastName());
        Assert.assertEquals("Surname3", testee.getSurname());
        Assert.assertEquals((long) 3, (long) testee.getQuizId());
    }

    @Test
    public void testInsert() throws Exception{
        final ArgumentCaptor<Integer> id = ArgumentCaptor.forClass(Integer.class);
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String surname = "Surname";
        final Integer groupNumber = 307;
        final Integer quizId = 19;

        TesteeEntity testeeEntity = Mockito.mock(TesteeEntity.class);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surname);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn(groupNumber);
        Mockito.when(testeeEntity.getQuizId()).thenReturn(quizId);

        this.testeesMapper.insert(testeeEntity);

        verify(testeeEntity, times(1)).setId(id.capture());

        Assert.assertNotNull(id.getValue());

        testeeEntity = this.testeesMapper.find(id.getValue());
        Assert.assertEquals(id.getValue(), testeeEntity.getId());
        Assert.assertEquals(firstName, testeeEntity.getFirstName());
        Assert.assertEquals(lastName, testeeEntity.getLastName());
        Assert.assertEquals(surname, testeeEntity.getSurname());
        Assert.assertEquals(groupNumber, testeeEntity.getGroupNumber());
        Assert.assertEquals(quizId, testeeEntity.getQuizId());
    }

    @Test
    public void testUpdate() throws Exception{
        final Integer id = 1;
        final String firstName = "NewFirstName";
        final String lastName = "NewLastName";
        final String surname = "NewSurname";
        final Integer groupNumber = 308;
        final Integer quizId = 19;

        TesteeEntity testeeEntity = this.testeesMapper.find(id);
        Assert.assertNotNull(testeeEntity);
        Assert.assertEquals(id, testeeEntity.getId());
        Assert.assertNotEquals(firstName, testeeEntity.getFirstName());
        Assert.assertNotEquals(lastName, testeeEntity.getLastName());
        Assert.assertNotEquals(surname, testeeEntity.getSurname());
        Assert.assertNotEquals(groupNumber, testeeEntity.getGroupNumber());
        Assert.assertNotEquals(quizId, testeeEntity.getQuizId());

        testeeEntity = Mockito.mock(TesteeEntity.class);
        Mockito.when(testeeEntity.getId()).thenReturn(id);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surname);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn(groupNumber);
        Mockito.when(testeeEntity.getQuizId()).thenReturn(quizId);

        this.testeesMapper.update(testeeEntity);

        testeeEntity = this.testeesMapper.find(id);
        Assert.assertEquals(id, testeeEntity.getId());
        Assert.assertEquals(firstName, testeeEntity.getFirstName());
        Assert.assertEquals(lastName, testeeEntity.getLastName());
        Assert.assertEquals(surname, testeeEntity.getSurname());
        Assert.assertEquals(groupNumber,testeeEntity.getGroupNumber());
        Assert.assertEquals(quizId, testeeEntity.getQuizId());
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
