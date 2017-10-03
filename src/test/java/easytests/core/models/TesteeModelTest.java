package easytests.core.models;

import easytests.core.entities.TesteeEntity;
import easytests.core.models.empty.QuizModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.meanbean.test.*;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteeModelTest {
    @Test
    public void testCommon() throws Exception {
        Configuration configuration = new ConfigurationBuilder()
                .ignoreProperty("quiz")
                .build();
        new BeanTester().testBean(TesteeModel.class, configuration);
    }

    @Test
    public void testMap() throws Exception {
        final Integer testeeId = 3;
        final String firstName = "FirstName";
        final String lastName = "LastName";
        final String surname = "Surname";
        final Integer groupNumber = 307;
        final Integer quizId =5;
        final TesteeEntity testeeEntity = Mockito.mock(TesteeEntity.class);

        Mockito.when(testeeEntity.getId()).thenReturn(testeeId);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surname);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn(groupNumber);
        Mockito.when(testeeEntity.getQuizId()).thenReturn(quizId);

        final TesteeModel testeeModel = new TesteeModel();
        testeeModel.map(testeeEntity);

        Assert.assertEquals(testeeId, testeeModel.getId());
        Assert.assertEquals(firstName, testeeModel.getFirstName());
        Assert.assertEquals(lastName, testeeModel.getLastName());
        Assert.assertEquals(surname, testeeModel.getSurname());
        Assert.assertEquals(groupNumber,testeeModel.getGroupNumber());
        Assert.assertEquals(new QuizModelEmpty(quizId), testeeModel.getQuiz());
    }

}
