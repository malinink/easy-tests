package easytests.models.empty;

import easytests.entities.SubjectEntity;
import easytests.models.*;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import java.util.List;


/**
 * @author vkpankov
 */
public class SubjectModelEmpty extends AbstractModelEmpty implements SubjectModelInterface {
    public SubjectModelEmpty() {
        super();
    }

    public SubjectModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getName() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setName(String name) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getDescription() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setDescription(String name) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public List<TopicModelInterface> getTopics() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setTopics(List<TopicModelInterface> topics) {
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
    public UserModelInterface getUser() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setUser(UserModelInterface user) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(SubjectEntity subjectEntity) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public List<IssueModelInterface> getIssues() {
        throw new CallMethodOnEmptyModelException(); }

    @Override
    public void setIssues(List<IssueModelInterface> issues) {
        throw new CallMethodOnEmptyModelException(); }
}
