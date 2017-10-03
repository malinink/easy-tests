package easytests.core.models;

import easytests.core.entities.IssueStandardEntity;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.SubjectModelEmpty;
import org.junit.Assert;
import org.junit.Test;
import org.meanbean.test.*;
import org.mockito.Mockito;

/**
 * @author SingularityA
 */
public class IssueStandardModelTest {

    @Test
    public void testCommon() throws Exception {
        Configuration configuration = new ConfigurationBuilder()
                .ignoreProperty("topicPriorities")
                .ignoreProperty("questionTypeOptions")
                .ignoreProperty("subject")
                .build();
        new BeanTester().testBean(IssueStandardModel.class, configuration);
    }

    @Test
    public void testMap() {
        final Integer id = 5;
        final Integer timeLimit = 3600;
        final Integer questionsNumber = 30;
        final Integer subjectId = 3;

        final IssueStandardEntity issueStandardEntity = Mockito.mock(IssueStandardEntity.class);
        Mockito.when(issueStandardEntity.getId()).thenReturn(id);
        Mockito.when(issueStandardEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardEntity.getQuestionsNumber()).thenReturn(questionsNumber);
        Mockito.when(issueStandardEntity.getSubjectId()).thenReturn(subjectId);

        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.map(issueStandardEntity);

        Assert.assertEquals(id, issueStandardModel.getId());
        Assert.assertEquals(timeLimit, issueStandardModel.getTimeLimit());
        Assert.assertEquals(questionsNumber, issueStandardModel.getQuestionsNumber());
        Assert.assertEquals(new ModelsListEmpty(), issueStandardModel.getTopicPriorities());
        Assert.assertEquals(new ModelsListEmpty(), issueStandardModel.getQuestionTypeOptions());
        Assert.assertEquals(new SubjectModelEmpty(subjectId), issueStandardModel.getSubject());
    }
}
