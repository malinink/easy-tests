package easytests.core.entities;

import easytests.core.models.TesteeModelInterface;
import easytests.core.models.empty.QuizModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

/**
 * @author DoZor-80
 */
public class TesteeEntityTest {
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(TesteeEntity.class);
        new EqualsMethodTester().testEqualsMethod(TesteeEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(TesteeEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer testeeId = 3;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String surname = "Surname";
        final Integer groupNumber = 307;
        final Integer quizId = 3;

        final TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        Mockito.when(testeeModel.getId()).thenReturn(testeeId);
        Mockito.when(testeeModel.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeModel.getLastName()).thenReturn(lastName);
        Mockito.when(testeeModel.getSurname()).thenReturn(surname);
        Mockito.when(testeeModel.getGroupNumber()).thenReturn(groupNumber);
        Mockito.when(testeeModel.getQuiz()).thenReturn(new QuizModelEmpty(quizId));

        final TesteeEntity testeeEntity = new TesteeEntity();
        testeeEntity.map(testeeModel);

        Assert.assertEquals(testeeId, testeeEntity.getId());
        Assert.assertEquals(firstName, testeeEntity.getFirstName());
        Assert.assertEquals(lastName, testeeEntity.getLastName());
        Assert.assertEquals(surname, testeeEntity.getSurname());
        Assert.assertEquals(groupNumber, testeeEntity.getGroupNumber());
        Assert.assertEquals(quizId, testeeEntity.getQuizId());
    }
}
