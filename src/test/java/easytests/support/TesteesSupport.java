package easytests.support;

import easytests.core.entities.TesteeEntity;
import easytests.core.models.TesteeModelInterface;
import easytests.core.models.empty.QuizModelEmpty;
import org.junit.Assert;
import org.mockito.Mockito;

public class TesteesSupport {

    private static Object[][] fixtures = new Object[][]{
            {
                    1,
                    "FirstName1",
                    "LastName1",
                    "Surname1",
                    301,
                    1
            },
            {
                    2,
                    "FirstName2",
                    "LastName2",
                    "Surname2",
                    302,
                    2
            },
            {
                    3,
                    "FirstName3",
                    "LastName3",
                    "Surname3",
                    303,
                    3
            }

    };

    private static  Object[][] additional = new Object[][]{
            // for insert
            {
                    null,
                    "FirstName5",
                    "LastName5",
                    "Surname5",
                    308,
                    38
            },
            // for update
            {
                    1,
                    "UpdatedFirstName4",
                    "UpdatedLastNme4",
                    "UpdatedSurname4",
                    304,
                    34
            }
    };


    public TesteeEntity getEntityFixtureMock(Integer index) { return this.getEntityMock(fixtures[index]); }

    public TesteeEntity getEntityAdditionalMock(Integer index) { return this.getEntityMock(additional[index]); }

    private TesteeEntity getEntityMock(Object[] data) {
        return this.getEntityMock(
                (Integer) data[0],
                (String) data[1],
                (String) data[2],
                (String) data[3],
                (Integer) data[4],
                (Integer) data[5]
        );
    }

    private TesteeEntity getEntityMock(
            Integer id,
            String firstName,
            String lastName,
            String surName,
            Integer groupNumber,
            Integer quizId
    )
    {
        TesteeEntity testeeEntity = Mockito.mock(TesteeEntity.class);
        Mockito.when(testeeEntity.getId()).thenReturn(id);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surName);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn(groupNumber);
        Mockito.when(testeeEntity.getQuizId()).thenReturn(quizId);
        return testeeEntity;
    }

    public TesteeModelInterface getModelFixtureMock(Integer index) { return this.getModelMock(fixtures[index]); }

    public TesteeModelInterface getModelAdditionalMock(Integer index) { return this.getModelMock(additional[index]); }

    private TesteeModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (String) data[1],
                (String) data[2],
                (String) data[3],
                (Integer) data[4],
                (Integer) data[5]
        );
    }

    private TesteeModelInterface getModelMock(
            Integer id,
            String firstName,
            String lastName,
            String surName,
            Integer groupNumber,
            Integer quizId
             )
    {
        TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        Mockito.when(testeeModel.getId()).thenReturn(id);
        Mockito.when(testeeModel.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeModel.getLastName()).thenReturn(lastName);
        Mockito.when(testeeModel.getSurname()).thenReturn(surName);
        Mockito.when(testeeModel.getGroupNumber()).thenReturn(groupNumber);
        Mockito.when(testeeModel.getQuiz()).thenReturn(new QuizModelEmpty(quizId));
        return testeeModel;
    }

    public void assertEquals(TesteeEntity first, TesteeEntity second) {assertEquals(first, second, false);}

    public void assertEqualsWithoutId(TesteeEntity first, TesteeEntity second) {assertEquals(first, second, true);}

    public void assertEquals(TesteeEntity first, TesteeEntity second, Boolean exceptId){
        if (!exceptId) {
            Assert.assertEquals(first.getId(), second.getId());
        }
        Assert.assertEquals(first.getFirstName(), second.getFirstName());
        Assert.assertEquals(first.getLastName(), second.getLastName());
        Assert.assertEquals(first.getSurname(), second.getSurname());
        Assert.assertEquals(first.getGroupNumber(), second.getGroupNumber());
        Assert.assertEquals(first.getQuizId(), second.getQuizId());
    }


    public void assertEquals(TesteeModelInterface first, TesteeEntity second){
        Assert.assertEquals(first.getId(), second.getId());
        Assert.assertEquals(first.getFirstName(), second.getFirstName());
        Assert.assertEquals(first.getLastName(), second.getLastName());
        Assert.assertEquals(first.getSurname(), second.getSurname());
        Assert.assertEquals(first.getGroupNumber(), second.getGroupNumber());
    }

    public void assertEquals(TesteeEntity first, TesteeModelInterface second)
    {
        assertEquals(second,first);
        Assert.assertEquals(new QuizModelEmpty(first.getQuizId()), second.getQuiz());
    }
  
      public void assertNotEqualsWithoutId(TesteeEntity first, TesteeEntity second){
        Assert.assertNotEquals(first.getFirstName(), second.getFirstName());
        Assert.assertNotEquals(first.getLastName(), second.getLastName());
        Assert.assertNotEquals(first.getSurname(), second.getSurname());
        Assert.assertNotEquals(first.getQuizId(), second.getQuizId());
        Assert.assertNotEquals(first.getGroupNumber(), second.getGroupNumber());
    }
}
