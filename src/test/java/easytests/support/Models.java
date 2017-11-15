package easytests.support;

import easytests.core.models.*;
import easytests.core.models.empty.*;

import java.time.LocalDateTime;

/**
 * @author malinink
 */
public abstract class Models {

    public static SubjectModelInterface createSubjectModel(
            Integer id,
            String name,
            String description,
            Integer userId
    ) {
        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.setId(id);
        subjectModel.setName(name);
        subjectModel.setDescription(description);
        subjectModel.setTopics(new ModelsListEmpty());
        subjectModel.setUser(new UserModelEmpty(userId));
        subjectModel.setIssueStandard(new IssueStandardModelEmpty());
        subjectModel.setIssues(new ModelsListEmpty());
        return subjectModel;
    }

    public static IssueStandardModelInterface createIssueStandardModel(
            Integer id,
            Integer timeLimit,
            Integer questionsNumber,
            Integer subjectId
    ) {

        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.setId(id);
        issueStandardModel.setTimeLimit(timeLimit);
        issueStandardModel.setQuestionsNumber(questionsNumber);
        issueStandardModel.setTopicPriorities(new ModelsListEmpty());
        issueStandardModel.setQuestionTypeOptions(new ModelsListEmpty());
        issueStandardModel.setSubject(new SubjectModelEmpty(subjectId));
        return issueStandardModel;
    }

    public static IssueStandardTopicPriorityModelInterface createTopicPriorityModel(
            Integer id,
            Integer topicId,
            Boolean isPreferable,
            Integer issueStandardId
    ) {
        final IssueStandardTopicPriorityModelInterface topicPriorityModel = new IssueStandardTopicPriorityModel();
        topicPriorityModel.setId(id);
        topicPriorityModel.setTopic(new TopicModelEmpty(topicId));
        topicPriorityModel.setIsPreferable(isPreferable);
        topicPriorityModel.setIssueStandard(new IssueStandardModelEmpty(issueStandardId));
        return topicPriorityModel;
    }

    public static IssueStandardQuestionTypeOptionModelInterface createQuestionTypeOptionModel(
            Integer id,
            Integer questionTypeId,
            Integer minQuestions,
            Integer maxQuestions,
            Integer timeLimit,
            Integer issueStandardId
    ) {
        final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                = new IssueStandardQuestionTypeOptionModel();
        questionTypeOptionModel.setId(id);
        questionTypeOptionModel.setQuestionType(new QuestionTypeModelEmpty(questionTypeId));
        questionTypeOptionModel.setMinQuestions(minQuestions);
        questionTypeOptionModel.setMaxQuestions(maxQuestions);
        questionTypeOptionModel.setTimeLimit(timeLimit);
        questionTypeOptionModel.setIssueStandard(new IssueStandardModelEmpty(issueStandardId));
        return questionTypeOptionModel;
    }

    public static TesteeModelInterface createTesteeModel(
            Integer id,
            String firstName,
            String lastName,
            String surname,
            Integer groupNumber,
            Integer quizId
    ) {
        final TesteeModelInterface testeeModel = new TesteeModel();
        testeeModel.setId(id);
        testeeModel.setFirstName(firstName);
        testeeModel.setLastName(lastName);
        testeeModel.setSurname(surname);
        testeeModel.setGroupNumber(groupNumber);
        testeeModel.setQuiz(new QuizModelEmpty(quizId));
        return testeeModel;
    }

    public static QuestionModelInterface createQuestionModel(
            Integer id, 
            String text, 
            Integer questionTypeId,  
            Integer topicId
    ) {
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModel.setId(id);
        questionModel.setText(text);
        questionModel.setQuestionType(new QuestionTypeModelEmpty(questionTypeId));
        questionModel.setTopic(new TopicModelEmpty(topicId));
        questionModel.setAnswers(new ModelsListEmpty());
        return questionModel;
    }

    public static QuizModelInterface createQuizModel(
            Integer id,
            String inviteCode,
            Integer issueId,
            LocalDateTime startedAt,
            LocalDateTime finishedAt,
            boolean codeExpired
    ){
        final QuizModelInterface quizModel = new QuizModel();
        quizModel.setId(id);
        quizModel.setInviteCode(inviteCode);
        quizModel.setStartedAt(startedAt);
        quizModel.setFinishedAt(finishedAt);
        quizModel.setCodeExpired(codeExpired);
        quizModel.setIssue(new IssueModelEmpty(issueId));
        quizModel.setPoints(new ModelsListEmpty());
        quizModel.setTestee(new TesteeModelEmpty());
        return quizModel;
    }

    public static IssueModelInterface createIssueModel(
           Integer id,
           String name,
           Integer subjectId
    ) {
        final IssueModelInterface issueModel = new IssueModel();
        issueModel.setId(id);
        issueModel.setName(name);
        issueModel.setSubject(new SubjectModelEmpty(subjectId));
        issueModel.setQuizzes(new ModelsListEmpty());
        return issueModel;
    }

    public static PointModelInterface createPointModel(
            Integer id,
            Integer questionId,
            Integer quizId
    ) {
        final PointModelInterface pointModel = new PointModel();
        pointModel.setId(id);
        pointModel.setQuestion(new QuestionModelEmpty(questionId));
        pointModel.setQuiz(new QuizModelEmpty(quizId));
        pointModel.setSolutions(new ModelsListEmpty());
        return pointModel;
    }

    public static AnswerModelInterface createAnswerModel(
            Integer id,
            String txt,
            Integer serialNumber,
            Integer questionId,
            Boolean right
    ) {
        final AnswerModelInterface answerModel = new AnswerModel();
        answerModel.setId(id);
        answerModel.setTxt(txt);
        answerModel.setSerialNumber(serialNumber);
        answerModel.setRight(right);
        answerModel.setQuestion(new QuestionModelEmpty(questionId));
        return answerModel;
    }
}
