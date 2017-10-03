package easytests.core.models.empty;

import easytests.core.entities.UserEntity;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.UserModelInterface;
import easytests.core.models.exceptions.CallMethodOnEmptyModelException;
import java.util.List;


/**
 * @author malinink
 */
public class UserModelEmpty extends AbstractModelEmpty implements UserModelInterface {
    public UserModelEmpty() {
        super();
    }

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
    public String getEmail() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setEmail(String email) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public String getPassword() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setPassword(String password) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Boolean getIsAdmin() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setIsAdmin(Boolean isAdmin) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Integer getState() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setState(Integer state) {
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
