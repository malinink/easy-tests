package easytests.models.empty;

import easytests.entities.IssueStandardTopicPriorityEntity;
import easytests.models.IssueStandardModelInterface;
import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;

/**
 * @author SingularityA
 */
public class IssueStandardTopicPriorityModelEmpty
        extends AbstractModelEmpty
        implements IssueStandardTopicPriorityModelInterface {

    public IssueStandardTopicPriorityModelEmpty() {
        super();
    }

    public IssueStandardTopicPriorityModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Integer getTopicId() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setTopicId(Integer topicId) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Boolean getIsPreferable() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setIsPreferable(Boolean isPreferable) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public IssueStandardModelInterface getIssueStandard() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setIssueStandard(IssueStandardModelInterface issueStandard) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(IssueStandardTopicPriorityEntity topicPriorityEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
