package easytests.core.models.empty;

import easytests.core.entities.IssueStandardQuestionTypeOptionEntity;
import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.core.models.QuestionTypeModel;
import easytests.core.models.QuestionTypeModelInterface;
import easytests.core.models.exceptions.CallMethodOnEmptyModelException;

/**
 * @author SingularityA
 */
public class IssueStandardQuestionTypeOptionModelEmpty
        extends AbstractModelEmpty
        implements IssueStandardQuestionTypeOptionModelInterface {

    public IssueStandardQuestionTypeOptionModelEmpty() {
        super();
    }

    public IssueStandardQuestionTypeOptionModelEmpty(Integer id) {
        super(id);
    }

    @Override
    public void setId(Integer id) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public QuestionTypeModel getQuestionType() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setQuestionType(QuestionTypeModelInterface questionType) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Integer getMinQuestions() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setMinQuestions(Integer minQuestions) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public Integer getMaxQuestions() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setMaxQuestions(Integer maxQuestions) {
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
    public IssueStandardModelInterface getIssueStandard() {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void setIssueStandard(IssueStandardModelInterface issueStandard) {
        throw new CallMethodOnEmptyModelException();
    }

    @Override
    public void map(IssueStandardQuestionTypeOptionEntity questionTypeOptionEntity) {
        throw new CallMethodOnEmptyModelException();
    }
}
