package easytests.models.empty;

import easytests.entities.IssueEntity;
import easytests.models.IssueModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;

/**
 * @author fortyways
 */
public class IssueModelEmpty extends AbstractModelEmpty implements IssueModelInterface {

    public IssueModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setName(String name) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getName() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setAuthorId(Integer authorId) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Integer getAuthorId() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(IssueEntity issueEntity) {
        throw new CallMethodOnEmptyModelException();
    }

}

