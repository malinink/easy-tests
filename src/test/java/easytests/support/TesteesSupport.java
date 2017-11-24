package easytests.support;

import easytests.core.entities.TesteeEntity;
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

    public void assertEquals(TesteeEntity first, TesteeEntity second){
                Assert.assertEquals(first.getId(), second.getId());
                Assert.assertEquals(first.getFirstName(), second.getFirstName());
                Assert.assertEquals(first.getLastName(), second.getLastName());
                Assert.assertEquals(first.getSurname(), second.getSurname());
                Assert.assertEquals(first.getQuizId(), second.getQuizId());
                Assert.assertEquals(first.getGroupNumber(), second.getGroupNumber());
    }

    public void assertNotEqualsWithoutId(TesteeEntity first, TesteeEntity second){
        Assert.assertNotEquals(first.getFirstName(), second.getFirstName());
        Assert.assertNotEquals(first.getLastName(), second.getLastName());
        Assert.assertNotEquals(first.getSurname(), second.getSurname());
        Assert.assertNotEquals(first.getQuizId(), second.getQuizId());
        Assert.assertNotEquals(first.getGroupNumber(), second.getGroupNumber());
    }

    public void assertEqualsWithoutId(TesteeEntity first, TesteeEntity second){
        Assert.assertEquals(first.getFirstName(), second.getFirstName());
        Assert.assertEquals(first.getLastName(), second.getLastName());
        Assert.assertEquals(first.getSurname(), second.getSurname());
        Assert.assertEquals(first.getQuizId(), second.getQuizId());
        Assert.assertEquals(first.getGroupNumber(), second.getGroupNumber());
    }


}
