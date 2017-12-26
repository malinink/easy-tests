package easytests.support;

import easytests.core.entities.IssueStandardEntity;
import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.SubjectModelEmpty;
import org.junit.Assert;
import org.mockito.Mockito;

public class IssueStandardSupport {
    protected static Object[][] fixtures = new Object[][]{
            {
                    1,
                    300,
                    30,
                    1
            },
            {
                    2,
                    null,
                    15,
                    3
            },
            {
                    5,
                    3600,
                    30,
                    3
            }
    };

    protected static Object[][] additional = new Object[][]{
            {
                    // for insert entity
                    null,
                    3600,
                    20,
                    2
            },
            {
                    // for update entity with id = 1
                    1,
                    3600,
                    20,
                    2
            },
    };

    public IssueStandardEntity getEntityFixtureMock(Integer index){
        return this.getEntityMock(fixtures[index]);
    }

    public IssueStandardEntity getEntityAdditionalMock(Integer index){
        return this.getEntityMock(additional[index]);
    }

    private IssueStandardEntity getEntityMock(Object[] data){
        return this.getEntityMock(
                (Integer) data[0],
                (Integer) data[1],
                (Integer) data[2],
                (Integer) data[3]
        );
    }

    private IssueStandardEntity getEntityMock(
            Integer id,
            Integer timeLimit,
            Integer questionsNumber,
            Integer subjectId
    ){
        final IssueStandardEntity issueStandardEntity = Mockito.mock(IssueStandardEntity.class);

        Mockito.when(issueStandardEntity.getId()).thenReturn(id);
        Mockito.when(issueStandardEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardEntity.getQuestionsNumber()).thenReturn(questionsNumber);
        Mockito.when(issueStandardEntity.getSubjectId()).thenReturn(subjectId);
        return issueStandardEntity;
    }

    public IssueStandardModelInterface getModelFixtureMock(Integer index){
        return this.getModelMock(fixtures[index]);
    }

    private IssueStandardModelInterface getModelMock(Object[] data) {
        return this.getModelMock(
                (Integer) data[0],
                (Integer) data[1],
                (Integer) data[2],
                (Integer) data[3]
        );
    }

    private IssueStandardModelInterface getModelMock(
            Integer id,
            Integer timeLimit,
            Integer questionsNumber,
            Integer subjectId
    ) {
        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(id);
        Mockito.when(issueStandardModel.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardModel.getQuestionsNumber()).thenReturn(questionsNumber);
        Mockito.when(issueStandardModel.getTopicPriorities()).thenReturn(new ModelsListEmpty());
        Mockito.when(issueStandardModel.getQuestionTypeOptions()).thenReturn(new ModelsListEmpty());
        Mockito.when(issueStandardModel.getSubject()).thenReturn(new SubjectModelEmpty(subjectId));
        return issueStandardModel;
    }

    public void assertEquals(IssueStandardEntity expected, IssueStandardEntity actual){
                assertEquals(expected, actual, false);
    }

    public void assertEquals(IssueStandardModelInterface expected, IssueStandardEntity actual){

        Assert.assertEquals(expected.getId(), actual.getId());
        Assert.assertEquals(expected.getTimeLimit(), actual.getTimeLimit());
        Assert.assertEquals(expected.getQuestionsNumber(), actual.getQuestionsNumber());
        Assert.assertEquals(expected.getSubject().getId(), actual.getSubjectId());
    }

    public void assertEqualsWithoutId(IssueStandardEntity expected, IssueStandardEntity actual){
        assertEquals(expected, actual, true);
    }

    public void assertNotEquals (IssueStandardEntity expected, IssueStandardEntity actual){
        assertNotEquals(expected, actual, false);
    }

    public void assertNotEqualsWithoutId(IssueStandardEntity expected, IssueStandardEntity actual){
        assertNotEquals(expected, actual, true);
    }


    private void assertEquals(IssueStandardEntity expected, IssueStandardEntity actual, Boolean expectId){
        if(!expectId){
            Assert.assertEquals(expected.getId(),actual.getId());
        }
        Assert.assertEquals(expected.getTimeLimit(), actual.getTimeLimit());
        Assert.assertEquals(expected.getQuestionsNumber(), actual.getQuestionsNumber());
        Assert.assertEquals(expected.getSubjectId(), actual.getSubjectId());
    }

    private void assertNotEquals(IssueStandardEntity expected, IssueStandardEntity actual, Boolean expectId){
        if(!expectId){
            Assert.assertNotEquals(expected.getId(),actual.getId());
        }
        Assert.assertNotEquals(expected.getTimeLimit(), actual.getTimeLimit());
        Assert.assertNotEquals(expected.getQuestionsNumber(), actual.getQuestionsNumber());
        Assert.assertNotEquals(expected.getSubjectId(), actual.getSubjectId());
    }

}
