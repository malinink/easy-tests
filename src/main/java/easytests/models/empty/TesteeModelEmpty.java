package easytests.models.empty;

import easytests.entities.TesteeEntity;
import easytests.models.TesteeModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;

/**
 * @author DoZor-80
 */
public class TesteeModelEmpty extends AbstractModelEmpty implements TesteeModelInterface {
    public TesteeModelEmpty() {
        super();
    }

    public TesteeModelEmpty(Integer id) {
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
    public Integer getGroupNumber() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setGroupNumber(Integer groupNumber) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(TesteeEntity testeeEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
