package easytests.models;

import easytests.entities.QuizEntity;
import easytests.models.empty.IssueModelEmpty;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.TesteeModelEmpty;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

import java.time.LocalDateTime;

/**
 * @author fortyways
 */
public class QuizModelTest {

    @Ignore
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(SubjectModel.class);
        new EqualsMethodTester().testEqualsMethod(SubjectModel.class);
        new HashCodeMethodTester().testHashCodeMethod(SubjectModel.class);

    }

    @Test
    public void testMap() throws Exception {

        final Integer quizId = 5;
        final String quizInviteCode= "Test code";
        final LocalDateTime quizStartedAt = LocalDateTime.of(2000,1,1,1,1,1);
        final LocalDateTime quizFinishedAt = LocalDateTime.of(2001,1,1,1,1,1);
        final Boolean quizCodeExpired =false;
        final Integer quizIssueId = 3;

        final QuizEntity quizEntity = Mockito.mock(QuizEntity.class);
        Mockito.when(quizEntity.getId()).thenReturn(quizId);
        Mockito.when(quizEntity.getInviteCode()).thenReturn(quizInviteCode);
        Mockito.when(quizEntity.getStartedAt()).thenReturn(quizStartedAt);
        Mockito.when(quizEntity.getFinishedAt()).thenReturn(quizFinishedAt);
        Mockito.when(quizEntity.getCodeExpired()).thenReturn(quizCodeExpired);
        Mockito.when(quizEntity.getIssueId()).thenReturn(quizIssueId);

        final QuizModelInterface quizModel = new QuizModel();
        quizModel.map(quizEntity);

        Assert.assertEquals(quizId, quizModel.getId());
        Assert.assertEquals(quizStartedAt, quizModel.getStartedAt());
        Assert.assertEquals(quizFinishedAt, quizModel.getFinishedAt());
        Assert.assertEquals(quizCodeExpired, quizModel.getCodeExpired());
        Assert.assertEquals(new ModelsListEmpty(), quizModel.getPoints());
        Assert.assertEquals(new IssueModelEmpty(quizIssueId), quizModel.getIssue());
        Assert.assertEquals(new TesteeModelEmpty(), quizModel.getTestee());

    }

}
