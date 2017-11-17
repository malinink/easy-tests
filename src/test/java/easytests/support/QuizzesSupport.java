package easytests.support;

import easytests.core.entities.IssueEntity;
import easytests.core.entities.QuizEntity;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.QuizModel;
import easytests.core.models.QuizModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.empty.IssueModelEmpty;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.TesteeModelEmpty;
import org.junit.Assert;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;

public class QuizzesSupport {
    protected static Object additional[][] = new Object[][]{
            {
                420,
                2,
                "code",
                false,
                LocalDateTime.of(1,1,1,1,1),
                LocalDateTime.of(2,2,2,2,2)
            }
    };

    public QuizEntity getEntityAdditionalMock(Integer index){
        return this.getEntityMock(additional[index]);
    }

    private QuizEntity getEntityMock(Object[] data){
        return this.getEntityMock(
                (Integer) data[0],
                (Integer) data[1],
                (String) data[2],
                (Boolean) data[3],
                (LocalDateTime) data[4],
                (LocalDateTime) data[5]
        );
    }

    private QuizEntity getEntityMock(
            Integer id,
            Integer issueId,
            String inviteCode,
            Boolean codeExpired,
            LocalDateTime startedAt,
            LocalDateTime finishedAt
    ){
        final QuizEntity quizEntity = Mockito.mock(QuizEntity.class);
        Mockito.when(quizEntity.getId()).thenReturn(id);
        Mockito.when(quizEntity.getIssueId()).thenReturn(issueId);
        Mockito.when(quizEntity.getInviteCode()).thenReturn(inviteCode);
        Mockito.when(quizEntity.getCodeExpired()).thenReturn(codeExpired);
        Mockito.when(quizEntity.getStartedAt()).thenReturn(startedAt);
        Mockito.when(quizEntity.getFinishedAt()).thenReturn(finishedAt);
        return quizEntity;
    }

    public QuizModelInterface getModelAdditionalMock(Integer index){
                return this.getModelMock(additional[index]);
    }

    private QuizModelInterface getModelMock(Object[] data){
        return this.getModelMock(
                (Integer) data[0],
                (Integer) data[1],
                (String) data[2],
                (Boolean) data[3],
                (LocalDateTime) data[4],
                (LocalDateTime) data[5]
        );
    }

    private QuizModelInterface getModelMock(
            Integer id,
            Integer issueId,
            String inviteCode,
            Boolean codeExpired,
            LocalDateTime startedAt,
            LocalDateTime finishedAt
    ){
        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(id);
        Mockito.when(quizModel.getInviteCode()).thenReturn(inviteCode);
        Mockito.when(quizModel.getCodeExpired()).thenReturn(codeExpired);
        Mockito.when(quizModel.getStartedAt()).thenReturn(startedAt);
        Mockito.when(quizModel.getFinishedAt()).thenReturn(finishedAt);
        Mockito.when(quizModel.getIssue()).thenReturn(new IssueModelEmpty(issueId));
        //Mockito.when(quizModel.getPoints()).thenReturn(new ModelsListEmpty());
        //Mockito.when(quizModel.getTestee()).thenReturn(new TesteeModelEmpty(id));
        return quizModel;
    }

    public void assertEquals(QuizModelInterface first, QuizEntity second){
        Assert.assertEquals(first.getId(), second.getId());
        Assert.assertEquals(first.getIssue().getId(), second.getIssueId());
        Assert.assertEquals(first.getInviteCode(), second.getInviteCode());
        Assert.assertEquals(first.getCodeExpired(), second.getCodeExpired());
        Assert.assertEquals(first.getStartedAt(), second.getStartedAt());
        Assert.assertEquals(first.getFinishedAt(), second.getFinishedAt());
    }
}
