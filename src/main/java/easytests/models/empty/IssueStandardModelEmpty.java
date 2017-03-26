package easytests.models.empty;

import easytests.entities.IssueStandardEntity;
import easytests.models.*;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import java.util.List;

/**
 * @author SingularityA
 */
public class IssueStandardModelEmpty
        extends AbstractModelEmpty
        implements IssueStandardModelInterface {

    public IssueStandardModelEmpty() {
        super();
    }

    public IssueStandardModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Integer getTimeLimit() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setTimeLimit(Integer timeLimit) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Integer getQuestionsNumber() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setQuestionsNumber(Integer questionsNumber) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public List<IssueStandardTopicPriorityModelInterface> getTopicPriorities() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setTopicPriorities(List<IssueStandardTopicPriorityModelInterface> topicPriorities) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public List<IssueStandardQuestionTypeOptionModelInterface> getQuestionTypeOptions() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setQuestionTypeOptions(List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptions) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public SubjectModelInterface getSubject() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setSubject(SubjectModelInterface subject) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(IssueStandardEntity issueStandardEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
