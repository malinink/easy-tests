package easytests.support;

import easytests.core.entities.TesteeEntity;
import org.junit.Assert;
import easytests.core.entities.SubjectEntity;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.empty.IssueStandardModelEmpty;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.UserModelEmpty;
import org.mockito.Mockito;

public class TesteesSupport {

    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    "FirstName1",
                    "LastName1",
                    "Surname1",
                    1
            }

    };

    public TesteeEntity getTesteeFixtureMock(Integer index) {
        return this.getTesteeMock(fixtures[index]);

    }

    private TesteeEntity getTesteeMock(Object[] data) {
        return this.getTesteeMock(
                (Integer) data[0],
                (String) data[1],
                (String) data[2],
                (String) data[3],
                (Integer) data[4],
                (Integer) data[5]
        );
    }

    private TesteeEntity getTesteeMock(
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
    }


}
