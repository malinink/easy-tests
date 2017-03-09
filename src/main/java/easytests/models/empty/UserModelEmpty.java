package easytests.models.empty;

import easytests.entities.UserEntity;
import easytests.models.SubjectModelInterface;
import easytests.models.UserModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import java.util.List;


/**
 * @author malinink
 */
public class UserModelEmpty extends AbstractModelEmpty implements UserModelInterface {
    public UserModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getFirstName() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setFirstName(String firstName) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getLastName() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setLastName(String lastName) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getSurname() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setSurname(String surname) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public List<SubjectModelInterface> getSubjects() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setSubjects(List<SubjectModelInterface> subjects) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(UserEntity userEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
