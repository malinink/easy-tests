package easytests.support;

import easytests.core.entities.TesteeEntity;
import easytests.core.models.TesteeModelInterface;
import easytests.core.models.empty.QuizModelEmpty;
import org.junit.Assert;
import org.mockito.Mockito;

public class TesteesSupport {

    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    "FirstName1",
                    "LastName1",
                    "Surname1",
                    301,
                    1
            }

    };

    public TesteeEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);

    }

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
    ){
        TesteeEntity testeeEntity = Mockito.mock(TesteeEntity.class);
        Mockito.when(testeeEntity.getId()).thenReturn(id);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surName);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn(groupNumber);
        Mockito.when(testeeEntity.getQuizId()).thenReturn(quizId);
        return testeeEntity;
    }

    public TesteeModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

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
    ){
        TesteeModelInterface testeeModel = Mockito.mock(TesteeModelInterface.class);
        Mockito.when(testeeModel.getId()).thenReturn(id);
        Mockito.when(testeeModel.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeModel.getLastName()).thenReturn(lastName);
        Mockito.when(testeeModel.getSurname()).thenReturn(surName);
        Mockito.when(testeeModel.getGroupNumber()).thenReturn(groupNumber);
        Mockito.when(testeeModel.getQuiz()).thenReturn(new QuizModelEmpty(quizId));
        return testeeModel;
    }

    public void assertEquals(TesteeEntity first, TesteeEntity second){
                Assert.assertEquals(first.getId(), second.getId());
                Assert.assertEquals(first.getFirstName(), second.getFirstName());
                Assert.assertEquals(first.getLastName(), second.getLastName());
                Assert.assertEquals(first.getSurname(), second.getSurname());
                //Assert.assertEquals(first.getGroupNumber(), second.getGroupNumber());
                Assert.assertEquals(first.getQuizId(), second.getQuizId());

    }

    public void assertEquals(TesteeModelInterface first, TesteeEntity second){
        Assert.assertEquals(first.getId(), second.getId());
        Assert.assertEquals(first.getFirstName(), second.getFirstName());
        Assert.assertEquals(first.getLastName(), second.getLastName());
        Assert.assertEquals(first.getSurname(), second.getSurname());
        Assert.assertEquals(first.getGroupNumber(), second.getGroupNumber());

    }


}
