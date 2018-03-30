package easytests.support;

import easytests.core.entities.*;
import org.mockito.Mockito;

import java.time.LocalDateTime;


/**
 * @author malinink
 */
public abstract class Entities {
    public static IssueStandardEntity createIssueStandardEntityMock(
            Integer id,
            Integer timeLimit,
            Integer questionsNumber,
            Integer subjectId
    ) {

        final IssueStandardEntity issueStandardEntity = Mockito.mock(IssueStandardEntity.class);
        Mockito.when(issueStandardEntity.getId()).thenReturn(id);
        Mockito.when(issueStandardEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(issueStandardEntity.getQuestionsNumber()).thenReturn(questionsNumber);
        Mockito.when(issueStandardEntity.getSubjectId()).thenReturn(subjectId);
        return issueStandardEntity;
    }

    public static IssueStandardTopicPriorityEntity createTopicPriorityEntityMock(
            Integer id,
            Integer topicId,
            Boolean isPreferable,
            Integer issueStandardId
    ) {
        final IssueStandardTopicPriorityEntity topicPriorityEntity
                = Mockito.mock(IssueStandardTopicPriorityEntity.class);
        Mockito.when(topicPriorityEntity.getId()).thenReturn(id);
        Mockito.when(topicPriorityEntity.getTopicId()).thenReturn(topicId);
        Mockito.when(topicPriorityEntity.getIsPreferable()).thenReturn(isPreferable);
        Mockito.when(topicPriorityEntity.getIssueStandardId()).thenReturn(issueStandardId);
        return topicPriorityEntity;
    }

    public static IssueStandardQuestionTypeOptionEntity createQuestionTypeOptionEntityMock(
            Integer id,
            Integer questionTypeId,
            Integer minQuestions,
            Integer maxQuestions,
            Integer timeLimit,
            Integer issueStandardId
    ) {
        final IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity
                = Mockito.mock(IssueStandardQuestionTypeOptionEntity.class);
        Mockito.when(questionTypeOptionEntity.getId()).thenReturn(id);
        Mockito.when(questionTypeOptionEntity.getQuestionTypeId()).thenReturn(questionTypeId);
        Mockito.when(questionTypeOptionEntity.getMinQuestions()).thenReturn(minQuestions);
        Mockito.when(questionTypeOptionEntity.getMaxQuestions()).thenReturn(maxQuestions);
        Mockito.when(questionTypeOptionEntity.getTimeLimit()).thenReturn(timeLimit);
        Mockito.when(questionTypeOptionEntity.getIssueStandardId()).thenReturn(issueStandardId);
        return questionTypeOptionEntity;
    }

    public static TesteeEntity createTesteeEntityMock(
            Integer id,
            String firstName,
            String lastName,
            String surname,
            Integer groupNumber,
            Integer quizId
    ) {
        final TesteeEntity testeeEntity = Mockito.mock(TesteeEntity.class);
        Mockito.when(testeeEntity.getId()).thenReturn(id);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surname);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn(groupNumber);
        Mockito.when(testeeEntity.getQuizId()).thenReturn(quizId);
        return testeeEntity;
    }

    public static TopicEntity createTopicEntityMock(
            Integer id,
            Integer subjectId,
            String name
    ) {

        final TopicEntity topicEntity = Mockito.mock(TopicEntity.class);

        Mockito.when(topicEntity.getId()).thenReturn(id);
        Mockito.when(topicEntity.getSubjectId()).thenReturn(subjectId);
        Mockito.when(topicEntity.getName()).thenReturn(name);
        return topicEntity;

    }

    public static QuestionEntity createQuestionEntityMock(
            Integer id, 
            String text, 
            Integer questionTypeId, 
            Integer topicId
    ) {
        final QuestionEntity questionEntity = Mockito.mock(QuestionEntity.class);
        Mockito.when(questionEntity.getId()).thenReturn(id);
        Mockito.when(questionEntity.getText()).thenReturn(text);
        Mockito.when(questionEntity.getQuestionTypeId()).thenReturn(questionTypeId);
        Mockito.when(questionEntity.getTopicId()).thenReturn(topicId);
        return questionEntity;
    }

    public static IssueEntity createIssueEntityMock(
            Integer id,
            String name,
            Integer subjectId
    ) {
        final IssueEntity issueEntity = Mockito.mock(IssueEntity.class);
        Mockito.when(issueEntity.getId()).thenReturn(id);
        Mockito.when(issueEntity.getName()).thenReturn(name);
        Mockito.when(issueEntity.getSubjectId()).thenReturn(subjectId);
        return issueEntity;
    }

    public static PointEntity createPointEntityMock(
            Integer id,
            Integer questionId,
            Integer quizId
    ) {
        final PointEntity pointEntity = Mockito.mock(PointEntity.class);
        Mockito.when(pointEntity.getId()).thenReturn(id);
        Mockito.when(pointEntity.getQuestionId()).thenReturn(questionId);
        Mockito.when(pointEntity.getQuizId()).thenReturn(quizId);
        return pointEntity;
    }

    public static QuizEntity createQuizEntityMock(
            Integer id,
            Integer issueId,
            String inviteCode,
            LocalDateTime startedAt,
            LocalDateTime finishedAt,
            boolean codeExpired
    ) {
        final QuizEntity quizEntity = Mockito.mock(QuizEntity.class);
        Mockito.when(quizEntity.getId()).thenReturn(id);
        Mockito.when(quizEntity.getIssueId()).thenReturn(issueId);
        Mockito.when(quizEntity.getInviteCode()).thenReturn(inviteCode);
        Mockito.when(quizEntity.getStartedAt()).thenReturn(startedAt);
        Mockito.when(quizEntity.getFinishedAt()).thenReturn(finishedAt);
        Mockito.when(quizEntity.getCodeExpired()).thenReturn(codeExpired);
        return quizEntity;
    }


}

