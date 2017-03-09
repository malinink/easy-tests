package easytests.models.empty;

import easytests.entities.SubjectEntity;
import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModelInterface;
import easytests.models.TopicModelInterface;
import easytests.models.UserModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;

import java.util.List;

/**
 * @author vkpankov
 */
public class SubjectModelEmpty extends AbstractModelEmpty implements SubjectModelInterface {

    public SubjectModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        this.throwException();
    }

    @Override
    public String getName() {
        this.throwException();
        return null;
    }

    @Override
    public void setName(String name) {
        this.throwException();
    }

    @Override
    public List<TopicModelInterface> getTopics() {
        this.throwException();
        return null;
    }

    @Override
    public void setTopics(List<TopicModelInterface> topics) {
        this.throwException();
    }

    @Override
    public IssueStandardModelInterface getIssueStandard() {
        this.throwException();
        return null;
    }

    @Override
    public void setIssueStandard(IssueStandardModelInterface issueStandard) {
        this.throwException();
    }

    @Override
    public UserModelInterface getUser() {
        this.throwException();
        return null;
    }

    @Override
    public void setUser(UserModelInterface user) {
        this.throwException();
    }

    @Override
    public void map(SubjectEntity subjectEntity) {
        this.throwException();
    }

    private void throwException() throws CallMethodOnEmptyModelException {
        throw new CallMethodOnEmptyModelException();
    }

}
