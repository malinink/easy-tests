package easytests.entities;

import easytests.models.QuizModelInterface;
import easytests.models.empty.IssueModelEmpty;
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
public class QuizEntityTest {
    @Ignore
    @Test
    public void testCommon() throws Exception {
        new BeanTester().testBean(QuizEntity.class);
        new EqualsMethodTester().testEqualsMethod(QuizEntity.class);
        new HashCodeMethodTester().testHashCodeMethod(QuizEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final Integer quizId = 420;
        final Integer issueId = 2;
        final String inviteCode = "code";
        final boolean codeExpired=false;
        final LocalDateTime startedAt=LocalDateTime.of(1,1,1,1,1);
        final LocalDateTime finishedAt=LocalDateTime.of(2,1,1,1,1);

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        Mockito.when(quizModel.getIssue()).thenReturn(new IssueModelEmpty(issueId));
       // Mockito.when(quizModel.getIssue().getId()).thenReturn(issueId);
        Mockito.when(quizModel.getStartedAt()).thenReturn(startedAt);
        Mockito.when(quizModel.getInviteCode()).thenReturn(inviteCode);
        Mockito.when(quizModel.getFinishedAt()).thenReturn(finishedAt);
        Mockito.when(quizModel.isCodeExpired()).thenReturn(codeExpired);

        final QuizEntity quizEntity = new QuizEntity();
        quizEntity.map(quizModel);

        Assert.assertEquals(quizId, quizEntity.getId());
        Assert.assertEquals(issueId, quizEntity.getIssueId());
        Assert.assertEquals(inviteCode, quizEntity.getInviteCode());
        Assert.assertEquals(startedAt, quizEntity.getStartedAt());
        Assert.assertEquals(finishedAt, quizEntity.getFinishedAt());
        Assert.assertEquals(codeExpired, quizEntity.isCodeExpired());
    }

}
