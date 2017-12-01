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


    public TesteeEntity getEntityFixtureMock(Integer index) {
        return this.getEntityMock(fixtures[index]);
    }

    public TesteeEntity getEntityAdditionalMock(Integer index) {
        return this.getEntityMock(additional[index]);
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

    public TesteeModelInterface getModelFixtureMock(Integer index) {
        return this.getModelMock(fixtures[index]);
    }

    public TesteeModelInterface getModelAdditionalMock(Integer index) {
        return this.getModelMock(additional[index]);
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

    public void assertEquals(TesteeEntity expected, TesteeEntity actual) {
        assertEquals(expected, actual, false);
    }

    public void assertEqualsWithoutId(TesteeEntity expected, TesteeEntity actual) {
        assertEquals(expected, actual, true);
    }

    public void assertEquals(TesteeEntity expected, TesteeEntity actual, Boolean exceptId) {
        if (!exceptId) {
            Assert.assertEquals(expected.getId(), actual.getId());
        }
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getSurname(), actual.getSurname());
        Assert.assertEquals(expected.getGroupNumber(), actual.getGroupNumber());
        Assert.assertEquals(expected.getQuizId(), actual.getQuizId());
    }

    public void assertNotEquals(TesteeEntity expected, TesteeEntity actual) {
        assertNotEquals(expected, actual, false);
    }

    public void assertNotEqualsWithoutId(TesteeEntity expected, TesteeEntity actual) {
        assertNotEquals(expected, actual, true);
    }

    public void assertNotEquals(TesteeEntity expected, TesteeEntity actual, Boolean exceptId){
        if (!exceptId) {
            Assert.assertNotEquals(expected.getId(), actual.getId());
        }
        Assert.assertNotEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertNotEquals(expected.getLastName(), actual.getLastName());
        Assert.assertNotEquals(expected.getSurname(), actual.getSurname());
        Assert.assertNotEquals(expected.getQuizId(), actual.getQuizId());
        Assert.assertNotEquals(expected.getGroupNumber(), actual.getGroupNumber());
    }

    public void assertEquals(TesteeModelInterface expected, TesteeEntity actual){
        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assert.assertEquals(expected.getLastName(), actual.getLastName());
        Assert.assertEquals(expected.getSurname(), actual.getSurname());
        Assert.assertEquals(expected.getGroupNumber(), actual.getGroupNumber());
    }

    public void assertEquals(TesteeEntity expected, TesteeModelInterface actual)
    {
        assertEquals(actual,expected);
        Assert.assertEquals(new QuizModelEmpty(expected.getQuizId()), actual.getQuiz());
    }
}
